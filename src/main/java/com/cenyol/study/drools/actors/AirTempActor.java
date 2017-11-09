package com.cenyol.study.drools.actors;

import com.cenyol.study.callback.SensorDataCbk;
import com.cenyol.study.models.MQTTCmdPublishClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 11:29
 * 处理空气温度
 */
public class AirTempActor {
    static Logger logger = LoggerFactory.getLogger(SensorDataCbk.class);

    private static String airConditionor = "$client/5ccf7f36f9af";
    private static String airConditionorOpenCode = "1";
    private static String airConditionorCloseCode = "0";

    public static void down() {
        try {
            MQTTCmdPublishClient.getInstance()
                    .publish(airConditionor, airConditionorCloseCode);
            logger.debug("op[AirTempActor.down()] publish msg: {}, to topic: {}", airConditionorCloseCode, airConditionor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void up() {
        try {
            MQTTCmdPublishClient.getInstance()
                    .publish(airConditionor, airConditionorOpenCode);
            logger.debug("op[AirTempActor.up()] publish msg: {}, to topic: {}", airConditionorCloseCode, airConditionor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
