package com.cenyol.study;

import com.cenyol.study.utils.SensorData;
import com.cenyol.study.utils.Time;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cenyol on 22/03/2017.
 */
public class TimeSync implements Runnable{
    private MyMqttClient myMqttClient;

    public TimeSync(MyMqttClient myMqttClient) {
        this.myMqttClient = myMqttClient;
    }

    Time time = new Time();

    public void run() {
        while (Calendar.getInstance().get(Calendar.SECOND) != 0){   // 等到下一分钟开始，即00秒的时候，再推送时间，然后
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (true){
            try {
                myMqttClient.publish("$time",  time.toJson());
//                System.out.println(time.toJson());
                Thread.sleep(60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}