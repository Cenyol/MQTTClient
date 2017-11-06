package com.cenyol.study.drools.models;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 10:59
 */
public class AirData extends Base {
    private double airTemp;
    private double airHumi;

    public AirData(double airTemp, double airHumi) {
        this.airTemp = airTemp;
        this.airHumi = airHumi;
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

    @Override
    public String toString() {
        return "AirData{" +
                "airTemp=" + airTemp +
                ", airHumi=" + airHumi +
                '}';
    }
}
