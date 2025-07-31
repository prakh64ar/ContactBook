package contactBook.admin;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import contactBook.dbtask.DataBaseConnection;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class ContactDetail extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtEmail;
	private JTextArea txtAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactDetail frame = new ContactDetail();
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
	public ContactDetail() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ContactDetail.class.getResource("/contactBook/images/icons8-contact-32.png")));
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setForeground(new Color(0, 0, 128));
		setFont(new Font("Arial Black", Font.ITALIC, 24));
		setTitle("Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setForeground(new Color(0, 0, 160));

		setContentPane(contentPane);
		setLocationRelativeTo(contentPane);// it palaces the frame in center of the screen
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setIcon(new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-name-32.png")));
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		lblName.setBackground(new Color(0, 64, 0));
		lblName.setBounds(65, 91, 108, 33);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.addKeyListener(this);
		txtName.setBounds(224, 86, 150, 44);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblNumber = new JLabel("Phone no.");
		lblNumber.setIcon(new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-phone-32.png")));
		lblNumber.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		lblNumber.setBackground(new Color(255, 0, 128));
		lblNumber.setBounds(65, 170, 121, 33);
		contentPane.add(lblNumber);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setIcon(new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-email-32.png")));
		lblEmail.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		lblEmail.setBackground(new Color(255, 0, 128));
		lblEmail.setBounds(65, 242, 108, 33);
		contentPane.add(lblEmail);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setIcon(new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-address-32.png")));
		lblAddress.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		lblAddress.setBackground(new Color(255, 0, 128));
		lblAddress.setBounds(65, 338, 121, 33);
		contentPane.add(lblAddress);

		txtNumber = new JTextField();
		txtNumber.addKeyListener(this);
		txtNumber.setBounds(224, 161, 150, 44);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);

		txtEmail = new JTextField();

		txtEmail.setBounds(224, 240, 150, 44);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(
				new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-submit-resume-32.png")));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBackground(new Color(255, 128, 0));

		btnSubmit.setFont(new Font("Berlin Sans FB", Font.BOLD, 27));
		btnSubmit.setBounds(146, 421, 194, 63);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(this);
		JLabel lblENter = new JLabel("Enter Contact Detail Here");
		lblENter.setIcon(new ImageIcon(ContactDetail.class.getResource("/contactBook/images/icons8-contact-32.png")));
		lblENter.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblENter.setBounds(117, 24, 406, 44);
		contentPane.add(lblENter);
		
	 txtAddress = new JTextArea();
		txtAddress.setBounds(224, 338, 150, 44);
		contentPane.add(txtAddress);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// String caption = e.getActionCommand();
		// System.out.println(caption + " is clicked");
		addContact();
	}

	public void addContact() {
		String phone = txtNumber.getText();
		String email = txtEmail.getText();
		String name = txtName.getText();
		String address = txtAddress.getText();
		if (name.isEmpty() ||  phone.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Data required");
		}
		 if(!email.isEmpty()) {
		 if (email.indexOf("@") == -1 || email.indexOf(".") == -1 || email.indexOf("com") == -1) {
			JOptionPane.showMessageDialog(this, "Email must contain @ and . and com");
		}
		}
		 if (phone.length() < 10 || phone.length() > 10) {
			JOptionPane.showMessageDialog(this, "Phone numbe must contain 10 digit only");
		}
		else {
			//code for adding data into contact table
			Connection con=DataBaseConnection.openConnection();
			PreparedStatement ps=null;//comnucate the table
			String insertQuery="insert into contact(name, email, phone, address, date)values(?,?,?,?,?)";
		java.util.Date d=new java.util.Date();
		System.out.println("java date : "+d);//it contain date and time both
		long dt=d.getTime();//1970
		java.sql.Date sqlDate = new java.sql.Date(dt);
		System.out.println("sql date is : "+sqlDate);
		
		try {
			ps=con.prepareStatement(insertQuery);
			ps.setString(1,name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setDate(5, sqlDate);
		System.out.println("final query is "+ps);
			int rowStatus=ps.executeUpdate();
			if(rowStatus>0) {
				JOptionPane.showMessageDialog(this, "conectact Added Succesfully");
			txtName.setText("");
			txtEmail.setText("");
			txtAddress.setText("");
			txtNumber.setText("");
			}
		}
		catch (SQLException se) {
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
		System.out.println("Name : " + name);
		System.out.println("Phone number : " + phone);
		System.out.println("Email : " + email);
		System.out.println("Address : " + address);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtName) {
			char c = e.getKeyChar();
			if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE
					|| c == KeyEvent.VK_DELETE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "only Alphabets are allowed");
			}
		}

		// for phone number txtFeild

		if (e.getSource() == txtNumber) {
			char c = e.getKeyChar();
			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "only Number are allowed");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
