package com.ss.apitesting.pet;

import com.ss.apitesting.client.PetClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore //TODO JSONDataProvider is removed, rewrite tests
public class AddPetTest {
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

    protected int findOccupiedID() {
        int id = 0;
        Response getPet;
        do {
            id++;
            getPet = petClient.getById("" + id);
        } while(getPet.statusCode() != 200);
        return id;
    }

    @Test
    public void testAddValidPet() {
        // Precondition
        int freeId = findFreeID();

        // Adding pet
        //petClient.createPet(JSONDataProvider.getPetInJSON("" + freeId, "doggo", "available"));
        int creatingStatus = petClient.getById("" + freeId).getStatusCode();
        Assert.assertEquals(creatingStatus, 200);

        // Remove added pet
        petClient.deleteById("" + freeId);
    }

    @Test
    public void testAddUnnamedPet() {
        // Precondition
        int freeId = findFreeID();

        // Adding pet
        //petClient.createPet(JSONDataProvider.getPetInJSON("" + freeId, "", "available"));
        int creatingStatus = petClient.getById("" + freeId).getStatusCode();
        Assert.assertEquals(creatingStatus, 200);

        // Remove added pet
        petClient.deleteById("" + freeId);
    }

    // Why we can add pet with existing ID?
    @Test
    public void testAddPetWithExistingId() {
        // Precondition
        int occupiedId = findOccupiedID();

        // Adding pet
        //int creatingStatus = petClient.createPet
        //        (JSONDataProvider.getPetInJSON("" + occupiedId, "temp", "available"))
        //        .statusCode();
        //Assert.assertEquals(creatingStatus, 200);

        // Remove added pet
        petClient.deleteById("" + occupiedId);
    }

    @Test
    public void testAddPetWithInvalidId() {
        // Precondition
        String invalidId = "188888888888888888888888888";

        // Adding pet
        //int addingResult = petClient.createPet(JSONDataProvider.getPetInJSON(invalidId, "dog", "sold"))
        //        .statusCode();
        //Assert.assertEquals(addingResult, 500);
    }

    @Test
    public void testAddPetWithNonDigitId() {
        // Precondition
        String nonDigitId = "\"eee\"";

        // Adding pet
        //int addingResult = petClient.createPet(JSONDataProvider.getPetInJSON(nonDigitId, "dog", "available"))
        //        .statusCode();
        //Assert.assertEquals(addingResult, 500);
    }
}
