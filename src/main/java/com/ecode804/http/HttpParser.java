package com.ecode804.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20; // 32, following hexadecimal
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    // Parse http req
    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        // Will eed to create a parse for each thread, allow for more configs alter.
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseReaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
        // Create a loop to look for hex chars, return once we find it.
        int _byte;
        StringBuilder processingDataBuffer = new StringBuilder();
        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        while( (_byte = reader.read()) >= 0 ) {
            // System.out.print((char) _byte);
            if (_byte == CR) {
                _byte = reader.read();
                System.out.print((char) _byte);
                if (_byte == LF) {
                    LOGGER.info("Request line VERSION to Process: {}", processingDataBuffer.toString());
                    if (!methodParsed || !requestTargetParsed) {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    return;
                }
            }
            // Looking for SP
            if (_byte == SP) {
                // System.out.print((char) _byte + "SP");
                // TODO Process previous data
                if (!methodParsed) {
                    LOGGER.info("Request line METHOD to Process: {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed) {
                    LOGGER.info("Request line REQ Target to Process: {}", processingDataBuffer.toString());
                    requestTargetParsed = true;
                } else {
                    // Detect an SP and already parsed method/request, error.
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
            } else {
                // Store char to process once SP appears
                processingDataBuffer.append((char) _byte);
                // Accounting for a really long method name
                if (!methodParsed) {
                    if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH) {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }

    private void parseReaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }


}
