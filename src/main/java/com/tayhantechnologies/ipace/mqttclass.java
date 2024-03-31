package com.tayhantechnologies.ipace;

import org.eclipse.paho.client.mqttv3.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class mqttclass implements MqttCallback {
    String publisherId = UUID.randomUUID().toString();
    IMqttClient publisher;

    {
        try {
            this.publisher = new MqttClient("tcp://52.63.161.248:1883",publisherId);
            this.publisher.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    void setupConnection(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        try {
            publisher.connect(options);
            publisher.subscribe("smarthome/room1/temp"); //subscribing to the topic name  test/topic
           // System.out.println(publisher.subscribeWithResponse("test/topic"));
            System.out.println(publisher.isConnected());

//Override methods from MqttCallback interface


        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        String url = "http://127.0.0.1:8080/PostMqtt?topic=smarthomeroom1temp&message=" + message;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header


        int responseCode = con.getResponseCode();
        System.out.println("message is : "+message + " Response Code " + responseCode);
    }
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
