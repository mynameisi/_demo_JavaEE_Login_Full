package wgz.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginDAO {
	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	static Connection connection = null;
	static ResultSet rs = null;
	private static DataSource dataSource;

	// 用JNDI机制获取了DataSource，从DataSource就可以进而获得链接
	// 这个资源是有Tomcat Container维持的，自动就是数据库池，所以不用在加上额外的数据库池了
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/testdb");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserBean login(UserBean bean) {
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from user where username='" + username + "' AND password='" + password + "'";

		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists) {
				bean.valid = false;
				logger.debug("用户名/密码不正确，或者用户名不存在");
			} else if (userExists) {
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				logger.debug("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.valid = true;
			}
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bean;
	}
}