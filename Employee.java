package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Employee extends JFrame {

	static String cust_id;
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;

	public int req_id1;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Grizzly's Entertainment Equipment Rental System");
		lblNewLabel.setBounds(10, 11, 1326, 23);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(740, 104, 593, 274);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		final Object[] row = new Object[5];
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "Request Number", "Equipment ID", "EquipName", "Rental Date", "Quantity", "CustID" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);
		table.getColumnModel().getColumn(3).setPreferredWidth(89);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 104, 573, 274);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "Equipment ID", "Request Number", "Equipment Name", "Status" }));
		table_1.getColumnModel().getColumn(2).setPreferredWidth(141);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(120);

		JButton btnNewButton = new JButton("Update List");
		btnNewButton.setBounds(231, 389, 143, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement("SELECT * FROM `rental`");
					ResultSet rs = ps.executeQuery();
					DefaultTableModel tm = (DefaultTableModel) table_1.getModel();
					tm.setRowCount(0);
					while (rs.next()) {
						Object o[] = { rs.getString("Equipment ID"), rs.getString("Request Number"),
								rs.getString("Equipment Name"), rs.getString("Status") };
						tm.addRow(o);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Load New Request");
		btnNewButton_1.setBounds(955, 389, 143, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement("SELECT * FROM `rental`");
					ResultSet rs = ps.executeQuery();
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					tm.setRowCount(0);
					while (rs.next()) {
						Object o[] = { rs.getString("Request Number"), rs.getString("Equipment ID"),
								rs.getString("Equipment Name"), rs.getString("Rental Date"), rs.getString("Quantity"),
								rs.getString("cust_id") };
						tm.addRow(o);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Schedule Equipment");
		lblNewLabel_1.setBounds(61, 444, 224, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Equipment ID:");
		lblNewLabel_2.setBounds(61, 492, 101, 14);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(199, 492, 124, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Request ID:");
		lblNewLabel_3.setBounds(61, 517, 101, 14);
		contentPane.add(lblNewLabel_3);

		textField_1 = new JTextField();
		textField_1.setBounds(199, 514, 124, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cost:");
		lblNewLabel_4.setBounds(61, 542, 101, 14);
		contentPane.add(lblNewLabel_4);

		textField_2 = new JTextField("$ ");
		textField_2.setBounds(199, 539, 124, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		/*
		 * JDateChooser dateChooser = new JDateChooser(); dateChooser.setBounds(199,
		 * 566, 124, 20); contentPane.add(dateChooser);
		 *
		 * JLabel lblNewLabel_5 = new JLabel("Rental Date:");
		 * lblNewLabel_5.setBounds(61, 566, 101, 14); contentPane.add(lblNewLabel_5);
		 */

		JButton btnNewButton_2 = new JButton("Schedule");
		btnNewButton_2.setBounds(45, 672, 89, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Shedule the equipment",
						"Grizzly's Rental System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

					Cust_Request obj1 = new Cust_Request();
					String equip_id = textField.getText();
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
								"root", "");
						PreparedStatement ps = con
								.prepareStatement("SELECT `Stock` FROM `equipment` WHERE `equipment`.`Equipment ID` = '"
										+ equip_id + "'");
						ResultSet rs = ps.executeQuery();
						rs.next();
						int Stock = rs.getInt("Stock");
						int quantity = Integer.parseInt(textField_4.getText());

						if (Stock == 0) {
							JOptionPane.showMessageDialog(null, "You Have No Stock Left");
						} else {

							if (Stock < quantity) {
								JOptionPane.showMessageDialog(null, "Not enough equipment in stock");
								String query3 = ("UPDATE `rental` SET `Status` = 'Failed - Not enough stock' WHERE `rental`.`Equipment ID` = '"+equip_id+"'");
								PreparedStatement st1 = con.prepareStatement(query3);
								st1.executeUpdate();
							} else {
								Stock = (Stock - quantity);
								String cost = textField_2.getText();
								int req_id = Integer.parseInt(textField_1.getText());
								req_id1 = req_id;
								obj1.schedule(cost, req_id, quantity, Stock, equip_id);
								textField.setText("");
								textField_4.setText("");
								textField_2.setText("$ ");
								textField_1.setText("");
								textField_5.setText("");
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Invalid Entry, Check Fields For Correct Entry");
					}
				}
			}
		});
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Reset");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_4.setText("");
				textField_2.setText("$ ");
				textField_1.setText("");
				textField_5.setText("");
			}
		});
		btnNewButton_3.setBounds(158, 672, 89, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_5 = new JButton("Generate Receipt");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("An error happend", e1.getMessage());
				}
			}
		});
		btnNewButton_5.setBounds(1072, 672, 148, 23);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("Logout");
		btnNewButton_7.setBounds(1247, 40, 89, 23);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Logout", "Grizzly's Rental System",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					EmployeeLogin cust = new EmployeeLogin();
					cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnNewButton_7);

		JLabel lblNewLabel_7 = new JLabel("Equipment Rental Status");
		lblNewLabel_7.setBounds(25, 79, 573, 14);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Rental Request");
		lblNewLabel_8.setBounds(740, 79, 596, 14);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Quantity:");
		lblNewLabel_9.setBounds(61, 567, 124, 14);
		contentPane.add(lblNewLabel_9);

		textField_4 = new JTextField();
		textField_4.setBounds(199, 564, 124, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Inventory:");
		lblNewLabel_10.setBounds(61, 592, 101, 14);
		contentPane.add(lblNewLabel_10);

		textField_5 = new JTextField();
		textField_5.setBounds(199, 589, 17, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setEditable(false);

		JButton btnNewButton_6 = new JButton("Check Stock");
		btnNewButton_6.setBounds(268, 672, 106, 23);
		contentPane.add(btnNewButton_6);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(539, 470, 618, 95);
		contentPane.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(new Object[][] {
				{ "Versa Wall - $4000", "Floodlight - $6000", "Amplifier - $9000 ", "Mixing Deck - $7000" },
				{ "Versa Deck - $4500", "Followspots - $8000", "Cam-Lock - $8000", "Microphone - $6000" },
				{ "Versa Shed - $3500", "Fresnels - $7000", "Circuit Panel - $4000", "Loudspeaker - $9000" },
				{ "Scenic Stairs - $3600", "Strip Light - $4000", "Twist Lock - $7000", "Microphone - $7000" }, },
				new String[] { "Staging ", "Lighting", "Power", "Sound" }));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(141);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(162);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(169);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(182);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String equip_id = textField.getText();

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement(
							"SELECT `Stock` FROM `equipment` WHERE `equipment`.`Equipment ID` = '" + equip_id + "'");
					ResultSet rs = ps.executeQuery();
					rs.next();
					int Stock = rs.getInt("Stock");

					textField_5.setText(String.valueOf(Stock));

					Cust_Request obj = new Cust_Request();
					obj.checkStock(equip_id, Stock);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid Entry, Enter Correct Equipment ID.");
				}
			}

		});

	}
}