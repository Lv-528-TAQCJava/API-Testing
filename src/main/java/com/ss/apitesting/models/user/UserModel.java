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

    public UserModel(Integer id, String username, String firstname, String lastname, String email,
                    String password, String phone, Integer userStatus) {
        super();
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public UserModel() {
        super();
        this.id = 0;
        this.username = "string";
        this.firstname = "string";
        this.lastname = "string";
        this.email = "string";
        this.password = "string";
        this.phone = "string";
        this.userStatus = 0;
    }

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

}
