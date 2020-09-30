package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("customRequestMappingHandlerMapping")
    private RequestMappingHandlerMapping customRequestMappingHandlerMapping;

    @RequestMapping(value = "/mappings", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getAllEndpoints () {
        Map<RequestMappingInfo, HandlerMethod> handlerMapping = customRequestMappingHandlerMapping.getHandlerMethods();
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
}
