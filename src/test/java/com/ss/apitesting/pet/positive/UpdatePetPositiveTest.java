package com.ss.apitesting.pet.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.UpdatePetDto;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.ss.apitesting.builder.PetBuilder.petWith;

@Epic("Operation with pets tests")
@Feature("Update pet positive test suite")
public class UpdatePetPositiveTest extends PetBaseTest {
    @DataProvider(name = "updatingPetsData")
    public Object[][] data() {
        return new Object [][] {
                {
                        petWith().id(suitableId).name("doggo").status("available").build(),
                        new UpdatePetDto(String.valueOf(suitableId), "hundo", null),
                        petWith().id(suitableId).name("hundo").status("available").build()
                },
                {
                        petWith().id(suitableId).name("doggo").status("available").build(),
                        new UpdatePetDto(String.valueOf(suitableId), "hundo", "sold"),
                        petWith().id(suitableId).name("hundo").status("sold").build()
                },
                {
                        petWith().id(suitableId).name("doggo").status("available").build(),
                        new UpdatePetDto(String.valueOf(suitableId), null, null),
                        petWith().id(suitableId).name("doggo").status("available").build()
                },
                {
                        petWith().id(suitableId).name("doggo").status("available").build(),
                        new UpdatePetDto(String.valueOf(suitableId), null, "undefined"),
                        petWith().id(suitableId).name("doggo").status("undefined").build()
                }
        };
    }

    @Test(dataProvider = "updatingPetsData")
    public void petUpdateTest(PetModel initialPet, UpdatePetDto petParams, PetModel expectedPet) {
        petClient.createPet(initialPet);

        Response updated = petClient.updatePet(petParams);
        BaseAssertion assertUpdating = new BaseAssertion(updated);
        assertUpdating.statusCode(200);

        // Verify that update was successful
        Response response = petClient.getById(String.valueOf(initialPet.id));
        PetAssertions.assertBodyEquals(response, expectedPet);

        petClient.deleteById(String.valueOf(initialPet.id));
    }
}
