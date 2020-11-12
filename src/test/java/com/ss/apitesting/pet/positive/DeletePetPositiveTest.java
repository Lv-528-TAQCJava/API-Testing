package com.ss.apitesting.pet.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Delete pet positive test suite")
public class DeletePetPositiveTest extends PetBaseTest {
    @DataProvider(name = "deletingPet")
    public Object[][] data() {
        return new Object[][]{
                {
                        PetBuilder.petWith().id(1L).name("hundo").status("sold").build()
                },
                {
                        PetBuilder.petWith().id(1000L).name("hundo").status("sold").build()
                },
                {
                        PetBuilder.petWith().id(Long.MAX_VALUE).name("hundo").status("sold").build()
                }
        };
    }

    @Test(dataProvider = "deletingPet")
    public void deleteById(PetModel pet) {
        // Precondition
        Response createResponse = petClient.createPet(pet);
        BaseAssertion createAssertion = new BaseAssertion(createResponse);
        createAssertion.statusCode(200);

        // Deleting
        Response deleteResponse = petClient.deleteById(String.valueOf(pet.id));
        BaseAssertion deleteAssertion = new BaseAssertion(deleteResponse);
        deleteAssertion.statusCode(200);

        // Verifying
        Response getResponse = petClient.getById(String.valueOf(pet.id));
        BaseAssertion getAssertion = new BaseAssertion(getResponse);
        getAssertion.statusCode(404);
    }
}
