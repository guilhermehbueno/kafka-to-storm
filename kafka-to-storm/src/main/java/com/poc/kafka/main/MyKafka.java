package com.poc.kafka.main;

import com.poc.kafka.consumer.Consumer;
import com.poc.kafka.properties.KafkaProperties;

public class MyKafka {
	
	public static void main(String[] args) {
		String topic = KafkaProperties.topic;
		if(args != null && args.length >= 1){
			topic = args[0];
		}
		
		/*
		Producer producer = new Producer(topic);
		producer.start();
		*/
		
		Consumer consumer = new Consumer(topic);
		consumer.start();
	}

}
