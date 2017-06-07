package com.cenyol.study.utils;

/**
 * Created by cenyol on 16/05/2017.
 */
public class SensorData {
    private String num;
    private int object_type;     // 0 plant, 1 room
    private int object_id;
    private int type;
    private double value;
    private int timestamp;

    public SensorData() {
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getObject_type() {
        return object_type;
    }

    public void setObject_type(int object_type) {
        this.object_type = object_type;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "num='" + num + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
