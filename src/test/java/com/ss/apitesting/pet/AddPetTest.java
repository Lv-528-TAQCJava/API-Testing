package com.ss.apitesting.pet;

import com.ss.apitesting.assertion.PetAssertions;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.client.PetClient;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.Tag;
import com.ss.apitesting.util.ValuesGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation with pets tests")
@Feature("Add pet test suite")
public class AddPetTest {
    protected PetClient petClient;
    private int suitableId;

    @BeforeClass
    public void init() {
        petClient = new PetClient(ContentType.JSON);
        suitableId = ValuesGenerator.generateId(1000, 10000);
    }

    @DataProvider(name = "addingPetsData")
    public Object[][] data() {
        return new Object [][] {
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
        int POSTstatus = petClient.createPet(pet).statusCode();
        Assert.assertEquals(POSTstatus, 200);

        PetAssertions.assertBodyEquals(petClient.getById(String.valueOf(pet.id)), pet);

        // Post condition
        petClient.deleteById(String.valueOf(pet.id));
    }
}
