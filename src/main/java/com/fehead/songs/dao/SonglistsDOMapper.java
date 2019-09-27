package com.fehead.songs.dao;

import com.fehead.songs.dataobject.SonglistsDO;
import org.apache.ibatis.annotations.Param;

import javax.swing.*;
import java.util.List;

public interface SonglistsDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SonglistsDO record);

    int insertSelective(SonglistsDO record);

    SonglistsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SonglistsDO record);

    int updateByPrimaryKeyWithBLOBs(SonglistsDO record);

    int updateByPrimaryKey(SonglistsDO record);

    List<SonglistsDO> selectAllListByUserId(Integer userId);

    List<SonglistsDO> getAllListsStatus_1();

    void updateStatusByListId(Integer listId,Integer status);

    List<SonglistsDO> selectStatus(Integer steps, @Param("user_id")Integer userId);

    List<SonglistsDO> selectAllLists();
}