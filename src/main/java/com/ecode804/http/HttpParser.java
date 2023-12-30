package com.ecode804.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    // Parse http req
    public void parseHttpRequest(InputStream inputStream) {
        // Will eed to create a parse for each thread, allow for more configs alter.

    }
}
