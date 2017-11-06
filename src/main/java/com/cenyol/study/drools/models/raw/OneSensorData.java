package com.cenyol.study.drools.models.raw;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 03/11/2017 10:11
 */
public class OneSensorData {
    private int type_id;
    private int value;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OneSensorData{" +
                "type_id=" + type_id +
                ", value=" + value +
                '}';
    }
}
