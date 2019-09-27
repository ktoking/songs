package com.fehead.songs.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fehead.songs.error.BusinessException;
import com.fehead.songs.model.UserModel;
import com.fehead.songs.model.WXSessionModel;
import com.fehead.songs.response.CommonReturnType;
import com.fehead.songs.service.UserService;
import com.fehead.songs.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1.0/songlists/user")
@RestController
public class UserController extends BaseController{


    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    JSONObject jsonObject=new JSONObject();


    /**
     * 通过userId查找user信息（详细）
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("detail/{id}")
    public UserModel getUserById(@PathVariable("id") Integer id)throws BusinessException {
        UserModel userModel=userService.getUserById(id);
        String str=jsonObject.toJSONString(userModel);
        return userModel;
    }

    @PostMapping("/wxLogin")
    public CommonReturnType wxLogin(String code){
        System.out.println("wx-code: "+code);
        String url="https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param=new HashMap<>();
        param.put("appid","wx571c3ab83566a08b");
        param.put("secret","b845f963deadf59ce75f7868dc698896");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");

        String wxResult=HttpClientUtil.doGet(url,param);
        System.out.println(wxResult);

        WXSessionModel wxSessionModel= JSON.parseObject(wxResult,WXSessionModel.class);

        //
        return CommonReturnType.creat(null);
    }





}
