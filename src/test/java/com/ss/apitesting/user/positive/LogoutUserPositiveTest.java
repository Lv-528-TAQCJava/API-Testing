package com.ss.apitesting.user.positive;

import com.ss.apitesting.user.UserBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

public class LogoutUserPositiveTest extends UserBaseTest {
    @Test
    public void getUserLogoutTest() {
        Response response = userClient.getUserLogout();
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        response.then().contentType(ContentType.JSON);
    }
}
