package com.ss.apitesting.client;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {
    public PetClient(ContentType contentType) {
        super(contentType, "pet");
    }
    public PetClient(String contentType) {
        super(contentType, "pet");
    }

    /**
     * Updates name and status of pet, selected by petId
     * @param petId - id of pet to update
     * @param newName - "optional" parameter. Put null to avoid it
     * @param newStatus - "optional" parameter. Put null to avoid it
     * @return result of POST request
     */
    public Response updateById(String petId, String newName, String newStatus) {
        Map<String, String> formParams = new HashMap<String, String>();
        if(newName != null)
            formParams.put("name", newName);
        if(newStatus != null)
            formParams.put("status", newStatus);

        return given()
                .baseUri(BASE_URL)
                .formParams(formParams)
                .pathParam("petId", petId)
                .post("/pet/{petId}");
    }

    /**
     * Tries to create new pet
     * @param data in JSON of in XML format
     * @return Result of creating
     */
    public Response createPet(String data) {
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .body(data)
                .post("/pet");
    }
}
