package com.cenyol.study.topics;

import com.cenyol.study.models.MyMqttClient;
import com.cenyol.study.models.WeatherPrediction;

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
            String string = weatherPrediction.getCurrent();
            try {
                myMqttClient.publish("$weather", string);
                for (int i = 0; i < 30; i++) {
                    Thread.sleep(60 * 1000);
                    myMqttClient.publish("$heartbeat", "1");        // 每分钟发一次心跳包，防止掉线
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}