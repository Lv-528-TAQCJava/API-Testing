package com.ss.apitesting.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {
    public final static String BASE_URL = "https://petstore.swagger.io/v2";
    public final ContentType contentType;
    public final String entity; //the first part of URL after BASE_URL (without slash)
    protected Logger log;

    public BaseClient(ContentType contentType, String entity) {
        this.contentType = contentType;
        this.entity = entity;
        String contentTypeString;
        switch (contentType) {
            case XML:
                contentTypeString = "XML";
                break;
            case JSON:
                contentTypeString = "JSON";
                break;
            default:
                contentTypeString = contentType.toString();
                break;
        }
        log = LoggerFactory.getLogger(entity + "Client" + contentTypeString);
    }

    public BaseClient(String contentType, String entity) {
        switch (contentType.toUpperCase()) {
            case "XML":
                this.contentType = ContentType.XML;
                break;
            case "JSON":
            default:
                this.contentType = ContentType.JSON;
                break;
        }
        this.entity = entity;
        log = LoggerFactory.getLogger(entity + "Client" + contentType.toUpperCase());
    }

    /**
     * Wrapper for given() with .baseUri, .contentType and so on
     * @return RequestSpecification which allows you to use .pathParams, .get and other
     */
    protected RequestSpecification prepareRequest() {
        log.debug("Preparing request...");
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .accept(contentType) //By default it doesn't accept response in the same content type
                .pathParam("entity", entity) //just write /{entity}/ instead of /pet/, /store/ etc. in your requests
                .urlEncodingEnabled(false); //allows passing special characters (slash, in particular) as a parameter
    }
}
