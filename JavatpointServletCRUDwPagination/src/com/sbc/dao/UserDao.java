package com.sbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbc.model.User;

public class UserDao {

	static final String DB_URL = "jdbc:mysql://localhost:3306/javaservletcrudwpagination";
	static final String USER = "root";
	static final String PASSWORD = "222Network";
	
	/* get Connection Object */
	public Connection getConnection() {
		Connection conn = null;
		try {
			/* load the JDBC Driver  */
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/* create the Connection Object */
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
		return conn;
	}
	
	public int AddUser(User user) {
		String query = "insert into user (firstname, lastname) values (?, ?)";
		int status = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			status = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status; 
	}

	public List<User> ListUsers() {
		List<User> users = new ArrayList<>();
		Connection conn = getConnection();
		String query = "select * from user";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public int DeleteUser(int id) {
		Connection conn = getConnection();
		String query = "delete from user where id = " + id;
		int status = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			status = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public User FindById(int id) {
		Connection conn = getConnection();
		User user = new User();
		String query = "select * from user where id = " + id;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2)); 
				user.setLastname(rs.getString(3)); 
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}

	public int UpdateUser(User user) {
		System.out.println("Inside UpdateUser() " + user.getId());
		Connection conn = getConnection();
		String query = "update user set firstname = ? , lastname = ? where id = ?";
		int status = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setInt(3, user.getId());
			status = ps.executeUpdate();
			System.out.println(status);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}

	public List<User> ListUsersByPagination(int startIndex, int size) {
		List<User> users = new ArrayList<>();
		Connection conn = getConnection();
		String query = "select * from user limit " + startIndex + " , " + size;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
