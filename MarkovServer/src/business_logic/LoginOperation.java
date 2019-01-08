package business_logic;

import java.sql.Connection;
import java.util.HashMap;

import dao.BaseDAO;
import dao.UserDAO;
import model.User;

public class LoginOperation extends BaseOperation {

	@Override
	public Object execute(HashMap<Object, Object> map) {
		BaseDAO baseDAO = new BaseDAO();
		Connection connection = baseDAO.createConnection();
		UserDAO userDAO = new UserDAO(connection);
		String email = (String) map.get("email");
		String password = (String) map.get("password");
		User user = userDAO.retrive(email, password);
		return user;
	}

}
