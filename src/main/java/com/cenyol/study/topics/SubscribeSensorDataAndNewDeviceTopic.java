package com.cenyol.study.topics;

import com.cenyol.study.callback.SensorDataCbk;
import com.cenyol.study.drools.models.raw.Base;
import com.cenyol.study.models.MyMqttClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SubscribeSensorDataAndNewDeviceTopic extends BaseTopic implements Runnable{
    private static SubscribeSensorDataAndNewDeviceTopic subscribeSensorDataAndNewDeviceTopic = null;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SubscribeSensorDataAndNewDeviceTopic.getInstance());
    }

    public static SubscribeSensorDataAndNewDeviceTopic getInstance() {
        if (subscribeSensorDataAndNewDeviceTopic == null) {
            synchronized (SubscribeSensorDataAndNewDeviceTopic.class) {
                if (subscribeSensorDataAndNewDeviceTopic == null) {
                    subscribeSensorDataAndNewDeviceTopic = new SubscribeSensorDataAndNewDeviceTopic(new MyMqttClient("paho.java.data.save"));
                }
            }
        }
        return subscribeSensorDataAndNewDeviceTopic;
    }

    private SubscribeSensorDataAndNewDeviceTopic(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        String[] topics = {"$new", "$data"};
        myMqttClient.setCallback(new SensorDataCbk());
        try {
            myMqttClient.subscribe(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}