package com.fehead.songs.dataobject;

import java.util.Date;

public class SonglistsDO {
    private Integer id;

    private String songerName;

    private String songName;

    private Integer steps;

    private Integer userId;

    private Date postDate;

    private Date wantDay;

    private String content;

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
        this.songerName = songerName == null ? null : songerName.trim();
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getWantDay() {
        return wantDay;
    }

    public void setWantDay(Date wantDay) {
        this.wantDay = wantDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}