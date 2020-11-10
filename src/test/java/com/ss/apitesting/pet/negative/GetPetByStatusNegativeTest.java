package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.ArrayAssertion;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Get pet by status negative test suite")
public class GetPetByStatusNegativeTest extends PetBaseTest {

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
