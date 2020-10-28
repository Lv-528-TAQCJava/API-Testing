package com.ss.apitesting.storetest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class DeleteOrderTest {
    private int orderId;
    StoreClient storeClient;

    //Values for creating test order
    private final int petId = 1, quantity = 1;
    private final String status = "available";
    private final boolean complete = false;

    @BeforeMethod
    public void createOrder() {
        orderId = generateId(); //from range [100, 999]

        storeClient = new StoreClient("json");
        StoreModel storeModel = OrderBuilder.orderWith()
                .id(orderId)
                .petId(petId)
                .quantity(quantity)
                .status(status)
                .complete(complete)
                .build();
        storeClient.postOrder(storeModel);
    }

    @AfterClass
    public void removeOrder() { //Some tests don't delete the temporarily used order
        storeClient = new StoreClient("json");
        storeClient.deleteById(orderId);
    }

    @Test
    public void deleteOrderTest() {
        BaseAssertion assertion = new BaseAssertion(
                storeClient.deleteById(orderId)
        );
        assertion.statusCode(200);
        assertion = new BaseAssertion(
                storeClient.getById(orderId)
        );
        assertion.statusCode(404);
    }

    @Test
    public void deleteOrderByNotExistingIdTest() {
        int notExistingId = generateId(100000, 9999999);
        BaseAssertion assertion = new BaseAssertion(
                storeClient.deleteById(notExistingId)
        );
        assertion.statusCode(404);
    }
}
