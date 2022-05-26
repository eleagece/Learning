package utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	/**
	 * Parser class related constants. If new options for the CLI are created, they
	 * should be added here in order to manage the parsing correctly.
	 */
	public static class Parser {
		public static final String OPTION_SEND = "-send";
		public static final Map<String, Boolean> optionsMap = new HashMap<String, Boolean>();
		static {
			optionsMap.put(Constants.Parser.OPTION_SEND, true);
		}
	}

	/**
	 * JsonUtils class related constants. If new JSON files with different
	 * structures are created, they should be added here in order to manage JSON
	 * validation.
	 */
	public static class JsonUtils {
		public static final String PERSONS_LIST = "Persons list";
	}

	/**
	 * Configuration related properties.
	 */
	public static class ConfigProperties {
		// Producer and consumer configuration
		public static final String TOPIC_NAME = "topicName";
		public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
		// Consumer configuration
		public static final String GROUP_ID_CONFIG = "group.id";
		public static final String AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset";
		public static final String ENABLE_AUTO_COMMIT_CONFIG = "enable.auto.commit";
		public static final String MS_POLL_WAIT = "msPollWait";
		// Map with the default values for the configuration properties
		public static final Map<String, String> optionsMap = new HashMap<String, String>();
		static {
			optionsMap.put(Constants.ConfigProperties.TOPIC_NAME, "kafkaTestTopic");
			optionsMap.put(Constants.ConfigProperties.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			optionsMap.put(Constants.ConfigProperties.GROUP_ID_CONFIG, "consumer-group");
			optionsMap.put(Constants.ConfigProperties.AUTO_OFFSET_RESET_CONFIG, "earliest");
			optionsMap.put(Constants.ConfigProperties.ENABLE_AUTO_COMMIT_CONFIG, "false");
			optionsMap.put(Constants.ConfigProperties.MS_POLL_WAIT, "5000");
		}
	}

}
