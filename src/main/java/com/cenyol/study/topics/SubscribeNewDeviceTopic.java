package com.cenyol.study.topics;

import com.cenyol.study.callback.NewDeviceCbk;
import com.cenyol.study.callback.SensorDataCbk;
import com.cenyol.study.models.MyMqttClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SubscribeNewDeviceTopic extends BaseTopic implements Runnable{
    private static SubscribeNewDeviceTopic subscribeNewDeviceTopic = null;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SubscribeNewDeviceTopic.getInstance());
    }

    public static SubscribeNewDeviceTopic getInstance() {
        if (subscribeNewDeviceTopic == null) {
            synchronized (SubscribeNewDeviceTopic.class) {
                if (subscribeNewDeviceTopic == null) {
                    subscribeNewDeviceTopic = new SubscribeNewDeviceTopic(new MyMqttClient("paho.java.new.device.save"));
                }
            }
        }
        return subscribeNewDeviceTopic;
    }

    private SubscribeNewDeviceTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
        this.myMqttClient.setCallback(new NewDeviceCbk());
    }

    public void run() {
        String[] topics = {"$new", "$null"};
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}