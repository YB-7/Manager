package co.jelly.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {	//static Class로 만들었음0
	public static Connection getConnction() {
		Connection conn = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "demo";
		String password = "1234";
		
		// 생성자 시킬 필요없이 알아서 생성됨
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
