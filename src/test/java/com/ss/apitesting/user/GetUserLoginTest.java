package com.ss.apitesting.user;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Get user login test suite")
public class GetUserLoginTest extends UserBaseTest {

    @Test
    public void getUserLoginTest() {
        Response response = userClient.getUserLogin(userModel.username, userModel.password);
        System.out.println(response.getContentType() + " " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        response.then().contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidPasswordTest() {
        Response response = userClient.getUserLogin(userModel.username, "111111");
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        response.then().contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidUsernameTest() {
        Response response = userClient.getUserLogin("someInvalidUsername", userModel.password);
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        response.then().contentType(ContentType.JSON);
    }
    @Test
    public void getUserLoginInvalidCredentialsTest() {
        Response response = userClient.getUserLogin("someInvalidUsername", "someInvalidPassword");
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        response.then().contentType(ContentType.JSON);
    }


}
