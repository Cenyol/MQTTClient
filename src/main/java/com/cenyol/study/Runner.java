package com.cenyol.study;

import com.cenyol.study.models.MyMqttClient;
import com.cenyol.study.topics.SubscribeFeedbackTopic;
import com.cenyol.study.topics.SubscribeSensorDataAndNewDeviceTopic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 06/11/2017 19:12
 *
 * 开发测试阶段使用Topic下的类来执行。最终打包使用的入口是本文件的main函数。
 */
public class Runner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new SubscribeFeedbackTopic(new MyMqttClient("paho.java.feedback")));
//        executorService.execute(new SubscribeSensorDataAndNewDeviceTopic(new MyMqttClient("paho.java.data.save")));
//        executorService.execute(new TimeSync(new MyMqttClient("paho.java.time.sync")));
//        executorService.execute(new Weather(new MyMqttClient("paho.java.weather")));
    }
}
