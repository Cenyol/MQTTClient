package com.cenyol.study.models;

import com.cenyol.study.callback.SensorDataCbk;
import com.cenyol.study.models.ServerHost;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;

/**
 * Created by cenyol on 22/03/2017.
 */
public class MyMqttClient {
    private String clientId = "";
    private ServerHost serverHost;
    private MqttClient sampleClient = null;
    private MemoryPersistence persistence;

    public MyMqttClient(String clientID) {
        serverHost = new ServerHost();
        persistence = new MemoryPersistence();
        this.clientId = clientID;
//        this.clientId = Utils.RandomString(32);
    }

    public void subscribe(String[] topicFilters) throws Exception{    // 其实就是加入一个群，然后等着接受消息
        try {
            this.connect();

            sampleClient.subscribe(topicFilters,new int[]{1,1});
            System.out.println( "Subscribe success for: " + Arrays.toString(topicFilters));
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
//            System. out .println( "Publish success for: " + topic);
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
//        System. out .println("The MQTT Version is:"+connOpts.getMqttVersion());
        connOpts.setCleanSession( false );
//        System. out .println( "Connecting to broker: " + broker);
        sampleClient.connect(connOpts);
//        System. out .println( "Connected"  + new Date().toString());
        sampleClient.setCallback(new SensorDataCbk());
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
}
