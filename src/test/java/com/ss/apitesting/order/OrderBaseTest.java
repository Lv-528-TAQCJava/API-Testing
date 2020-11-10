package com.ss.apitesting.order;

import com.ss.apitesting.BaseTest;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.ss.apitesting.util.ValuesGenerator.generateDateString;
import static com.ss.apitesting.util.ValuesGenerator.generateId;

public abstract class OrderBaseTest extends BaseTest {
    protected int orderId;
    protected String dateStr;
    protected StoreClient storeClient;

    //Values for creating test order
    protected final int petId = 1, quantity = 1;
    protected final String status = "available";
    protected final boolean complete = false;

    @Override
    protected String getLoggerName() {
        return "OrderTest";
    }

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        storeClient = new StoreClient("json");
    }

    @BeforeMethod
    public void createOrder() {
        log.info("Creating a temporary order for tests");

        orderId = generateId(); //from range [100, 999]
        dateStr = generateDateString();

        StoreModel storeModel = OrderBuilder.orderWith()
                .id(orderId)
                .petId(petId)
                .quantity(quantity)
                .status(status)
                .shipDate(dateStr)
                .complete(complete)
                .build();
        storeClient.postOrder(storeModel);
    }

    @AfterMethod
    public void removeOrder() {
        log.info("Deleting a temporary order used in tests");
        storeClient.deleteById(orderId);
    }
}
