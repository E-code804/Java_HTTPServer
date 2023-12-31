package com.ecode804.http;

public enum HttpStatusCode {
    // Client Errors - Following 7230 documentation.
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request."),
    CLIENT_ERROR_401_BAD_METHOD_NOT_ALLOWED(401, "Not allowed."),
    CLIENT_ERROR_414_URI_TOO_LONG(414, "URI too long."),

    // Server Errors
    CLIENT_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error."),
    CLIENT_ERROR_501_NOT_IMPLEMENTED(501, "Method Not Implemented.");


    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
