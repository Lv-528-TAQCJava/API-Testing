package com.ss.apitesting.assertion;

import com.ss.apitesting.models.pet.PetModel;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;


public class PetAssertions {

    public static void assertBodyEquals(Response response, PetModel expected) {
        SoftAssert softAssert = new SoftAssert();
        PetModel actual = response.as(PetModel.class);
        softAssert.assertTrue(PetModel.equals(actual, expected));
        softAssert.assertAll();
    }
}
