package com.ss.apitesting.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
        this.entity = entity;
    }

    /**
     * Every entity must have GET by ID method, so it's in the base class
     */
    public Response getById(String id) {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .pathParams("entity", entity, "id", id)
                .get("/{entity}/{id}");
    }

    /**
     * Every entity must have DELETE by ID method, so it's in the base class
     */
    public Response deleteById(String id) {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .pathParams("entity", entity, "id", id)
                .delete("/{entity}/{id}");
    }
}
