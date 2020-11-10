package com.ss.apitesting.models.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserModel {

    public Integer id;
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String phone;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username){ this.username = username;}

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
