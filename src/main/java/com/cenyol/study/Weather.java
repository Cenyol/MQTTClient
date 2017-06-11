package com.cenyol.study;

import com.cenyol.study.utils.Time;

import java.util.Calendar;

/**
 * Created by cenyol on 22/03/2017.
 */
public class Weather implements Runnable{
    private MyMqttClient myMqttClient;

    public Weather(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    Time time = new Time();

    public void run() {
        while (true){

            try {
                myMqttClient.publish("$weather",  time.toJson());
//                System.out.println(time.toJson());
                Thread.sleep(60 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}