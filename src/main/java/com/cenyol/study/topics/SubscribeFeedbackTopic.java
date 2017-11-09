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
            synchronized (SubscribeNewDeviceTopic.class) {
                if (subscribeFeedbackTopic == null) {
                    subscribeFeedbackTopic = new SubscribeFeedbackTopic(new MyMqttClient("paho.java.feedback"));
                }
            }
        }
        return subscribeFeedbackTopic;
    }

    private SubscribeFeedbackTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
        this.myMqttClient.setCallback(new FeedbackCbk());
    }

    public void run() {
        String[] topics = {"$feedback", "$null"};
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}