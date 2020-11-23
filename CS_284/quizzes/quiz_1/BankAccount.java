package quizzes.quiz_1;

/*
Vincent Tufo, Ming Lin
CS-284 Quiz 1
I pledge my honor that I have abided by the Stevens Honor System.
*/

public class BankAccount {
	
	private double balance;
	
	public BankAccount() {
		balance = 0;
	}
	
	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdraw(double amount) {
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient funds.");
		}
		else {
			balance -= amount;
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void transfer(double amount, BankAccount destination) {
		this.withdraw(amount);
		destination.deposit(amount);
	}
		
}
