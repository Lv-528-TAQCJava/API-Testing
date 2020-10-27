package com.ss.apitesting.assertion;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.contains;

public class BaseAssertion {
    protected ValidatableResponse response;

    /**
     * Constructor from response without .then()
     */
    public BaseAssertion(Response response) {
        this.response = response.then();
    }

    /**
     * Constructor from response.then()
     */
    public BaseAssertion(ValidatableResponse response) {
        this.response = response;
    }

    /**
     * Asserts status code and content type
     */
    public BaseAssertion defaultAsserts() {
        return statusCode(200)
                .contentType(ContentType.JSON);
    }

    public BaseAssertion statusCode(int httpStatus) {
        response.statusCode(httpStatus);
        return this;
    }

    public BaseAssertion contentType(ContentType type) {
        response.contentType(type);
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, String expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Integer expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Double expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Boolean expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueContains(String bodyParameter, String expectedValue) {
        response.body(bodyParameter, contains(expectedValue));
        return this;
    }
}
