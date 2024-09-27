package atm.database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import atm.model.User;

//import atm.database.MyConnection;


public class UserDAO {
	Connection connection;
	PreparedStatement pStatement;

	public User getUserByIdAndPin(int userId, String pin) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE userId = ? AND pin = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, pin);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                    resultSet.getInt("userId"),
                    resultSet.getString("name"),
                    resultSet.getString("pin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
