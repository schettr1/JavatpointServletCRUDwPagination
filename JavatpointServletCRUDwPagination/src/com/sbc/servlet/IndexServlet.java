package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.append("<h2>This is IndexServlet</h2>");
		writer.append("<form action='AddUserServlet' method='POST'>");
		writer.append("<div>");
		writer.append("<label for='firstname'>First Name: ");
		writer.append("<input type='text' name='firstname' > &nbsp;&nbsp;");
		writer.append("<label for='lastname'>Last Name :");
		writer.append("<input type='text' name='lastname' > &nbsp;&nbsp;");
		writer.append("<input type='submit' value='Add User'>");
		writer.append("</div>");
		writer.append("</form>");
		writer.append("<a href='ListUsersServlet'>List Users</a>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath() + " - doGet()");
	}

}
