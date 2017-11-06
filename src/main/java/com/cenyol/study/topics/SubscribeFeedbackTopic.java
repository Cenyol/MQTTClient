package com.cenyol.study.topics;

import com.cenyol.study.callback.FeedbackCbk;
import com.cenyol.study.models.MyMqttClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SubscribeFeedbackTopic implements Runnable{
    private MyMqttClient myMqttClient;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new SubscribeFeedbackTopic(new MyMqttClient("paho.java.feedback")));
    }

    public SubscribeFeedbackTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        String[] topics = {"$feedback", "$feedback"};
        myMqttClient.setCallback(new FeedbackCbk());
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyMqttClient getMyMqttClient() {
        return myMqttClient;
    }
}