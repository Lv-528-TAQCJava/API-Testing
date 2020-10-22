package com.ss.apitesting.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {
    public final static String BASE_URL = "https://petstore.swagger.io/v2";
    public final ContentType contentType;
    public final String entity; //the first part of URL after BASE_URL (without slash)

    public BaseClient(ContentType contentType, String entity) {
        this.contentType = contentType;
        this.entity = entity;
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
    }

    /**
     * Wrapper for given() with .baseUri, .contentType and so on
     * @return RequestSpecification which allows you to use .pathParams, .get and other
     */
    protected RequestSpecification prepareRequest() {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .pathParam("entity", entity) //just write /{entity}/ instead of /pet/, /store/ etc. in your requests
                .urlEncodingEnabled(false); //allows passing special characters (slash, in particular) as a parameter
    }


}
