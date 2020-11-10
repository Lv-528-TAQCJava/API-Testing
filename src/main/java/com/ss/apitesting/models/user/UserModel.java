package com.ss.apitesting.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ss.apitesting.models.pet.Category;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.Tag;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;

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
    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }

        if (!(a instanceof UserModel)) {
            return false;
        }

        return a == null? false : a.equals(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserModel)) {
            return false;
        }
        UserModel u = (UserModel) obj;

        return id.equals(u.id) && username.equals(u.username)
                && firstname.equals(firstname) && lastname.equals(lastname)
                && email.equals(email) && password.equals(u.password)
                && phone.equals(u.phone) && userStatus.equals(u.userStatus);
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
