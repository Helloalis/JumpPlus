package com.dollarsbank.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.dollarsbank.exceptions.TransactionException;
import com.dollarsbank.exceptions.UnknownAccountException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Transaction;

public class BankController {
	private int idCounter;
	private ArrayList<Account> accounts;
	
	public BankController(int idCounter, ArrayList<Account> accounts) {
		super();
		this.idCounter = idCounter;
		this.accounts = accounts;
	}	
	public boolean authenticate(int id, String password) {
		if(password.equals(this.getAccounts().get(id).getPassword())) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	public Account getAccount(int id) throws UnknownAccountException {
		if(accounts.size() < id) {
			throw new UnknownAccountException("No account exists with id " + id);
		}
		return accounts.get(id);
	}
	
	public boolean register(String nam, String phone, String add, double amount, String password) {
		accounts.add(new Account(idCounter, nam, phone, add, amount, password));
		idCounter++;
		return true;
	}
	
	public void deposit(int id, double value) throws TransactionException {
		if(value <= 0) {
			throw new TransactionException("Value must be above 0");
		}
		Account account = accounts.get(id);
		account.setAmount(account.getAmount()+value);
		account.addTrans(new Transaction(LocalDateTime.now(), value, id, null));
		accounts.set(id, account);
	}
	public void withdraw (int id, double value) throws TransactionException {
		if(value <= 0) {
			throw new TransactionException("Value must be above 0");
		}
		Account account = accounts.get(id);
		account.setAmount(account.getAmount()-value);
		account.addTrans(new Transaction(LocalDateTime.now(), value, id, null));
		accounts.set(id, account);
	}
	
	public void fundsTransfer(Account root, Account receive, double value) throws TransactionException {
		if(value <= 0) {
			throw new TransactionException("Transfer value must be greater than zero");
		}
		receive.setAmount(receive.getAmount() + value);
		receive.addTrans(new Transaction(LocalDateTime.now(), value, root.getId(), receive.getId()));
		root.setAmount(root.getAmount() - value);
		root.addTrans(new Transaction(LocalDateTime.now(), value, root.getId(), receive.getId()));
		accounts.set(root.getId(), root);
		accounts.set(receive.getId(), receive);
		
	}

	public int getIdCounter() {
		return idCounter;
	}

	public void setIdCounter(int idCounter) {
		this.idCounter = idCounter;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	public void printTransactions(int logInId) {
		System.out.println(accounts.get(logInId).getTrans());
		
	}
	public void printAccount(int logInId) {
		System.out.println(accounts.get(logInId));
		
	}
	
	
		

}
