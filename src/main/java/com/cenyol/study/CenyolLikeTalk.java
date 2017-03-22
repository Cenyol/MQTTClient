package com.cenyol.study;

import java.util.concurrent.TimeUnit;

/**
 * Created by cenyol on 22/03/2017.
 */
public class CenyolLikeTalk implements Runnable{
    private MyMqttClient myMqttClient;

    public CenyolLikeTalk(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
        while (true){
            try {
                myMqttClient.publish("test",  "hello everybody, I am " + myMqttClient.getClientId());
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}