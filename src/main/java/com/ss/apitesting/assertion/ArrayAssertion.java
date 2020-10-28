package com.ss.apitesting.assertion;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

public class ArrayAssertion extends BaseAssertion {
    Response responseSimple;
    /**
     * Constructor from response without .then()
     */
    public ArrayAssertion(Response response) {
        super(response);
        responseSimple = response;
    }

    /**
     * Asserts status code and content type
     */
    @Override
    public ArrayAssertion defaultAsserts() {
        return (ArrayAssertion) super.defaultAsserts();
    }

    public ArrayAssertion bodyArrayEquals(String bodyParameter, String expectedValue) {
        List<String> list = getBodyArrayValue(bodyParameter);
        for (String actual: list) {
            Assert.assertEquals(actual, expectedValue); //or better SoftAssert?
        }
        return this;
    }

    public ArrayAssertion bodyArrayContains(String bodyParameter, String expectedValue) {
        List<String> list = getBodyArrayValue(bodyParameter);
        for (String actual: list) {
            Assert.assertTrue(actual.contains(expectedValue));
        }
        return this;
    }

    private List<String> getBodyArrayValue(String bodyParameter) {
        List<String> list;
        switch (responseSimple.contentType().toUpperCase()) {
            case "XML":
                list = responseSimple.xmlPath().getList(bodyParameter);
                break;
            case "JSON":
            default:
                list = responseSimple.jsonPath().getList(bodyParameter);
                break;

        }
        return list;
    }

    public ArrayAssertion bodyArraySizeGreater(String bodyParameter, int expectedValue) {
        response.body(bodyParameter + ".size()", greaterThan(expectedValue));
        return this;
    }
}
