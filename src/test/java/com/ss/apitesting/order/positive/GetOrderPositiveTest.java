package com.ss.apitesting.order.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.order.OrderTestRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("Access to Petstore orders tests")
@Feature("Get order test suite")
public class GetOrderPositiveTest extends OrderTestRunner {
    @Test
    public void orderFindByIdTest() {
        log.debug("Starting orderFindByIdTest");
        Response response = storeClient.getById(orderId);
        BaseAssertion assertion = new BaseAssertion(response);
        assertion.defaultAsserts()
                .bodyValueEquals("id", orderId)
                .bodyValueEquals("status", status);
    }

    @Test
    public void orderFindByIdXmlTest() {
        log.debug("Starting orderFindByIdXmlTest");
        StoreClient storeClientXml = new StoreClient("XML");

        Response response = storeClientXml.getById(orderId);
        BaseAssertion assertion = new BaseAssertion(response);

        assertion.statusCode(200)
                .contentType(ContentType.XML)
                .bodyValueEquals("order.id", String.valueOf(orderId)) //Mind these!
                    // 1. You need to specify the XML path, not simply the parameter name
                    // 2. You need to provide the second parameter as string!
                .bodyValueEquals("order.status", status);

    }




}
