package kafkaproducer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import utils.Constants;
import utils.Utils;

public class ProducerController {

	Producer<String, String> producer;

	/**
	 * Configures and creates the producer.
	 * 
	 * @throws IOException
	 */
	public ProducerController() throws IOException {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.BOOTSTRAP_SERVERS_CONFIG));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		this.producer = new KafkaProducer<>(props);
	}

	/**
	 * Sends the message with the info 'recordValue' to the topic 'topicName'. It
	 * also closes the producer 'this.producer'.
	 * 
	 * @param recordValue - the value to sent to the topic 'topicName' through the
	 *                    producer 'this.producer'.
	 */
	public void send(String recordValue) {
		String topicName = Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.TOPIC_NAME);
		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, null, recordValue);
		System.out.println("[" + Utils.getCurrentTime() + "] Message sent to topic '" + topicName + "'");
		this.producer.send(record);
		this.producer.flush();
		this.producer.close();
	}

}
