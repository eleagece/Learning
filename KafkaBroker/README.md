# **Kafka Broker**

## Keywords
* Java, Kafka, CLI, Docker, JSON.

## Dev notes
* The goal of this exercise is to implement a CLI application that produces information into a Kafka message broker that runs on a Docker container.
* Developed in [**Java**](https://www.java.com/es/) language and [**Maven**](https://maven.apache.org/) as building tool.

## Installations
* [**Docker Desktop**](https://hub.docker.com/editions/community/docker-ce-desktop-windows/) for Windows:
    * Needed for the image that will run the Kafka Broker.
    * Wizard installation. May ask to install WSL2 linux kernel (accept).
    * Restart.
* [**Kafka scripts**](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.7.0/kafka_2.12-2.7.0.tgz) for Windows:
    * Needed for the scripts to create topics in the Kafka Broker .
    * Extract to an easy path, for example `c:/kafka/`.
* [**Offset explorer**](https://www.kafkatool.com/download.html) for Windows:
    * Needed to easy check of the Kafka Broker: created topics, producers, consumers, messages...
    * Wizard installation.

## Setup
* **Docker**:
    * The Kafka Broker will run in a docker container.
    * Create a directory for `docker-compose.yml` file. For example  `c:/docker/`.
    * Create the `docker-compose.yml` in the directory:
        ```
        version: "3"
        services:
          zookeeper:
            image: 'bitnami/zookeeper:latest'
            ports:
              - '2181:2181'
            environment:
              - ALLOW_ANONYMOUS_LOGIN=yes
          kafka:
            image: 'bitnami/kafka:latest'
            ports:
              - '9092:9092'
            environment:
              - KAFKA_BROKER_ID=1
              - KAFKA_LISTENERS=PLAINTEXT://:9092
              - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
              - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
              - ALLOW_PLAINTEXT_LISTENER=yes
            depends_on:
              - zookeeper
        ```
    * From a `cmd` in `c:/docker/` execute the command `docker-compose up -d`.
    * Wait for the container to be running (it could be checked in Docker Desktop).    
* **Kafka scripts**:
    * From a `cmd` in `c:/kafka/kafka_2.12–2.7.0/bin/windows`.
    * Execute the command `kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafkaTestTopic` which will create a topic with 1 partition in the Kafka Broker created in Docker.
* **Offset explorer**:
    * Open the program and in the `Clusters` folder right click and `Add new connection...`.
    * Cluster name set to `1` and click on `Add`.
    * Right click on the new created connection and click `Connect`.
    * Just explore the `Brokers`, `Topics`, `Consumers`... and how they change when the application is used (Usage chapter next).
* **Code and JAR**:
    * From your git client prompt, go to a folder of your choice and clone the repository with the code and the already generated JAR:
    `git clone https://github.com/eleagece/Learning.git`. The code for this project is on `MessageBroker` folder.
    * The `MessageBroker` project has two entry points which are in `src/main/java/kafkaproducer/ProducerApplication.java` and `src/main/java/kafkaconsumer/ConsumerApplication.java`.
	* One JAR file has been created for each one of them and are included in `jars/` folder.

## Usage
* **ConsumerApplication**:
    * The **ConsumerApplication** listens every five seconds to the configured topic waiting for new messages. These messages should have the asked sctructure. 
    * The application is included in `jars/ConsumerApplication.jar`.
    * From a `cmd` just launch the command `java -jar ConsumerApplication.jar`. Every five seconds the polling message appears, and if a message is read from topic its contets are shown.
* **ProducerApplication**: 
    * The **ProducerApplication** produces a message taken from a JSON file and is sent to the topic. 
    * The application is included in `jars/ProducerApplication.jar`.
    * From another `cmd` just launch the command `java -jar ProducerApplication.jar -send "C:/route/to/file.json"`. The message will be sent to the topic and the **ConsumerApplication** will consume it in the next poll.
* **JSON files**:
    * A couple of JSON files to test are included in `src/main/resources/messages/`.