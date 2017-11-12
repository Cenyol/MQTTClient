package com.cenyol.study.topics;

import com.cenyol.study.callback.SensorDataCbk;
import com.cenyol.study.models.MyMqttClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SubscribeSensorDataTopic extends BaseTopic implements Runnable{
    private static SubscribeSensorDataTopic subscribeSensorDataTopic = null;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SubscribeSensorDataTopic.getInstance());
    }

    public static SubscribeSensorDataTopic getInstance() {
        if (subscribeSensorDataTopic == null) {
            synchronized (SubscribeSensorDataTopic.class) {
                if (subscribeSensorDataTopic == null) {
                    subscribeSensorDataTopic = new SubscribeSensorDataTopic(new MyMqttClient("data.save"));
                }
            }
        }
        return subscribeSensorDataTopic;
    }

    private SubscribeSensorDataTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
        this.myMqttClient.setCallback(new SensorDataCbk());
    }

    public void run() {
        String[] topics = {"$data", "$null"};
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}