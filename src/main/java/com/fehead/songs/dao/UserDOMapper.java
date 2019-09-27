package com.fehead.songs.dao;

import com.fehead.songs.dataobject.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}