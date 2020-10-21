package com.ss.apitesting.builder;

import com.ss.apitesting.models.user.UserModel;

/**
 * Gives a possibility to get a new user with wanted properties
 * For example:
 *          UserModel user = UserBuilder.userWith().id(5).username("Trevis").password("qwerty").build();
 */
public class UserBuilder {
    private UserModel user;

    private UserBuilder() {
        user = new UserModel();
    }

    /**
     * Starts building
     * @return builder with possibility to set properties
     */
    public static UserBuilder userWith() {
        return new UserBuilder();
    }

    public UserBuilder id(Integer id){
        user.id = id;
        return this;
    }

    public UserBuilder username(String username){
        user.username = username;
        return this;
    }

    public UserBuilder firstname(String firstname){
        user.firstname = firstname;
        return this;
    }

    public UserBuilder lastname(String lastname){
        user.lastname = lastname;
        return this;
    }

    public UserBuilder email(String email){
        user.email = email;
        return this;
    }

    public UserBuilder password(String password){
        user.password = password;
        return this;
    }

    public UserBuilder phone(String phone){
        user.phone = phone;
        return this;
    }

    public UserBuilder userStatus(Integer userStatus){
        user.userStatus = userStatus;
        return this;
    }

    /**
     * Final method
     * @return constructed user. If user was not constructed, then returns default user.
     */
    public UserModel build() {
        return user;
    }
}
