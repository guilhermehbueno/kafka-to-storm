 package com.poc.storm.spout;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import com.poc.kafka.consumer.Consumer;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class MyKafkaSpout extends BaseRichSpout{
	
	private static final long serialVersionUID = 5768931437038022278L;
	private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private Consumer consumer;
	private  SpoutOutputCollector collector;
	
	public MyKafkaSpout() {
		super();
	}

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.consumer = new Consumer("twitter", queue);
		this.consumer.start();
	}

	public void nextTuple() {
		String message = queue.poll();
		if(message!=null){
			collector.emit(new Values(message));
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		 declarer.declare(new Fields("tweet"));
	}
	
	@Override
	public void ack(Object msgId) {
	}
	
	@Override
	public void fail(Object msgId) {
	}

}
