package com.ss.apitesting.assertion;

import com.ss.apitesting.models.pet.Category;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.Tag;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;


public class PetAssertions {

    public static void assertBodyEquals(Response response, PetModel pet) {
        SoftAssert softAssert = new SoftAssert();
        JsonPath body = response.jsonPath();
        softAssert.assertEquals(body.getInt("id"), pet.petId.intValue());
        softAssert.assertEquals(body.getObject("category", Category.class), pet.category);
        softAssert.assertEquals(body.getString("name"), pet.name);
        softAssert.assertEquals(body.getList("photoUrls", String.class).toArray(), (pet.photoUrls));
        softAssert.assertEquals(body.getList("tags", Tag.class).toArray(), (pet.tags));
        softAssert.assertEquals(body.getString("status"), pet.status);
        softAssert.assertAll();
    }
}
