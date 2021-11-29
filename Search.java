package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Search extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String cust_id;
	static int req_id;
	static String equip_name;
	static String quantity;
	static String rentdate;
	static String equip_id;

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search(cust_id);
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
	public Search(String cust_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 155, 683, 210);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, "", null, null, null }, },
				new String[] { "Request Number", "Equipment ID", "Customer ID", "Equipment Name", "Date Rental", "Cost",
						"Quantity", "Status" }));

		JLabel lblNewLabel = new JLabel("Grizzly's Entertainment Rental System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 861, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////////////
			
				Update up = new Update(req_id, cust_id);
				up.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(303, 80, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(482, 80, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int req_id = Integer.parseInt(textField.getText());

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con
							.prepareStatement("DELETE FROM `rental` WHERE `rental`.`Request Number` = '" + req_id
									+ "' AND `cust_id` LIKE '" + cust_id + "'");
					PreparedStatement ps1 = con
							.prepareStatement("SELECT `Request Number` FROM `rental` WHERE `rental`.`cust_id` LIKE '" + cust_id+"'");
					ResultSet rs = ps1.executeQuery();
					rs.next();
						ps.executeUpdate();
						DefaultTableModel tm = (DefaultTableModel) table.getModel();
						tm.setRowCount(0);
						while (rs.next()) {
							Object o[] = { rs.getString("Request Number"), rs.getString("Equipment ID"),
									rs.getString("cust_id"), rs.getString("Equipment Name"), rs.getString("Rental Date"),
									rs.getString("Cost"), rs.getString("Quantity"), rs.getString("Status") };
							tm.addRow(o);
					
					}
						JOptionPane.showMessageDialog(null, "Order Deleted!!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid ID Number");
				}
			}
		});
		

		JLabel lblNewLabel_1 = new JLabel("Enter Request No:");
		lblNewLabel_1.setBounds(10, 33, 120, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(140, 30, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.setBounds(137, 61, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con
							.prepareStatement("SELECT * FROM `rental` WHERE `cust_id` LIKE '" + cust_id + "'");
					ResultSet rs = ps.executeQuery();
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					tm.setRowCount(0);
					while (rs.next()) {
						Object o[] = { rs.getString("Request Number"), rs.getString("Equipment ID"),
								rs.getString("cust_id"), rs.getString("Equipment Name"), rs.getString("Rental Date"),
								rs.getString("Cost"), rs.getString("Quantity"), rs.getString("Status") };
						tm.addRow(o);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ID Not Found");
				}
			}
		});

		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cust = new Customer(cust_id);
				cust.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(762, 36, 89, 23);
		contentPane.add(btnNewButton_3);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
	}
}
