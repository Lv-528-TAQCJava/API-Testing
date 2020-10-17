package com.ss.apitesting.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class StoreClient extends BaseClient {
    public StoreClient(ContentType contentType) {
        super(contentType, "store");    //it would be great to use store/order,
                                            // but slashes are bad encoded then
    }

    public StoreClient(String contentType) {
        super(contentType, "store");
    }

    public Response postOrder(String requestBody) {
        return prepareRequest()
                .body(requestBody)
                .post("/{entity}/order");
    }

    /**
     * Provides JSON string for postOrder method parameter
     */
    public String prepareOrder(int id, int petId, int quantity,
                               String shipDate, String status, Boolean complete) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", Integer.toString(id));
        requestParams.put("petId", Integer.toString(petId));
        requestParams.put("quantity", Integer.toString(quantity));
        requestParams.put("shipDate", shipDate);
        requestParams.put("status", status);
        requestParams.put("complete", complete);

        return requestParams.toJSONString();
    }

    @Override //should be overridden due to store/order in URL
    public Response getById(String id) {
        return prepareRequest()
                .pathParam("id", id)
                .get("/{entity}/order/{id}");
    }

    //public Response deleteById(String id) - should be overridden due to store/order in URL
}
