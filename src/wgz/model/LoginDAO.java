package wgz.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import wgz.helper.db.NativePool;

public class LoginDAO {
	static Connection connection = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) {
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from user where username='" + username + "' AND password='" + password + "'";

		try {
			NativePool db = new NativePool("");
			// connecting to the DB
			connection = db.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists) {
				System.out.println("Username/Password entered is Incorrect or User doesnot Exists.");
				bean.setValid(false);
			} else if (userExists) {
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				System.out.println("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
			connection.close();

		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		return bean;
	}
}