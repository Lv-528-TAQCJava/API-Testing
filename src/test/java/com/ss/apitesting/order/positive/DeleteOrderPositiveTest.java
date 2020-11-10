package com.ss.apitesting.order.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.order.OrderBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

@Epic("Access to Petstore orders tests")
@Feature("Delete order positive test suite")
public class DeleteOrderPositiveTest extends OrderBaseTest {

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
