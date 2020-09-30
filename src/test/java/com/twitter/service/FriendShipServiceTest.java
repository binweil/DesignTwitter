package com.twitter.service;

import com.twitter.BaseUnitTest;
import com.twitter.dao.FriendShipDAO;
import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.Impl.FriendShipServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;

public class FriendShipServiceTest {

    @Mock
    private FriendShipDAO friendShipDAO;

    @InjectMocks
    private FriendShipService friendShipService = new FriendShipServiceImpl();

    @Before
    public void initialize() {
        initMocks(this);
    }

    @Test
    public void testFollowAndUnFollow() {
        FriendShipEditRequestModel request = new FriendShipEditRequestModel("test","following");
        doNothing().when(friendShipDAO).addFriendShip(any(String.class), any(String.class));
        doNothing().when(friendShipDAO).removeFriendShip(any(String.class), any(String.class));
        friendShipService.follow(request);
        friendShipService.unFollow(request);
    }
}
