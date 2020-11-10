package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import org.testng.annotations.Test;

public class CreateUserNegativeTest extends UserBaseTest {
    @Test(description = "Create user without password")
    public void createUserWithoutPasswordTest(){
        userModel = userClient.createUserWithoutPassword();
        BaseAssertion assertion = new BaseAssertion(userClient.createNewUser(userModel));
        assertion.defaultAsserts();
        BaseAssertion bodyAssertion = new BaseAssertion(userClient.getByUsername(userModel.username));
        bodyAssertion.defaultAsserts().bodyValueEquals("password", "string");
    }
}
