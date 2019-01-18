package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business_logic.CompilerOperation;

@WebServlet("/CompilerController")
public class CompilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CompilerController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sourceCode = request.getParameter("code");
		CompilerOperation compilerOperation = new CompilerOperation();
		HashMap<Object, Object> sourceCodeHashMap = new HashMap<Object, Object>();
		sourceCodeHashMap.put("code", sourceCode);
		String output = (String) compilerOperation.execute(sourceCodeHashMap);
		response.getWriter().print(output);
	}

}
