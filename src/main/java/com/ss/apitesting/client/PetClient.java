package com.ss.apitesting.client;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {
    public PetClient(ContentType contentType) {
        super(contentType);
    }
    public PetClient(String contentType) {
        super(contentType);
    }

    public Response getById(String petId) {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .pathParam("petId", petId)
                .get("/pet/{petId}");
    }
}
