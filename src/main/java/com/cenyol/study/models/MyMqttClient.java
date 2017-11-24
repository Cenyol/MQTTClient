package com.cenyol.study.models;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cenyol on 22/03/2017.
 */
public class MyMqttClient {
    private String clientId = "";
    private ServerHost serverHost;
    private MqttClient sampleClient = null;
    private MemoryPersistence persistence;
    private MqttCallback callback = null;

    Logger logger = LoggerFactory.getLogger(MyMqttClient.class);

    public MyMqttClient(String clientID) {
        serverHost = new ServerHost();
        persistence = new MemoryPersistence();
        this.clientId = clientID;
    }

    public void subscribe(String[] topicFilters) throws Exception{    // 其实就是加入一个群，然后等着接受消息
        try {
            this.connect();
            sampleClient.subscribe(topicFilters,new int[]{1,1});
            logger.debug("Subscribe success for {}", topicFilters);
        } catch (MqttException me) {
            this.printExceptionInfo(me);
        }
    }

    public void publish(String topic, String messageString) throws Exception{      // 其实就是加入一个群，然后开始发消息
        try {
            this.connect();
            MqttMessage message=new MqttMessage();
            message.setPayload(messageString.getBytes());
            message.setQos(0);
            message.setRetained(true);
            sampleClient.publish(topic,message);
            logger.debug("Publish success for {}", topic);
        } catch (MqttException me) {
            this.printExceptionInfo(me);
        }
    }

    private void connect() throws MqttException{
        if (sampleClient != null) return;
        String broker = serverHost.getBrokerAddress();
        sampleClient = new MqttClient(broker, clientId,persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setUserName(clientId);
//        char[] passwd = {'t', 'e', 's', 't'};
//        connOpts.setPassword(passwd);
        //connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        logger.debug("The MQTT Version is:"+connOpts.getMqttVersion());
        connOpts.setCleanSession( false );
//        System. out .println( "Connecting to broker: " + broker);
        sampleClient.connect(connOpts);
//        System. out .println( "Connected"  + new Date().toString());
        sampleClient.setCallback(this.callback);
    }


    public void printExceptionInfo(MqttException me){
        System. out .println( "reason " + me.getReasonCode());
        System. out .println( "msg " + me.getMessage());
        System. out .println( "loc " + me.getLocalizedMessage());
        System. out .println( "cause " + me.getCause());
        System. out .println( "excep " + me);
        me.printStackTrace();
    }

    public String getClientId() {
        return clientId;
    }

    public void disconnect() throws MqttException {
        sampleClient.disconnect();
        System. out .println( "Disconnected" );
    }

    public MqttCallback getCallback() {
        return callback;
    }

    public void setCallback(MqttCallback callback) {
        this.callback = callback;
    }
}
