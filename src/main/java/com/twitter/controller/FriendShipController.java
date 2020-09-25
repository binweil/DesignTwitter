package com.twitter.controller;

import com.twitter.model.ErrorPageModel;
import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendShipController {

    @Autowired
    private FriendShipService friendShipService;

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView follow(@ModelAttribute FriendShipEditRequestModel request) {
        ModelAndView res;
        try {
            String fromUserID = request.getFromUserID();
            String toUserID = request.getToUserID();
            System.out.println(fromUserID + "\t" + toUserID);
            friendShipService.follow(request);
            return new ModelAndView("success");
        } catch (Exception exception) {
            exception.printStackTrace();
            res = new ModelAndView("error");
            ErrorPageModel errorPageModel = new ErrorPageModel("Internal Server Error", exception.getMessage());
            res.addObject("error", errorPageModel);
            return res;
        }
    }

    @RequestMapping(value = "/unFollow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView unFollow(@ModelAttribute FriendShipEditRequestModel request) {
        ModelAndView res;
        try {
            String fromUserID = request.getFromUserID();
            String toUserID = request.getToUserID();
            System.out.println(fromUserID + "\t" + toUserID);
            return new ModelAndView("success");
        } catch (Exception exception) {
            exception.printStackTrace();
            res = new ModelAndView("error");
            ErrorPageModel errorPageModel = new ErrorPageModel("Internal Server Error", exception.getMessage());
            res.addObject("error", errorPageModel);
            return res;
        }
    }
}
