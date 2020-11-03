package com.ss.apitesting.order;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ss.apitesting.util.ValuesGenerator.*;
import static org.hamcrest.Matchers.is;

@Epic("Access to Petstore orders tests")
@Feature("Post order test suite")
public class PostOrderTest {
    private Logger log;
    @DataProvider(name = "postValues")
    public static Object[][] postValues() {
        return new Object[][]{
                {5, 1, "placed", false},
                {66, 0, "ordered", true}
        };
    }

    @BeforeClass
    public void setLog() {
        log = LoggerFactory.getLogger("PostOrderTest");
    }

    @Test(dataProvider = "postValues")
    public void orderPostTest(int petId, int quantity, String status, Boolean complete) {
        int id = generateId(); //from range [100, 999]
        String dateStr = generateDateString();
        log.debug("Starting orderPostTest with generated ID = " + id + ", datetime = " + dateStr);

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

        BaseAssertion assertion = new BaseAssertion(storeClient.getById(id));
        assertion.defaultAsserts()
                .bodyValueEquals("status", storeModel.status)
                .bodyValueEquals("petId", storeModel.petId)
                .bodyValueEquals("quantity", storeModel.quantity)
                .bodyValueEquals("complete", storeModel.complete);

        storeClient.deleteById(id); //clean up
    }
}
