package com.ecode804.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//
public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            // TODO we would read


            // TODO we would do the writing
            // Defining page to browser
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my Java HTTP Server!!</h1></body></html>";
            // HTTP Protocol
            final String CRLF = "\n\r"; // 13, 10
            String response = "HTTP/1.1 200 OK" + CRLF + // // Status line of response: HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE.
                    "Content-Length: " + html.getBytes().length + CRLF + // Also sender header w/ size of message we're sending, they end w/ CRLF
                    CRLF + // Done w/ header
                    html + // send html
                    CRLF + CRLF;
            // Write to output stream
            outputStream.write(response.getBytes());



            LOGGER.info("Connection processing finished.");
        } catch (IOException e) {
            LOGGER.error("Problem w/ communication", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
