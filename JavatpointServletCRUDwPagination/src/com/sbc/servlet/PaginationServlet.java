package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbc.dao.UserDao;
import com.sbc.model.User;

@WebServlet("/PaginationServlet")
public class PaginationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		writer.append("<h2>List Users</h2>");
		writer.append("<a href='AddUserServlet'>Add User</a> | <a href='ListUsersServlet'>List Users</a><br/><br/>");
		writer.append("<table cellpadding='7'><tr><th>Id</th><th>Firstname</th><th>Lastname</th></tr>");
		List<User> users = new ArrayList<>();
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int size = 5;
		int startIndex = 5 * (pageNo - 1);		
		
		users = dao.ListUsersByPagination(startIndex, size);
		
		for(User user: users) {
			writer.append("<tr><td>" + user.getId() + "</td><td>" + user.getFirstname() + "</td><td>" + user.getLastname() + 
					"</td><td> <a href='UpdateUserServlet?id=" + user.getId() + "'>update</a> </td>" +
					"<td> <a href='DeleteUserServlet?id=" + user.getId() + "'>delete</a> </td></tr>");
		}
		writer.append("</table><br/>");

		writer.append("<a href='PaginationServlet?pageNo=1'>1</a> | ");
		writer.append("<a href='PaginationServlet?pageNo=2'>2</a> | ");
		writer.append("<a href='PaginationServlet?pageNo=3'>3</a> | ");
		writer.append("<a href='PaginationServlet?pageNo=4'>4</a> | ");
		writer.append("<a href='PaginationServlet?pageNo=5'>5</a> | ");
		writer.append("<a href='PaginationServlet?pageNo=6'>6</a> ");
		
		writer.append("<h2>Pagination in Java</h2>");
		writer.append("<p>Pagination is implemented by using <b><i>limit</i></b> in SQL query.</p>");
		writer.append("<p><i> ListUsers(0, 5); </i></p>");
		writer.append("<p><i> Select * from Table limit 0, 5; </i></p>");
		writer.append("<p>It will retrieve the first 5 records from the table</p>");
		writer.append("<p>0 is the position of first record and 5 is the position of sixth record in the table</p>");
		writer.append("<p>Different page numbers in pagination link to the same servlet by using <i>URL-Rewriting technique</i> </p>");
		writer.append("<p><i> <.a href='PaginationServlet?pageNo=1'>1<./a> </i></p>");
		writer.append("<p><i> <.a href='PaginationServlet?pageNo=2'>2<./a> </i></p>");
		writer.append("<p>To find the starting index of the record for each page is</p>");
		writer.append("<p><i> StartIndex = Size * (PageNo - 1) </i></p>");
		
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
