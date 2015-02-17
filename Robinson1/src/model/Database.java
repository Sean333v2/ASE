package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {
	public Database(){
		
	}
	
	public static Connection getConnection() throws SQLException{
		DataSource ds = getDataSource();
		Connection conn;
		if(ds == null) {
        	throw new SQLException("Datasource is null!");
        }
		try {
        	conn = ds.getConnection();
		} catch (SQLException e) {
			throw new SQLException("SQL Error: " + e.getMessage());
		}
		
		return conn;
	}
	
	private static DataSource getDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
        try {
        	fis = new FileInputStream("db.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
        	MysqlDataSource mysqlDS = new MysqlDataSource();
        	mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
        	mysqlDS.setUser(props.getProperty("MYSQL_DB_USER"));
        	mysqlDS.setPassword(props.getProperty("MYSQL_DB_PW"));
        	return mysqlDS;
        } catch(RuntimeException e) {
            e.printStackTrace();
            return null;
        }
	}
}
