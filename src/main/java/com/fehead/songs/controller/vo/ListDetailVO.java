package com.fehead.songs.controller.vo;

import com.fehead.songs.dataobject.UserDO;
import com.fehead.songs.model.ListsModel;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

public class ListDetailVO implements Serializable {
    private Integer id;

    private String songerName;

    private String songName;

    private Integer steps;

    private Integer userId;

    private String postDate;

    private String wantDay;

    private String content;

    private UserDO userDO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongerName() {
        return songerName;
    }

    public void setSongerName(String songerName) {
        this.songerName = songerName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getWantDay() {
        return wantDay;
    }

    public void setWantDay(String wantDay) {
        this.wantDay = wantDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }
}
