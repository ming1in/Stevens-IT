package quiz1;

/*
Vincent Tufo, Ming Lin
CS-284 Quiz 1
I pledge my honor that I have abided by the Stevens Honor System.
*/

public class CheckingAccount extends BankAccount{

	private int transactions;
	
	public CheckingAccount(double initialBalance) {
		super(initialBalance);
		transactions = 0;
	}
	
	public void withdraw(double amount) {
		super.withdraw(amount);
		transactions++;
	}
	
	public void deposit(double amount) {
		super.deposit(amount);
		transactions++;
	}
	
	public void transfer(double amount, BankAccount destination) {
		super.transfer(amount, destination);
		transactions++;
	}
	
}
