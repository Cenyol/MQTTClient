package com.cenyol.study.drools.actors;

import com.cenyol.study.topics.SensorDataAndNewDeviceTopic;

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
        System.out.println("=======================================================");
        System.out.println("The temp of air too high, it is cooling...");
        System.out.println("Open the wind door.");
        System.out.println("Open the air conditioner.");
        System.out.println("=======================================================");
        System.out.println("");

        try {
            SensorDataAndNewDeviceTopic.myMqttClient.publish("$client/" + airConditionor, airConditionorCloseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void up() {
        System.out.println("=======================================================");
        System.out.println("The temp of air too low, it is heating...");
        System.out.println("Close the wind door.");
        System.out.println("Open the warmer.");
        System.out.println("=======================================================");
        System.out.println("");

        try {
            SensorDataAndNewDeviceTopic.myMqttClient.publish("$client/" + airConditionor, airConditionorOpenCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
