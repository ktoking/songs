package com.fehead.songs.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1.0/songlists/redis")
@RestController
public class RedisController {



    private JSONObject json=new JSONObject();


//    @GetMapping("detail/set")
//    public PersonDO getPerson(){
//        PersonDO personDO=new PersonDO();
//        personDO.setKey1("sdsa");
//        personDO.setTel(12312313);
//
//        PersonDO personDO1=new PersonDO();
//        personDO1.setKey1("KKK");
//        personDO1.setTel(12312313);
//
//        List<PersonDO> personDOList=new ArrayList<>();
//        personDOList.add(personDO);
//        personDOList.add(personDO1);
//        redisService.set("kkk12",json.toJSONString(personDOList));
//        List<PersonDO> list=json.parseArray(redisService.get("kkk12"),PersonDO.class);
//
//        Map<String,Integer> map=new HashMap<>();
//        map.put("myphone",123123213);
//        map.put("sda",17723);
//        redisService.set("mymap",json.toJSONString(map));
//
//        JSONObject jsonObject=json.parseObject(redisService.get("mymap"));
//        Map<String,Object> map1 = (Map<String,Object>)jsonObject;
//        Object o=map1.get("myphone");
//
//
//        String result=redisService.get("my");
//        PersonDO person = JSON.parseObject(result,PersonDO.class);
//
//        redisService.remove("kkk1");
//        redisService.expire("my",10);
//        return person;
//    }

}
