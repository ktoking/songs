package com.fehead.songs.service;

import com.fehead.songs.dataobject.UserDO;
import com.fehead.songs.error.BusinessException;
import com.fehead.songs.model.UserModel;

public interface UserService {

    public UserModel getUserById(Integer id) throws BusinessException;
}
