package com.ss.apitesting.order;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static com.ss.apitesting.util.ValuesGenerator.*;
import static org.hamcrest.Matchers.is;

@Epic("Access to Petstore orders tests")
@Feature("Post order test suite")
public class PostOrderTest {
    private Logger log;
    private StoreClient storeClient;

    private int id;
    private String dateStr;

    @DataProvider(name = "postValues")
    public static Object[][] postValues() {
        return new Object[][]{
                {5, 1, "placed", false},
                {66, 0, "ordered", true},
                {-20, -3, "negative", false},
        };
    }

    @BeforeClass
    public void init() {
        log = LoggerFactory.getLogger("PostOrderTest");
        storeClient = new StoreClient("json");
    }
    @BeforeMethod
    public void generateValues() {
        id = generateId(); //from range [100, 999]
        dateStr = generateDateString();
    }
    @AfterMethod
    public void deleteOrder() {
        storeClient.deleteById(id); //clean up
    }

    @Test(dataProvider = "postValues")
    public void orderPostTest(int petId, int quantity, String status, Boolean complete) {
        log.debug("Starting orderPostTest with generated ID = " + id + ", datetime = " + dateStr);

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
    }
}
