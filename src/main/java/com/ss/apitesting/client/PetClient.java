package com.ss.apitesting.client;
import com.ss.apitesting.models.pet.PetModel;
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

    public PetClient() {
        super(ContentType.JSON, "pet");
    }

    public Response getById(String id) {
        return prepareRequest()
                .pathParam("id", id)
                .get("/{entity}/{id}");
    }

    public Response deleteById(String id) {
        return prepareRequest()
                .pathParam("id", id)
                .delete("/{entity}/{id}");
    }

    /**
     * TODO repalece all parameters with PetModel obj
     * Updates name and status of pet, selected by petId
     * @param petId - id of pet to update
     * @param newName - "optional" parameter. Put null to avoid it
     * @param newStatus - "optional" parameter. Put null to avoid it
     * @return result of POST request
     */
    public Response updateById(String petId, String newName, String newStatus) {
        Map<String, String> formParams = new HashMap<String, String>();
        // TODO find a way to put not null values into map
        if(newName != null)
            formParams.put("name", newName);
        if(newStatus != null)
            formParams.put("status", newStatus);

        return given()
                .baseUri(BASE_URL)
                // PAY ATTENTION!
                // Also note that formParam() adds a Content-Type header with the value “application/x-www-form-urlencoded“.
                // ContentType.URLENC == URLENC(new String[]{"application/x-www-form-urlencoded"}),
                .formParams(formParams)
                .pathParam("petId", petId)
                .post("/pet/{petId}");
    }

    /**
     * TODO replace data with PetModel
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
    public Response createNewPet(PetModel petModel) {
        return prepareRequest()
                .body(petModel)
                .post("/{entity}");
    }
    public Response getPetByStatus(String status){
        return  prepareRequest()
                .queryParam("status", status)
                .get("/{entity}/findByStatus");
    }
}
