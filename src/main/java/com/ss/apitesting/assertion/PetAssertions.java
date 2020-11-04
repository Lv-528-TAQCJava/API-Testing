package com.ss.apitesting.assertion;

import com.ss.apitesting.models.pet.PetModel;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;


public class PetAssertions {

    public static void assertBodyEquals(Response response, PetModel expected) {
        SoftAssert softAssert = new SoftAssert();
        PetModel actual = response.as(PetModel.class);
        softAssert.assertTrue(PetModel.equals(actual, expected));
//        JsonPath body = response.jsonPath();
//        softAssert.assertEquals(body.getInt("id"), pet.petId.intValue());
//        softAssert.assertEquals(body.getObject("category", Category.class), pet.category);
//        softAssert.assertEquals(body.getString("name"), pet.name);
//        softAssert.assertEquals(body.getList("photoUrls", String.class).toArray(), (pet.photoUrls));
//        softAssert.assertEquals(body.getList("tags", Tag.class).toArray(), (pet.tags));
//        softAssert.assertEquals(body.getString("status"), pet.status);
        softAssert.assertAll();
    }
}
