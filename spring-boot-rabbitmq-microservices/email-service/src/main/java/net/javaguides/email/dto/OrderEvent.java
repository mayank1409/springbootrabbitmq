package net.javaguides.email.dto;

import lombok.Data;

@Data
public class OrderEvent {
	
	private String status;
	
	private String message;
	
	private Order order;

}
