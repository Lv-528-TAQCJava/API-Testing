package com.ss.apitesting.assertion;

import com.ss.apitesting.models.user.UserModel;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class UserAssertions {

    public static void assertBodyEquals(Response response, UserModel expected) {
        SoftAssert softAssert = new SoftAssert();
        UserModel actual = response.as(UserModel.class);
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
    }

    public static void assertBodyNotEquals(Response response, UserModel expected) {
        SoftAssert softAssert = new SoftAssert();
        UserModel actual = response.as(UserModel.class);
        softAssert.assertNotEquals(actual, expected);
        softAssert.assertAll();
    }
}
