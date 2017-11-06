package com.cenyol.study.topics;

import com.cenyol.study.models.MyMqttClient;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SensorDataAndNewDeviceTopic implements Runnable{
    public static MyMqttClient myMqttClient;

    public SensorDataAndNewDeviceTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        String[] topics = {"$new", "$data"};
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}