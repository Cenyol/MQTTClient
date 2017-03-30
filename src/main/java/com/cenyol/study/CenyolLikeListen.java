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
//        try {
//            myMqttClient.publish("test",  "hello everybody, I am " + myMqttClient.getClientId());
//
//            Thread.sleep(10 * 1000);
//
//            myMqttClient.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String[] topics = {"test", "test"};
        while (true){
            try {
                myMqttClient.subscribe(topics);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}