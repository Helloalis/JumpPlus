package com.dollarsbank.model;

import java.time.LocalDateTime;

public class Transaction {
	private LocalDateTime time;
	private double value;
	private Integer source;
	private Integer recieve;
	
	
	
	public Transaction(LocalDateTime time, double value, Integer source, Integer recieve) {
		super();
		this.time = time;
		this.value = value;
		this.source = source;
		this.recieve = recieve;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getRecieve() {
		return recieve;
	}
	public void setRecieve(Integer recieve) {
		this.recieve = recieve;
	}



}