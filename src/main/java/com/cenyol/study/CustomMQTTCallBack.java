package com.cenyol.study;

import com.cenyol.study.utils.HttpRequest;
import com.cenyol.study.utils.MySQL;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;

/**
 * Created by cenyol on 22/03/2017.
 */
public class CustomMQTTCallBack implements MqttCallback{

    public void connectionLost(Throwable throwable) {

    }

    // 收到消息之后的回调处理
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String messageString = mqttMessage.toString();
//        System.out.println("message from other publish[" + Calendar.getInstance().getTime() + "]");
//        System.out.println("topic: " + s);
//        System.out.println("message: " + messageString);
//        System.out.println();

        // TODO 调用短信接口

        if (s.equals("$new"))
            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/new-device", "data=" + messageString);
        if (s.equals("$data"))
            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/new-data", "data=" + messageString);

//        System.out.println(sr);

        // 暂时不直接保存数据库，而是转发到PHP Server
        // 存数据库
//        if (messageString.startsWith("[{")) {
//            new MySQL().insert(messageString);
//        }else {
//            System.out.println("not json");
//        }
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
