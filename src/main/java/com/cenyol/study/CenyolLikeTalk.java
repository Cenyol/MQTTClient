package com.cenyol.study;

import java.util.Date;

/**
 * Created by cenyol on 22/03/2017.
 */
public class CenyolLikeTalk implements Runnable{
    private MyMqttClient myMqttClient;

    public CenyolLikeTalk(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    public void run() {
//        try {
//            myMqttClient.publish("test",  "hello everybody, I am " + myMqttClient.getClientId());
//
//            Thread.sleep(10 * 1000);
//
//            myMqttClient.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        while (true){
            try {
                myMqttClient.publish("test",  new Date().toString());
//                myMqttClient.publish("test",  "hello everybody, I am " + myMqttClient.getClientId() +
//                    ", now time is " + new Date().toString()
//                );
                Thread.sleep(6 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}