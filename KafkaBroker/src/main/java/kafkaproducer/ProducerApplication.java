package kafkaproducer;

import utils.Constants;
import utils.JsonUtils;

import java.util.Map;

public class ProducerApplication {
	public static void main(String[] args) {
		try {
			// Parsing the CLI command.
			// Example: 'java -jar ProducerApplication -send "c:/route/to/list01.json"'.
			Parser parser = new Parser(args);
			Map<String, String> optionsValues = parser.parse();
			// Obtaining JSON string from JSON file.
			String recordValue = JsonUtils.toString(Constants.JsonUtils.PERSONS_LIST,
					optionsValues.get(Constants.Parser.OPTION_SEND));
			// Producer.
			ProducerController producerController = new ProducerController();
			producerController.send(recordValue);
		} catch (Exception e) {
			System.err.format(e.getMessage());
		}
	}
}