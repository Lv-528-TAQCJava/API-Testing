package com.ss.apitesting.client;

import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
                .urlEncodingEnabled(false)
                .get("/{entity}/{username}");
    }

    public Response putUser(UserModel reqBody, String username) {
        return prepareRequest()
                .body(reqBody)
                .urlEncodingEnabled(false)
                .put("/{entity}/" + username);
    }
/*
    *//**
     * Create one user
     * @param indexOfUser - user's index in TestUserData.json file
     * @return server response
     *//*
    public Response createUser(int indexOfUser){
        return prepareRequest()
                .body(readJson.getUser(indexOfUser))
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }*/

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

   /* *//**
     * Create array of users
     * @return server response
     *//*
    public Response createUserArray(){
        return prepareRequest()
                .body(createUser())
                .urlEncodingEnabled(false)
                .post("/{entity}/createWithArray");
    }*/

    public Response deleteByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username)
                .delete("/{entity}/{username}");
    }
}
