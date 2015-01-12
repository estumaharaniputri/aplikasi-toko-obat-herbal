package UASPBO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Penjualan extends JFrame {

	protected static final String Hasil_stock = null;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtIDPenjualan;
	private JTextField txtNCustomer;
	private JTextField txtIDProduct;
	private JTextField txtNProduct;
	private JTextField txtJumlah;
	private JTextField txtHarga;
	private JTextField txtTgl;
	private JTextField txtTotal;
	private JTextField txtStock;
	private JTextField txtSisaStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Penjualan frame = new Penjualan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh()
	{
		try {
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			String query="select * from DetilTransaksi";
			PreparedStatement pst=konek.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Penjualan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdTranskasi = new JLabel("ID Penjualan");
		lblIdTranskasi.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblIdTranskasi.setBounds(13, 61, 93, 14);
		contentPane.add(lblIdTranskasi);
		
		JLabel lblTgl = new JLabel("tgl");
		lblTgl.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblTgl.setBounds(13, 95, 46, 14);
		contentPane.add(lblTgl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 60, 532, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtIDPenjualan = new JTextField();
		txtIDPenjualan.setBounds(131, 61, 211, 20);
		contentPane.add(txtIDPenjualan);
		txtIDPenjualan.setColumns(10);
		
		JLabel lblNamaSupplier = new JLabel("Nama Customer");
		lblNamaSupplier.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNamaSupplier.setBounds(13, 130, 93, 14);
		contentPane.add(lblNamaSupplier);
		
		txtNCustomer = new JTextField();
		txtNCustomer.setColumns(10);
		txtNCustomer.setBounds(131, 130, 211, 20);
		contentPane.add(txtNCustomer);
		
		JLabel lblIdProduk = new JLabel("ID Produk");
		lblIdProduk.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblIdProduk.setBounds(13, 169, 71, 14);
		contentPane.add(lblIdProduk);
		
		txtIDProduct = new JTextField();
		txtIDProduct.setColumns(10);
		txtIDProduct.setBounds(131, 166, 138, 20);
		contentPane.add(txtIDProduct);
		
		JButton btnCari = new JButton("Cari");
		btnCari.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select Id, Nama, Harga, stock from products where Id=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, txtIDProduct.getText());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIDProduct.setText(rs.getString("Id"));
						txtNProduct.setText(rs.getString("Nama"));
						txtHarga.setText(rs.getString("Harga"));
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
		btnCari.setBounds(276, 165, 66, 23);
		contentPane.add(btnCari);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk");
		lblNamaProduk.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNamaProduk.setBounds(13, 201, 93, 14);
		contentPane.add(lblNamaProduk);
		
		txtNProduct = new JTextField();
		txtNProduct.setColumns(10);
		txtNProduct.setBounds(131, 198, 211, 20);
		contentPane.add(txtNProduct);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblJumlah.setBounds(13, 232, 71, 14);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(131, 229, 138, 20);
		contentPane.add(txtJumlah);
		
		JLabel lblTotal = new JLabel("Harga");
		lblTotal.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblTotal.setBounds(13, 325, 71, 14);
		contentPane.add(lblTotal);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(131, 322, 211, 20);
		contentPane.add(txtHarga);
		
		JLabel lblTransaksi = new JLabel("PENJUALAN");
		lblTransaksi.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblTransaksi.setBounds(405, 10, 357, 39);
		contentPane.add(lblTransaksi);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
				 	String query="insert into DetilTransaksi(Id,Id_transaksi,Nama,Harga,Jumlah) values (?,?,?,?,?)";			 	
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtIDProduct.getText());
					pst.setString(2,txtIDPenjualan.getText());
					pst.setString(3,txtNProduct.getText());
					pst.setString(4, txtHarga.getText());
					pst.setString(5, txtJumlah.getText());				
					
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
		btnSave.setBounds(131, 423, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnExit.setBounds(253, 423, 89, 23);
		contentPane.add(btnExit);
		
		txtTgl = new JTextField();
		txtTgl.setColumns(10);
		txtTgl.setBounds(131, 92, 211, 20);
		contentPane.add(txtTgl);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		
		lblTotal_1.setBounds(13, 395, 71, 14);
		contentPane.add(lblTotal_1);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(131, 392, 211, 20);
		contentPane.add(txtTotal);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Jumlah = txtJumlah.getText();
				String Harga = txtHarga.getText();
				String Stock = txtStock.getText();
				
				 int Jumlah_J = Integer.parseInt(Jumlah);
				 int Harga_H = Integer.parseInt(Harga);
				 int S_Stock = Integer.parseInt(Stock);
				 int Total = Jumlah_J * Harga_H;
				 int sStock = S_Stock - Jumlah_J;
				 
				 String Hasil = String.valueOf(Total);
				 txtTotal.setText(Hasil);
				 String Hasil_Stock = String.valueOf(sStock);
				 txtSisaStock.setText(Hasil_Stock);
				 
				 try 
					{
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					 	String query="insert into transaksi(Id_transaksi,Nama,Tanggal_jual,Harga_total) values (?,?,?,?)";
					 	String query1="Update products set stock='"+txtSisaStock.getText()+"' where Id='"+txtIDProduct.getText()+"' ";
						PreparedStatement pst=konek.prepareStatement(query);
						PreparedStatement pst1=konek.prepareStatement(query1);
						pst.setString(1,txtIDPenjualan.getText());
						pst.setString(3,txtNCustomer.getText());
						pst.setString(4,txtTgl.getText());
						pst.setString(5, txtHarga.getText());
						pst.setString(7, txtTotal.getText());
						
						
						pst.execute();
						pst1.execute();


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
		btnTotal.setBounds(253, 358, 89, 23);
		contentPane.add(btnTotal);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(131, 260, 138, 20);
		contentPane.add(txtStock);
		
		txtSisaStock = new JTextField();
		txtSisaStock.setColumns(10);
		txtSisaStock.setBounds(131, 291, 138, 20);
		contentPane.add(txtSisaStock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblStock.setBounds(13, 263, 71, 14);
		contentPane.add(lblStock);
		
		JLabel lblSisaStock = new JLabel("Sisa Stock");
		lblSisaStock.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblSisaStock.setBounds(13, 294, 71, 14);
		contentPane.add(lblSisaStock);
		
		refresh();
	}
}
