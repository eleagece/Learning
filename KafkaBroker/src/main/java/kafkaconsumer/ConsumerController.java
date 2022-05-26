package kafkaconsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.simple.JSONArray;

import utils.Constants;
import utils.JsonUtils;
import utils.Utils;

public class ConsumerController {

	Consumer<String, String> consumer;

	/**
	 * Configures and creates the consumer.
	 * 
	 * @throws IOException
	 */
	public ConsumerController() throws IOException {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.BOOTSTRAP_SERVERS_CONFIG));
		props.put(ConsumerConfig.GROUP_ID_CONFIG,
				Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.GROUP_ID_CONFIG));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
				Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.AUTO_OFFSET_RESET_CONFIG));
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.parseBoolean(
				Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.ENABLE_AUTO_COMMIT_CONFIG)));
		this.consumer = new KafkaConsumer<>(props);
	}

	/**
	 * Polls the topic in search for new messages.
	 * 
	 * @throws Exception
	 * 
	 */
	public void pollTopic() throws Exception {
		String topicName = Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.TOPIC_NAME);
		consumer.subscribe(Collections.singleton(topicName));
		while (true) {
			System.out.println("[" + Utils.getCurrentTime() + "] Polling to topic...");
			long ms = Long.parseLong(Constants.ConfigProperties.optionsMap.get(Constants.ConfigProperties.MS_POLL_WAIT));
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(ms));
			for (ConsumerRecord<String, String> record : records) {
				JSONArray jsonArray = (JSONArray) JsonUtils.fromString(record.value());
				this.printMessages(jsonArray);
			}
			consumer.commitAsync();
		}
	}

	/**
	 * Prints the contents of JSONArray 'jsonArray'
	 * 
	 * @param jsonArray - the JSONArray to be printed.
	 * @throws Exception
	 */
	private void printMessages(JSONArray jsonArray) throws Exception {
		JsonUtils.printJsonObject(Constants.JsonUtils.PERSONS_LIST, jsonArray);
	}
}
