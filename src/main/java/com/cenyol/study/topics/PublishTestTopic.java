package com.cenyol.study.topics;

import com.cenyol.study.models.MyMqttClient;

import java.util.Date;

/**
 * Created by cenyol on 22/03/2017.
 */
public class PublishTestTopic implements Runnable{
    private MyMqttClient myMqttClient;

    public PublishTestTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        while (true){
            try {
                myMqttClient.publish("test",  new Date().toString());
                Thread.sleep(6 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}