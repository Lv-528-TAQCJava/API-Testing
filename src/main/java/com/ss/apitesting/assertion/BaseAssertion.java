package com.ss.apitesting.assertion;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
     * Asserts status code (200) and content type (JSON)
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

    /**
     * Asserts that the value written in the response body equals the expected one
     * Use only this for XML comparison
     */
    public BaseAssertion bodyValueEquals(String bodyParameter, String expectedValue) {
        response.body(bodyParameter, equalTo(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Integer expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Double expectedValue) { //Maybe add delta?
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueEquals(String bodyParameter, Boolean expectedValue) {
        response.body(bodyParameter, is(expectedValue));
        return this;
    }

    public BaseAssertion bodyValueContains(String bodyParameter, String expectedValue) {
        response.body(bodyParameter, containsString(expectedValue));
        return this;
    }

    public BaseAssertion bodyHasParameter(String bodyParameter) {
        response.body("$", hasKey(bodyParameter));
        return this;
    }

    public BaseAssertion bodyNoParameter(String bodyParameter) {
        response.body("$", not(hasKey(bodyParameter)));
        return this;
    }

    /**
     * For further assertions with fluid interface, not defined here but present in ValidatableResponse
     */
    public ValidatableResponse assertAnother() {
        return response;
    }
}
