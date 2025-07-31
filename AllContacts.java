package contactBook.admin;

import java.awt.EventQueue;
import java.sql.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import contactBook.dbtask.DataBaseConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllContacts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllContacts frame = new AllContacts();
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
	public void fillTable() {
		Connection con = DataBaseConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *from contact";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			TableModel tm = DbUtils.resultSetToTableModel(rs);
			table.setModel(tm);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public AllContacts() {
		setTitle("Contacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 92, 523, 352);
		contentPane.add(scrollPane);

		table = new JTable();
		// chance the format of table header
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(136, 255, 62));
		header.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
		header.setForeground(new Color(100, 144, 233));
		table.setForeground(new Color(13, 188, 144));

		fillTable();

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(3).setHeaderValue("MobileNumber");
		tcm.getColumn(2).setHeaderValue("EmailId");
		scrollPane.setViewportView(table);
	}
}
