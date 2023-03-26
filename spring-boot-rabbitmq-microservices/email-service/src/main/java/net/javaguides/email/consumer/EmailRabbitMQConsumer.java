package net.javaguides.email.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.javaguides.email.dto.OrderEvent;

@Service
public class EmailRabbitMQConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailRabbitMQConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.email.queue.name:email_queue}")
	public void consumeOrder(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event received -> %s ", orderEvent));
		
		// send email to customer here...
	}
		
}
