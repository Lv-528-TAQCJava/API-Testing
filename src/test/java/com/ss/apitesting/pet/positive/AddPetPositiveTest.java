package com.ss.apitesting.pet.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.pet.PetBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Add pet positive test suite")
public class AddPetPositiveTest extends PetBaseTest {

    @DataProvider(name = "addingPetsData")
    public Object[][] data() {
        return new Object[][]{
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").photoUrls(new String[0]).build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").tags(null).build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").category(null).build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("").status("").build()
                }
        };
    }

    @Test(dataProvider = "addingPetsData")
    public void testAddPet(PetModel pet) {
        // Precondition
        petClient.deleteById(String.valueOf(pet.id));

        // Adding pets
        Response created = petClient.createPet(pet);
        BaseAssertion assertCreated = new BaseAssertion(created);
        assertCreated.statusCode(200);

        PetAssertions.assertBodyEquals(petClient.getById(String.valueOf(pet.id)), pet);

        // Post condition
        petClient.deleteById(String.valueOf(pet.id));
    }
}
