package com.ss.apitesting.user.positive;

import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class UpdateUserPositiveTest extends UserBaseTest {
    @Test
    public void updateUserTest() {
        UserModel updatedModel;
        updatedModel = userModel;
        updatedModel.setFirstname("Drake");
        userClient.putUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(userModel.username);
        response.then().body("firstName", is("Drake"));
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);
    }
}
