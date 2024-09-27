package atm.service;

import java.util.Date;

import atm.database.AccountDAO;
import atm.database.TransactionDAO;
import atm.database.UserDAO;
import atm.model.Account;
import atm.model.Transaction;
import atm.model.User;

public class Bank {

	 private UserDAO userDAO;
	    private AccountDAO accountDAO;
	    private TransactionDAO transactionDAO;
	    
	    public Bank() {
	    	userDAO = new UserDAO();
	    	accountDAO = new AccountDAO();
	    	transactionDAO = new TransactionDAO();
	    }
	    
	    public User authenticateUser(int userId, String pin) {
	        return userDAO.getUserByIdAndPin(userId, pin);
	    }

	    public double checkBalance(int userId) {
	        Account account = accountDAO.getAccountByUserId(userId);
	        return account != null ? account.getBalance() : 0.0;
	    }

	    public void deposit(int userId, double amount) {
	        Account account = accountDAO.getAccountByUserId(userId);
	        if (account != null) {
	            double newBalance = account.getBalance() + amount;
	            accountDAO.updateAccountBalance(account.getAccountId(), newBalance);

	            Transaction transaction = new Transaction(0, account.getAccountId(), "Deposit", amount, new Date());
	            transactionDAO.saveTransaction(transaction);
	        } else {
	            System.out.println("Account not found.");
	        }
	    }

	    public void withdraw(int userId, double amount) {
	        Account account = accountDAO.getAccountByUserId(userId);
	        if (account != null) {
	            if (account.getBalance() >= amount) {
	                double newBalance = account.getBalance() - amount;
	                accountDAO.updateAccountBalance(account.getAccountId(), newBalance);

	                Transaction transaction = new Transaction(0, account.getAccountId(), "Withdrawal", amount, new Date());
	                transactionDAO.saveTransaction(transaction);
	            } else {
	                System.out.println("Insufficient balance.");
	            }
	        } else {
	            System.out.println("Account not found.");
	        }
	    }

	   
	    public void transfer(int fromUserId, int toUserId, double amount) {
	        Account fromAccount = accountDAO.getAccountByUserId(fromUserId);
	        Account toAccount = accountDAO.getAccountByUserId(toUserId);
	        
	        if (fromAccount != null && toAccount != null) {
	            if (fromAccount.getBalance() >= amount) {
	                double newFromBalance = fromAccount.getBalance() - amount;
	                double newToBalance = toAccount.getBalance() + amount;

	                accountDAO.updateAccountBalance(fromAccount.getAccountId(), newFromBalance);
	                accountDAO.updateAccountBalance(toAccount.getAccountId(), newToBalance);

	                Transaction fromTransaction = new Transaction(0, fromAccount.getAccountId(), "Transfer Out", amount, new Date());
	                Transaction toTransaction = new Transaction(0, toAccount.getAccountId(), "Transfer In", amount, new Date());

	                transactionDAO.saveTransaction(fromTransaction);
	                transactionDAO.saveTransaction(toTransaction);
	            } else {
	                System.out.println("Insufficient balance for transfer.");
	            }
	        } else {
	            System.out.println("One or both accounts not found.");
	        }
	    }


	    public void viewTransactionHistory(int userId) {

	    }
}
