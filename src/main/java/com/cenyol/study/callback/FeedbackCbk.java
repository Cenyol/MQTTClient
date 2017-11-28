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
public class FeedbackCbk implements MqttCallback{
    Logger logger = LoggerFactory.getLogger(SensorDataCbk.class);

    public void connectionLost(Throwable throwable) {

    }

    // 收到消息之后的回调处理
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String messageString = mqttMessage.toString();
        logger.debug("message from other publish[{}]", Calendar.getInstance().getTime());
        logger.debug("topic: {}", s);
        logger.debug("message: {}", messageString);

        // 测试规则引擎期间，暂不保存至数据库
        if (s.equals("$feedback"))
            HttpRequest.sendPost("http://agriot-api.cenyol.com/site/update-relay-status", "data=" + messageString);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
