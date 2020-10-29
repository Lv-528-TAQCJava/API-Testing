package com.ss.apitesting.orderTest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateDateString;
import static com.ss.apitesting.util.ValuesGenerator.generateId;


public class PostOrderTest {
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

        BaseAssertion assertion = new BaseAssertion(storeClient.getById("7"));
        assertion.defaultAsserts()
                .bodyValueEquals("status", "placed");

    }

    @Test(dataProvider = "postValues")
    public void orderPostTest(int petId, int quantity, String status, Boolean complete) {
        int id = generateId(); //from range [100, 999]
        System.out.println("Using ID: " + id);
        String dateStr = generateDateString();
        System.out.println("Current datetime: " + dateStr);

        StoreClient storeClient = new StoreClient("json");
        StoreModel storeModel = OrderBuilder.orderWith()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(dateStr)
                .status(status)
                .complete(complete)
                .build();
        storeClient.postOrder(storeModel).then()
                .statusCode(200);

        BaseAssertion assertion = new BaseAssertion(storeClient.getById(Integer.toString(id)));
        assertion.defaultAsserts()
                .bodyValueEquals("status", storeModel.status)
                .bodyValueEquals("petId", storeModel.petId)
                .bodyValueEquals("quantity", storeModel.quantity)
                .bodyValueEquals("complete", storeModel.complete);

        storeClient.deleteById(Integer.toString(id)); //clean up
    }
}
