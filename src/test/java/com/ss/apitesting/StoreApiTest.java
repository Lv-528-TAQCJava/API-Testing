package com.ss.apitesting;

import com.ss.apitesting.client.StoreClient;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.hamcrest.Matchers.is;

public class StoreApiTest {
    @DataProvider(name = "POST_values")
    public static Object[][] postValues() {
        return new Object[][]{
                {5, 1, "placed", false},
                {66, 0, "ordered", true}
        };
    }

    @Test
    public void orderFindByIdTest() {
        StoreClient storeClient = new StoreClient("json");
        Response response = storeClient.getById("268");

        response.then().body("status", is("ordered"));

    }

    @Test(dataProvider = "POST_values")
    public void orderPostTest(int petId, int quantity, String status, Boolean complete) {
        Random rand = new Random();

        int id = rand.nextInt(900) + 100; //from range [100, 999]
        System.out.println("Using ID: " + id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //2020-10-17T17:08:20.912Z
        Date date = new Date(System.currentTimeMillis());
        String dateStr = formatter.format(date);
        System.out.println("Current datetime: " + dateStr);

        StoreClient storeClient = new StoreClient("json");
        String body = storeClient.prepareOrder(id, petId, quantity, dateStr, status, complete);
        storeClient.postOrder(body).then()
                .statusCode(200);

        storeClient.getById(Integer.toString(id)).then()
                .statusCode(200)
                .body("status", is(status))
                .body("petId", is(petId))
                .body("quantity", is(quantity))
                .body("complete", is(complete));

        storeClient.deleteById(Integer.toString(id)); //clean up
    }
}
