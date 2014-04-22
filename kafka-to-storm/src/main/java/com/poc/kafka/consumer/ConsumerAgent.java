package com.poc.kafka.consumer;

import java.util.concurrent.LinkedBlockingQueue;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class ConsumerAgent implements Runnable {

	private final KafkaStream stream;
	private final int threadNumber;
	private final LinkedBlockingQueue<String> queue;
	private final String topicName;

	public ConsumerAgent(String topic, KafkaStream stream, int threadNumber, LinkedBlockingQueue<String> queue) {
		super();
		this.topicName = topic;
		this.stream = stream;
		this.threadNumber = threadNumber;
		this.queue = queue;
	}

	public void run() {
		System.out.println("Starting thread: "+topicName+" number: "+threadNumber);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()){
			String message = new String(it.next().message());
			System.out.println("Thread " + threadNumber + ": " + message);
			this.queue.add(message);
		}
		System.out.println("Shutting down Thread: " + threadNumber);
	}
}
