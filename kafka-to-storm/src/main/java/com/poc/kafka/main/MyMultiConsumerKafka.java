package com.poc.kafka.main;

import com.poc.kafka.consumer.ConsumerMultiTopics;


public class MyMultiConsumerKafka {
	
	public static void main(String[] args) {
		ConsumerMultiTopics multiTopics = new ConsumerMultiTopics(args);
		multiTopics.start();
		
//		LinkedBlockingQueue queue = new LinkedBlockingQueue();
//		
//		for (String topic : args) {
//			Consumer consumer = new Consumer(topic, queue);
//			consumer.start();
//		}
	}

}
