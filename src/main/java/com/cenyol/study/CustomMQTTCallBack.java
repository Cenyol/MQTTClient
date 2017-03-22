package com.cenyol.study;

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
        System.out.println("message from other publish[" + Calendar.getInstance().getTime() + "]");
        System.out.println("topic: " + s);
        System.out.println("message: " + mqttMessage.toString());
        System.out.println();
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
