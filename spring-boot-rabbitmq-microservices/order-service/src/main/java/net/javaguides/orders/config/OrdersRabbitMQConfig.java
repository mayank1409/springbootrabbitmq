package net.javaguides.orders.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersRabbitMQConfig {
	
	@Value("${rabbitmq.stock.queue.name:stock_queue}")
	private String stockQueue;
	
	@Value("${rabbitmq.email.queue.name:email_queue}")
	private String emailQueue;
	
	@Value("${rabbitmq.order.exchange.name:order_exchange}")
	private String orderExchange;
	
	@Value("${rabbitmq.stock.routing.key:stock_routing_key}")
	private String stockRoutingKey;
	
	@Value("${rabbitmq.email.routing.key:email_routing_key}")
	private String emailRoutingKey;

	@Bean
	public Queue stockQueue() {
		return new Queue(stockQueue);
	}
	
	@Bean
	public Queue emailQueue() {
		return new Queue(emailQueue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(orderExchange);
	}
	
	@Bean
	public Binding stockBinding() {
		return BindingBuilder.bind(stockQueue()).to(exchange()).with(stockRoutingKey);
	}
	
	@Bean
	public Binding emailBinding() {
		return BindingBuilder.bind(emailQueue()).to(exchange()).with(emailRoutingKey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
