package com.twitter.service.Impl;


import com.twitter.dao.FriendShipDAO;
import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("friendShipService")
public class FriendShipServiceImpl implements FriendShipService {

    @Autowired
    private FriendShipDAO friendShipDAO;

    @Override
    public void follow (FriendShipEditRequestModel request) {
        friendShipDAO.addFriendShip(request.getFromUserID(), request.getToUserID());
    }

    @Override
    public void unFollow (FriendShipEditRequestModel request) {
        friendShipDAO.removeFriendShip(request.getFromUserID(), request.getToUserID());
    }
}
