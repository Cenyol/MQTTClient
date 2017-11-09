package com.cenyol.study.drools.actors;

import com.cenyol.study.models.MQTTCmdPublishClient;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 11:29
 * 处理空气温度
 */
public class AirTempActor {
    private static String airConditionor = "5ccf7f36f9af";
    private static String airConditionorOpenCode = "1";
    private static String airConditionorCloseCode = "0";

    public static void down() {
        try {
            MQTTCmdPublishClient.getInstance()
                    .publish("$client/" + airConditionor, airConditionorCloseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void up() {
        try {
            MQTTCmdPublishClient.getInstance()
                    .publish("$client/" + airConditionor, airConditionorOpenCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
