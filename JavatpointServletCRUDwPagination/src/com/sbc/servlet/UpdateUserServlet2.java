package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbc.dao.UserDao;
import com.sbc.model.User;

@WebServlet("/UpdateUserServlet2")
public class UpdateUserServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    UserDao dao = new UserDao();
    User user = new User();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		user.setId(Integer.parseInt(id));
		user.setFirstname(firstname);
		user.setLastname(lastname); 
		
		int status = 0;
		status = dao.UpdateUser(user);
		
		if(status != 0) {
			writer.append("<p>User is successfully updated!</p>");
		}
		else {
			writer.append("<p>Unable to update user. Please try again later!</p>");
		}
		
		/* return the list of users */
		response.sendRedirect("ListUsersServlet");
	
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
