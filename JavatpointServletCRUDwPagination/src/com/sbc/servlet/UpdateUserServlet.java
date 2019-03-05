package com.sbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbc.dao.UserDao;
import com.sbc.model.User;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    UserDao dao = new UserDao();
    User user = new User();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String id= request.getParameter("id");
		System.out.println("Inside UpdateUserServlet: id = " + id);
		
		user = dao.FindById(Integer.parseInt(id));
		 
		writer.append("<form action='UpdateUserServlet2' method='POST'>");
		writer.append("<div>");
		writer.append("<input type='text' name='id' value='" + user.getId() + "' hidden> &nbsp;&nbsp;");
		writer.append("<label for='firstname'>Firstname: ");
		writer.append("<input type='text' name='firstname' value='" + user.getFirstname() + "' > &nbsp;&nbsp;");
		writer.append("<label for='lastname'>Lastname :");
		writer.append("<input type='text' name='lastname' value='" + user.getLastname() + "'> &nbsp;&nbsp;");
		writer.append("<input type='submit' value='Update User'>");
		writer.append("</form>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
