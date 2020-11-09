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

import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Get user logout test suite")
public class GetUserLogoutTest extends UserBaseTest {

    @Test
    public void getUserLogoutTest() {
        Response response = userClient.getUserLogout();
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        response.then().contentType(ContentType.JSON);
    }
}
