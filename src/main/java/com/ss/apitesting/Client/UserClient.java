package com.ss.apitesting.client;

import com.ss.apitesting.util.ReadJson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class UserClient extends BaseClient {
    public UserClient(ContentType contentType) {
        super(contentType, "user");

    }

    public UserClient(String contentType) {
        super(contentType, "user");

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

    public Response putUser(String reqBody) {
        return prepareRequest()
                .body(reqBody)
                .urlEncodingEnabled(false)
                .put("/{entity}/{username}");
    }

    public String updateUser(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        JSONObject reqParams = new JSONObject();
        reqParams.put("id", Integer.toString(id));
        reqParams.put("username", username);
        reqParams.put("firstName", firstName);
        reqParams.put("lastName", lastName);
        reqParams.put("email", email);
        reqParams.put("password", password);
        reqParams.put("phone", phone);
        reqParams.put("userStatus", Integer.toString(userStatus));

        return reqParams.toJSONString();
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
