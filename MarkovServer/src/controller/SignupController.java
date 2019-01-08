package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business_logic.SignupOperation;

@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SignupController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		SignupOperation signupOperation = new SignupOperation();
		HashMap<Object, Object> userHashMap = new HashMap<Object, Object>();
		userHashMap.put("email", email);
		userHashMap.put("password", password);
		if ((boolean) signupOperation.execute(userHashMap)) {
			servletOutputStream.print("Signup Success");
		} else {
			servletOutputStream.print("Signup Error.");
		}
		
	}

}
