package com.ss.apitesting.storetest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class GetOrderTest {
    private int orderId;
    StoreClient storeClient;

    //Values for creating test order
    private final int petId = 1, quantity = 1;
    private final String status = "available";
    private final boolean complete = false;

    @BeforeClass
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
    public void removeOrder() {
        StoreClient storeClient = new StoreClient("json");
        storeClient.deleteById(orderId);
    }

    @Test
    public void orderFindByIdTest() {
        BaseAssertion assertion = new BaseAssertion(
                storeClient.getById(orderId)
        );
        assertion.defaultAsserts()
                .bodyValueEquals("id", orderId)
                .bodyValueEquals("status", status);
    }

    @Test
    public void orderFindByIdXmlTest() {
        StoreClient storeClientXml = new StoreClient("XML");
        BaseAssertion assertion = new BaseAssertion(
                storeClientXml.getById(orderId)
        );
        assertion.statusCode(200)
                .contentType(ContentType.XML)
                .bodyValueEquals("id", orderId)
                .bodyValueEquals("status", status);
    }

    @Test
    public void orderFindByNotExistingIdTest() {
        int notExistingId = generateId(100000, 9999999);
        BaseAssertion assertion = new BaseAssertion(
                storeClient.getById(notExistingId)
        );
        assertion.statusCode(404);

        StoreClient storeClientXml = new StoreClient("xml");
        assertion = new BaseAssertion(
                storeClientXml.getById(notExistingId)
        );
        assertion.statusCode(404);
    }

    @Test
    public void orderFindByZeroIdTest() {
        BaseAssertion assertion = new BaseAssertion(
                storeClient.getById(0)
        );
        assertion.statusCode(404);
    }

    @Test
    public void orderFindByNegativeIdTest() {
        int negativeId = generateId(-999, -1);
        BaseAssertion assertion = new BaseAssertion(
                storeClient.getById(negativeId)
        );
        assertion.statusCode(404); //maybe another 4** status code is more suitable
    }

    /* Could not be tested using storeClient.getById
    @Test
    public void orderFindByDecimalIdTest() {
        float notId = 123.4f;
        BaseAssertion assertion = new BaseAssertion(
                storeClient.getById(notId)
        );
        assertion.bodyValueContains("message", "Exception");
        //will be something like <message>java.lang.NumberFormatException: For input string: "123.4"</message>
    }
    */

}
