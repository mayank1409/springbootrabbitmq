package net.javaguides.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.javaguides.rabbitmq.dto.User;

@Service
public class RabbitMQJsonConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

	@RabbitListener(queues = {"${rabbitmq.queue.json.name:javaguides_json}"})
	public void consume(User user) {
		LOGGER.info(String.format("Json Message received -> %s ", user));
	}
	
}
