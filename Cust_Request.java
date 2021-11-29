package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Cust_Request extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String cust_id;
	static int req_id;
	static int Stock1;

	void sendRequest(int equip_id, String cust_id, String equip_name, int quantity, String date) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			// database is my_tutorial, userid =root, password //
			// Update your usrid, password and database name //
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment", "root","");
			Statement st = con.createStatement();
			//int Quantity = Integer.parseInt(quantity); // Mark is an integer
			// Adding record
			
			String query1 = ("INSERT INTO `rental` (`Equipment ID`, `cust_id`, `Equipment Name`, `Rental Date`, `Cost`, `Quantity`, `Status`) VALUES ('" +equip_id+"', '" +cust_id+"', '" +equip_name+"',  '" +date+"', '', '" +quantity+"', 'Pending')");
			st.executeUpdate(query1); // record added.
			JOptionPane.showMessageDialog(null, "Request has been sent");
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		//////////////////////////////
	}


	void updateList(String cust_id, String equip_name) {
		
		try {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment", "root","");
		Statement st = con.createStatement();
		//int Quantity = Integer.parseInt(quantity); // Mark is an integer
		// Adding record
		
		String query2 = ("SELECT * FROM `rental`");
		//String query3 = ("UPDATE `rental` SET `Status` = '"+Status+"' WHERE `rental`.`cust_id` = '"+cust_id+"')");
		st.executeUpdate(query2);
		//st.executeUpdate(query3);// record added.
		JOptionPane.showMessageDialog(null, "List Updated");
		con.close();

	} catch (Exception e) {
		System.out.println(e);
	}
		
	//////////////////////////////
	}
	void schedule(String cost, int req_id1, int quantity, int Stock, String equip_id) {
		try {
			
			Employee i = new Employee();
			req_id = i.req_id1;
					
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment", "root","");
			
			String query2 = "UPDATE `equipment` SET `Stock` = '"+Stock+"' WHERE `equipment`.`Equipment ID` = '"+equip_id+"'";
			String query3 = ("UPDATE `rental` SET `Cost` = '"+cost+"', `Status` = 'Approved' WHERE `rental`.`Request Number` = '"+req_id1+"'");
			
			PreparedStatement st = con.prepareStatement(query2);
			PreparedStatement st1 = con.prepareStatement(query3); 
			
			
			st1.executeUpdate();// record added.
			
			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Request has been scheduled");
			con.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//////////////////////////////
	}


	public void checkStock(String equip_id, int stock) {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment", "root","");
			
			PreparedStatement ps=con.prepareStatement("SELECT `Stock` FROM `equipment` WHERE `equipment`.`Equipment ID` = '"+equip_id+"'");
			//String equip_id = textField.getText();
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			
			}
		
		}
	public boolean CustLogout() {
		
		JFrame frame = new JFrame();
		if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Logout", "Grizzly's Rental System",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			CustomerLogin cust = new CustomerLogin();
			cust.setVisible(true);
			dispose();
		}
		return true;

	}
	}
	
	
