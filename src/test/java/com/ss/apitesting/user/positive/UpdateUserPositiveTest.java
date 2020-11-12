package com.ss.apitesting.user.positive;

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

import static com.ss.apitesting.builder.UserBuilder.userWith;

@Epic("Operation about user tests")
@Feature("Update user positive test suite")
public class UpdateUserPositiveTest extends UserBaseTest {


    @DataProvider(name = "updateUserData")
    public Object[][] data(){
        return new Object[][]{
                {
                        userWith()
                                .id(123450)
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
                        userWith()
                                .id(879402)
                                .username("SpongeBob")
                                .firstname("Bob")
                                .lastname("Square")
                                .email("bobbb@gmail.com")
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                                .build()
                },
                {
                        userWith()
                                .id(777777)
                                .username("JimCarry1")
                                .firstname("Jim")
                                .lastname("Carry")
                                .email("jimcarrrry@gmail.com")
                                .password(RandomStringUtils.randomAlphanumeric(6))
                                .build()
                },
                {
                        userWith()
                                .id(777778)
                                .username("JimCarry2")
                                .firstname("Jim")
                                .lastname("Carry")
                                .email("jimcarrrry@gmail.com")
                                .build()
                },
                {
                        userWith()
                                .id(777779)
                                .username("JimCarry3")
                                .firstname("Jim")
                                .lastname("Carry")
                                .build()
                },
                {
                        userWith()
                                .id(777788)
                                .username("JimCarry4")
                                .firstname("Jim")
                                .build()
                },
                {
                        userWith()
                                .id(777555)
                                .username("JimCarry5")
                                .build()
                }
        };
    }

    @Test(dataProvider = "updateUserData")
    public void updateUserTest(UserModel updatedModel) {
        userClient.updateUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(updatedModel.username);
        BaseAssertion updateAssertion = new BaseAssertion(response);
        updateAssertion.statusCode(200);
        UserAssertions.assertBodyEquals(response, updatedModel);
    }
}
