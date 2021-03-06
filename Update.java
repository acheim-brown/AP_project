package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Update extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//static String cust_id;
	static int req_id;
	static String cust_id;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Update frame = new Update(req_id, cust_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void update(int equip_id, String cust_id, String equip_name, String rentdate, int quantity, int req_id1) {
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment", "root",
					"");

			String query2 = "UPDATE `rental` SET `Equipment ID` = '" + equip_id + "',`Equipment Name` = '"+equip_name+"', `Rental Date` = '" + rentdate+ "', `Quantity` = '" + quantity + "', `Status` = 'Pending', `Cost` = '' WHERE `rental`.`Request Number` = '" + req_id1 + "'";

			PreparedStatement st = con.prepareStatement(query2);

			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Order has been Updated");
			con.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//////////////////////////////
	}

	public Update(int req_id, String cust_id) {
		
		//String cust_id = cl.cust_id;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Equipment Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 793, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Request Number:");
		lblNewLabel_1.setBounds(22, 68, 134, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(166, 65, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Equipment Number:");
		lblNewLabel_2.setBounds(22, 98, 134, 14);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(166, 95, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Equipment Name:");
		lblNewLabel_3.setBounds(22, 129, 134, 14);
		contentPane.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setBounds(166, 126, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);

		JLabel lblNewLabel_4 = new JLabel("Enter Quantity:");
		lblNewLabel_4.setBounds(22, 164, 134, 14);
		contentPane.add(lblNewLabel_4);

		textField_3 = new JTextField();
		textField_3.setBounds(166, 161, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Enter Rental Date:");
		lblNewLabel_5.setBounds(22, 200, 134, 14);
		contentPane.add(lblNewLabel_5);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(166, 192, 86, 20);
		contentPane.add(dateChooser);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(93, 225, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					int req_id = Integer.parseInt(textField.getText());
					int equip_id = Integer.parseInt(textField_1.getText());
					
					

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement(
							"SELECT `Equipment ID`, `Equipment Name`, `Quantity` FROM `rental` WHERE `Request Number` = '"
									+ req_id + "'");
					PreparedStatement ps1 = con.prepareStatement(
							"SELECT `Equipment Name` FROM `equipment` WHERE `equipment`.`Equipment ID` = '" + equip_id
									+ "'");
					ResultSet rs = ps.executeQuery();
					ResultSet rs1 = ps1.executeQuery();
					rs.next();
					rs1.next();
					
					//String cust_id = rs1.getString("cust_id");
					String equip_name = rs1.getString("Equipment Name");
					
					JOptionPane.showMessageDialog(null, "You changed your order to " +equip_name);

					textField_2.setText(String.valueOf(equip_name));

					int quantity = Integer.parseInt(textField_3.getText());

					String rentdate = "" + dateChooser.getDate();
					SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
					Date_Format.format(dateChooser.getDate());
					
					if(quantity <= 0 || quantity > 3) {
						JOptionPane.showMessageDialog(null, "Invalid Quantity");
					}else {

					update(equip_id,cust_id,equip_name,rentdate, quantity, req_id);
					}
				} catch (Exception e1) {
					//JOptionPane.showMessageDialog(null, "Invalid Update Request! Check all Fields and Try Again.");
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		JLabel lblNewLabel_6 = new JLabel("Choose from the list of Equipment");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(351, 44, 586, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Staging ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(351, 68, 46, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("001 - Versa Wall");
		lblNewLabel_8.setBounds(351, 98, 118, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("002 - Versa Deck");
		lblNewLabel_9.setBounds(351, 129, 118, 14);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("003 - Versa Shed");
		lblNewLabel_10.setBounds(351, 164, 118, 14);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("004 - Scenic Stairs");
		lblNewLabel_11.setBounds(351, 200, 118, 14);
		contentPane.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Lighting ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11_1.setBounds(493, 68, 67, 14);
		contentPane.add(lblNewLabel_11_1);

		JLabel lblNewLabel_11_2 = new JLabel("005 - FloodLight");
		lblNewLabel_11_2.setBounds(493, 98, 114, 14);
		contentPane.add(lblNewLabel_11_2);

		JLabel lblNewLabel_11_3 = new JLabel("006 - Followspots");
		lblNewLabel_11_3.setBounds(493, 129, 114, 14);
		contentPane.add(lblNewLabel_11_3);

		JLabel lblNewLabel_11_4 = new JLabel("007 - Fresnel");
		lblNewLabel_11_4.setBounds(493, 164, 101, 14);
		contentPane.add(lblNewLabel_11_4);

		JLabel lblNewLabel_11_5 = new JLabel("008 - Strip Light");
		lblNewLabel_11_5.setBounds(493, 200, 114, 14);
		contentPane.add(lblNewLabel_11_5);

		JLabel lblNewLabel_11_6 = new JLabel("Power");
		lblNewLabel_11_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11_6.setBounds(617, 68, 46, 14);
		contentPane.add(lblNewLabel_11_6);

		JLabel lblNewLabel_11_7 = new JLabel("009 - Amplifier");
		lblNewLabel_11_7.setBounds(617, 98, 101, 14);
		contentPane.add(lblNewLabel_11_7);

		JLabel lblNewLabel_11_8 = new JLabel("010 - Cam-Lock Cable Set");
		lblNewLabel_11_8.setBounds(617, 129, 117, 14);
		contentPane.add(lblNewLabel_11_8);

		JLabel lblNewLabel_11_9 = new JLabel("011 - Circit Breaker Panel");
		lblNewLabel_11_9.setBounds(617, 164, 117, 14);
		contentPane.add(lblNewLabel_11_9);

		JLabel lblNewLabel_11_10 = new JLabel("012 - 3OA Twist Lock");
		lblNewLabel_11_10.setBounds(617, 200, 117, 14);
		contentPane.add(lblNewLabel_11_10);

		JLabel lblNewLabel_11_11 = new JLabel("Sound");
		lblNewLabel_11_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11_11.setBounds(744, 68, 46, 14);
		contentPane.add(lblNewLabel_11_11);

		JLabel lblNewLabel_11_12 = new JLabel("013 - Mixing Desk");
		lblNewLabel_11_12.setBounds(744, 98, 158, 14);
		contentPane.add(lblNewLabel_11_12);

		JLabel lblNewLabel_11_13 = new JLabel("014 - Microphone");
		lblNewLabel_11_13.setBounds(744, 129, 158, 14);
		contentPane.add(lblNewLabel_11_13);

		JLabel lblNewLabel_11_14 = new JLabel("015 - LoudSpeaker Set");
		lblNewLabel_11_14.setBounds(744, 164, 158, 14);
		contentPane.add(lblNewLabel_11_14);

		JLabel lblNewLabel_11_15 = new JLabel("016 - Microphone Set");
		lblNewLabel_11_15.setBounds(744, 200, 158, 14);
		contentPane.add(lblNewLabel_11_15);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search ser = new Search(cust_id);
				ser.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(813, 24, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					int req_id = Integer.parseInt(textField.getText());

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement(
							"SELECT `Equipment ID`, `Equipment Name`, `Quantity`, `cust_id`FROM `rental` WHERE `Request Number` = '"
									+ req_id + "' AND `cust_id` LIKE '"+cust_id+"'");
					ResultSet rs = ps.executeQuery();
					rs.next();

					int equip_id = rs.getInt("Equipment ID");
					String equip_name = rs.getString("Equipment Name");
					String quantity = rs.getString("Quantity");

					textField_1.setText(String.valueOf(equip_id));
					textField_2.setText(String.valueOf(equip_name));
					textField_3.setText(String.valueOf(quantity));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No Records Found");
				}
			}
		});
		btnNewButton_3.setBounds(260, 65, 75, 21);
		contentPane.add(btnNewButton_3);
	}

}
