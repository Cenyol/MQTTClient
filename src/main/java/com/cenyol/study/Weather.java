package com.cenyol.study;

import com.cenyol.study.utils.HttpRequest;
import com.cenyol.study.utils.Time;
import com.google.gson.*;
import com.oracle.javafx.jmx.json.JSONFactory;

import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by cenyol on 22/03/2017.
 */
public class Weather implements Runnable{
    private MyMqttClient myMqttClient;

    public Weather(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    WeatherPrediction weatherPrediction = new WeatherPrediction();

    public void run() {
        while (true){
//            System.out.println(weatherPrediction.getCurrent());
//            System.out.println(weatherPrediction.getNextThreeDaysSimple());
            try {
                myMqttClient.publish("$weather",  weatherPrediction.getCurrent());
//                System.out.println(time.toJson());
//                Thread.sleep(60 * 60 * 1000);
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}