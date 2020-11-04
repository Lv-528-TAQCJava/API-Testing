package com.ss.apitesting.order.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.StoreClient;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class DeleteOrderNegativeTest {
    @Test
    public void deleteOrderByNotExistingIdTest() {
        StoreClient storeClient = new StoreClient();
        int notExistingId = generateId(100000, 9999999);
        BaseAssertion assertion = new BaseAssertion(
                storeClient.deleteById(notExistingId)
        );
        assertion.statusCode(404);
    }
}
