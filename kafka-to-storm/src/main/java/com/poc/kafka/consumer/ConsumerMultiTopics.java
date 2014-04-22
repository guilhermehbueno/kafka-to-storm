package com.poc.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.apache.commons.lang3.StringUtils;

import com.poc.kafka.properties.KafkaProperties;

public class ConsumerMultiTopics extends Thread {

	private final String[] topics;
	private final ConsumerConnector consumer;
	private final LinkedBlockingQueue<String> queue;

	public ConsumerMultiTopics(String[] topics, ConsumerConnector consumer, LinkedBlockingQueue<String> queue) {
		super();
		this.topics = topics;
		this.consumer = consumer;
		this.queue = queue;
	}

	public ConsumerMultiTopics(String[] topics) {
		super();
		this.topics = topics;
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.queue = new LinkedBlockingQueue<String>();
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", KafkaProperties.zkConnect);
		props.put("group.id", KafkaProperties.groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		return new ConsumerConfig(props);
	}
	
	public void run() {
	    Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
	    for (String  topic : this.topics) {
	    	topicCountMap.put(topic, new Integer(1));
		}
	    System.out.println("["+topics.length+"] Listening topics: "+StringUtils.join(topics));
	    
	    Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
	    
	    for (String topic : this.topics) {
	    	ExecutorService executor = Executors.newFixedThreadPool(topics.length);
	    	List<KafkaStream<byte[], byte[]>> streams =  consumerMap.get(topic);
	    	int threadNumber = 0;
	    	System.out.println("Generating ConsumerAgent to "+streams.size()+ " streams");
	    	for (final KafkaStream stream : streams) {
	    		executor.submit(new ConsumerAgent(topic, stream, threadNumber, queue));
	    		threadNumber++;
	    	}
		} 
	  }
}
