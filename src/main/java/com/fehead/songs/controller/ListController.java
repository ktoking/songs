package com.fehead.songs.controller;

import com.fehead.songs.controller.vo.ListDetailVO;
import com.fehead.songs.controller.vo.ListsShowVO;
import com.fehead.songs.controller.vo.ListsVO;
import com.fehead.songs.dataobject.SonglistsDO;
import com.fehead.songs.error.BusinessException;
import com.fehead.songs.model.ListsModel;
import com.fehead.songs.response.CommonReturnType;
import com.fehead.songs.service.ListsService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1.0/songlists/list")
@RestController
@Component
@Configuration
@EnableScheduling
public class ListController {

    @Autowired
    private ListsService listsService;

    /**
     * 修改订单状态
     * @param listId
     * @param status
     * @return
     */
    @PutMapping("update/{list_id}/status/{status}")
    public CommonReturnType updateListStatus(@PathVariable("list_id")Integer listId,
                                             @PathVariable("status") Integer status){
        listsService.updateListStatus(listId,status);
        return CommonReturnType.creat(null);
    }

    /**
     * 通过userId分页查询user的所有订单(简略信息)
     * @param userId
     * @param page
     * @param pagesize
     * @return
     * @throws BusinessException
     */
    @GetMapping("easyInfo/{user_id}")
    public CommonReturnType getListsByUserId(@PathVariable("user_id") Integer userId,
                                             @RequestParam("page")Integer page,
                                             @RequestParam("pagesize")Integer pagesize
                                             )throws BusinessException {
        List<ListsModel> listsModelList =listsService.selectAllListByUserId(userId,page,pagesize);
        List<ListsShowVO> listsShowVOList=new ArrayList<>();
        for (ListsModel lm:listsModelList) {
            ListsShowVO listsShowVO=new ListsShowVO();
            BeanUtils.copyProperties(lm,listsShowVO);
            listsShowVO.setPostDate(lm.getPostDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            listsShowVO.setWantDay(lm.getWantDay().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            listsShowVOList.add(listsShowVO);
        }
        return CommonReturnType.creat(listsShowVOList);
    }


    /**
     * 修改订单信息（如果订单状态不为1则返回：订单状态非进行中）
     * @param listId
     * @param content
     * @param songerName
     * @param songName
     * @param wantDay
     * @return
     */
    @PutMapping("updateList/{list_id}")
    public CommonReturnType updateList(@PathVariable("list_id") Integer listId,
                                       @RequestParam("content")String content,
                                       @RequestParam("songer_name")String songerName,
                                       @RequestParam("song_name")String songName,
                                       @RequestParam("want_day") Date wantDay) {
        String data=null;
        ListDetailVO listDetailVO=listsService.getListInfoByListId(listId);
        if(listDetailVO.getSteps()==1){
            ListsModel listsModel=new ListsModel();
            BeanUtils.copyProperties(listDetailVO,listsModel);
            listsModel.setContent(content);
            listsModel.setSongName(songName);
            listsModel.setSongerName(songerName);
            listsModel.setWantDay(new DateTime(wantDay));
            listsService.updateList(listsModel);
            data="订单修改成功";
            return CommonReturnType.creat(data);
        }else{
            data="订单状态非进行中！";
            return CommonReturnType.creat(data);
        }
    }

    /**
     * 删除订单（如果订单状态不为1则返回：订单状态非进行中）
     * @param listId
     * @return
     */
    @DeleteMapping("deleteList/{list_id}")
    public CommonReturnType deleteList(@PathVariable("list_id")Integer listId){
        String data=null;
        ListDetailVO listDetailVO=listsService.getListInfoByListId(listId);
        if(listDetailVO.getSteps()==1){
            listsService.updateListStatus(listDetailVO.getId(),4);
            data="订单删除成功";
            return CommonReturnType.creat(data);
        }else{
            data="订单删除失败，订单状态不是进行中！";
            return CommonReturnType.creat(data);
        }
    }


    /**
     * 发布订单信息
     * @param userId
     * @param content
     * @param songerName
     * @param songName
     * @param wantDay
     * @return
     */
    @PostMapping("putList/{user_id}")
    public CommonReturnType insertList(@PathVariable("user_id")Integer userId,
                                    @RequestParam("content")String content,
                                    @RequestParam("songer_name")String songerName,
                                    @RequestParam("song_name")String songName,
                                    @RequestParam("want_day") Date wantDay) {

        DateTime wantday = new DateTime(wantDay);
        DateTime postdate = new DateTime(new Date());

        ListsModel listsModel=new ListsModel();
        listsModel.setWantDay(wantday);
        listsModel.setPostDate(postdate);
        listsModel.setContent(content);
        listsModel.setUserId(userId);
        listsModel.setSongerName(songerName);
        listsModel.setSongName(songName);
        listsModel.setSteps(1);

        ListDetailVO listDetailVO=listsService.insertList(listsModel);

        return CommonReturnType.creat(listDetailVO);
    }

    /**
     * 找到所有订单状态为1的信息（简略信息）->状态1：进行中，2：已完成，3：已删除，4：订单异常(包括已过期)
     * @param makeDate 传入进去想要查这个时间之前并且在这一天的且订单状态为1的订单列表（时间按降序排列）
     * @return
     */
    @GetMapping("status_1")
    public CommonReturnType getAllListsStatus_1(@RequestParam("make_date") Date makeDate){
        List<ListsModel> listsModelList=listsService.getAllListsStatus_1();
        List<ListsVO> listsVOList=new ArrayList<>();
        for (ListsModel listsModel:listsModelList) {
            DateTime dateTime=new DateTime(makeDate);
            DateTime zeroDate=new DateTime(makeDate).withMillisOfDay(0);
            ListsVO listsVO=new ListsVO();
            if(listsModel.getWantDay().isBefore(new DateTime(makeDate))&&listsModel.getWantDay().isAfter(zeroDate)){
                BeanUtils.copyProperties(listsModel,listsVO);
                listsVO.setPostDate(listsModel.getPostDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
                listsVO.setWantDay(listsModel.getWantDay().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
                listsVOList.add(listsVO);
            }
        }
        return CommonReturnType.creat(listsVOList);
    }

    /**
     *查询指定userId的状态为status的订单
     * @param status
     * @param userId
     * @return
     */
    @GetMapping("returnStatus/user/{user_id}/status/{status}")
    public CommonReturnType selectStatus(@PathVariable("status")Integer status,
                                         @PathVariable("user_id")Integer userId){
        List<ListsModel> listsModelList=listsService.selectStatus(status,userId);
        List<ListsVO> listsVOList=listsModelList.stream().map(listsModel -> {
            ListsVO listsVO=new ListsVO();
            BeanUtils.copyProperties(listsModel,listsVO);
            listsVO.setPostDate(listsModel.getPostDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            listsVO.setWantDay(listsModel.getWantDay().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            return listsVO;
        }).collect(Collectors.toList());
        return CommonReturnType.creat(listsVOList);
    }


    /**
     * 通过订单信息查找此订单的所有信息（详细）
     * @param listId
     * @return
     */
    @GetMapping("getList/{list_id}")
    public CommonReturnType getListInfoByListId(@PathVariable("list_id") Integer listId){
        ListDetailVO listDetailVO=listsService.getListInfoByListId(listId);

        return CommonReturnType.creat(listDetailVO);
    }

    /**
     * 执行定时任务,把在现在时间之前的want_day时间的订单状态置为4已删除
     * @return
     */
    @Scheduled(cron = "0 10 01 * * ?")//定时器设定每天凌晨1点10分执行
    public List<SonglistsDO> ScheduledTime(){
        List<SonglistsDO> songlistsDOList=listsService.selectAllLists();
        for (SonglistsDO songlistsDO:songlistsDOList) {
            if(new DateTime(songlistsDO.getWantDay()).isBefore(new DateTime())){
                listsService.updateListStatus(songlistsDO.getId(),4);
            }
        }
        return songlistsDOList;
    }


    /**
     * 转换ListsModel到ListDetailVO
     * @param listsModel
     * @return
     */
    public ListDetailVO convertFromModel(ListsModel listsModel){
        ListDetailVO listDetailVO=new ListDetailVO();
        BeanUtils.copyProperties(listsModel,listDetailVO);
        return listDetailVO;
    }






}
