package com.dollarsbank;

import java.util.ArrayList;
import java.util.Scanner;

import com.dollarsbank.controller.BankController;
import com.dollarsbank.exceptions.TransactionException;
import com.dollarsbank.exceptions.UnknownAccountException;
import com.dollarsbank.model.*;

public class BankDriver {
	public static void main(String[] args) {
		boolean running = true;
		BankController bank = new BankController(0, new ArrayList<Account>());
		Scanner scanner = new Scanner(System.in);
		bank.register("Rohan Kurup", "2036431234", "1849 Makeshift Road", 1000.0, "password1" );
		while(running) {
			
			System.out.println("+----------------------------+");
			System.out.println("|  DOLLARSBANK Welcomes You  |");
			System.out.println("+----------------------------+");
			System.out.println("1. Create New Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			
			int input = 0;
			boolean flag = false;
			while(!flag) {
				System.out.println("Please input 1, 2, or 3.");
				input = scanner.nextInt();
				if(input == 1 || input == 2 || input == 3) {
					flag = true;
				}
				scanner.nextLine();
			}
			if(input == 1) {
				System.out.println("+---------------------------+");
				System.out.println("|   Enter Account Details   |");
				System.out.println("+---------------------------+");
				String name, address, number, password;
				double amount;
				System.out.println("Customer Name:");
				name = scanner.nextLine();
				System.out.println("Customer Address:");
				address = scanner.nextLine();
				System.out.println("Customer Number: ");
				number = scanner.nextLine();
				System.out.println("Customer Password:");
				password = scanner.nextLine();
				System.out.println("Customer Initial Deposit:");
				amount = scanner.nextDouble();
				bank.register(name, number, address, amount, password);
				System.out.println("Your user id is " + (bank.getIdCounter() - 1));
				System.out.println("Please Remember this, as you will use it to log in");	
			
			}
			if(input == 2) {
				System.out.println("+----------------------------+");
				System.out.println("|           Log In           |");
				System.out.println("+----------------------------+");
				System.out.println("Please type your id");
				int logInId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Please type your password");
				String pass = scanner.nextLine();
				boolean loggedIn = false;
				try {
					if(bank.authenticate(logInId, pass)) {
						System.out.println("Logged in as " + logInId);
						loggedIn = true;
					}
					else {
						while(!loggedIn) {
							System.out.println("Incorrect login information. Please try again");
							System.out.println("Please type your id");
							logInId = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Please type your password");
							pass = scanner.nextLine();
							if(bank.authenticate(logInId, pass)) {
								System.out.println("Logged in as " + bank.getAccount(logInId).getName());
								loggedIn = true;
							}
						}
					}
				} catch (UnknownAccountException e) {
					System.out.println(e.getMessage());
				}
				while(loggedIn) {
					System.out.println("+----------------------------+");
					System.out.println("|      Welcome Customer      |");
					System.out.println("+----------------------------+");
					System.out.println("1. Deposit Amount");
					System.out.println("2. Withdraw Amount");
					System.out.println("3. Transfer Funds");
					System.out.println("4. View most recent Transctions");
					System.out.println("5. Display Info");
					System.out.println("6. Sign out");
					int nextAction = scanner.nextInt();
					switch(nextAction) {
						case 1:
							boolean deposited = false;
							while(!deposited) {
								System.out.println("How much would you like to deposit?");
								double depAmount = scanner.nextDouble();
								try {
									bank.deposit(logInId, depAmount);
									deposited = true;
								} catch (TransactionException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
							}
							break;
						case 2:
							boolean withdrawn = false;
							while(!withdrawn) {
								System.out.println("How much would you like to withdraw?");
								System.out.println("Should you withdraw more than you possess, a $25 overdraft fee will be assessed");
								double withAm = scanner.nextDouble();
								try {
									bank.withdraw(logInId, withAm);
									withdrawn = true;
								} catch (TransactionException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								
							}
							break;
						case 3:
							boolean transfer = false;
							while(!transfer) {
								System.out.println("How much would you like to transfer?");
								System.out.println("Should you transfer more than you possess, a $25 overdraft fee will be assessed");
								double transAm = scanner.nextDouble();
								System.out.println("Please enter the account id of whom you wish to transfer to:");
								int transId = scanner.nextInt();
								scanner.nextLine();
								try {
									System.out.println("You wish to transfer $" + transAm + " to " + bank.getAccount(transId).getName() + "?");
								}  catch (UnknownAccountException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								System.out.println("Please type y or Y to confirm");
								String con = scanner.nextLine();
								if(con.equals("y") || con.equals("Y")) {
									try {
										bank.fundsTransfer(bank.getAccount(logInId), bank.getAccount(logInId), transAm);
										transfer = true;
									} catch (TransactionException e) {
										// TODO Auto-generated catch block
										System.out.println(e.getMessage());
									} catch (UnknownAccountException e) {
										// TODO Auto-generated catch block
										System.out.println(e.getMessage());
									}
								}
							}
							break;
						case 4:
							bank.printTransactions(logInId);
							break;
						case 5:
							bank.printAccount(logInId);
							break;
						case 6:
							loggedIn = false;
							break;
						}
					}
				}
				
			}
			
		}
	}
