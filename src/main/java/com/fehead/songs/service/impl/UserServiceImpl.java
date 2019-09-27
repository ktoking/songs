package com.fehead.songs.service.impl;


import com.fehead.songs.dao.UserDOMapper;
import com.fehead.songs.dataobject.UserDO;
import com.fehead.songs.error.BusinessException;
import com.fehead.songs.error.EmBusinessError;
import com.fehead.songs.model.UserModel;
import com.fehead.songs.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;


    @Override
    @Cacheable(cacheNames="user", key="#id")
    public UserModel getUserById(Integer id) throws BusinessException {
        System.out.println("没缓存!");
        UserModel userModel=new UserModel();
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        if(userDO==null){
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"用户不存在");
        }
        BeanUtils.copyProperties(userDO,userModel);

        return userModel;
    }
}
