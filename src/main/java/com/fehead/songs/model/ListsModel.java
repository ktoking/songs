package com.fehead.songs.model;

import com.fehead.songs.dataobject.UserDO;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

public class ListsModel implements Serializable {

    private Integer id;

    private String songerName;

    private String songName;

    private Integer steps;

    private Integer userId;

    private DateTime postDate;

    private DateTime wantDay;

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

    public DateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(DateTime postDate) {
        this.postDate = postDate;
    }

    public DateTime getWantDay() {
        return wantDay;
    }

    public void setWantDay(DateTime wantDay) {
        this.wantDay = wantDay;
    }
}
