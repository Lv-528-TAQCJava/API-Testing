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
    private Integer id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("userStatus")
    private Integer userStatus;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
