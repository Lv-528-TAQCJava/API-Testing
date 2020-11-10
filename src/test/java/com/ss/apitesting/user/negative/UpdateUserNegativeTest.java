package com.ss.apitesting.user.negative;

import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class UpdateUserNegativeTest extends UserBaseTest {
    @Test
    public void updateUserNullPropTest() {
        UserModel updatedModel;
        updatedModel = userModel;
        updatedModel.setFirstname("");
        updatedModel.setLastname("");
        userClient.putUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(userModel.username);
        response.then().body("firstName", is(""));
        response.then().body("lastName", is(""));
        response.then().contentType(ContentType.JSON);
    }
}
