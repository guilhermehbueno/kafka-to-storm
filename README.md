kafka-to-storm
==============

Starting zookeeper: 
bin/zookeeper-server-start.sh config/zookeeper.properties


Starting Kafka:
bin/kafka-server-start.sh config/server.properties


Running poc:
mvn clean compile exec:java -Dexec.mainClass=com.poc.kafka.main.MyKafka -Dexec.args="topic_name"