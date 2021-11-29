package project;

import java.sql.*;
import javax.swing.JOptionPane;
;
public class DatabaseConnection {

	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/";
		Connection myConn = null;
		try {
			//1. Get Connection to localhost/xampp server
			myConn = DriverManager.getConnection(url, "root", "");
				if(myConn !=null) {//If connection was made to the xampp server
					JOptionPane.showMessageDialog(null, "Connected to local Server", "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
        return myConn;
	
		}
}