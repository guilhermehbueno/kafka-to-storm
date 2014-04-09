package com.poc.kafka.consumer;

import org.testng.annotations.Test;

public class KafkaConsumerTest {
	
	@Test
	public void test() throws Exception{
		new Consumer("twitter").run();
		Thread.sleep(60000);
	}

}
