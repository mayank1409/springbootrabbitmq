package net.javaguides.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.javaguides.rabbitmq.dto.User;

@Service
public class RabbitMQJsonProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	@Value("${rabbitmq.exchange.name:javaguides_exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key:javaguides_routing_key_json}")
	private String routingKeyJson;
	
	private final RabbitTemplate rabbitTemplate;
	
	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(User user) {
		LOGGER.info(String.format("Message sent -> %s", user));
		rabbitTemplate.convertAndSend(exchange, routingKeyJson, user);
	}
}
