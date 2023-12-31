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
    public HttpRequest parseHttpRequest(InputStream inputStream) {
        // Will eed to create a parse for each thread, allow for more configs alter.
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        parseRequestLine(reader, request);
        parseReaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
        // Create a loop to look for CRLF chars
        int _byte;

        while( (_byte = reader.read()) >= 0 ) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    return;
                }
            }
        }
    }

    private void parseReaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }


}
