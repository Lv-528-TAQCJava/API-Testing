package com.ss.apitesting.order.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.models.order.StoreModelString;
import com.ss.apitesting.order.OrderTestRunner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateDateString;
import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class PostOrderNegativeTest extends OrderTestRunner {
    @DataProvider(name = "postInvalidValues")
    public static Object[][] postInvalidValues() {
        return new Object[][]{
                {"vasia", "1", "placed", "false"},
                {"66", "zero", "ordered", "true"},
                {"20", "3", "placed", "FALSE"},
                {"20", "3.14", "decimal", "true"}
        };
    }

    @BeforeMethod
    @Override
    public void createOrder() {
        orderId = generateId(); //from range [100, 999]
        dateStr = generateDateString();
    }

    @Test(dataProvider = "postInvalidValues")
    public void orderPostInvalidValuesTest(String petId, String quantity, String status, String complete) {
        log.debug("Starting orderPostInvalidValuesTest with generated ID = " + orderId + ", datetime = " + dateStr);

        StoreModelString storeModelString = OrderBuilder.orderWith()
                .idString(String.valueOf(orderId))
                .petIdString(petId)
                .quantityString(quantity)
                .shipDate(dateStr)
                .status(status)
                .completeString(complete)
                .buildString();
        storeClient.postOrder(storeModelString).then()
                .statusCode(500);

        Response posted = storeClient.getById(orderId);
        BaseAssertion assertion = new BaseAssertion(posted);
        assertion.contentType(ContentType.JSON)
                .statusCode(404);
    }
}
