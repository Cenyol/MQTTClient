package com.cenyol.study.callback;

import com.cenyol.study.utils.HttpRequest;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by cenyol on 22/03/2017.
 */
public class NewDeviceCbk implements MqttCallback{
    Logger logger = LoggerFactory.getLogger(NewDeviceCbk.class);

    public void connectionLost(Throwable throwable) {

    }

    // 收到消息之后的回调处理
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String messageString = mqttMessage.toString();
        logger.debug("message from other publish[{}]", Calendar.getInstance().getTime());
        logger.debug("topic: {}", s);
        logger.debug("message: {}", messageString);

        // 测试规则引擎期间，暂不保存至数据库
        if (s.equals("$new")) {
            String url = "http://agriot-api.cenyol.com/site/new-device";
            HttpRequest.sendPost(url, "data=" + messageString);
            logger.debug("Send a post request, url: {}, params: {}", url, messageString);
        }


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
