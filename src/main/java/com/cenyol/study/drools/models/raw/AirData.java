package com.cenyol.study.drools.models.raw;

import java.util.Arrays;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 03/11/2017 10:12
 */
public class AirData extends Base{
    private OneSensorData[] data;

    public OneSensorData[] getData() {
        return data;
    }

    public void setData(OneSensorData[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AirData{" +
                "mac='" + mac + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
