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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		
		int status = 0;
		status = dao.DeleteUser(Integer.parseInt(id));

		if(status != 0) {
			writer.append("<p>User is successfully deleted!</p>");
		}
		else {
			writer.append("<p>Unable to delete user. Please try again later!</p>");
		}
		
		/* return the list of users */
		RequestDispatcher rd = request.getRequestDispatcher("ListUsersServlet");
		rd.include(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
