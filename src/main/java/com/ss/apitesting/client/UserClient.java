package com.ss.apitesting.client;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.util.ValuesGenerator;
import com.sun.codemodel.JForEach;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.List;

import static com.ss.apitesting.util.ValuesGenerator.generateId;
import static java.net.HttpURLConnection.HTTP_OK;

public class UserClient extends BaseClient {

    public UserClient(ContentType contentType) {
        super(contentType, "user");
    }

    public UserClient(String contentType) {
        super(contentType, "user");
    }

    public UserClient() {
        super(ContentType.JSON, "user");
    }

    /**
     *
     * @param username Allows find user vy username (for POST functionality)
     * @return
     */
    public Response getByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username )
                .get("/{entity}/{username}");
    }

    public Response putUser(UserModel reqBody, String username) {
        return prepareRequest()
                .body(reqBody)
                .put("/{entity}/" + username);
    }

    /**
     * Create new user
     * @param userModel
     * @return
     */
    public Response createNewUser(UserModel userModel) {
        return prepareRequest()
                .body(userModel)
                .post("/{entity}");
    }

    public Response createUserArray(List<UserModel> userList){
        return prepareRequest()
                .body(userList)
                .post("/{entity}/createWithArray");
    }

    public Response deleteByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username)
                .delete("/{entity}/{username}");
    }
    private UserModel userModel;


    public UserModel createUserData() {
        int userId = generateId();
        userModel = UserBuilder.userWith()
                .id(userId)
                .username("Username" + userId)
                .firstname("User" + userId + "firstname")
                .lastname("User" + userId + "lastname")
                .email("User" + userId + "email" + "@gmail.com")
                .password("User" + userId + "password")
                .phone("+380" + RandomStringUtils.randomAlphabetic(9))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
        return userModel;
    }

    public List<UserModel> createUserList(int numberOfUser){
        List<UserModel> userList= new ArrayList<>();
        for(int i = 0; i < numberOfUser; i++){
            userList.add(createUserData());
        }
        return userList;
    }
}
