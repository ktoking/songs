package com.fehead.songs.dataobject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class PersonDO {
    private String key1;
    private Integer tel;


    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    @Override
    public String toString() {
        return "PersonDO{" +
                "key1='" + key1 + '\'' +
                ", tel=" + tel +
                '}';
    }
}
