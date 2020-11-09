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
@Feature("Delete user test suite")
public class DeleteUserTest extends UserBaseTest {

    @Test
    public void deleteUserTest() {
        Response response = userClient.deleteByUsername(userModel.username);
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
    }

    @Test
    public void deleteUserWithInvalidUsernameTest() {
        Response response = userClient.deleteByUsername("111");
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        Assert.assertEquals(response.getContentType(), "");
    }
}
