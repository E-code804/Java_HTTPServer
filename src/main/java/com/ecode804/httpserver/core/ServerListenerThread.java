package com.ecode804.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Removing the connection from main thread in driver class,
 * allow for multiple connections.
 */
public class ServerListenerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        // Moving server socket into thread
        this.serverSocket = new ServerSocket(this.port); // Pass in the port, generate IOException.
    }

    @Override
    public void run() {
        // Needs to handle tcp connections from browser.
        try {
            // Having many connections to our server.
            // server socket has a queue -> they get processed one by one - could have delays.
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // Accepts connections that arise, code stops and wait for connection.

                LOGGER.info(" * Connection accepted: " + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
            }
        } catch (IOException e) {
            LOGGER.error("Problem w/ setting socket", e);
        } finally { // Making sure to close server socket.
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
