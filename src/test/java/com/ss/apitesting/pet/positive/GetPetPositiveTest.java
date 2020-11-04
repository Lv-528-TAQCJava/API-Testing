package com.ss.apitesting.pet.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import io.restassured.response.Response;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Get pet positive test suite")
public class GetPetPositiveTest {
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
    }

    @DataProvider(name = "petsData")
    public Object[][] data() {
        return new Object [][] {
                {
                        PetBuilder.petWith().id(1L).name("doggo").status("available").build()
                },
                {
                        PetBuilder.petWith().id(100L).name("doggo").status("available").build()
                },
                {
                        PetBuilder.petWith().id((long) Integer.MAX_VALUE).name("doggo").status("available").build()
                },
                {
                        PetBuilder.petWith().id(Long.MAX_VALUE).name("doggo").status("available").build()
                }
        };
    }

    @Test(dataProvider = "petsData")
    public void getByIdTest(PetModel pet) {
        // Precondition
        Response creatingResponse = petClient.createPet(pet);
        BaseAssertion createAssertion = new BaseAssertion(creatingResponse);
        createAssertion.statusCode(200);

        // Getting pet
        Response gettingResponse = petClient.getById(String.valueOf(pet.id));
        BaseAssertion getAssertion = new BaseAssertion(gettingResponse);
        getAssertion.statusCode(200);

        PetAssertions.assertBodyEquals(gettingResponse, pet);

        // Deleting temporary pet
        petClient.deleteById(String.valueOf(pet.id));
    }
}
