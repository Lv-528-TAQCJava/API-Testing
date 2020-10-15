package com.ss.apitesting.client;

import io.restassured.http.ContentType;

public abstract class BaseClient {
    public final static String BASE_URL = "https://petstore.swagger.io/v2";
    public final ContentType contentType;

    public BaseClient(ContentType contentType) {
        this.contentType = contentType;
    }
    public BaseClient(String contentType) {
        switch (contentType) {
            case "XML":
            case "Xml":
            case "xml":
                this.contentType = ContentType.XML;
                break;
            case "JSON":
            case "Json":
            case "json":
            default:
                this.contentType = ContentType.JSON;
                break;
        }
    }
}
