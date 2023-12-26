package NesneProje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baglan {
	
	 public Connection getConnection() throws SQLException 
	 {
	 final String dbUrl="jdbc:mysql://localhost:3306/NESPRO";
	 final String userName="*****";
	 final String password="*****";
	 return DriverManager.getConnection(dbUrl,userName,password);
	 }
	 
	 public void showErrorMessage(SQLException exception)
	 {
	   System.out.println("Error : "+exception.getMessage());
	   System.out.println("Error code : "+exception.getErrorCode());
	 }
	 
	 public static Connection bagkur() throws SQLException {
		  String dbUrl="jdbc:mysql://localhost:3306/NESPRO";
		  String userName="*****";
		 String password="*****";
	        
	        Connection connection = DriverManager.getConnection(dbUrl,userName,password);
	        return connection;
	    }
	
	 }
