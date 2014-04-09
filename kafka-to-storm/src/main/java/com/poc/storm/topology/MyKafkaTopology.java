package com.poc.storm.topology;

import com.poc.storm.bolt.PrinterBolt;
import com.poc.storm.spout.MyKafkaSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

public class MyKafkaTopology {
	
	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new MyKafkaSpout(), 1);
        builder.setBolt("printer", new PrinterBolt()).shuffleGrouping("spout");
       
        Config config = new Config();

        config.setDebug(false);
        if(args!=null && args.length > 0) {
            config.setNumWorkers(1);
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());
        } else {        
            config.setMaxTaskParallelism(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("kafka", config, builder.createTopology());
            Thread.sleep(120000);
            cluster.shutdown();
        }
	}
}
