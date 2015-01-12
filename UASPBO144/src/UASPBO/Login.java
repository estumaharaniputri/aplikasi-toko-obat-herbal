package UASPBO;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.UIManager;

import com.mckoi.database.User;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private Connection konek = null;
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 270);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblUsername.setBounds(59, 87, 76, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		lblPassword.setBounds(59, 118, 76, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("Georgia", Font.PLAIN, 12));
		txtUsername.setBounds(146, 93, 165, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.GRAY);
		passwordField.setToolTipText("");
		passwordField.setFont(new Font("Georgia", Font.PLAIN, 12));
		passwordField.setBounds(145, 123, 165, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			 User usr = null;
			private long eventMask;
			private String level;
			private int kondisilogin=3;
			private String userlogin;

			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from Loginpbo where username=? and password=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtUsername.getText());
					pst.setString(2,passwordField.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while (rs.next())
					{
						level = rs.getString(3);
						count=count+1;
					}
					if(count==1)
					{
							if(level.equals("admin"))
							{
								kondisilogin=0;	
								JOptionPane.showMessageDialog(null, "Anda Masuk Sebagai Admin");
								new MenuUtama().setVisible(true);
								frame.dispose();

							}
							else if (level.equals("user"))
							{
								kondisilogin=1;
								JOptionPane.showMessageDialog(null, "Anda Masuk Sebagai User");
								new Penjualan().setVisible(true);
								frame.dispose();
							}

							userlogin=txtUsername.getText();

						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Maaf username atau password yang anda masukkan salah");
					}
					
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				final String user = txtUsername.getText();
			}
		});
		btnLogin.setFont(new Font("Georgia", Font.PLAIN, 15));
		btnLogin.setBounds(146, 164, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblTokoObatHerbal = new JLabel("TOKO OBAT HERBAL");
		lblTokoObatHerbal.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblTokoObatHerbal.setBounds(78, 27, 213, 14);
		contentPane.add(lblTokoObatHerbal);
		

	}
}
