package com.twitter.service;

import com.twitter.BaseUnitTest;
import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.Impl.FriendShipServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class FriendShipServiceImplTest extends BaseUnitTest {

    private FriendShipService friendShipService;

    @Before
    public void initialize() {
        friendShipService =
                (FriendShipService) applicationContext.getBean("friendShipService");
    }

    @Test
    public void testFollowAndUnFollow() {
        FriendShipEditRequestModel request = new FriendShipEditRequestModel("test","following");
        friendShipService.follow(request);
        friendShipService.unFollow(request);
    }
}
