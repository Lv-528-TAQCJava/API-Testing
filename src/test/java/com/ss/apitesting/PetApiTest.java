package com.ss.apitesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.ss.apitesting.Client.PetClient;
import io.restassured.response.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PetApiTest {
    protected final static String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void petFindByIdTest() {
        PetClient petClient = new PetClient("json");
        Response response = petClient.getById("5"); //often changes, it's better to POST first and then GET

        response.then().body("name", is("doggie"));

    }

    @Test
    public void petFindByNotExistingIdTest() {
        PetClient petClient = new PetClient("json");
        Response response = petClient.getById("0");

        response.then().statusCode(404);

    }

    //TODO rewrite using Client methods
    @Test
    public void petFindByNotExistingStatusTest() {
        Response response = getAndLog("/pet/findByStatus?status=returned");
        response.then().body("status.size()", is(0));
    }

    //TODO rewrite using Client methods
    @Test
    public void petFindByStatusTest() {
        Response response = getAndLog("/pet/findByStatus?status=sold");
        response.then().body("status.size()", greaterThan(0));

        List<String> petStatuses = response.jsonPath().getList("status");
        for (String s: petStatuses) {
            Assert.assertEquals("sold", s);
        }
    }

    //TODO replace completely with Client methods
    /**
     * Performs rest-assured get(BASE_URL + subUrl), logs to console and returns the Response
     */
    protected Response getAndLog(String subUrl) {
        String currentUrl = BASE_URL + subUrl;
        System.out.println("Getting " + currentUrl);
        Response response = get(currentUrl);
        System.out.println(response.asString());
        return response;
    }
}
