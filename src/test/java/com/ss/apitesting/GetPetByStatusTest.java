package com.ss.apitesting;

import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class GetPetByStatusTest {
    protected PetClient petClient;


    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @Test
    public void getPetByValidStatusTest(){
        Response response = petClient.getPetByStatus("available");
        response.then().body("status.size()", greaterThan(0));

    }

    @Test
    public void getPetByInvalidStatusTest(){
        Response response = petClient.getPetByStatus("InvalidStatus");
        response.then().body("status.size()", is(0));
    }

    @Test
    public void getPetByEmptyStatusTest(){
        Response response = petClient.getPetByStatus("");
        response.then().body("status.size()", is(0));
    }

}
