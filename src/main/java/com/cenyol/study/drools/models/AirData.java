package com.cenyol.study.drools.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 10:59
 */
public class AirData extends Base {
    private double airTemp;
    private double airHumi;
    private String createdTime;

    public AirData(double airTemp, double airHumi) {
        this.airTemp = airTemp;
        this.airHumi = airHumi;
        this.createdTime = new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date());
    }

    public double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }

    public double getAirHumi() {
        return airHumi;
    }

    public void setAirHumi(double airHumi) {
        this.airHumi = airHumi;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "AirData{" +
                "airTemp=" + airTemp +
                ", airHumi=" + airHumi +
                '}';
    }
}
