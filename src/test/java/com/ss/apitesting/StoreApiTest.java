package com.ss.apitesting;

import com.ss.apitesting.client.StoreClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class StoreApiTest {
    @Test
    public void orderFindByIdTest() {
        StoreClient storeClient = new StoreClient("json");
        Response response = storeClient.getById("3");

        response.then().body("status", is("placed"));

    }
}
