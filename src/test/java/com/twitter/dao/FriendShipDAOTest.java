package com.twitter.dao;

import com.twitter.model.FriendShipModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FriendShipDAOTest {

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
    public void testFollowAndUnFollow() throws IOException {
        // Get Mapper
        FriendShipDAO dao = session.getMapper(FriendShipDAO.class);
        // Perform query
        dao.addFriendShip("test", "follower");
        dao.removeFriendShip("test", "follower");
    }

    @Test
    public void testGetFollowing() throws IOException {
        // Get Mapper
        FriendShipDAO dao = session.getMapper(FriendShipDAO.class);
        // Perform query
        List<FriendShipModel> user2Following = dao.getFollowing("test");
        //Print results
        System.out.println(user2Following);
    }

    @After
    public void cleanUp() throws IOException {
        // Close resources
        session.close();
        inputStream.close();
    }
}
