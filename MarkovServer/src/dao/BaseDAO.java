package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	public Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/KSSMServer?user=mainak&password=@Override256");
		} catch(ClassNotFoundException e) {
			System.out.println(e);
			return null;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
}
