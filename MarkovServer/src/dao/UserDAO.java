package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.User;

public class UserDAO extends BaseDAO {
	
	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	
	public User retrive(String email, String password) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select email from users where email = ? and password = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setEmail(email);
				user.setPassword(password);
			}
			return user; //returns null if email or password is incorrect.
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Boolean insert(String email, String password) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into users(email, password) values(?, ?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			if (preparedStatement.executeUpdate() > 0) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			return Boolean.FALSE;
		} catch(SQLException e) {
			return Boolean.FALSE;
		}
	}
	
}
