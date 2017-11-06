package com.cenyol.study.drools.actors;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 14:28
 * 处理空气湿度
 */
public class AirHumiActor {
    public static void down() {
        System.out.println("Open the fog machine.");
    }

    public static void up() {
        System.out.println("Close the fog machine.");
    }
}
