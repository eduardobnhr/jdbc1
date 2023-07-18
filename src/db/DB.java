package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	// metodo para conectar o banco
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return conn;
	}
	
	// metodo para fechar a conexao com o banco
	public static void closeConnection() {
		if (conn == null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
//	private static Properties loadProperties() throws FileNotFoundException, IOException {
//		try(FileInputStream fs = new FileInputStream("C:\\Users\\laraujo\\eclipse-workspace\\jdbc1\\db.properties")){
//			Properties props = new Properties();
//			props.load(fs);
//			return props;
//		}
//		
//	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
