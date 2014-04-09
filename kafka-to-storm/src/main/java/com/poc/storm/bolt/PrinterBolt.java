package com.poc.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;


public class PrinterBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 5070579378212785908L;

	public void execute(Tuple tuple, BasicOutputCollector collector) {
        System.out.println("PrinterBolt: "+tuple.getString(0));
    }

    public void declareOutputFields(OutputFieldsDeclarer ofd) {
    }
    
}
