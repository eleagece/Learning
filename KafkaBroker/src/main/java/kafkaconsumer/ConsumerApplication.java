package kafkaconsumer;

import java.io.IOException;

public class ConsumerApplication {
	public static void main(String[] args) throws IOException {
		try {
			// Consumer.
			ConsumerController consumerController = new ConsumerController();
			consumerController.pollTopic();
		} catch (Exception e) {
			System.err.format(e.getMessage());
		}
	}
}