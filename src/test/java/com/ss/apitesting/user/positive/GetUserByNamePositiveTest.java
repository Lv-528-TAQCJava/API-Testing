package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import org.testng.annotations.Test;

public class GetUserByNamePositiveTest extends UserBaseTest {
    @Test(description = "Get user with existing username")
    public void getUserByValidUsernameTest(){
        userModel = userClient.createUserData();
        userClient.createNewUser(userModel);

        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(userModel.username));
        assertion.defaultAsserts()
                .bodyValueEquals("id", userModel.id)
                .bodyValueEquals("username", userModel.username)
                .bodyValueEquals("firstName", userModel.firstname)
                .bodyValueEquals("lastName", userModel.lastname)
                .bodyValueEquals("email", userModel.email)
                .bodyValueEquals("password", userModel.password)
                .bodyValueEquals("phone", userModel.phone)
                .bodyValueEquals("userStatus", userModel.userStatus);

        userClient.deleteByUsername(userModel.username).then().statusCode(200);
    }
}
