package com.ss.apitesting.pet;

import com.ss.apitesting.assertion.ArrayAssertion;
import com.ss.apitesting.client.PetClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Operation with pets tests")
@Feature("Get pet by status test suite")
public class GetPetByStatusTest extends PetBaseTest {
    @Test
    public void getPetByValidStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus("available"));
        assertion.defaultAsserts()
                .bodyArrayNotEmpty("status")
                .bodyArrayAllEquals("status", "available");

    }

    @Test
    public void getPetByInvalidStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus("InvalidStatus"));
        assertion.defaultAsserts()
                .bodyArrayEmpty("status");
    }

    @Test
    public void getPetByEmptyStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus(""));
        assertion.defaultAsserts()
                .bodyArrayEmpty("status"); //Now fails, one pet with empty tag <status/> exists
    }

}
