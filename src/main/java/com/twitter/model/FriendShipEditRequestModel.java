package com.twitter.model;

public class FriendShipEditRequestModel {

    private String fromUserID;

    private String toUserID;

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }

    public FriendShipEditRequestModel(String fromUserID, String toUserID) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
    }

    @Override
    public String toString() {
        return "FollowRequestModel{" +
                "fromUserID='" + fromUserID + '\'' +
                ", toUserID='" + toUserID + '\'' +
                '}';
    }
}
