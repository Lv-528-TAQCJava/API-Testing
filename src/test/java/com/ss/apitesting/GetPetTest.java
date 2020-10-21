package com.ss.apitesting;

import com.ss.apitesting.client.PetClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class GetPetTest {
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
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

//    @Test
//    public void petGetByExistingId() {
//        // Precondition
//        int freeId = findFreeID();
//        petClient.createPet(JSONDataProvider.getPetInJSON("" + freeId, "catto", "sold"));
//
//        // Getting pet
//        Response response = petClient.getById("" + freeId);
//        response.then().body("name", is("catto"));
//        response.then().body("status", is("sold"));
//
//        // Remove temporary pet
//        petClient.deleteById("" + freeId);
//    }

    @Test
    public void petGetByNonexistentId() {
        // Precondition
        int freeId = findFreeID();

        // Getting pet
        int getResult = petClient.getById("" + freeId).statusCode();
        Assert.assertEquals(getResult, 404);
    }

    @Test void petGetByNonDigitId() {
        // Getting pet
        int getResult = petClient.getById("aaa").statusCode();
        Assert.assertEquals(getResult, 404);
    }
}
