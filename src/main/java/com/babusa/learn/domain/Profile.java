package com.babusa.learn.domain;

import java.util.Arrays;

public class Profile {
    private String userName;
    private String[] userHobbies;

    public Profile(String userName, String[] userHobbies) {
        this.userName = userName;
        this.userHobbies = userHobbies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getUserHobbies() {
        return userHobbies;
    }

    public void setUserHobbies(String[] userHobbies) {
        this.userHobbies = userHobbies;
    }

    public String getUserHobbiesList() {
        return  Arrays.toString(userHobbies);
    }
}
