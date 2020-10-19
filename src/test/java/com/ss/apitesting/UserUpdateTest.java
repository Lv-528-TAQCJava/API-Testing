package com.ss.apitesting;
import com.ss.apitesting.client.UserClient;
import org.junit.Ignore;
import org.junit.Test;
import org.testng.annotations.DataProvider;

public class UserUpdateTest {


    @DataProvider
    public static Object[][] usernameValue() {
        return new Object[][] {
                {857504, "user1", "Christophe", "Dur", "string", "string", "string", 0}};
    }
    @Ignore
    @Test
    public void userPutTest(int id, String username,String firstName, String lastName, String email, String password, String phone, int userStatus) {
        UserClient userClient = new UserClient("json");
        String body = userClient.updateUser(id, username, firstName, lastName, email, password, phone, userStatus);
        userClient.putUser(body).then()
                .statusCode(200);
    }
}
