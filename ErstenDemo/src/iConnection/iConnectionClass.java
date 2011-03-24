package iConnection;

import java.sql.*;

public class iConnectionClass 
{
	Connection conn;
	String url = "jdbc:mysql://192.168.1.39";
	String dbName = "test";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "sapass";
	
	public void connectToDatabase()
	{
		try
		{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement s  = conn.createStatement();
			String query = "....";
			s.executeQuery(query);
			conn.close();			
		}
		catch(Exception ex)
		{
			
		}
	}
}
