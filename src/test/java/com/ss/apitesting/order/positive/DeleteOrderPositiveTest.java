package com.ss.apitesting.order.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import com.ss.apitesting.order.OrderTestRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

@Epic("Access to Petstore orders tests")
@Feature("Delete order test suite")
public class DeleteOrderPositiveTest extends OrderTestRunner {
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
}
