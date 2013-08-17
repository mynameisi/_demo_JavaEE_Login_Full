package wgz.helper.db;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 这个Enum类对数据库进行先关的操作 之所以为enum而不是utility final class，是因为他需要初始化，共享资源
 * 
 * @author Administrator
 * 
 */
public class NativePool extends DB implements DBFrameWork {
	private final String cleanUp;
	private DataSource dataSource;

	public NativePool(String cleanUp) {
		this.cleanUp = cleanUp;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/testdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public void shutdown(File f) {
		try {
			cleanUP(dataSource.getConnection(), cleanUp, f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean query(String sql, boolean showResult) {
		logger.debug("query() started with SQL: " + sql);
		boolean result = false;
		try {
			result = query(dataSource.getConnection(), sql, showResult);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("query() done with result=" + result);
		return result;
	}

	public void update(String expression) {
		try {
			update(dataSource.getConnection(), expression, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void batchUpdate(File f) {
		try {
			update(dataSource.getConnection(), null, f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
