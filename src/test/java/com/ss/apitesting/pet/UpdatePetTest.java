package com.ss.apitesting.pet;

import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.util.ValuesGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;

@Epic("Operation with pets tests")
@Feature("Update pet test suite")
public class UpdatePetTest {
    protected int suitableId;
    protected PetClient petClient;

    @BeforeClass
    public void init() {
        suitableId = ValuesGenerator.generateId(1000, 10000);
        petClient = new PetClient(ContentType.JSON);
    }

    @DataProvider (name = "updatingPetsData")
    public Object[][] data() {
        return new Object [][] {
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        PetBuilder.petWith().id(suitableId).name("hundo").status(null).build(),
                        PetBuilder.petWith().id(suitableId).name("hundo").status("available").build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        PetBuilder.petWith().id(suitableId).name("hundo").status("sold").build(),
                        PetBuilder.petWith().id(suitableId).name("hundo").status("sold").build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        PetBuilder.petWith().id(suitableId).name(null).status(null).build(),
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build()
                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        PetBuilder.petWith().id(suitableId).name(null).status("undefined").build(),
                        PetBuilder.petWith().id(suitableId).name("doggo").status("undefined").build()
                }
        };
    }

    @Test(dataProvider = "updatingPetsData")
    public void petUpdateTest(PetModel initialPet, PetModel petParams, PetModel expectedPet) {
        petClient.createPet(initialPet);

        petClient.updatePet(petParams);

        // Verify that update was successful
        Response response = petClient.getById(String.valueOf(initialPet.id));
        PetAssertions.assertBodyEquals(response, expectedPet);

        petClient.deleteById(String.valueOf(initialPet.id));
    }
}
