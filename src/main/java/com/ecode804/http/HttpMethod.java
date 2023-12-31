package com.ecode804.http;

public enum HttpMethod {
    GET, HEAD;
    public static final int MAX_LENGTH;

    static {
        int tempMaxLength = -1;
        for(HttpMethod method: values()) {
            int methodNameLength = method.name().length();
            if (methodNameLength > tempMaxLength) {
                tempMaxLength = methodNameLength;
            }
        }
        MAX_LENGTH = tempMaxLength;
    }
}
