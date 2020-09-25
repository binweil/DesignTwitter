package com.twitter.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EndpointDocControllerTest {

    @Mock
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Mock
    private EndpointDocController endpointDocController;

    private MockMvc mockMvc;

    @Before
    public void initialize () {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(endpointDocController).build();
    }

    @Test
    public void testGetAllEndpoints() throws Exception {
        Map<RequestMappingInfo, HandlerMethod> mappings = new HashMap<>();
        when(this.requestMappingHandlerMapping.getHandlerMethods())
                .thenReturn(mappings);
        this.mockMvc.perform(get("/mappings"))
                .andExpect(status().is(200));
    }
}
