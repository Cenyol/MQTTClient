package com.cenyol.study.models;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 07/11/2017 19:59
 * 专门用来发布执行命令给指定的客户端
 */
public class MQTTCmdPublishClient {
    private static MyMqttClient cmdPublishClient = null;

    public static MyMqttClient getInstance() {
        if (cmdPublishClient == null) {
            synchronized (MQTTCmdPublishClient.class) {
                if (cmdPublishClient == null) {
                    cmdPublishClient = new MyMqttClient("paho.java.cmd.publish");
                }
            }
        }
        return cmdPublishClient;
    }

    private MQTTCmdPublishClient() {}
}
