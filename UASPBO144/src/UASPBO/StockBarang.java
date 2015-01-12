package UASPBO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.Color;

public class StockBarang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblIdProduct;
	private JTextField txtProduct;
	private JTextField txtNamaProduct;
	private JLabel lblStock;
	private JTextField txtStock;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNamaProduct;
	private JLabel lblFormMenuStockBarang;
	private JComboBox cmbCari;
	private JTextField txtCari;
	private JComboBox cmbSelect;
	private JButton btnBack;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockBarang frame = new StockBarang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int coba() 
	{
		int a=1;
		do
		{
			int b= a+1;
		}
		while(a==100);
		return a;
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select Id, Nama,stock from products";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Combobox()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select Id, Nama, stock from products";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 cmbCari.addItem(rs.getString("Nama"));
			 }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the frame.
	 */
	public StockBarang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 431);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadDataProduct = new JButton("Load Data Product");
		btnLoadDataProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoadDataProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					 String query="select Id, Nama, stock from products";
					 PreparedStatement pst=konek.prepareStatement(query);
					 ResultSet rs=pst.executeQuery();
					 table.setModel(DbUtils.resultSetToTableModel(rs));
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnLoadDataProduct.setBounds(320, 315, 345, 32);
		contentPane.add(btnLoadDataProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 52, 345, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				try
				{	
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					int row=table.getSelectedRow();
					String idP_=(table.getModel().getValueAt(row, 0).toString());
					String query="select Id, Nama, stock from products where Id='"+idP_+"'";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtProduct.setText(rs.getString("Id"));
						txtNamaProduct.setText(rs.getString("Nama"));
						txtStock.setText(rs.getString("stock"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		scrollPane.setViewportView(table);
		
		lblIdProduct = new JLabel("ID Product");
		lblIdProduct.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblIdProduct.setBounds(10, 84, 73, 14);
		contentPane.add(lblIdProduct);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(103, 81, 207, 21);
		contentPane.add(txtProduct);
		txtProduct.setColumns(10);
		
		txtNamaProduct = new JTextField();
		txtNamaProduct.setBounds(103, 113, 207, 23);
		contentPane.add(txtNamaProduct);
		txtNamaProduct.setColumns(10);
		
		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblStock.setBounds(10, 151, 73, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setBounds(103, 147, 207, 23);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="insert into products(Id,Nama,stock) values (?,?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtProduct.getText());
					pst.setString(2,txtNamaProduct.getText());
					pst.setString(3,txtStock.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "data saved");
					pst.close();

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				refresh();
			}
		});
		btnInsert.setBounds(320, 358, 107, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="Update products set Id='"+txtProduct.getText()+"',Nama='"+txtNamaProduct.getText()+"',stock='"+txtStock.getText()+"' where Id='"+txtProduct.getText()+"' ";    
					PreparedStatement pst=konek.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "data Updated");
					pst.close();
					
				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
				refresh();
			}	
		});
		btnUpdate.setBounds(437, 358, 111, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"Apakah anda yakin mau menghapus data tersebut","delete",JOptionPane.YES_NO_OPTION);
				if(action==0)
				{
					try
					{
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
						String query="delete from products where Id='"+txtProduct.getText()+"' ";
						PreparedStatement pst=konek.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "data deleted");
						pst.close();
	
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
	
					}
				}	
				refresh();
			}
		});
		btnDelete.setBounds(558, 358, 107, 23);
		contentPane.add(btnDelete);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		lblNamaProduct.setBounds(10, 117, 83, 14);
		contentPane.add(lblNamaProduct);
		
		lblFormMenuStockBarang = new JLabel("Form Menu Stock Barang");
		lblFormMenuStockBarang.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblFormMenuStockBarang.setBounds(199, 9, 269, 32);
		contentPane.add(lblFormMenuStockBarang);
		
		cmbCari = new JComboBox();
		cmbCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select Id, Nama, stock from products where Nama=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtProduct.setText(rs.getString("Id"));
						txtNamaProduct.setText(rs.getString("Nama"));
						txtStock.setText(rs.getString("stock"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		cmbCari.setBounds(103, 50, 207, 20);
		contentPane.add(cmbCari);
		
		txtCari = new JTextField();
		txtCari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					String selection=(String)cmbSelect.getSelectedItem();
					String query="select Id, Nama, stock from products where "+selection+"=?";
					System.out.println(query);
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(coba(),txtCari.getText() );
					
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
				
			}
		});
		txtCari.setBounds(103, 181, 207, 23);
		contentPane.add(txtCari);
		txtCari.setColumns(10);
		
		cmbSelect = new JComboBox();
		cmbSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbSelect.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nama", "Id_supplier"}));
		cmbSelect.setBounds(10, 182, 83, 20);
		contentPane.add(cmbSelect);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuUtama().setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setBounds(203, 358, 107, 23);
		contentPane.add(btnBack);
	
		refresh();
		Combobox();
	}
}
