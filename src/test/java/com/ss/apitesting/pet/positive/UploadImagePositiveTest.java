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

public class UploadImagePositiveTest extends PetBaseTest {
    @DataProvider(name = "CreatePet")
    public Object[][] data() {
        return new Object [][] {
                {
                        PetBuilder.petWith().id(560l).name("doggo").status("available").build(),

                },
        };
    }

    @Test(dataProvider = "CreatePet")
    public void uploadImageTest(PetModel Pet) {
        petClient.createPet(Pet);


        Response updated = petClient.uploadImage(Pet);
        System.out.println(" Response : "+updated.asString());
        BaseAssertion assertUpdating = new BaseAssertion(updated);
        assertUpdating.statusCode(200);

       // petClient.deleteById(String.valueOf(initialPet.id));
    }
}
