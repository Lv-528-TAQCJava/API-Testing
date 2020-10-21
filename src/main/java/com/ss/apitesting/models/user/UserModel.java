package com.ss.apitesting.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "username",
        "firstname",
        "lastname",
        "email",
        "password",
        "phone",
        "userStatus"
})
public class UserModel {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("firstname")
    public String firstname;
    @JsonProperty("lastname")
    public String lastname;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("userStatus")
    public Integer userStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("email", email)
                .append("password", password)
                .append("phone", phone)
                .append("userStatus", userStatus)
                .toString();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
