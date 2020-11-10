package com.ss.apitesting.user.positive;

import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Login user positive test suite")
public class LoginUserPositiveTest extends UserBaseTest {

    @Test
    public void getUserLoginTest() {
        Response response = userClient.getUserLogin(userModel.username, userModel.password);
        System.out.println(response.getContentType() + " " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        response.then().contentType(ContentType.JSON);
    }
}
