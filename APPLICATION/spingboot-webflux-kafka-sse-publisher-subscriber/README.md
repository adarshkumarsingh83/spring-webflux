
# spingboot-webflux-kafka-sse-publisher-subscriber

----

## [springboot-webflux-kafka-producer](./springboot-webflux-kafka-producer)
## [springboot-webflux-kafka-consumer](./springboot-webflux-kafka-consumer)



## kafka configuration 

### Kafka Download link 
* https://kafka.apache.org/downloads

### Move to the kafka home directory 
* cd kafka.x.x.x

### To start the zookeeper server 
* $ bin/zookeeper-server-start.sh config/zookeeper.properties
```
[2022-02-22 21:43:12,760] INFO Reading configuration from: config/zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,761] WARN config/zookeeper.properties is relative. Prepend ./ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,767] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,767] INFO secureClientPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,767] INFO observerMasterPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,767] INFO metricsProvider.className is org.apache.zookeeper.metrics.impl.DefaultMetricsProvider (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,769] INFO autopurge.snapRetainCount set to 3 (org.apache.zookeeper.server.DatadirCleanupManager)
[2022-02-22 21:43:12,769] INFO autopurge.purgeInterval set to 0 (org.apache.zookeeper.server.DatadirCleanupManager)
[2022-02-22 21:43:12,769] INFO Purge task is not scheduled. (org.apache.zookeeper.server.DatadirCleanupManager)
[2022-02-22 21:43:12,769] WARN Either no config or no quorum defined in config, running in standalone mode (org.apache.zookeeper.server.quorum.QuorumPeerMain)
[2022-02-22 21:43:12,772] INFO Log4j 1.2 jmx support found and enabled. (org.apache.zookeeper.jmx.ManagedUtil)
[2022-02-22 21:43:12,780] INFO Reading configuration from: config/zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,780] WARN config/zookeeper.properties is relative. Prepend ./ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,781] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,781] INFO secureClientPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,781] INFO observerMasterPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,781] INFO metricsProvider.className is org.apache.zookeeper.metrics.impl.DefaultMetricsProvider (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2022-02-22 21:43:12,781] INFO Starting server (org.apache.zookeeper.server.ZooKeeperServerMain)
[2022-02-22 21:43:12,788] INFO ServerMetrics initialized with provider org.apache.zookeeper.metrics.impl.DefaultMetricsProvider@341b80b2 (org.apache.zookeeper.server.ServerMetrics)
[2022-02-22 21:43:12,791] INFO zookeeper.snapshot.trust.empty : false (org.apache.zookeeper.server.persistence.FileTxnSnapLog)
[2022-02-22 21:43:12,797] INFO  (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,797] INFO   ______                  _                                           (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO  |___  /                 | |                                          (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO     / /    ___     ___   | | __   ___    ___   _ __     ___   _ __    (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO    / /    / _ \   / _ \  | |/ /  / _ \  / _ \ | '_ \   / _ \ | '__| (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO   / /__  | (_) | | (_) | |   <  |  __/ |  __/ | |_) | |  __/ | |     (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO  /_____|  \___/   \___/  |_|\_\  \___|  \___| | .__/   \___| |_| (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO                                               | |                      (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO                                               |_|                      (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,798] INFO  (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,805] INFO Server environment:zookeeper.version=3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,805] INFO Server environment:host.name=usmb113823.attlocal.net (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,805] INFO Server environment:java.version=15.0.2 (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,805] INFO Server environment:java.vendor=Oracle Corporation (org.apache.zookeeper.server.ZooKeeperServer)
[2022-02-22 21:43:12,805] INFO Server environment:java.home=/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home (org.apache.zookeeper.server.ZooKeeperServer)
.
.
.
.

```

### To start the kafka server 
* $ bin/kafka-server-start.sh config/server.properties
```
[2022-02-22 21:43:42,333] INFO Registered kafka:type=kafka.Log4jController MBean (kafka.utils.Log4jControllerRegistration$)
[2022-02-22 21:43:42,519] INFO Setting -D jdk.tls.rejectClientInitiatedRenegotiation=true to disable client-initiated TLS renegotiation (org.apache.zookeeper.common.X509Util)
[2022-02-22 21:43:42,579] INFO Registered signal handlers for TERM, INT, HUP (org.apache.kafka.common.utils.LoggingSignalHandler)
[2022-02-22 21:43:42,582] INFO starting (kafka.server.KafkaServer)
[2022-02-22 21:43:42,583] INFO Connecting to zookeeper on localhost:2181 (kafka.server.KafkaServer)
[2022-02-22 21:43:42,594] INFO [ZooKeeperClient Kafka server] Initializing a new session to localhost:2181. (kafka.zookeeper.ZooKeeperClient)
[2022-02-22 21:43:42,603] INFO Client environment:zookeeper.version=3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT (org.apache.zookeeper.ZooKeeper)
[2022-02-22 21:43:42,603] INFO Client environment:host.name=usmb113823.attlocal.net (org.apache.zookeeper.ZooKeeper)
[2022-02-22 21:43:42,603] INFO Client environment:java.version=15.0.2 (org.apache.zookeeper.ZooKeeper)
[2022-02-22 21:43:42,603] INFO Client environment:java.vendor=Oracle Corporation (org.apache.zookeeper.ZooKeeper)
[2022-02-22 21:43:42,603] INFO Client environment:java.home=/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home (org.apache.zookeeper.ZooKeeper)

.
.
.
.

```
### To create a kafka topic  
* $ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic espark-topic
```
Created topic espark-topic.
```

### To list the kafka topisc 
* $ bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
espark-topic
```

## Test open the two seprate terminal and type the message 
### Produce a message 
* bin/kafka-console-producer.sh --broker-list localhost:9092 --topic espark-topic
```
>hi 
>welcome to the epark kafka example 
```

### Consumer a message 
* bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic espark-topic
```
hi 
welcome to the epark kafka example 
```

---
# tbd 
## kafka ui tool 
* https://github.com/provectus/kafka-ui
```
docker run -p 8080:8080 \
	-e KAFKA_CLUSTERS_0_NAME=Default \
	-e KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=localhost:9092 \
	-e KAFKA_CLUSTERS_0_ZOOKEEPER=localhost:2181 \
	 provectuslabs/kafka-ui:latest
```

### To access the ui tool 
* http://localhost:8080

### other options 
* https://github.com/cloudhut/kowl
