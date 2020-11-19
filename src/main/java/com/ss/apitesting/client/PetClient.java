package com.ss.apitesting.client;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.StringPetModel;
import com.ss.apitesting.models.pet.UpdatePetDto;
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
        log.debug("GET pet by id: {}", id);
        return prepareRequest()
                .pathParam("id", id)
                .get("/{entity}/{id}");
    }

    public Response deleteById(String id) {
        log.debug("DELETE pet by id: {}", id);
        return prepareRequest()
                .pathParam("id", id)
                .delete("/{entity}/{id}");
    }

    /**
     * Updates name and status of pet, selected by pet's Id
     * @param params a values of pet to update
     * @return result of POST request
     */
    public Response updatePet(UpdatePetDto params) {
        log.debug("POST(update) pet with new values: {}", params);
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", params.name);
        formParams.put(STATUS_PARAM, params.status);

        return prepareRequest()
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .pathParam("petId", params.id)
                .post("/{entity}/{petId}");
    }

    /**
     * Upload image to pet, selected by pet's Id
     * @param pet a pet to upload image
     * @param file path to file
     * @param memeType type of the file
     * @return result of POST request
     */
    public Response uploadImage(PetModel pet, File file, String memeType) {
        return given().log().all()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType("multipart/form-data")
                .formParam("additionalMetadata","data")
                .multiPart("file", file, memeType)
                .pathParam("petId", pet.id)
                .post("/pet/{petId}/uploadImage");
    }

    /**
     * Upload image to pet, selected by pet's Id(as String)
     * @param pet a pet to upload image
     * @param file path to file
     * @param memeType type of the file
     * @return result of POST request
     */
    public Response uploadImage(StringPetModel pet, File file, String memeType) {
        return given().log().all()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType("multipart/form-data")
                .formParam("additionalMetadata","data")
                .multiPart("file", file, memeType)
                .pathParam("petId", pet.id)
                .post("/pet/{petId}/uploadImage");
    }

    /**
     * Tries to create new pet
     * @param pet to create
     * @return Result of creating
     */
    public Response createPet(PetModel pet) {
        log.debug("POST(create) pet: {}", pet);
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
