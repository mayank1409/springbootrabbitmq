package net.javaguides.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.rabbitmq.dto.User;
import net.javaguides.rabbitmq.publisher.RabbitMQJsonProducer;
import net.javaguides.rabbitmq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	private RabbitMQProducer producer;
	
	private RabbitMQJsonProducer jsonProducer;
	
	@Autowired
	public MessageController(RabbitMQProducer producer, RabbitMQJsonProducer jsonProducer) {
		this.producer = producer;
		this.jsonProducer = jsonProducer;
	}

	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam String message) {
		producer.sendMessage(message);
		return new ResponseEntity<>("Message Sent successfully to RabbitMQ", HttpStatus.OK);
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestBody User user) {
		jsonProducer.sendMessage(user);
		return new ResponseEntity<>("Message Sent successfully to RabbitMQ", HttpStatus.OK);
	}
}
