package atm.model;

public class Account {

	private int accountId;
	private int userId;
	private double balance;
	
	public Account(int accountId, int userId, double balance) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public int getUserId() {
		return userId;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance=balance;
	}
}
