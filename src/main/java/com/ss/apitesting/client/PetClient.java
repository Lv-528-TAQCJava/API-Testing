package com.ss.apitesting.client;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {
    public PetClient(ContentType contentType) {
        super(contentType, "pet");
    }
    public PetClient(String contentType) {
        super(contentType, "pet");
    }

    //public Response getById(String id) - implemented in BaseClient

    //public Response deleteById(String id) - implemented in BaseClient
}
