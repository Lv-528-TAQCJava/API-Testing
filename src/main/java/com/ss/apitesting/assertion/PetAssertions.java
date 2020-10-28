package com.ss.apitesting.assertion;

import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.Tag;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;


public class PetAssertions {

    public static void assertBodyEquals(Response response, PetModel pet) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getInt("id"), pet.petId.intValue());
        softAssert.assertEquals(response.jsonPath().getInt("category.id"), pet.category.id.intValue());
        softAssert.assertEquals(response.jsonPath().getString("category.name"), pet.category.name);
        softAssert.assertEquals(response.jsonPath().getString("name"), pet.name);
        softAssert.assertEquals(response.jsonPath().getList("photoUrls", String.class), pet.photoUrls);
        softAssert.assertEquals(response.jsonPath().getList("tags", Tag.class), pet.tags);
        softAssert.assertEquals(response.jsonPath().getString("status"), pet.status);
        softAssert.assertAll();
    }
}
