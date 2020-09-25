package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Controller
public class EndpointDocController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value = "/mappings", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getAllEndpoints () {
        Map<RequestMappingInfo, HandlerMethod> handlerMapping = requestMappingHandlerMapping.getHandlerMethods();
        Map<String, Set<String>> urlMapping = new HashMap<>();
        ModelAndView mav = new ModelAndView("mapping");
        for (Map.Entry<RequestMappingInfo, HandlerMethod> mapping : handlerMapping.entrySet()) {
            Set<String> urls = mapping.getKey().getPatternsCondition().getPatterns();
            String methodName = mapping.getValue().getMethod().getName();
            urlMapping.put(methodName, urls);
        }
        mav.addObject("urlMapping", urlMapping);
        return mav;
    }

    @CrossOrigin
    @RequestMapping(value = "/JSON/mappings", method = RequestMethod.GET)
    public @ResponseBody Map<String, Set<String>> getAllEndpointsJSON () {
        Map<RequestMappingInfo, HandlerMethod> handlerMapping = requestMappingHandlerMapping.getHandlerMethods();
        Map<String, Set<String>> urlMapping = new HashMap<>();
        ModelAndView mav = new ModelAndView("mapping");
        for (Map.Entry<RequestMappingInfo, HandlerMethod> mapping : handlerMapping.entrySet()) {
            Set<String> urls = mapping.getKey().getPatternsCondition().getPatterns();
            String methodName = mapping.getValue().getMethod().getName();
            urlMapping.put(methodName, urls);
        }
        mav.addObject("urlMapping", urlMapping);
        return urlMapping;
    }
}
