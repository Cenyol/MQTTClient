package com.cenyol.study.callback;

import com.cenyol.study.drools.DroolsExample;
import com.cenyol.study.drools.models.raw.AirData;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;

/**
 * Created by cenyol on 22/03/2017.
 */
public class SensorDataCbk implements MqttCallback{

    public void connectionLost(Throwable throwable) {

    }

    // 收到消息之后的回调处理
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String messageString = mqttMessage.toString();
        System.out.println("message from other publish[" + Calendar.getInstance().getTime() + "]");
        System.out.println("topic: " + s);
        System.out.println("message: " + messageString);
        System.out.println();

        // TODO 调用短信接口


        // 测试规则引擎期间，暂不保存至数据库
//        if (s.equals("$new"))
//            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/new-device", "data=" + messageString);
//        if (s.equals("$data"))
//            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/new-data", "data=" + messageString);

//        System.out.println(messageString);

        Gson gson = new Gson();
        AirData airData = gson.fromJson(messageString, AirData.class);
        DroolsExample.airDataValid(airData);


//        AirData airData = new AirData(sensorDatas[0].getValue(), sensorDatas[1].getValue());
//        System.out.println(sensorDatas[0].getValue());
//        System.out.println(airData);


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
