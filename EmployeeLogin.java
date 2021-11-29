package project;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EmployeeLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JFrame frame;
	//private JPasswordField passwordField;
	private JPanel contentPane;
	private JTextField IdtextField;
	private JPasswordField PasstextField_1;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	//private DatabaseConnection DB = new DatabaseConnection();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	public EmployeeLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel employeeId = new JLabel("Employee ID");
		employeeId.setFont(new Font("Tahoma", Font.BOLD, 13));
		employeeId.setBounds(66, 71, 86, 14);
		contentPane.add(employeeId);

		IdtextField = new JTextField();
		IdtextField.setBounds(181, 68, 131, 20);
		contentPane.add(IdtextField);
		IdtextField.setColumns(10);

		JLabel title = new JLabel("Employee Login for Grizzly's Entertainment Rental");
		title.setFont(new Font("Tahoma", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 469, 28);
		contentPane.add(title);

		JLabel empPass = new JLabel("Password");
		empPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		empPass.setBounds(66, 111, 86, 14);
		contentPane.add(empPass);

		PasstextField_1 = new JPasswordField();
		PasstextField_1.setBounds(181, 108, 131, 20);
		contentPane.add(PasstextField_1);
		PasstextField_1.setColumns(10);

		JButton Loginbtn = new JButton("Login");
		Loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					try {
						String query = "SELECT * FROM `employee` WHERE emp_id=? and emp_password=?";
						
						con = DriverManager.getConnection("jdbc:mysql://localhost/grizzlyentertainment", "root", "");
						pst = con.prepareStatement(query);
					
						pst.setString(1, IdtextField.getText());
						pst.setString(2, PasstextField_1.getText());
						
						rs = pst.executeQuery();
						
						if (rs.next()) {
							dispose();
							Employee frame = new Employee();
							frame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(employeeId, "username and password do not match");

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, this ,ex.getMessage(), 0);
					}
				}
			});
				
		Loginbtn.setBounds(181, 174, 131, 23);
		contentPane.add(Loginbtn);

		JButton btnNewButton = new JButton("Switch User?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage main = new MainPage();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(181, 225, 131, 23);
		contentPane.add(btnNewButton);

	}
}