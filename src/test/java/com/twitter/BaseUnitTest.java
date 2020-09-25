package com.twitter;

import com.twitter.service.FriendShipService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseUnitTest {

    public ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("ApplicationConfig.xml");

}
