package com.cenyol.study.topics;

import com.cenyol.study.models.MyMqttClient;

/**
 * Created by cenyol on 22/03/2017.
 */
public class ListenForFeedback implements Runnable{
    public static MyMqttClient myMqttClient;

    public ListenForFeedback(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        String[] topics = {"$feedback", "$feedback"};
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}