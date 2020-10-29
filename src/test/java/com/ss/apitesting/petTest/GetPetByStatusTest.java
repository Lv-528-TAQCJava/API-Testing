package com.ss.apitesting.petTest;

import com.ss.apitesting.assertion.ArrayAssertion;
import com.ss.apitesting.client.PetClient;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetPetByStatusTest {
    protected PetClient petClient;


    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @Test
    public void getPetByValidStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus("available"));
        assertion.defaultAsserts()
                .bodyArrayNotEmpty("status")
                .bodyArrayEquals("status", "available");

    }

    @Test
    public void getPetByInvalidStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus("InvalidStatus"));
        assertion.defaultAsserts()
                .bodyArrayEmpty("status");
    }

    @Test
    public void getPetByEmptyStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus(""));
        assertion.defaultAsserts()
                .bodyArrayEmpty("status");
    }

}
