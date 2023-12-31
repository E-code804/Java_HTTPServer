package com.ecode804.http;

public class HttpRequest extends HttpMessage {

    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {

    }

    public HttpMethod getMethod() {
        return method;
    }

    // Parsing of the method, making sure throw exception.
    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
    }
}
