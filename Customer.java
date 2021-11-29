package project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Customer extends JFrame {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	static String cust_id;
	static String req_id;
	
	//public int req_id1;

	private JPanel contentPane;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTable table;

//public String cust_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer(cust_id);
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
	public Customer(String cust_id) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Grizzly's Entertainment Equipment Rental System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 11, 1329, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Choose From The List of Available Equipments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(126, 60, 325, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Choose the Required Rental Date Below");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(607, 60, 264, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Staging ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(25, 86, 72, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Lighting");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(147, 87, 72, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Power");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(270, 85, 46, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Sound");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(428, 87, 46, 14);
		contentPane.add(lblNewLabel_6);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(665, 80, 129, 20);
		contentPane.add(dateChooser);

		JButton btnNewButton = new JButton("Request Item");
		btnNewButton.addActionListener(new ActionListener() {
			// @SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {

				Cust_Request obj = new Cust_Request();

				try {

					int equip_id = Integer.parseInt(textField_4.getText());

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con.prepareStatement(
							"SELECT `Equipment Name` FROM `equipment` WHERE `equipment`.`Equipment ID` = '" + equip_id
									+ "'");
					ResultSet rs = ps.executeQuery();
					rs.next();
					String equip_name = rs.getString("Equipment Name");

					textField.setText(String.valueOf(equip_name));

					int Quantity = Integer.parseInt(textField_1.getText());
					String date = "" + dateChooser.getDate();
					SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
					Date_Format.format(dateChooser.getDate());
					
					if(Quantity <= 0 || Quantity > 3) {
					JOptionPane.showMessageDialog(null, "Invalid Quantity");
					}else {
						obj.sendRequest(equip_id, cust_id, equip_name, Quantity, date);
					
					textField_4.setText("");
					textField.setText("");
					textField_1.setText("");
					dateChooser.setCalendar(null);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid Request, Check all Fields and Try Again");
				}

				// creating one object
			}
		});

		btnNewButton.setBounds(270, 276, 141, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_7 = new JLabel("Present Transaction");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(270, 321, 824, 14);
		contentPane.add(lblNewLabel_7);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(270, 346, 824, 273);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "Request Number", "EquipmentID", "CustomerID", "Equipment Name", "Rental Date", "Cost",
						"Quantity ", "Status" }));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(110);

		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.setBounds(647, 619, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzlyentertainment",
							"root", "");
					PreparedStatement ps = con
							.prepareStatement("SELECT * FROM `rental` WHERE `cust_id` LIKE '" + cust_id + "'");
					ResultSet rs = ps.executeQuery();
					DefaultTableModel tm = (DefaultTableModel) table_1.getModel();
					tm.setRowCount(0);
					while (rs.next()) {
						Object o[] = { rs.getString("Request Number"), rs.getString("Equipment ID"),
								rs.getString("cust_id"), rs.getString("Equipment Name"), rs.getString("Rental Date"),
								rs.getString("Cost"), rs.getString("Quantity"), rs.getString("Status") };
						tm.addRow(o);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame frame = new JFrame();
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to Logout", "Grizzly's Rental System",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					CustomerLogin cust = new CustomerLogin();
					cust.setVisible(true);
					dispose();
				}

			}
		});
		btnNewButton_3.setBounds(1259, 31, 89, 23);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel_9 = new JLabel("001 - Versa Wall");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(25, 111, 112, 14);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("002 - Versa Deck");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(25, 128, 112, 14);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("003 - Versa Sled");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(25, 147, 112, 14);
		contentPane.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("004 - Scenic Stairs");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setBounds(25, 168, 112, 14);
		contentPane.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("005 - Floodlights");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(147, 111, 113, 14);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("006 - Followspots");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_14.setBounds(147, 128, 118, 14);
		contentPane.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("007 - Fresnels");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setBounds(147, 147, 113, 14);
		contentPane.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("008 - Strip Lights");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_16.setBounds(147, 168, 113, 14);
		contentPane.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("009 - Amplifer");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_17.setBounds(270, 110, 141, 14);
		contentPane.add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("010 - Cam-Lock Cable Sets");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_18.setBounds(270, 128, 163, 14);
		contentPane.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("011 - Circuit Breaker Panel");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_19.setBounds(270, 147, 157, 14);
		contentPane.add(lblNewLabel_19);

		JLabel lblNewLabel_20 = new JLabel("012 - 30A Twist Lock");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_20.setBounds(270, 168, 163, 14);
		contentPane.add(lblNewLabel_20);

		JLabel lblNewLabel_21 = new JLabel("013 - Mixing Desk");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_21.setBounds(428, 111, 129, 14);
		contentPane.add(lblNewLabel_21);

		JLabel lblNewLabel_22 = new JLabel("014 - Microphone");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_22.setBounds(428, 128, 118, 14);
		contentPane.add(lblNewLabel_22);

		JLabel lblNewLabel_23 = new JLabel("015 - Loudspeaker Set");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_23.setBounds(428, 147, 173, 14);
		contentPane.add(lblNewLabel_23);

		JLabel lblNewLabel_24 = new JLabel("016 - Microphone Set");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_24.setBounds(428, 168, 173, 14);
		contentPane.add(lblNewLabel_24);

		JLabel lblNewLabel_25 = new JLabel("Item Name:");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_25.setBounds(25, 209, 129, 14);
		contentPane.add(lblNewLabel_25);

		textField = new JTextField();
		textField.setBounds(147, 207, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		JLabel lblNewLabel_26 = new JLabel("Quantity:");
		lblNewLabel_26.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_26.setBounds(25, 243, 94, 14);
		contentPane.add(lblNewLabel_26);

		textField_1 = new JTextField();
		textField_1.setBounds(147, 238, 143, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_27 = new JLabel("(Max item 3)");
		lblNewLabel_27.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_27.setBounds(300, 243, 118, 14);
		contentPane.add(lblNewLabel_27);

		JLabel lblNewLabel_28 = new JLabel("Enter ID Number:");
		lblNewLabel_28.setBounds(384, 210, 109, 14);
		contentPane.add(lblNewLabel_28);

		textField_4 = new JTextField();
		textField_4.setBounds(494, 207, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(842, 121, 493, 94);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "Versa Wall - $4000", "Floodlight - $6000", "Apmlifer - $9000", "Mixing Desk - $7000" },
				{ "Versa Deck - $4500", "Followspots - $8000", "Cam - Lock - $8000", "Microphone - $6000" },
				{ "Versa Shed - $3500", "Fresnels  $7000", "Circuit Panel - $4000", "LoudSpeaker - $9000" },
				{ "Scenic Stairs - $3600", "Strips Light - $4000", "Twist Lock - $7000", "Mics Set -  $7000" }, },
				new String[] { "Staging", "Lighting", "Power", "Sound" }));

		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Search ser = new Search(cust_id);
				ser.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(647, 225, 89, 23);
		contentPane.add(btnNewButton_2);
		table.getColumnModel().getColumn(0).setPreferredWidth(142);
		table.getColumnModel().getColumn(1).setPreferredWidth(138);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(142);
	}

}