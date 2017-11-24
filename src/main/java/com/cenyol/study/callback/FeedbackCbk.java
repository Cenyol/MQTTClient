package com.cenyol.study.callback;

import com.cenyol.study.utils.HttpRequest;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by cenyol on 22/03/2017.
 */
public class FeedbackCbk implements MqttCallback{

    public void connectionLost(Throwable throwable) {

    }

    // 收到消息之后的回调处理
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String messageString = mqttMessage.toString();
//        System.out.println("message from other publish[" + Calendar.getInstance().getTime() + "]");
//        System.out.println("topic: " + s);
//        System.out.println("message: " + messageString);
//        System.out.println();

        // 测试规则引擎期间，暂不保存至数据库
        if (s.equals("$feedback"))
            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/update-relay-status", "data=" + messageString);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
