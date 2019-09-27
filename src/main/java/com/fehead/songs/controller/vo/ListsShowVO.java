package com.fehead.songs.controller.vo;

import com.fehead.songs.dataobject.UserDO;
import org.joda.time.DateTime;

import java.util.Date;

public class ListsShowVO {

    private Integer id;

    private String songName;

    private Integer steps;

    private String postDate;

    private String wantDay;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
