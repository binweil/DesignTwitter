package com.twitter.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class AuthenticationDAOTest {

    private SqlSessionFactory factory;

    private InputStream inputStream;

    private SqlSession session;

    private AuthenticationDAO authenticationDAO;

    @Before
    public void initialize() throws IOException {
        // Load configuration file
        inputStream = Resources.getResourceAsStream("SqlMappingForTest.xml");
        // Create SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        // Create Session
        this.session = factory.openSession();
        authenticationDAO = session.getMapper(AuthenticationDAO.class);
    }

    @Test
    public void testGetPassword() throws NotFoundException {
        authenticationDAO.getPassword("test");
    }

    @Test
    public void testIsUserExist() throws NotFoundException {
        authenticationDAO.isUserExist("test");
    }

    @After
    public void cleanUp() throws IOException {
        // Close resources
        session.close();
        inputStream.close();
    }
}
