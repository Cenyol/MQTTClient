package com.cenyol.study.topics;

import com.cenyol.study.callback.FeedbackCbk;
import com.cenyol.study.models.MyMqttClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SubscribeFeedbackTopic extends BaseTopic implements Runnable{
    private static SubscribeFeedbackTopic subscribeFeedbackTopic = null;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SubscribeFeedbackTopic.getInstance());
    }


    public static SubscribeFeedbackTopic getInstance() {
        if (subscribeFeedbackTopic == null) {
            synchronized (SubscribeSensorDataAndNewDeviceTopic.class) {
                if (subscribeFeedbackTopic == null) {
                    subscribeFeedbackTopic = new SubscribeFeedbackTopic(new MyMqttClient("paho.java.feedback"));
                }
            }
        }
        return subscribeFeedbackTopic;
    }

    private SubscribeFeedbackTopic(MyMqttClient myMqttClient) {
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

}