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

import java.io.File;

public class UploadImagePositiveTest extends PetBaseTest {
    @DataProvider(name = "CreatePet")
    public Object[][] data() {
        String memeType;
        return new Object [][] {
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        new File("D:\\image.jpg"),
                        memeType = "image/jpeg",

                },
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        new File("D:\\icon.png"),
                        memeType = "image/png",

                },
        };
    }

    @Test(dataProvider = "CreatePet")
    public void uploadImageTest(PetModel Pet, File file, String memeType) {
        petClient.createPet(Pet);


        Response updated = petClient.uploadImage(Pet, file, memeType);
        System.out.println(" Response : "+updated.asString());
        BaseAssertion assertUpdating = new BaseAssertion(updated);
        assertUpdating.statusCode(200);

        petClient.deleteById(String.valueOf(Pet.id));
    }
}
