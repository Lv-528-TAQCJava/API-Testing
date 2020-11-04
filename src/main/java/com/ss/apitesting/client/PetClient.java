package com.ss.apitesting.client;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.StringPetModel;
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
     * Updates name and status of pet, selected by pet's Id
     * @param pet a pet to update
     * @return result of POST request
     */
    public Response updatePet(PetModel pet) {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("name", pet.name);
        formParams.put("status", pet.status);

        return prepareRequest()
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .pathParam("petId", pet.id)
                .post("/{entity}/{petId}");
    }

    /**
     * Updates name and status of pet, selected by pet's Id(as string)
     * @param pet a pet to update
     * @return result of POST request
     */
    public Response updatePet(StringPetModel pet) {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("name", pet.name);
        formParams.put("status", pet.status);

        return prepareRequest()
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .pathParam("petId", pet.id)
                .post("/{entity}/{petId}");
    }

    /**
     * Tries to create new pet
     * @param pet to create
     * @return Result of creating
     */
    public Response createPet(PetModel pet) {
        return prepareRequest()
                .body(pet)
                .post("/{entity}");
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
