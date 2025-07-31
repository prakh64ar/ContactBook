package contactBook.admin;

import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

public class AdminDashCode extends JFrame implements ActionListener ,WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashCode frame = new AdminDashCode();
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
	public AdminDashCode() {
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminDashCode.class.getResource("/contactBook/images/contact-mail.png")));
		setTitle("AdminDashCode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 409);
		setExtendedState(MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnContact = new JMenu("Contacts");
		mnContact.setBackground(new Color(0, 255, 128));
		menuBar.add(mnContact);

		JMenuItem mi_add = new JMenuItem("NewContact");
		mi_add.addActionListener(this);
		mnContact.add(mi_add);

		JMenuItem mi_edit = new JMenuItem("EditContact");
		mi_edit.addActionListener(this);
		mnContact.add(mi_edit);

		JMenuItem mi_delete = new JMenuItem("DeleteContact");
		mi_delete.addActionListener(this);
		mnContact.add(mi_delete);
		
		JMenu FriendContacts = new JMenu("FriendContact");
		FriendContacts.setBackground(new Color(0, 0, 160));
		FriendContacts.addActionListener(this);
		menuBar.add(FriendContacts);
		
		JMenuItem NewFriendContacts = new JMenuItem("New Friend");
		NewFriendContacts.addActionListener(this);
		FriendContacts.add(NewFriendContacts);
		
		JMenuItem DeleteFriendContact = new JMenuItem("DeleteFriendContact");
		DeleteFriendContact.addActionListener(this);
		FriendContacts.add(DeleteFriendContact);
		
		JMenuItem UpdateFriendContact = new JMenuItem("UpdateFriendContact");
		UpdateFriendContact.addActionListener(this);
		FriendContacts.add(UpdateFriendContact);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 64));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption = e.getActionCommand();
		if (caption.equalsIgnoreCase("NewContact")) {
			ContactDetail cd = new ContactDetail();
			cd.setVisible(true);
		}
		if (caption.equalsIgnoreCase("DeleteContact")) {
			DeleteContact dc = new DeleteContact();
			dc.setVisible(true);
		}
		if (caption.equalsIgnoreCase("EditContact")) {
			UpdateContact uc = new UpdateContact();
			uc.setVisible(true);
		}
		if (caption.equalsIgnoreCase("New Friend")) {
			FriendContact cd = new FriendContact();
			cd.setVisible(true);
		}
		if (caption.equalsIgnoreCase("DeleteFriendContact")) {
			DeleteFriendContact dc = new DeleteFriendContact();
			dc.setVisible(true);
		}
		if (caption.equalsIgnoreCase("UpdateFriendContact")) {
			UpdateFriendContact uc = new UpdateFriendContact();
			uc.setVisible(true);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		JOptionPane.showMessageDialog(this, "Thanku for using me", "Thanku Message" ,JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
