package com.twitter.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserInfoDAOTest {

    private SqlSessionFactory factory;

    private InputStream inputStream;

    private SqlSession session;

    @Before
    public void initialize() throws IOException {
        // Load configuration file
        inputStream = Resources.getResourceAsStream("SqlMappingForTest.xml");
        // Create SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        // Create Session
        this.session = factory.openSession();
    }

    @Test
    public void testGetUserInfo() throws IOException, NotFoundException {
        // Get Mapper
        UserInfoDAO dao = session.getMapper(UserInfoDAO.class);
        // Perform query
        System.out.println(dao.getUserInfo("Lamy"));
    }
}
