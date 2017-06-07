package com.cenyol.study;

import com.cenyol.study.utils.SensorData;
import com.cenyol.study.utils.Time;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by cenyol on 22/03/2017.
 */
public class TimeSync implements Runnable{
    private MyMqttClient myMqttClient;

    public TimeSync(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    Time time = new Time();
//    SensorData[] sensorDatas = gson.toJson(jsonString, SensorData[].class);

    public void run() {
        while (true){
            try {
                myMqttClient.publish("$time",  time.toJson());
                System.out.println(time.toJson());
                Thread.sleep(60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}