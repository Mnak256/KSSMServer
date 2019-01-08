package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business_logic.LoginOperation;
import model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LoginOperation loginOperation = new LoginOperation();
		HashMap<Object, Object> userHashMap = new HashMap<Object, Object>();
		userHashMap.put("email", email);
		userHashMap.put("password", password);
		User user = (User) loginOperation.execute(userHashMap);
		if (user == null) {
			response.sendRedirect("error.html?er=Email+or+Password+Incorrect.");
		} else {
			request.getSession(true).setAttribute("email", email);
			response.sendRedirect("services.jsp");
		}
	}

}
