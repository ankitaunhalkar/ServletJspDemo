package com.bridgelabz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @purpose Login Example Using Servlet and JSP 
 *  @author  Ankita Unhalkar
 *  @version 1.0
 *  @since   01-07-2018
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting Content Type
		response.setContentType("text/html");

		// Fetching from jsp page input field
		String name = request.getParameter("name");

		try {
			// Register mysql driver class
			Class.forName("com.mysql.jdbc.Driver");

			// Establish Connection with a database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bridgeit");

			// Perform's Operation with database and execute Query
			PreparedStatement pst = connection
					.prepareStatement("select * from user where username = ? and password = ?");

			pst.setString(1, name);

			pst.setString(2, "qwerty");

			int status = pst.executeUpdate();
			if (status > 0) {
				
				System.out.println("Successful");
				
			} else {
				
				System.out.println("Unsuccessful");
				
			}
		} catch (SQLException | ClassNotFoundException e) {

			// Dispatching to home page
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			
			dispatcher.forward(request, response);
		}
	}

}
