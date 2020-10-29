package com.ss.apitesting.petTest;

import com.ss.apitesting.client.PetClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

@Epic("Operation with pets tests")
@Feature("Update pet test suite")
public class UpdatePetTest {
    protected int suitableId;
    protected PetClient petClient;

    // TODO Use dataProvider

    @BeforeClass
    public void init() {
        suitableId = 0;
        petClient = new PetClient(ContentType.JSON);
    }

    @Deprecated
    @BeforeMethod
    public void createTemporaryPet() {
        Response getPet;
        do {
            suitableId++;
            getPet = petClient.getById("" + suitableId);
        } while(getPet.statusCode() != 404);

        //petClient.createPet(JSONDataProvider.getPetInJSON("" + suitableId, "doggo", "available"));
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
        petClient.updateById("" + suitableId, "imu", null);

        // Verify that update was successful
        Response response = petClient.getById("" + suitableId);
        response.then().body("name", is("imu"));
        response.then().body("status", is(oldStatus));
    }

    @Test
    public void petUpdateOnlyStatusTest() {
        String oldName = petClient.getById("" + suitableId).body().jsonPath().getString("name");

        // Update pet
        petClient.updateById("" + suitableId, null, "sold");

        // Verify that update was successful
        Response response = petClient.getById("" + suitableId);
        response.then().body("status", is("sold"));
        response.then().body("name", is(oldName));
    }

    @Test
    public void petUpdateToInvalidStatus() {
        String oldStatus = petClient.getById("" + suitableId).body().jsonPath().getString("status");

        // Update pet
        int updateStatus = petClient.updateById("" + suitableId, null, "invalid_status")
                .statusCode();
        Response updatedPet = petClient.getById("" + suitableId);

        // Verify that request was unsuccessful
        updatedPet.then().body("status", is(oldStatus));
        Assert.assertEquals(updateStatus, 405);
    }

    // Questionable test
    @Test
    public void petUpdateWithEmptyName() {
        // Update pet
        int updatingStatus = petClient.updateById("" + suitableId, "", null).statusCode();
        Assert.assertEquals(updatingStatus, 200);
        // Verify that update was successful
        Response response = petClient.getById("" + suitableId);
        response.then().body("name", is(" "));
    }
}
