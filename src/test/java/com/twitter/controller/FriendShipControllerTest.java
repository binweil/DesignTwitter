package com.twitter.controller;

import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.FriendShipService;
import com.twitter.utils.SerializationUtils;

import org.junit.Before;
import org.junit.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FriendShipControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FriendShipService friendShipService;

    @InjectMocks
    private FriendShipController friendShipController;

    @Before
    public void initialize() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(friendShipController).build();
    }

    @Test
    public void testFollowAndUnFollow () throws Exception {
        FriendShipEditRequestModel friendShipEditRequestModel =
                new FriendShipEditRequestModel("test", "following");
        doNothing()
                .when(this.friendShipService)
                .follow(any(FriendShipEditRequestModel.class));
        this.mockMvc.perform(post("/follow")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(SerializationUtils.serialize(friendShipEditRequestModel)))
                .andExpect(status().is(200));
        this.mockMvc.perform(post("/unFollow")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(SerializationUtils.serialize(friendShipEditRequestModel)))
                .andExpect(status().is(200));
    }

    @Test
    public void testFollowAndUnFollowException () throws Exception {
        FriendShipEditRequestModel friendShipEditRequestModel =
                new FriendShipEditRequestModel("test", "following");
        doThrow(Exception.class)
                .when(this.friendShipService)
                .follow(any(FriendShipEditRequestModel.class));
        doThrow(Exception.class)
                .when(this.friendShipService)
                .unFollow(any(FriendShipEditRequestModel.class));
        this.mockMvc.perform(post("/follow")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(SerializationUtils.serialize(friendShipEditRequestModel)))
                .andExpect(status().is(500));
        this.mockMvc.perform(post("/unFollow")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(SerializationUtils.serialize(friendShipEditRequestModel)))
                .andExpect(status().is(500));
    }

}
