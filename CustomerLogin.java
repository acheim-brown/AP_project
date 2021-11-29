package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CustomerLogin extends JFrame {

	static int req_id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JPasswordField textField_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	public String cust_id;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private DatabaseConnection DB = new DatabaseConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLogin frame = new CustomerLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(89, 90, 95, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(233, 88, 104, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNewLabel_1 = new JLabel("Customer Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(89, 133, 134, 14);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JPasswordField();
		textField_1.setBounds(233, 131, 104, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		

		lblNewLabel_2 = new JLabel("Customer Login for Grizzly's Equipment Rental");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 11, 414, 20);
		contentPane.add(lblNewLabel_2);

		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM `renter` WHERE cust_id=? and Cust_password=?";
										
					con = DriverManager.getConnection("jdbc:mysql://localhost/grizzlyentertainment", "root", "");
					pst = con.prepareStatement(query);
								
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					
					
					String cust_id = textField.getText();
										
					rs = pst.executeQuery();
										
					if (rs.next()) {
					dispose();
					Update up = new Update(req_id,cust_id);
					up.setVisible(false);
					Customer frame = new Customer(cust_id);
					frame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "username and password do not match");

						}
					} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, this ,ex.getMessage(), 0);
				}
			}
		});
		btnNewButton.setBounds(156, 182, 104, 23);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Switch User?");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage main = new MainPage();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(156, 227, 104, 23);
		contentPane.add(btnNewButton_1);
	}

}