package contactBook.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class HomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen frame = new HomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public void showLogin() {
    	Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
					LoginFrame login=new LoginFrame();
					login.setVisible(true);
					
					HomeScreen.this.dispose();//this outer class obj.we can access outer class obj . by there class name 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
    	t.start();
    }
	
	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Contact Book");
		lblNewLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(254, 152, 128, 58);
		contentPane.add(lblNewLabel);
		showLogin();//for showing login page after some time interval
	}
}
