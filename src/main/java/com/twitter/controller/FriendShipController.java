package com.twitter.controller;

import com.twitter.model.ErrorPageModel;
import com.twitter.model.FriendShipEditRequestModel;
import com.twitter.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FriendShipController {

    @Autowired
    private FriendShipService friendShipService;

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView follow(@ModelAttribute FriendShipEditRequestModel request,
                               HttpServletResponse response) {
        ModelAndView res;
        try {
            friendShipService.follow(request);
            return new ModelAndView("success");
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(500);
            res = new ModelAndView("error");
            ErrorPageModel errorPageModel = new ErrorPageModel("Internal Server Error", exception.getMessage());
            res.addObject("error", errorPageModel);
            return res;
        }
    }

    @RequestMapping(value = "/unFollow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView unFollow(@ModelAttribute FriendShipEditRequestModel request,
                                 HttpServletResponse response) {
        ModelAndView res;
        try {
            friendShipService.unFollow(request);
            return new ModelAndView("success");
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(500);
            res = new ModelAndView("error");
            ErrorPageModel errorPageModel = new ErrorPageModel("Internal Server Error", exception.getMessage());
            res.addObject("error", errorPageModel);
            return res;
        }
    }
}
