package com.ss.apitesting.data;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class UserRepository {

    public static UserModel createUserData() {
        int userId = generateId();
        return UserBuilder.userWith()
                .id(userId)
                .username("Username" + userId)
                .firstname("User" + userId + "firstname")
                .lastname("User" + userId + "lastname")
                .email("User" + userId + "@gmail.com")
                .password("User" + userId + "password")
                .phone("+380" + RandomStringUtils.randomAlphabetic(9))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
    }

    public static UserModel createUserWithoutPassword() {
        int userId = generateId();
        return UserBuilder.userWith()
                .id(userId)
                .username("Username" + userId)
                .firstname("User" + userId + "firstname")
                .lastname("User" + userId + "lastname")
                .email("User" + userId + "@gmail.com")
                .phone("+380" + RandomStringUtils.randomAlphabetic(9))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
    }

    public static List<UserModel> createListOfUsers(int numberOfUser){
        List<UserModel> userList= new ArrayList<>();
        for(int i = 0; i < numberOfUser; i++){
            userList.add(createUserData());
        }
        return userList;
    }

}
