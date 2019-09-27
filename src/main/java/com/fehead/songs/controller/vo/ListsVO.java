package com.fehead.songs.controller.vo;

import com.fehead.songs.dataobject.UserDO;
import org.joda.time.DateTime;

import java.util.Date;

public class ListsVO {
    private Integer id;

    private String songerName;

    private String songName;

    private String postDate;

    private String wantDay;

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
}
