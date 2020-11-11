package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

@Epic("Operation about user tests")
@Feature("Login user negative test suite")
public class LoginUserNegativeTest extends UserBaseTest {

    @Test
    public void getUserLoginInvalidPasswordTest() {
        Response response = userClient.getUserLogin(userModel.username, "111111");
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        BaseAssertion baseAssertion = new BaseAssertion(response);
        baseAssertion.contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidUsernameTest() {
        Response response = userClient.getUserLogin("someInvalidUsername", userModel.password);
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        BaseAssertion baseAssertion = new BaseAssertion(response);
        baseAssertion.contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidCredentialsTest() {
        Response response = userClient.getUserLogin("someInvalidUsername", "someInvalidPassword");
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        BaseAssertion baseAssertion = new BaseAssertion(response);
        baseAssertion.contentType(ContentType.JSON);
    }
}
