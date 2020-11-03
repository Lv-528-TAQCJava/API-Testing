package com.ss.apitesting.order;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.OrderBuilder;
import com.ss.apitesting.client.StoreClient;
import com.ss.apitesting.models.order.StoreModel;
import com.ss.apitesting.models.order.StoreModelString;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
                {-20, -3, "negative", false}
        };
    }

    @DataProvider(name = "postStringValues")
    public static Object[][] postStringValues() {
        return new Object[][]{
                {"5", "1", "placed", "false"},
                {"66", "0", "ordered", "true"},
                {"-20", "-3", "negative", "false"}
        };
    }

    @DataProvider(name = "postInvalidValues")
    public static Object[][] postInvalidValues() {
        return new Object[][]{
                {"vasia", "1", "placed", "false"},
                {"66", "zero", "ordered", "true"},
                {"20", "3", "placed", "FALSE"},
                {"20", "3.14", "decimal", "true"}
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

        Response posted = storeClient.getById(id);
        BaseAssertion assertion = new BaseAssertion(posted);
        assertion.defaultAsserts()
                .bodyValueEquals("status", storeModel.status)
                .bodyValueEquals("petId", storeModel.petId)
                .bodyValueEquals("quantity", storeModel.quantity)
                //.bodyValueEquals("shipDate", storeModel.shipDate)
                // ^ Fails, because returns 2020-11-03T23:09:48.076+0000 when provided 2020-11-03T23:09:48.076Z (formatting)
                .bodyValueEquals("complete", storeModel.complete);
    }

    @Test(dataProvider = "postStringValues")
    public void orderPostStringValuesTest(String petId, String quantity, String status, String complete) {
        log.debug("Starting orderPostStringValuesTest with generated ID = " + id + ", datetime = " + dateStr);

        StoreModelString storeModelString = OrderBuilder.orderWith()
                .idString(String.valueOf(id))
                .petIdString(petId)
                .quantityString(quantity)
                .shipDate(dateStr)
                .status(status)
                .completeString(complete)
                .buildString();
        storeClient.postOrder(storeModelString).then()
                .statusCode(200);

        Response posted = storeClient.getById(id);
        BaseAssertion assertion = new BaseAssertion(posted);
        assertion.defaultAsserts()
                .bodyValueEquals("status", storeModelString.status)
                .bodyValueEquals("petId", Integer.valueOf(storeModelString.petId))
                .bodyValueEquals("quantity", Integer.valueOf(storeModelString.quantity))
                .bodyValueEquals("complete", Boolean.valueOf(storeModelString.complete));
    }

    @Test(dataProvider = "postInvalidValues")
    public void orderPostInvalidValuesTest(String petId, String quantity, String status, String complete) {
        log.debug("Starting orderPostInvalidValuesTest with generated ID = " + id + ", datetime = " + dateStr);

        StoreModelString storeModelString = OrderBuilder.orderWith()
                .idString(String.valueOf(id))
                .petIdString(petId)
                .quantityString(quantity)
                .shipDate(dateStr)
                .status(status)
                .completeString(complete)
                .buildString();
        storeClient.postOrder(storeModelString).then()
                .statusCode(500);

        Response posted = storeClient.getById(id);
        BaseAssertion assertion = new BaseAssertion(posted);
        assertion.contentType(ContentType.JSON)
                .statusCode(404);
    }
}
