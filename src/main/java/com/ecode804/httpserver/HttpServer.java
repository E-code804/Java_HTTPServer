package com.ecode804.httpserver;

/*
 * Driver Class for the HTTPServer
 */

import com.ecode804.httpserver.config.Configuration;
import com.ecode804.httpserver.config.ConfigurationManager;
import com.ecode804.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    // Modifying to use the login, can now replace System.out's w/ this. Do same in listener thread class.
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        int port = conf.getPort();
        String webroot = conf.getWebroot();

        LOGGER.info("Using port: " + port);
        LOGGER.info("Using webroot: " + webroot);

        // Create a thread
        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(port, webroot);
            serverListenerThread.start(); // Working on a different thread.
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Handle later
        }
        // Needs to understand http protocol.
    }
}
