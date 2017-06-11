package com.cenyol.study.utils;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cenyol on 07/06/2017.
 */
public class Time {
    private long stamp;
    private int type;
    private String string;

    public String toJson() {
        Calendar calendar = Calendar.getInstance();
        this.setStamp(calendar.getTimeInMillis() / 1000);

        // 1表示半点，2表示整点报时。整点的时候可以使用蜂鸣器响两声，半点的时候滴一声
        if (calendar.get(Calendar.MINUTE) == 30)
            this.setType(1);
        if (calendar.get(Calendar.MINUTE) == 00)
            this.setType(2);
        this.setString(new SimpleDateFormat("MM-dd HH:mm EEE  ").format(calendar.getTime()));
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
