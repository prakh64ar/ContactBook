package contactBook.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class LoginFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAdminId;
	private JPasswordField txtAdminPass;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/contactBook/images/icons8-name-32.png")));
		setTitle("AdminLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 869);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 50));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 59, 803, 763);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSubmit = new JButton("Login");
		btnSubmit.setSelectedIcon(new ImageIcon(LoginFrame.class.getResource("/contactBook/images/pngwing.com (2).png")));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setIcon(new ImageIcon(LoginFrame.class.getResource("/contactBook/images/pngwing.com (4).png")));
		btnSubmit.setBounds(141, 407, 194, 62);
		panel.add(btnSubmit);
		btnSubmit.setBackground(new Color(30, 30, 55));
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 30));
		
		txtAdminPass = new JPasswordField();
		txtAdminPass.setBackground(new Color(192, 192, 192));
		txtAdminPass.setBounds(55, 313, 280, 55);
		panel.add(txtAdminPass);
		
		txtAdminId = new JTextField();
		txtAdminId.setBackground(new Color(192, 192, 192));
		txtAdminId.setBounds(55, 196, 280, 46);
		panel.add(txtAdminId);
		txtAdminId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/contactBook/images/icons8-password-32.png")));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(55, 266, 148, 37);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("AdminID");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/contactBook/images/icons8-admin-32.png")));
		lblNewLabel.setBounds(55, 127, 148, 43);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/contactBook/images/730_generated.jpg")));
		lblNewLabel_2.setBounds(10, 10, 783, 743);
		panel.add(lblNewLabel_2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	//	String caption =e.getActionCommand();
	//	System.out.println(caption+" is clicked");
		checkEmpty();
		
	}
	public void checkEmpty() {
		String id=txtAdminId.getText().trim().toLowerCase();//leading and trailing space remove
		char[] pwd=txtAdminPass.getPassword();
		String pass=String.valueOf(pwd);//convert char array into string
		if(id.length()==0||pass.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Please Provide ID and Password");
		}
		else {
			if(id.equals("prakhar")&&pass.equals("1234")) {
				JOptionPane.showMessageDialog(this, "Hello "+id);
				AdminDashCode admin=new AdminDashCode();//object of admin dash code
				admin.setVisible(true);
				this.dispose();//closing login frame
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
