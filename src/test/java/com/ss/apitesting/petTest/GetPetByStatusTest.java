package com.ss.apitesting.petTest;

import com.ss.apitesting.assertion.ArrayAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@Ignore
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
