package com.ss.apitesting;

import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.StoreModel;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.*;
import static org.hamcrest.Matchers.is;

public class GetOrderTest {
    @DataProvider(name = "postValues")
    public static Object[][] postValues() {
        return new Object[][]{
                {5, 1, "placed", false},
                {66, 0, "ordered", true}
        };
    }

    //TODO move to GetOrderTest or remove
    @Test
    public void orderFindByIdTest() {
        StoreClient storeClient = new StoreClient("json");
        Response response = storeClient.getById("10");

        response.then().body("status", is("placed"));

    }

    @Test(dataProvider = "postValues")
    public void orderPostTest(int petId, int quantity, String status, Boolean complete) {
        int id = generateId(); //from range [100, 999]
        System.out.println("Using ID: " + id);
        String dateStr = generateDateString();
        System.out.println("Current datetime: " + dateStr);

        StoreClient storeClient = new StoreClient("json");
        StoreModel storeModel = new StoreModel(id, petId, quantity, dateStr, status, complete);
        storeClient.postOrder(storeModel).then()
                .statusCode(200);

        storeClient.getById(Integer.toString(id)).then()
                .statusCode(200)
                .body("status", is(storeModel.status))
                .body("petId", is(storeModel.petId))
                .body("quantity", is(storeModel.quantity))
                .body("complete", is(storeModel.complete));

        storeClient.deleteById(Integer.toString(id)); //clean up
    }
}
