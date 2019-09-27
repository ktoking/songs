package com.fehead.songs.service;

import com.fehead.songs.controller.vo.ListDetailVO;
import com.fehead.songs.dataobject.SonglistsDO;
import com.fehead.songs.model.ListsModel;

import java.util.List;

public interface ListsService {

    public List<ListsModel> selectAllListByUserId(Integer userId,Integer page,Integer pageSize);

    public List<ListsModel> getAllListsStatus_1();

    public ListDetailVO getListInfoByListId(Integer listId);

    public ListDetailVO insertList(ListsModel listsModel);

    public void updateListStatus(Integer listId,Integer status);

    public void updateList(ListsModel listsModel);

    public List<ListsModel> selectStatus(Integer status,Integer userId);

    public List<SonglistsDO> selectAllLists();

}
