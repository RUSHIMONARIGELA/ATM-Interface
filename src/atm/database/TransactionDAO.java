package atm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import atm.model.Transaction;

public class TransactionDAO {

	public void saveTransaction(Transaction transaction) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO transactions (accountId, transactionType, amount, transactionDate) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, transaction.getAccountId());
            statement.setString(2, transaction.getTransactionType());
            statement.setDouble(3, transaction.getAmount());
            statement.setDate(4, new java.sql.Date(transaction.getTransactionDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
