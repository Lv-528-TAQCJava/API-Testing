package com.ss.apitesting.pet.positive;

import com.ss.apitesting.assertion.ArrayAssertion;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Get pet by status positive test suite")
public class GetPetByStatusPositiveTest extends PetBaseTest {

    @Test
    public void getPetByValidStatusTest(){
        ArrayAssertion assertion = new ArrayAssertion(petClient.getPetByStatus("available"));
        assertion.defaultAsserts()
                .bodyArrayNotEmpty("status")
                .bodyArrayAllEquals("status", "available");
    }
}
