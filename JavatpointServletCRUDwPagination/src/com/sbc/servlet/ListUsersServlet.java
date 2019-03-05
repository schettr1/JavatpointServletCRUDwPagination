package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sbc.dao.UserDao;
import com.sbc.model.User;

@WebServlet("/ListUsersServlet")
public class ListUsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.append("<h2>List Users</h2>");
		writer.append("<a href='AddUserServlet'>Add User</a> | <a href='PaginationServlet?pageNo=1'>Pagination</a> <br/><br/>");
		writer.append("<table cellpadding='7'><tr><th>Id</th><th>Firstname</th><th>Lastname</th></tr>");
		List<User> users = new ArrayList<>();
		users = dao.ListUsers();
		users.forEach(user -> writer.append("<tr><td>" + user.getId() + "</td><td>" + user.getFirstname() + "</td><td>" + user.getLastname() + 
				"</td><td> <a href='UpdateUserServlet?id=" + user.getId() + "'>update</a> </td>" +
				"<td> <a href='DeleteUserServlet?id=" + user.getId() + "'>delete</a> </td></tr>"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}