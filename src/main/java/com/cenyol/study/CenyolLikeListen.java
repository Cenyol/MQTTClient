package com.cenyol.study;

/**
 * Created by cenyol on 22/03/2017.
 */
public class CenyolLikeListen implements Runnable{
    private MyMqttClient myMqttClient;

    public CenyolLikeListen(MyMqttClient myMqttClient) {
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