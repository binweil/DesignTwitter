package com.twitter.model;

public class UserInfoModel {

    public String username;

    public String id;

    public String name;

    public Integer age;

    public String region;

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", region='" + region + '\'' +
                '}';
    }
}
