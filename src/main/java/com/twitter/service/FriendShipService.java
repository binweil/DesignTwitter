package com.twitter.service;

import com.twitter.model.FriendShipEditRequestModel;

public interface FriendShipService {

    public void follow (FriendShipEditRequestModel request);

    public void unFollow (FriendShipEditRequestModel request);

}
