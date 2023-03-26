package net.javaguides.orders.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order {
	
	private int id;
	
	private String name;
	
	private int qty;
	
	private BigDecimal price;
}
