package com.ss.apitesting.client;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.StringPetModel;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {

    private static final String STATUS_PARAM = "status";
    private static final String PET_ENTITY = "pet";

    public PetClient(ContentType contentType) {
        super(contentType, PET_ENTITY);
    }

    public PetClient(String contentType) {
        super(contentType, PET_ENTITY);
    }

    public PetClient() {
        super(ContentType.JSON, PET_ENTITY);
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
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", pet.name);
        formParams.put(STATUS_PARAM, pet.status);

        return prepareRequest()
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .pathParam("petId", pet.id)
                .post("/{entity}/{petId}");
    }

    public Response uploadImage(PetModel pet) {
        return given().log().all()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType("multipart/form-data")
                .formParam("additionalMetadata","data")
                .multiPart("file", new File("D:\\icon.png"),"image/png")
                .pathParam("petId", pet.id)
                .post("/pet/{petId}/uploadImage");
    }

    /**
     * Updates name and status of pet, selected by pet's Id(as string)
     * @param pet a pet to update
     * @return result of POST request
     */
    public Response updatePet(StringPetModel pet) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", pet.name);
        formParams.put(STATUS_PARAM, pet.status);

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

    public Response getPetByStatus(String status){
        return  prepareRequest()
                .queryParam(STATUS_PARAM, status)
                .get("/{entity}/findByStatus");
    }
}
