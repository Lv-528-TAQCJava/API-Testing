package com.ss.apitesting.Client;

import com.ss.apitesting.Util.ReadJson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserClient extends BaseClient {
    public UserClient(ContentType contentType) {
        super(contentType, "user");
    }

    public UserClient(String contentType) {
        super(contentType, "user");
    }

    /**
     * Create one user
     * @param indexOfUser - user's index in TestUserData.json file
     * @return server response
     */
    public Response createUser(int indexOfUser){
        ReadJson readJson = new ReadJson();
        return prepareRequest()
                .body(readJson.getUser(indexOfUser))
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }
    /**
     * Create array of users
     * @return server response
     */
    public Response createUserArray(){
        ReadJson readJson = new ReadJson();
        return prepareRequest()
                .body(readJson.getArrayOfUsers())
                .urlEncodingEnabled(false)
                .post("/{entity}/createWithArray");
    }
}

