package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.assertion.UserAssertions;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation about user tests")
@Feature("Update user negative test suite")
public class UpdateUserNegativeTest extends UserBaseTest {

    @DataProvider(name = "updateUserData")
    public Object[][] data() {
        return new Object[][]{
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname("Drake")
                                .lastname("Black")
                                .email("drakeblack@gmail.com")
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname("Black")
                                .email("drakeblack@gmail.com")
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname(null)
                                .email("drakeblack@gmail.com")
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname(null)
                                .email(null)
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname(null)
                                .email(null)
                                .password(null)
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname(null)
                                .email(null)
                                .password(null)
                                .phone(null)
                                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                                .build()

                },
                {
                        UserBuilder.userWith()
                                .id(null)
                                .username("DrakeBlack")
                                .firstname(null)
                                .lastname(null)
                                .email(null)
                                .password(null)
                                .phone(null)
                                .userStatus(null)
                                .build()

                },
        };
    }

    @Test(dataProvider = "updateUserData")
    public void updateUserNullPropTest(UserModel updatedModel) {
        userClient.updateUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(updatedModel.username);
        BaseAssertion updateAssertion = new BaseAssertion(response);
        updateAssertion.statusCode(200);
        UserAssertions.assertFalseBodyEquals(response, updatedModel);
    }
}
