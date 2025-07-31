package contactBook.admin;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import contactBook.dbtask.DataBaseConnection;
import contactBook.dbtask.FriendContack_db_connection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class FriendContact extends JFrame implements ActionListener,KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtwhat;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextField txtname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendContact frame = new FriendContact();
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
	public FriendContact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FriendContact.class.getResource("/contactBook/images/icons8-submit-resume-32.png")));
		setTitle("friend contact");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(55, 111, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("Name");
		lblname.setIcon(new ImageIcon(FriendContact.class.getResource("/contactBook/images/icons8-name-32.png")));
		lblname.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblname.setBounds(10, 63, 165, 35);
		contentPane.add(lblname);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setIcon(new ImageIcon(FriendContact.class.getResource("/contactBook/images/icons8-email-32.png")));
		lblemail.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblemail.setBounds(10, 131, 199, 35);
		contentPane.add(lblemail);
		
		JLabel lblphone = new JLabel("Phone number");
		lblphone.setIcon(new ImageIcon(FriendContact.class.getResource("/contactBook/images/icons8-phone-32.png")));
		lblphone.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblphone.setBounds(10, 196, 216, 35);
		contentPane.add(lblphone);
		
		JLabel lblwhatsapp = new JLabel("Whatsapp");
		lblwhatsapp.setIcon(new ImageIcon(FriendContact.class.getResource("/contactBook/images/icons8-whatsapp-32.png")));
		lblwhatsapp.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblwhatsapp.setBounds(10, 261, 199, 35);
		contentPane.add(lblwhatsapp);
		
		txtwhat = new JTextField();
		txtwhat.setBounds(236, 273, 189, 40);
		contentPane.add(txtwhat);
		txtwhat.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBounds(236, 212, 189, 40);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(236, 143, 189, 40);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(236, 75, 189, 40);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addKeyListener(this);
		btnsubmit.addActionListener(this);
		btnsubmit.setIcon(new ImageIcon(FriendContact.class.getResource("/contactBook/images/icons8-update-30.png")));
		btnsubmit.setBackground(new Color(255, 0, 128));
		btnsubmit.setFont(new Font("Pristina", Font.ITALIC, 27));
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsubmit.setBounds(145, 371, 176, 43);
		contentPane.add(btnsubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addContact();
		
	}
	public void addContact() {
		String phone = txtphone.getText(); 
		String email = txtemail.getText();
		String name = txtname.getText();
		String whatsapp = txtwhat.getText();
		if (name.isEmpty() ||  phone.isEmpty() ) {
			JOptionPane.showMessageDialog(this, "Data required");
		}
		
		
		else if (phone.length() < 10 || phone.length() > 10) {
			JOptionPane.showMessageDialog(this, "Phone number must contain 10 digit only");
		}
		else if (whatsapp.length() < 10 || whatsapp.length() > 10) {
			JOptionPane.showMessageDialog(this, "whatsApp number must contain 10 digit only");}
		else {
			
			Connection con=FriendContack_db_connection.openConnection();
			PreparedStatement ps=null;
			String insertQuery="insert into friend( name, email, phoneNumber, whatsappNumber, date)values(?,?,?,?,?)";
		java.util.Date d=new java.util.Date();
		System.out.println("java date : "+d);
		long dt=d.getTime();
		java.sql.Date sqlDate = new java.sql.Date(dt);
		System.out.println("sql date is : "+sqlDate);
		
		try {
			ps=con.prepareStatement(insertQuery);
			ps.setString(1,name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, whatsapp);
			ps.setDate(5, sqlDate);
		
			int rowStatus=ps.executeUpdate();
			if(rowStatus>0) {
				JOptionPane.showMessageDialog(this, "conectact Added Succesfully");
			txtname.setText("");
			txtemail.setText("");
			txtphone.setText("");
			txtwhat.setText("");
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
		System.out.println("WhatsApp number : " + whatsapp);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtname) {
			char c = e.getKeyChar();
			if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE
					|| c == KeyEvent.VK_DELETE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "only Alphabets are allowed");
			}
		}

		// for phone number txtFeild

		if (e.getSource() == txtphone) {
			char c = e.getKeyChar();
			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "only Number are allowed");
			}
			if (e.getSource() == txtwhat) {
				char ca = e.getKeyChar();
				if (!(Character.isDigit(ca) || ca == KeyEvent.VK_BACK_SPACE || ca == KeyEvent.VK_DELETE)) {
					e.consume();
					JOptionPane.showMessageDialog(this, "only Number are allowed");
				}
		}}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
