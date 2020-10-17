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

    public Response getById0(String petId) {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .pathParam("petId", petId)
                .get("/pet/{petId}");
    }
}
