package com.tayhantechnologies.ipace;


import com.tayhantechnologies.ipace.Entities.DevicePortsEntity;
import com.tayhantechnologies.ipace.Entities.ExternalCommands;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.OSEnvironment;
import com.tayhantechnologies.ipace.Entities.OsEntity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class IpaceApplication {


	@Autowired
	static Scanner scanner;



	public static void main(String[] args) throws IOException {

		SpringApplication.run(IpaceApplication.class, args);
		ExternalCommands externalCommands = new ExternalCommands();
		//externalCommands.runCommand();
		System.out.println("test12345");

		Field[] fields = OsEntity.class.getDeclaredFields();
		Field[] fields1 = DevicePortsEntity.class.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			//gives the names of the fields
			System.out.println(field.getName());
		}
		for (Field field : fields1) {
			//gives the names of the fields
			System.out.println(field.getName());
		}

		mqttclass testclass = new mqttclass() {

		};
		testclass.setupConnection();

		List<Book> students = new ArrayList<Book>();
// logic to build student data
		//model.addAttribute("students", students);
		//scanner.checkHosts("192.168.0");
		
	}

}
