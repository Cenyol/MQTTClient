package com.cenyol.study;

import com.cenyol.study.models.MyMqttClient;
import com.cenyol.study.topics.SensorDataAndNewDeviceTopic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 06/11/2017 19:12
 */
public class Runner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new ListenForFeedback(new MyMqttClient("paho.java.feedback")));
        executorService.execute(new SensorDataAndNewDeviceTopic(new MyMqttClient("paho.java.data.save")));
//        executorService.execute(new TimeSync(new MyMqttClient("paho.java.time.sync")));
//        executorService.execute(new Weather(new MyMqttClient("paho.java.weather")));
    }

}
