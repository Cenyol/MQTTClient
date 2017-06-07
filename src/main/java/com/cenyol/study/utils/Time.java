package com.cenyol.study.utils;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by cenyol on 07/06/2017.
 */
public class Time {
    private long stamp;
    private int type;
    private String string;

    public String toJson() {
        this.setStamp(System.currentTimeMillis() / 1000);
        this.setType(2);
        this.setString(new Date().toString());
        return new Gson().toJson(this);
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
