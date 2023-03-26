package net.javaguides.orders.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.javaguides.orders.dto.OrderEvent;

@Service
public class OrderRabbitMQProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRabbitMQProducer.class);

	@Value("${rabbitmq.order.exchange.name:order_exchange}")
	private String orderExchange;

	@Value("${rabbitmq.stock.routing.key:stock_routing_key}")
	private String stockRoutingKey;

	@Value("${rabbitmq.email.routing.key:email_routing_key}")
	private String emailRoutingKey;

	private final RabbitTemplate rabbitTemplate;

	public OrderRabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendOrderEvent(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event placed on queue -> %s ", orderEvent));
		
		// Publishing order event to stock queue
		rabbitTemplate.convertAndSend(orderExchange, stockRoutingKey, orderEvent);
		
		// Publish order event to email service
		rabbitTemplate.convertAndSend(orderExchange, emailRoutingKey, orderEvent);
	}

}
