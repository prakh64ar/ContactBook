package contactBook.admin;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import contactBook.dbtask.DataBaseConnection;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class DeleteContact extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cmb;

	/**
	 * Launch the application.
	 */
	public void fillCombo() {
		Connection con = DataBaseConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select*from contact";// "*"is mean all coloumns
		try {
			ps = con.prepareStatement(sql);// prepare statement method prepare the query .
			rs = ps.executeQuery();
			String data = "";
			while (rs.next() == true) {
				String userName = rs.getString("name");// to fetch data from column
				String userPhone = rs.getString(4);// columnNo. started from 1

				data = userName + ":" + userPhone;
				cmb.addItem(data);// to add items in combo box
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		finally {
			try { 
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			
	}}}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteContact frame = new DeleteContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
					
					
						
				}
			}
		}); 
	}

	/**
	 * Create the frame.
	 */
	public DeleteContact() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DeleteContact.class.getResource("/contactBook/images/icons8-delete-24.png")));
		setTitle("Delete");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(contentPane);
		contentPane.setLayout(null);

		cmb = new JComboBox();
		cmb.setModel(new DefaultComboBoxModel(new String[] { "Select Contact" }));
		cmb.setBounds(165, 81, 219, 52);

		// call method
		fillCombo();
		contentPane.add(cmb);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 0, 128));
		btnDelete.addActionListener(this);
		btnDelete.setIcon(new ImageIcon(DeleteContact.class.getResource("/contactBook/images/icons8-delete-24.png")));
		btnDelete.setFont(new Font("Algerian", Font.BOLD, 20));
		btnDelete.setBounds(201, 229, 157, 46);
		contentPane.add(btnDelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteContact();

	}

	public void deleteContact() {
		String contactData = (String) cmb.getSelectedItem();
		System.out.println(contactData);
		if (contactData.equalsIgnoreCase("Select contact")) {
			JOptionPane.showMessageDialog(this, "Please select a vaild contact");
		} else {
			int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete");
			// System.out.println(option);
			if (option == 0) {
				// delete code will be here
				Connection con = DataBaseConnection.openConnection();
				PreparedStatement ps = null;
				String[] contactArray = contactData.split(":");
				String name = contactArray[0];
				String ph = contactArray[1];
				String deleteQuery = "delete from contact where phone=?";
				try {
					ps = con.prepareStatement(deleteQuery);
					ps.setString(1, ph);
					int status = ps.executeUpdate();
					if (status > 0) {
						JOptionPane.showMessageDialog(this, "Contact delet succesfully");
					cmb.removeAllItems();
					cmb.addItem("Select contact");
					fillCombo();
					}
				} catch (SQLException se) {
					se.printStackTrace();
				}
				finally {
					try { 
						if(ps!=null)
							ps.close();
						if(con!=null)
							con.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					
				}
					}
			}
		}
	}
}
