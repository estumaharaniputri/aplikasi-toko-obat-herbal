package UASPBO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuUtama extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUtama frame = new MenuUtama();
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
	public MenuUtama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 388);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFormProduct = new JButton("Form Product");
		btnFormProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Product().setVisible(true);
				frame.dispose();
			}
		});
		btnFormProduct.setBounds(10, 70, 258, 48);
		contentPane.add(btnFormProduct);
		
		JButton btnFormSupplier = new JButton("Form Supplier");
		btnFormSupplier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Supplier().setVisible(true);
				frame.dispose();
			}
		});
		btnFormSupplier.setBounds(10, 129, 258, 48);
		contentPane.add(btnFormSupplier);
		
		JButton btnFormStockBarang = new JButton("Form Stock Barang");
		btnFormStockBarang.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormStockBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StockBarang().setVisible(true);
				frame.dispose();
			}
		});
		btnFormStockBarang.setBounds(10, 188, 258, 48);
		contentPane.add(btnFormStockBarang);
		
		JLabel lblMenuUtama = new JLabel("Menu Utama");
		lblMenuUtama.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblMenuUtama.setBounds(228, 11, 212, 48);
		contentPane.add(lblMenuUtama);
		
		JButton btnFormMenuUser = new JButton("Form Menu User");
		btnFormMenuUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormMenuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuUser().setVisible(true);
				frame.dispose();
			}
		});
		btnFormMenuUser.setBounds(292, 129, 258, 48);
		contentPane.add(btnFormMenuUser);
		
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Laporan().setVisible(true);
				frame.dispose();
			}
		});
		btnLaporan.setBounds(292, 188, 258, 48);
		contentPane.add(btnLaporan);
		
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKeluar.setBounds(228, 287, 132, 45);
		contentPane.add(btnKeluar);
		
		JButton btnFormPenjualan = new JButton("Form Penjualan");
		btnFormPenjualan.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormPenjualan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Penjualan().setVisible(true);
				frame.dispose();
			}
		});
		btnFormPenjualan.setBounds(292, 70, 258, 48);
		contentPane.add(btnFormPenjualan);
		
	}
}
