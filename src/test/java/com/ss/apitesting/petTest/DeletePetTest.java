package com.ss.apitesting.petTest;

import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation with pets tests")
@Feature("Delete pet test suite")
public class DeletePetTest {
    protected PetClient petClient;
    private PetModel petModel;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
        petModel = PetBuilder.petWith()
                .id (17768)
                .category (null)
                .name ("Catto")
                .photoUrls(null)
                .status("available")
                .tags(null)
                .build();
        petClient.createNewPet(petModel);
        Response response = petClient.getById(String.valueOf(petModel.petId));
        Assert.assertEquals(response.getStatusCode(),HTTP_OK, "Error - pet has not been created");
    }

    protected int findFreeID() {
        int id = 0;
        Response getPet;
        do {
            id++;
            getPet = petClient.getById("" + id);
        } while(getPet.statusCode() != 404);
        return id;
    }

    @Test
    public void deletePetTest(){
        Response response = petClient.deleteById(String.valueOf(petModel.petId));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void deleteNotExistingPetTest(){
        Response response = petClient.deleteById(String.valueOf(findFreeID()));
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void deletePetWithInvalidIdTest(){
        Response response = petClient.deleteById("InvalidId");
        Assert.assertEquals(response.getStatusCode(), 400);
    }

}
