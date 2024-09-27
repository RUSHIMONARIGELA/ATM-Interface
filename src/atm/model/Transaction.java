package atm.model;

import java.util.Date;

public class Transaction {

	private int transactionId;
	private int accountId;
	private String transactionType;
	private double amount;
	private Date transactionDate;
	
	
	public Transaction(int transactionId, int accountId, String transactionType, double amount, Date transactionDate) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public int getAccountId() {
		return accountId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	
}
