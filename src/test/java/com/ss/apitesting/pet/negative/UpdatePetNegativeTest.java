package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.StringPetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Update pet negative test suite")
public class UpdatePetNegativeTest {
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @DataProvider(name = "negativeId")
    public Object[][] negativeIdData() {
        return new Object [][] {
                {
                        PetBuilder.petWith().id(0L).name("hundo").status("sold").build()
                },
                {
                        PetBuilder.petWith().id(-10L).name("hundo").status("sold").build()
                },
                {
                        PetBuilder.petWith().id(Long.MIN_VALUE).name("hundo").status("sold").build()
                }
        };
    }

    @DataProvider(name = "invalidId")
    public Object[][] invalidIdData() {
        return new Object [][] {
                {
                        new StringPetModel("a", "hundo", "sold")
                },
                {
                        new StringPetModel("1000a", "hundo", "sold")
                },
                {
                        new StringPetModel("-", "hundo", "sold")
                },
                {
                        new StringPetModel("-844584845684848454815151444558", "hundo", "sold")
                },
                {
                        new StringPetModel("-844584845684848454815151444558", "hundo", "sold")
                }
        };
    }

    @Test(dataProvider = "invalidId")
    public void updatePetWithInvalidId(StringPetModel pet) {
        Response invalidResponse = petClient.updatePet(pet);
        BaseAssertion invalidAssertion = new BaseAssertion(invalidResponse);
        invalidAssertion.statusCode(500);
    }

    @Test(dataProvider = "negativeId")
    public void updatePetWithNegativeId(PetModel pet) {
        Response negativeResponse = petClient.updatePet(pet);
        BaseAssertion negativeAssertion = new BaseAssertion(negativeResponse);
        negativeAssertion.statusCode(404);
    }

    @Test
    public void updatePetWithoutId() {
        Response prohibitedResponse = petClient.updatePet(new StringPetModel("", "hundo", "sold"));
        BaseAssertion prohibitedAssertion = new BaseAssertion(prohibitedResponse);
        prohibitedAssertion.statusCode(415);
    }
}
