package com.ss.apitesting.user.negative;

import com.ss.apitesting.user.UserBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class LoginUserNegativeTest extends UserBaseTest {
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
