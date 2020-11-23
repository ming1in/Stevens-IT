package quizzes.quiz_1;

/*
Vincent Tufo, Ming Lin
CS-284 Quiz 1
I pledge my honor that I have abided by the Stevens Honor System.
*/

public class SavingsAccount extends BankAccount{

	private double rate;
	
	public SavingsAccount(double rate) {
		this.rate = rate;
	}
	
	void addInterest() {
		this.deposit(this.getBalance() * rate);
	}
	
}
