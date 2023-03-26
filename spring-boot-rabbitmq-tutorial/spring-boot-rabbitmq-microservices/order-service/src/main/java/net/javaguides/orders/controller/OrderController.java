package net.javaguides.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.orders.dto.Order;
import net.javaguides.orders.dto.OrderEvent;
import net.javaguides.orders.publisher.OrderRabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	private final OrderRabbitMQProducer producer;
	
	public OrderController(OrderRabbitMQProducer producer) {
		this.producer = producer;
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<OrderEvent> placeOrder(@RequestBody Order order) {
		OrderEvent event = new OrderEvent();
		event.setStatus("PLACED");
		event.setMessage("Order Placed");
		event.setOrder(order);
		LOGGER.info(String.format("Order from client -> %s ", order));
		producer.sendOrderEvent(event);
		return new ResponseEntity<OrderEvent>(event, HttpStatus.ACCEPTED);
	}
}
