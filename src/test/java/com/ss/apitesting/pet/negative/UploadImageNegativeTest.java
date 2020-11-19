package com.ss.apitesting.pet.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.StringPetModel;
import com.ss.apitesting.pet.PetBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class UploadImageNegativeTest extends PetBaseTest {
    @DataProvider(name = "InvalidFileType")
    public Object[][] data() {
                return new Object [][] {
                {
                        PetBuilder.petWith().id(suitableId).name("doggo").status("available").build(),
                        new File("D:\\TextFile.txt"),
                        "text/plain",

                },
        };
    }

    @DataProvider(name = "invalidId")
    public Object[][] invalidIdData() {
        String memeType;
        return new Object [][] {
                {
                        new StringPetModel("a", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                },
                {
                        new StringPetModel("1000a", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                },
                {
                        new StringPetModel("-", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                },
                {
                        new StringPetModel("-844584845684848454815151444558", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                },
                {
                        new StringPetModel("-844584845684848454815151444558", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                },
                {
                        new StringPetModel("", "hundo", "sold"),
                        new File("D:\\image.jpg"),
                        "image/jpeg",
                }
        };
    }

    @Test(dataProvider = "InvalidFileType")
    public void uploadInvalidFileTypeTest(PetModel Pet, File file, String memeType) {
        petClient.createPet(Pet);

        Response invalidResponse = petClient.uploadImage(Pet, file, memeType);
        System.out.println(" Response : "+invalidResponse.asString());
        BaseAssertion invalidAssertion = new BaseAssertion(invalidResponse);
        invalidAssertion.statusCode(400);

        petClient.deleteById(String.valueOf(Pet.id));
    }

    @Test(dataProvider = "invalidId")
    public void uploadImageWithInvalidIdTest(StringPetModel Pet, File file, String memeType) {
        Response invalidResponse = petClient.uploadImage(Pet, file, memeType);
        System.out.println(" Response : "+invalidResponse.asString());
        BaseAssertion invalidAssertion = new BaseAssertion(invalidResponse);
        invalidAssertion.statusCode(404);
    }
}
