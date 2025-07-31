package contactBook.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import contactBook.beans.Contact;

import contactBook.dbtask.FriendContack_db_connection;

public class UpdateFriendContact extends JFrame implements KeyListener, ActionListener, ItemListener {
	
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField txtPhone; 
		private JTextField txtEmail;
		private JComboBox<Contact> cmb;
		private JTextField txtWhat;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UpdateFriendContact frame = new UpdateFriendContact();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public void fillCombo() {
			Connection con =FriendContack_db_connection.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from friend";// "*"is mean all coloumns
			try {
				ps = con.prepareStatement(sql);// prepare statement method prepare the query .
				// System.out.println(ps);
				rs = ps.executeQuery();

				while (rs.next() == true) {
					String userName = rs.getString("id");// to fetch data from column
					// String userPhone = rs.getString(4);// columnNo. started from 1

					// System.out.println(userName);
					int id = rs.getInt("id");
					Contact c = new Contact(id, userName);
					// System.out.println("contact object "+c);
					cmb.addItem(c);// to add items in combo box
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

		/**
		 * Create the frame.
		 */
		public UpdateFriendContact() {
	
			setBackground(new Color(255, 128, 255));
			setTitle("update");
			setIconImage(Toolkit.getDefaultToolkit()
					.getImage(UpdateContact.class.getResource("/contactBook/images/icons8-update-30.png")));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 686, 533);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			setLocationRelativeTo(contentPane);
			contentPane.setLayout(null);

			cmb = new JComboBox();

			cmb.addItemListener(this);
			cmb.setModel(new DefaultComboBoxModel(new String[] { "select contact" }));
			fillCombo();
			cmb.setToolTipText("");
			cmb.setBackground(new Color(255, 255, 255));
			cmb.setBounds(236, 40, 162, 46);

			contentPane.add(cmb);

			JLabel lblPhone = new JLabel("Phone");
			lblPhone.setIcon(new ImageIcon(UpdateContact.class.getResource("/contactBook/images/icons8-phone-32.png")));
			lblPhone.setFont(new Font("Arial Narrow", Font.BOLD, 20));
			lblPhone.setBounds(10, 143, 197, 39);
			contentPane.add(lblPhone);

			JLabel lblEmail = new JLabel("Email");
			lblEmail.setIcon(new ImageIcon(UpdateContact.class.getResource("/contactBook/images/icons8-email-32.png")));
			lblEmail.setFont(new Font("Arial Narrow", Font.BOLD, 20));
			lblEmail.setBounds(10, 274, 197, 39);
			contentPane.add(lblEmail);

			txtPhone = new JTextField();
			txtPhone.addKeyListener(this);
			txtPhone.setBounds(236, 143, 162, 32);
			contentPane.add(txtPhone);
			txtPhone.setColumns(10);

			txtEmail = new JTextField();
			txtEmail.setBounds(236, 281, 162, 32);
			contentPane.add(txtEmail);
			txtEmail.setColumns(10);

			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(this);
			btnUpdate.setIcon(new ImageIcon(UpdateContact.class.getResource("/contactBook/images/icons8-update-30.png")));
			btnUpdate.setBackground(new Color(0, 255, 64));
			btnUpdate.setFont(new Font("Book Antiqua", Font.BOLD, 24));
			btnUpdate.setBounds(174, 396, 149, 46);
			contentPane.add(btnUpdate);
			
			JLabel lblWhat = new JLabel("WhatsAppNumber");
			lblWhat.setIcon(new ImageIcon(UpdateFriendContact.class.getResource("/contactBook/images/icons8-whatsapp-32.png")));
			lblWhat.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
			lblWhat.setBounds(10, 211, 197, 39);
			contentPane.add(lblWhat);
			
			txtWhat = new JTextField();
			txtWhat.setBounds(236, 211, 162, 32);
			contentPane.add(txtWhat);
			txtWhat.setColumns(10);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getSource() == txtPhone) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
					JOptionPane.showMessageDialog(this, "only Number are allowed");
				}
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			updateContact();

		}

		public void updateContact() {

			String uEmail = txtEmail.getText();
			String uAddress = txtWhat.getText();
			String uPhone = txtPhone.getText();
			Contact c = (Contact) cmb.getSelectedItem();
			int id = c.getId();
			Connection con =FriendContack_db_connection.openConnection();
			PreparedStatement ps = null;
			String updateQuery = "update friend set phoneNumber=?,email=?,whatsappNumber=? where id=?";
			try {
				ps = con.prepareStatement(updateQuery);
				ps.setString(1, uPhone);
				ps.setString(2, uEmail);
				ps.setString(3, uAddress);
				ps.setInt(4, id);
				int status = ps.executeUpdate();
				if (status > 0)
					JOptionPane.showMessageDialog(this, "contact Update successfully");

			} catch (SQLException se) {
				se.printStackTrace();

			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				Contact c = (Contact) cmb.getSelectedItem();
				int id = c.getId();
				Connection con = FriendContack_db_connection.openConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = "select * from friend where id=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					if (rs.next()) {
						String uName = rs.getString("name");
						String uEmail = rs.getString("email");
						String uwhat = rs.getString("whatsappNumber");
						String uPhone = rs.getString("phoneNumber");
						txtPhone.setText(uPhone);
						txtWhat.setText(uwhat);
						if (uEmail != null)
							txtEmail.setText(uEmail);
						else
							txtEmail.setText("no email given");
					}
				} catch (SQLException se) {
					se.printStackTrace();
				}

			
		}
	}
}
