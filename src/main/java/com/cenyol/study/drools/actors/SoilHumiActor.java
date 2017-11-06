package com.cenyol.study.drools.actors;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 01/11/2017 14:28
 * 处理土壤湿度
 */
public class SoilHumiActor {
    public static void down() {
        System.out.println("Open the drop irrigation system.");
    }

    public static void up() {
        System.out.println("Close the drop irrigation system.");
    }
}
