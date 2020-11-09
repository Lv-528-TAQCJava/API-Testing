package com.ss.apitesting.order.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.order.OrderBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class GetOrderNegativeTest extends OrderBaseTest {
    @BeforeMethod
    @Override
    public void createOrder() {
        //no need to create order for negative tests
    }

    @AfterMethod
    @Override
    public void removeOrder() {
        //no need to remove order after negative tests
    }

    @Test
    public void orderFindByNotExistingIdTest() {
        log.debug("Starting orderFindByIdTest");
        int notExistingId = generateId(100000, 9999999);
        log.debug("Generated not existing ID: " + notExistingId);
        Response response = storeClient.getById(notExistingId);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.statusCode(404);

        StoreClient storeClientXml = new StoreClient("xml");
        response = storeClientXml.getById(notExistingId);
        assertion = new BaseAssertion(response);
        assertion.statusCode(404);
    }

    @Test
    public void orderFindByZeroIdTest() {
        log.debug("Starting orderFindByZeroIdTest");
        Response response = storeClient.getById(0);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.statusCode(404);
    }

    @Test
    public void orderFindByNegativeIdTest() {
        log.debug("Starting orderFindByNegativeIdTest");
        int negativeId = generateId(-999, -1);
        log.debug("Generated negative ID: " + negativeId);
        Response response = storeClient.getById(negativeId);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.statusCode(404); //maybe another 4** status code is more suitable
    }

    @Test
    public void orderFindByDecimalIdTest() {
        log.debug("Starting orderFindByDecimalIdTest");
        String notId = "123.4";
        Response response = storeClient.getById(notId);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.bodyValueContains("message", "Exception");
        //will be something like <message>java.lang.NumberFormatException: For input string: "123.4"</message>
    }
}
