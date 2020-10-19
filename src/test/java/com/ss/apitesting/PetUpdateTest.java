package com.ss.apitesting;

import com.ss.apitesting.Client.PetClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class PetUpdateTest {
    protected int suitableId;
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        suitableId = 0;
        petClient = new PetClient(ContentType.JSON);
    }

    @BeforeMethod
    public void createTemporaryPet() {
        Response getPet;
        do {
            suitableId++;
            getPet = petClient.getById("" + suitableId);
        } while(getPet.statusCode() != 404);

        petClient.createPet("" + suitableId, "doggo", "available");
    }

    @AfterMethod
    public void clearTemporaryPet() {
        petClient.deleteById("" + suitableId);
        suitableId = 0;
    }


    @Test
    public void petUpdateOnlyNameTest() {
        String oldStatus = petClient.getById("" + suitableId).body().jsonPath().getString("status");

        // Update pet
        petClient.updateById("" + suitableId, "imu", null).getStatusCode();
        // Verify that update was successful
        Response response = petClient.getById("" + suitableId);
        response.then().body("name", is("imu"));
        response.then().body("status", is(oldStatus));
    }
}
