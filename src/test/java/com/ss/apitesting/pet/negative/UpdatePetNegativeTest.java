package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.models.pet.UpdatePetDto;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Update pet negative test suite")
public class UpdatePetNegativeTest extends PetBaseTest {

    @DataProvider(name = "negativeId")
    public Object[][] negativeIdData() {
        return new Object [][] {
                {
                        new UpdatePetDto("0", "hundo", "sold")
                },
                {
                        new UpdatePetDto("-10", "hundo", "sold")
                },
                {
                        new UpdatePetDto(String.valueOf(Long.MIN_VALUE), "hundo", "sold")
                }
        };
    }

    @DataProvider(name = "invalidId")
    public Object[][] invalidIdData() {
        return new Object [][] {
                {
                        new UpdatePetDto("a", "hundo", "sold")
                },
                {
                        new UpdatePetDto("1000a", "hundo", "sold")
                },
                {
                        new UpdatePetDto("-", "hundo", "sold")
                },
                {
                        new UpdatePetDto("-844584845684848454815151444558", "hundo", "sold")
                },
                {
                        new UpdatePetDto("844584845684848454815151444558", "hundo", "sold")
                }
        };
    }

    @Test(dataProvider = "invalidId")
    public void updatePetWithInvalidId(UpdatePetDto pet) {
        Response invalidResponse = petClient.updatePet(pet);
        BaseAssertion invalidAssertion = new BaseAssertion(invalidResponse);
        invalidAssertion.statusCode(404);
    }

    @Test(dataProvider = "negativeId")
    public void updatePetWithNegativeId(UpdatePetDto pet) {
        Response negativeResponse = petClient.updatePet(pet);
        BaseAssertion negativeAssertion = new BaseAssertion(negativeResponse);
        negativeAssertion.statusCode(404);
    }

    @Test
    public void updatePetWithoutId() {
        Response prohibitedResponse = petClient.updatePet(new UpdatePetDto("", "hundo", "sold"));
        BaseAssertion prohibitedAssertion = new BaseAssertion(prohibitedResponse);
        prohibitedAssertion.statusCode(415);
    }
}
