package com.ecode804.httpserver;

/*
 * Driver Class for the HTTPServer
 */

import com.ecode804.httpserver.config.Configuration;
import com.ecode804.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getPort());
        System.out.println("Using webroot: " + conf.getWebroot());

        // Needs to handle tcp connections from browser.
        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort()); // Pass in the port
            Socket socket = serverSocket.accept(); // Accepts connections that arise, code stops and wait for connection.

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO we would read

            // TODO we would do the writing
            // Defining page to browser
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my Java HTTP Server!</h1></body></html>";
            // HTTP Protocol
            final String CRLF = "\n\r"; // 13, 10
            String response = "HTTP/1.1 200 OK" + CRLF + // // Status line of response: HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE.
                    "Content-Length: " + html.getBytes().length + CRLF + // Also sender header w/ size of message we're sending, they end w/ CRLF
                    CRLF + // Done w/ header
                    html + // send html
                    CRLF + CRLF;
            // Write to output stream
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Needs to understand http protocol.
    }
}
