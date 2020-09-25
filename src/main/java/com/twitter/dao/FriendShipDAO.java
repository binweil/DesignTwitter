package com.twitter.dao;

import com.twitter.model.FriendShipModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendShipDAO {

    @Select("INSERT into friendShipTable (userID, following) VALUES ('test', 'testFollowing')")
    public void addFriendShip(String fromUserID, String toUserID);

    @Select("DELETE from friendShipTable WHERE (userID=#{fromUserID} AND following=#{toUserID})")
    public void removeFriendShip(@Param("fromUserID") String fromUserID,
                                 @Param("toUserID") String toUserID);

    @Select("SELECT * FROM friendShipTable WHERE (userID=#{userID})")
    public List<FriendShipModel> getFollowing (@Param("userID") String userID);
}
