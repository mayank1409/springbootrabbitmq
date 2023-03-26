package net.javaguides.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = {"${rabbitmq.queue.name:javaguides}"})
	public void consume(String message) {
		LOGGER.info(String.format("Message received -> %s ", message));
	}
	
}