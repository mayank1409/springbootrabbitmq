package net.javaguides.stock.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.javaguides.stock.dto.OrderEvent;

@Service
public class StockRabbitMQConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockRabbitMQConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.stock.queue.name:stock_queue}")
	public void consumeOrder(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event received -> %s ", orderEvent));
		// Stock Service Logic here...
	}
}
