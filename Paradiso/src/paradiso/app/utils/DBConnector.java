package paradiso.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

	private final static String USER = "root";
	private final static String PASSWORD = "root";
	private final static String DB_URL = "jdbc:mysql://localhost:3306/reservations_db";
	
	public static Connection connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.err.println("Error in loading driver: " +  e.getMessage());
		}
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.err.println("Error in getting connection: " + e.getMessage());
		}
		
		return con;
	}
	
	public static void closeResources(PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null)
			{
				ps.close();
			} 
			if(rs != null)
			{
				rs.close();
			} 
			if(con != null)
			{
				con.close();
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DBConnector.connect();
	}
	
}

