package com.twitter.model;

public class FriendShipModel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    private String following;

    @Override
    public String toString() {
        return "FriendShipModel{" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", following='" + following + '\'' +
                '}';
    }
}
