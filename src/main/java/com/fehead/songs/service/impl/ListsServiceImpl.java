package com.fehead.songs.service.impl;

import com.fehead.songs.controller.vo.ListDetailVO;
import com.fehead.songs.controller.vo.ListsVO;
import com.fehead.songs.dao.SonglistsDOMapper;
import com.fehead.songs.dao.UserDOMapper;
import com.fehead.songs.dataobject.SonglistsDO;
import com.fehead.songs.dataobject.UserDO;
import com.fehead.songs.model.ListsModel;
import com.fehead.songs.service.ListsService;
import com.github.pagehelper.PageHelper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ListsServiceImpl implements ListsService {

    @Autowired
    private SonglistsDOMapper songlistsDOMapper;
    @Autowired
    private UserDOMapper userDOMapper;


    /**
     * 通过userId查所有ListModel
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<ListsModel> selectAllListByUserId(Integer userId,Integer page,Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ListsModel> listsModelList=new ArrayList<>();
        List<SonglistsDO> songlistsDOList=songlistsDOMapper.selectAllListByUserId(userId);
        UserDO userDO=userDOMapper.selectByPrimaryKey(userId);
        for (SonglistsDO songlistsDO:songlistsDOList) {
            ListsModel listsModel=new ListsModel();
            listsModel=convertFromTwoObject(songlistsDO,userDO);
            listsModel.setPostDate(new DateTime(songlistsDO.getPostDate()));
            listsModel.setWantDay(new DateTime(songlistsDO.getWantDay()));
            listsModelList.add(listsModel);
        }

        return listsModelList;
    }

    /**
     * 返回所有状态为1的Model列表
     * @return
     */
    @Override
    public List<ListsModel> getAllListsStatus_1() {
        List<ListsModel> listsModelList=new ArrayList<>();
        List<SonglistsDO> songlistsDOList=songlistsDOMapper.getAllListsStatus_1();
        for (SonglistsDO songlistsDO:songlistsDOList) {
            ListsModel listsModel=new ListsModel();
            Integer userId=songlistsDO.getUserId();
            UserDO userDO=userDOMapper.selectByPrimaryKey(userId);
            listsModel=convertFromTwoObject(songlistsDO,userDO);
            listsModel.setPostDate(new DateTime(songlistsDO.getPostDate()));
            listsModel.setWantDay(new DateTime(songlistsDO.getWantDay()));
            listsModelList.add(listsModel);
        }
        return listsModelList;
    }



    @Override
    @Cacheable(cacheNames="getListInfoByListId", key="#listId")
    public ListDetailVO getListInfoByListId(Integer listId) {
        SonglistsDO songlistsDO=songlistsDOMapper.selectByPrimaryKey(listId);
        UserDO userDO=userDOMapper.selectByPrimaryKey(songlistsDO.getUserId());
        ListsModel listsModel=this.convertFromTwoObject(songlistsDO,userDO);
        listsModel.setPostDate(new DateTime(songlistsDO.getPostDate()));
        listsModel.setWantDay(new DateTime(songlistsDO.getWantDay()));
        ListDetailVO listDetailVO=new ListDetailVO();
        BeanUtils.copyProperties(listsModel,listDetailVO);
        listDetailVO.setPostDate(listsModel.getPostDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        listDetailVO.setWantDay(listsModel.getWantDay().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        return listDetailVO;
    }

    /**
     * 订单插入发布返回一个ListDetailVO
     * @param listsModel
     * @return
     */
    @Override
    public ListDetailVO insertList(ListsModel listsModel) {
        SonglistsDO songlistsDO=new SonglistsDO();
        BeanUtils.copyProperties(listsModel,songlistsDO);
        songlistsDO.setPostDate(listsModel.getPostDate().toDate());
        songlistsDO.setWantDay(listsModel.getWantDay().toDate());

        songlistsDOMapper.insertSelective(songlistsDO);
        UserDO userDO=userDOMapper.selectByPrimaryKey(songlistsDO.getUserId());
        ListDetailVO listDetailVO=new ListDetailVO();

        listDetailVO.setUserDO(userDO);
        BeanUtils.copyProperties(songlistsDO,listDetailVO);
        listDetailVO.setPostDate(new DateTime(songlistsDO.getPostDate()).toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        listDetailVO.setWantDay(new DateTime(songlistsDO.getWantDay()).toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        return listDetailVO;
    }

    /**
     * 通过listId查找订单修改里面的状态值status
     * @param listId
     * @param status
     */
    @Override
    public void updateListStatus(Integer listId, Integer status) {
        SonglistsDO songlistsDO=new SonglistsDO();
        songlistsDO.setId(listId);
        songlistsDO.setSteps(status);
        songlistsDOMapper.updateByPrimaryKeySelective(songlistsDO);
    }

    @Override
    public void updateList(ListsModel listsModel) {
        UserDO userDO=listsModel.getUserDO();
        SonglistsDO songlistsDO=new SonglistsDO();
        BeanUtils.copyProperties(listsModel,songlistsDO);
        songlistsDOMapper.updateByPrimaryKeySelective(songlistsDO);
    }

    @Override
    public List<ListsModel> selectStatus(Integer status,Integer userId) {
       List<SonglistsDO> songlistsDOList=songlistsDOMapper.selectStatus(status,userId);
       List<ListsModel> listsModelList=new ArrayList<>();
        for (SonglistsDO sl:songlistsDOList) {
            ListsModel listsModel=new ListsModel();
            BeanUtils.copyProperties(sl,listsModel);
            listsModel.setWantDay(new DateTime(sl.getWantDay()));
            listsModel.setPostDate(new DateTime(sl.getPostDate()));
            listsModelList.add(listsModel);
        }
        return listsModelList;
    }

    @Override
    public List<SonglistsDO> selectAllLists() {
        List<SonglistsDO> songlistsDOList=songlistsDOMapper.selectAllLists();
        return songlistsDOList;
    }


    /**
     * 将ListsModel转化为ListsVO
     * @param listsModel
     * @return
     */
    public ListsVO convertFromListsModel(ListsModel listsModel){
        ListsVO listsVO=new ListsVO();
        BeanUtils.copyProperties(listsModel,listsVO);
        return listsVO;
    }

    /**
     * 将SonglistsDO与UserDO转化为ListsModel
     * @param songlistsDO
     * @param userDO
     * @return
     */
    public ListsModel convertFromTwoObject(SonglistsDO songlistsDO,UserDO userDO){
        ListsModel listsModel=new ListsModel();
        listsModel.setUserDO(userDO);
        BeanUtils.copyProperties(songlistsDO,listsModel);
        return listsModel;
    }
}
