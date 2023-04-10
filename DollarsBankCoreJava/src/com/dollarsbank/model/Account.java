package com.dollarsbank.model;

import java.util.ArrayList;

public class Account {
	
	private Integer id;
	private String name;
	private String phoneNum;
	private String address;
	private double amount;
	private String password;
	private ArrayList<Transaction> trans;
	
	
	
	public Account(Integer id, String name, String phoneNum, String address, double amount, String password,
			ArrayList<Transaction> trans) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
		this.amount = amount;
		this.password = password;
		this.trans = trans;
	}
	public Account(int id, String name, String phoneNum, String address, double amount, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
		this.amount = amount;
		this.password = password;
		this.trans = new ArrayList<Transaction>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Transaction> getTrans() {
		return trans;
	}
	public void setTrans(ArrayList<Transaction> trans) {
		this.trans = trans;
	}
	public void addTrans(Transaction tran) {
		this.trans.add(0, tran);
		if(trans.size() == 6) {
			trans.remove(5);
		}
	}
	

}
