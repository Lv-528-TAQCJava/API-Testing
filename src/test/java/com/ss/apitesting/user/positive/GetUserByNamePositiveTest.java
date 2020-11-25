package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.assertion.UserAssertions;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import com.ss.apitesting.util.ValuesGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ss.apitesting.builder.UserBuilder.userWith;

@Epic("Operation about user tests")
@Feature("Get user by username positive test suite")
public class GetUserByNamePositiveTest extends UserBaseTest {

    private UserModel currentUser;

    @DataProvider(name = "validUsers")
    public Object[] data() {
        return new Object[]{
                userWith().id(ValuesGenerator.generateId(1000, 10000))
                        .username("karl")
                        .email("karlovich@gmail.com")
                        .password("djkassjak")
                        .phone("0680085423")
                        .build(),

                userWith().id(ValuesGenerator.generateId(1000, 10000))
                        .username("null")
                        .email("nullovich@gmail.com")
                        .password("djkassjak")
                        .phone("")
                        .build()
        };
    }

    @Test(dataProvider = "validUsers", description = "Getting user by name test")
    public void getUserTest(UserModel user) {
        currentUser = user;
        Response created = userClient.createNewUser(user);
        BaseAssertion assertCreation = new BaseAssertion(created);
        assertCreation.statusCode(200);

        Response given = userClient.getByUsername(user.username);
        UserAssertions.assertBodyEquals(given, user);
    }

    @AfterMethod
    public void removeUser() {
        userClient.deleteByUsername(currentUser.username);
    }
}
