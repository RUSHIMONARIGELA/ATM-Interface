package atm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import atm.model.Account;

public class AccountDAO {
	public Account getAccountByUserId(int userId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM accounts WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Account(
                    resultSet.getInt("accountId"),
                    resultSet.getInt("userId"),
                    resultSet.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccountBalance(int accountId, double newBalance) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE accounts SET balance = ? WHERE accountId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, newBalance);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
