package com.ss.apitesting.assertion;

import com.ss.apitesting.models.user.UserModel;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class UserAssertions {
    public static void assertBodyEquals(Response response, UserModel expected) {
        SoftAssert softAssert = new SoftAssert();
        UserModel actual = response.as(UserModel.class);
        softAssert.assertTrue(UserModel.equals(actual, expected));
        softAssert.assertAll();
    }
    public static void assertFalseBodyEquals(Response response, UserModel expected) {
        SoftAssert softAssert = new SoftAssert();
        UserModel actual = response.as(UserModel.class);
        softAssert.assertFalse(UserModel.equals(actual, expected));
        softAssert.assertAll();
    }
}
