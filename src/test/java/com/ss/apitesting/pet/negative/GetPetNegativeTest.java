package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.util.RetryAnalyzerImpl;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Get pet negative test suite")
public class GetPetNegativeTest {

    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @DataProvider(name = "negativeIds")
    public Object[][] negativeData() {
        return new Object [][] {
                {0L},
                {-1L},
                {Long.MIN_VALUE}
        };
    }

    @Test(dataProvider = "negativeIds")
    public void getPetByNegativeId(Long id) {
        // Precondition
        petClient.deleteById(String.valueOf(id));

        // Get by negative id
        Response negativeIdResponse = petClient.getById(String.valueOf(id));
        BaseAssertion negativeAssertion = new BaseAssertion(negativeIdResponse);
        negativeAssertion.statusCode(404);
    }

    @DataProvider(name = "invalidIds")
    public Object[][] invalidData() {
        return new Object [][] {
                {"a"},
                {"19V"},
                {"\'abc\'"},
                {"12345678901234567892345678"},
                {"-123456786578965789678978978"}
        };
    }

    @Test(dataProvider = "invalidIds", retryAnalyzer = RetryAnalyzerImpl.class)
    public void getPetByInvalidId(String id) {
        Response invalidResponse = petClient.getById(id);
        BaseAssertion invalidAssertion = new BaseAssertion(invalidResponse);
        invalidAssertion.statusCode(500);
    }

    @Test
    public void getPetWithoutId() {
        Response notAllowedResponse = petClient.getById("");
        BaseAssertion notAllowedAssertion = new BaseAssertion(notAllowedResponse);
        notAllowedAssertion.statusCode(405);
    }
}
