package com.ss.apitesting.petTest;

import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.util.ValuesGenerator;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetPetTest {
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @Test
    public void petGetByExistingIdTest() {
        // Precondition
        int id = ValuesGenerator.generateId(1000, 10000);
        PetModel pet = PetBuilder.petWith().id(id).name("Catty").status("available").build();
        petClient.createPet(pet);

        // Getting pet
        PetAssertions.assertBodyEquals(petClient.getById(String.valueOf(id)), pet);

        // Remove temporary pet
        petClient.deleteById(String.valueOf(id));
    }

    @Test
    public void petGetByNonexistentId() {
        // Precondition
        int id = -1;
        petClient.deleteById(String.valueOf(id));

        // Getting pet
        int getResult = petClient.getById(String.valueOf(id)).statusCode();
        Assert.assertEquals(getResult, 404);
    }

    @Test void petGetByNonDigitId() {
        // Getting pet
        int getResult = petClient.getById("aaa").statusCode();
        Assert.assertEquals(getResult, 404);
    }
}
