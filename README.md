kafka-to-storm
==============

Starting zookeeper: 
bin/zookeeper-server-start.sh config/zookeeper.properties


Starting Kafka:
bin/kafka-server-start.sh config/server.properties


Running poc do kafka:
mvn clean compile exec:java -Dexec.mainClass=com.poc.kafka.main.MyKafka -Dexec.args="topic_name"
mvn clean compile exec:java -Dexec.mainClass=com.poc.kafka.main.MyMultiConsumerKafka -Dexec.args="price popularity"

Gerando o jar para o Storm:
mvn clean compile assembly:single

Rodando a topologia no Storm:
storm jar target/kafka-to-storm-1.0-SNAPSHOT-jar-with-dependencies.jar com.poc.storm.topology.MyKafkaTopology


