package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sbc.dao.UserDao;
import com.sbc.model.User;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserDao dao = new UserDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 1);
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		user.setFirstname(firstname);
		user.setLastname(lastname);
			
		int status = 0;
		if((firstname != "" && firstname != null && lastname != "" && lastname != null)){
			status = dao.AddUser(user);
			if(status == 0){
				response.sendRedirect(request.getContextPath() + "/index.html");
				writer.append("<p>Unable to add user. Please try again!</p>");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/index.html");
				writer.append("<p>User is added successfully to the Database!</p>");
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/index.html");
			writer.append("<p>Firstname and Lastname can't be empty.</p>");
		}

		writer.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
