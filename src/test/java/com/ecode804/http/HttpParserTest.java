package com.ecode804.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    /*
    @Test
    void parseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(generateValidGETTestCase());
        } catch (HttpParsingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(), HttpMethod.GET);
    }

    @Test
    void parseHttpRequestBadMethodName() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseMethodName1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseHttpRequestBadMethodNameTooLong() {
        // For too long a method name
        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseMethodNameTooLong());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    @Test
    void parseHttpRequestBadMethodInvalidNumItems() {
        // For too long a method name
        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseRequestLineInvalidNumItems1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }

    @Test
    void parseHttpRequestBadMethodEmptyRequestLine() {
        // For too long a method name
        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseEmptyRequestLine());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    */
    @Test
    void parseHttpRequestBadMethodNoLF() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseRequestNoLF());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }

    private InputStream generateValidGETTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"118\", \"Brave\";v=\"118\", \"Not=A?Brand\";v=\"99\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"macOS\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8\r\n" +
                "Sec-GPC: 1\r\n" +
                "Accept-Language: en-US,en\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName1() {
        String rawData = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodNameTooLong() {
        String rawData = "GETTTT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseRequestLineInvalidNumItems1() {
        String rawData = "GET / SSSSS / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseEmptyRequestLine() {
        String rawData = "\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseRequestNoLF() {
        String rawData = "GET / HTTP/1.1\r" + // No LF -> \n.
                "Hos7: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
}