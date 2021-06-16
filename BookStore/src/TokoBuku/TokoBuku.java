package TokoBuku;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class TokoBuku {

	private JFrame frmTokoBukuLinda;
	private JTabbedPane tabbedPane;
	private JTextField id;
	private JTextField judul;
	private JTextField penerbit;
	private JTextField harga;
	private JTextField stok;
	private JTable table;
	private JTextField cari;
	private JTextArea textAreaCek;
	private JTextField beli;
	private JTextField nominal;
	private JTextArea textAreaBeli;

	/**
	 * Fungsi untuk menjalankan aplikasi
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TokoBuku window = new TokoBuku();
					window.frmTokoBukuLinda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 *  Fungsi untuk menampilkan data ke table, mengimplementasikan data struktur Array
	 * 
	 */
	public void showData() {
		try {
			String query = "select * from databuku";
			Connection con = (Connection)JDBC.koneksi();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
	        model.setRowCount(0);
	        
	        String [] data = new String [5];
	        
	        while(rs.next()) {
	        	 data[0] = rs.getString("idbuku");
		         data[1] = rs.getString("judul");
		         data[2] = rs.getString("penerbit");
		         data[3] = rs.getString("harga");
		         data[4] = rs.getString("stok");
		         
	             model.addRow(data); 
	        }
	        
	        if (con != null) {
	        	try {
	        		con.close();
				} catch (SQLException e4) {}
	        }
		 
		    if (pst != null) {
	        	try {
	        		pst.close();
				} catch (SQLException e5) {}
	        }
		    
		    if (rs != null) {
	        	try {
	        		rs.close();
				} catch (SQLException e5) {}
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Insert Data");
			System.out.println(e);
		}
	}
	
	/*
	 *  Fungsi untuk bersihkan field
	 */
	public void clearForm() {
		id.setText("");
		judul.setText("");
		penerbit.setText("");
		harga.setText("");
		stok.setText("");
	}
	
	/*
	 *  Fungsi untuk mencari data dari database
	 */
	public class searchFunction{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		public ResultSet find(String s){
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/tokobuku", "root", "");
				ps = con.prepareStatement("select * from databuku where idbuku= ?");
				ps.setString(1, s);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return rs;
		}
	}

	/**
	 * Create the application.
	 */
	public TokoBuku() {
		initialize();
		showData();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmTokoBukuLinda = new JFrame();
		frmTokoBukuLinda.setIconImage(Toolkit.getDefaultToolkit().getImage(TokoBuku.class.getResource("/image/logo3.png")));
		frmTokoBukuLinda.setTitle("Toko Buku Linda");
		frmTokoBukuLinda.setBounds(100, 100, 850, 537);
		frmTokoBukuLinda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTokoBukuLinda.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 102));
		panel.setBounds(0, 0, 206, 576);
		frmTokoBukuLinda.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(34, 75, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 11, 186, 136);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(TokoBuku.class.getResource("/image/logo2.png")));
		
		JLabel lblNewLabel_3 = new JLabel("Toko Buku");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_3.setBounds(39, 150, 123, 29);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("     BOOK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(new Color(0,0,0));
				btnNewButton.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setBackground(Color.BLACK);
			}
		});
		btnNewButton.setForeground(new Color(0,0,0));
		btnNewButton.setBackground(new Color(224,255,255));
		btnNewButton.setFocusable(false);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 23));
		btnNewButton.setBounds(10, 242, 186, 79);
		btnNewButton.setIcon(new ImageIcon(TokoBuku.class.getResource("/image/plus.png")));
		panel.add(btnNewButton);
		
		JButton btnBuy = new JButton("     BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnBuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuy.setForeground(Color.WHITE);
				btnBuy.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuy.setForeground(new Color(0,0,0));
				btnBuy.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnBuy.setForeground(Color.WHITE);
				btnBuy.setBackground(Color.BLACK);
			}
		});
		btnBuy.setForeground(new Color(0,0,0));
		btnBuy.setBackground(new Color(224,255,255));
		btnBuy.setFocusable(false);
		btnBuy.setHorizontalAlignment(SwingConstants.LEADING);
		btnBuy.setFont(new Font("Stencil", Font.PLAIN, 24));
		btnBuy.setBounds(10, 347, 186, 79);
		btnBuy.setIcon(new ImageIcon(TokoBuku.class.getResource("/image/buy.png")));
		panel.add(btnBuy);
		
		JLabel lblNewLabel_4 = new JLabel("LINDA");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(60, 186, 83, 14);
		panel.add(lblNewLabel_4);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(196, -29, 649, 541);
		frmTokoBukuLinda.getContentPane().add(tabbedPane);
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(0, 153, 153));
		tabbedPane.addTab("New tab", null, panelHome, null);
		panelHome.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(146, 93, 366, 297);
		panelHome.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(TokoBuku.class.getResource("/image/gtt.png")));
		
		JPanel panelTambah = new JPanel();
		panelTambah.setBackground(new Color(0, 139, 139));
		tabbedPane.addTab("New tab", null, panelTambah, null);
		panelTambah.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("ID Buku");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5.setBounds(163, 86, 80, 31);
		panelTambah.add(lblNewLabel_5);
		
		id = new JTextField();
		id.setFont(new Font("Tahoma", Font.BOLD, 14));
		id.setBounds(301, 86, 195, 31);
		panelTambah.add(id);
		id.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBounds(23, 348, 600, 143);
		panelTambah.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 122);
		panel_1.add(scrollPane);
		
		table = new JTable();
		
		/*
		 *  Fungsi untuk ketika klik kolom tabel akan mengirim data ke form
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean set = false;
				id.setEditable(set);
				
				int row = table.rowAtPoint(e.getPoint());
				
				id.setText(table.getValueAt(row, 0).toString());
				judul.setText(table.getValueAt(row, 1).toString());
				penerbit.setText(table.getValueAt(row, 2).toString());
				harga.setText(table.getValueAt(row, 3).toString());
				stok.setText(table.getValueAt(row, 4).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Buku", "Judul", "Penerbit", "Harga", "Stok"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Long.class, Integer.class
			};
	
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	
		judul = new JTextField();
		judul.setFont(new Font("Tahoma", Font.BOLD, 14));
		judul.setColumns(10);
		judul.setBounds(301, 129, 195, 30);
		panelTambah.add(judul);
		
		JLabel lblNewLabel_5_1 = new JLabel("Judul Buku");
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5_1.setBounds(163, 129, 100, 30);
		panelTambah.add(lblNewLabel_5_1);
		
		penerbit = new JTextField();
		penerbit.setFont(new Font("Tahoma", Font.BOLD, 14));
		penerbit.setColumns(10);
		penerbit.setBounds(301, 170, 195, 31);
		panelTambah.add(penerbit);
		
		JLabel lblNewLabel_5_2 = new JLabel("Penerbit");
		lblNewLabel_5_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5_2.setBounds(163, 170, 80, 31);
		panelTambah.add(lblNewLabel_5_2);
		
		harga = new JTextField();
		harga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}	
		});
		harga.setFont(new Font("Tahoma", Font.BOLD, 14));
		harga.setColumns(10);
		harga.setBounds(301, 212, 195, 30);
		panelTambah.add(harga);
		
		JLabel lblNewLabel_5_3 = new JLabel("Harga");
		lblNewLabel_5_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5_3.setBounds(163, 212, 80, 30);
		panelTambah.add(lblNewLabel_5_3);
		
		stok = new JTextField();
		stok.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}	
		});
		stok.setFont(new Font("Tahoma", Font.BOLD, 14));
		stok.setColumns(10);
		stok.setBounds(301, 253, 195, 30);
		panelTambah.add(stok);
		
		JLabel lblNewLabel_5_4 = new JLabel("Stok");
		lblNewLabel_5_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_5_4.setBounds(163, 253, 80, 30);
		panelTambah.add(lblNewLabel_5_4);
		
		JButton simpan = new JButton("Simpan");
		
		/*
		 *  Fungsi untuk memasukkan data buku baru ke database dan tabel
		 */
		simpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "insert into databuku values('"+id.getText()+"','"+judul.getText()+"','"+penerbit.getText()+"','"+Long.parseLong(harga.getText())+"','"+Integer.parseInt(stok.getText())+"')";
					Connection con = (Connection)JDBC.koneksi();
					PreparedStatement pst = con.prepareStatement(sql);
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "ID Buku tidak boleh kosong!");
					} else if (judul.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Judul Buku tidak boleh kosong!");
					} else if (penerbit.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Penerbit tidak boleh kosong!");
					} else if (harga.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Harga Buku tidak boleh kosong!");
					} else if (stok.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Stok tidak boleh kosong!");
					} else {
						pst.execute();
						JOptionPane.showMessageDialog(simpan, "Data Buku Tersimpan");
						showData();
						clearForm();
						id.setEditable(true);
						
						if (con != null) {
	        	        	try {
	        	        		con.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (pst != null) {
	        	        	try {
	        	        		pst.close();
	        				} catch (SQLException e5) {}
	        	        }
					} 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(simpan, "Gagal simpan data", "KESALAHAN INPUT!!", 0);
					System.out.println(e1);
				}
			}
		});
		simpan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				simpan.setForeground(Color.WHITE);
				simpan.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				simpan.setForeground(new Color(0,0,0));
				simpan.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				simpan.setForeground(Color.WHITE);
				simpan.setBackground(Color.BLACK);
			}
		});
		simpan.setBackground(new Color(255, 255, 255));
		simpan.setFont(new Font("Tahoma", Font.BOLD, 11));
		simpan.setBounds(103, 308, 89, 29);
		panelTambah.add(simpan);
		
		JButton edit = new JButton("Edit");
		
		/*
		 *  Fungsi untuk mengedit data buku
		 */
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(edit, "Yakin ingin mengubah data barang?", "Confirm", JOptionPane.YES_NO_OPTION);
				try {
					String query = "update databuku set judul='"+id.getText()+"',penerbit='"+penerbit.getText()+"',harga='"+Long.parseLong(harga.getText())+"',stok='"+Integer.parseInt(stok.getText())+"' where idbuku='"+id.getText()+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(query);
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "ID Buku tidak boleh kosong!");
					} else if (judul.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Judul Buku tidak boleh kosong!");
					} else if (penerbit.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Penerbit tidak boleh kosong!");
					} else if (harga.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Harga Buku tidak boleh kosong!");
					} else if (stok.getText().equals("")) {
						JOptionPane.showMessageDialog(simpan, "Stok tidak boleh kosong!");
					} else if (confirm==0) {
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(edit, "Update data barang berhasil");
                        showData();
                        clearForm();
                        id.setEditable(true);
                        
                        if (conn != null) {
	        	        	try {
	        	        		conn.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (st != null) {
	        	        	try {
	        	        		st.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(edit, "Gagal update data");
					System.out.println(e2);
				}
			}
		});
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				edit.setForeground(Color.WHITE);
				edit.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				edit.setForeground(new Color(0,0,0));
				edit.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				edit.setForeground(Color.WHITE);
				edit.setBackground(Color.BLACK);
			}
		});
		edit.setBackground(new Color(255, 255, 255));
		edit.setFont(new Font("Tahoma", Font.BOLD, 11));
		edit.setBounds(226, 308, 89, 29);
		panelTambah.add(edit);
		
		JButton hapus = new JButton("Hapus");
		
		/*
		 *  Fungsi untuk menghapus data buku
		 */
		hapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(hapus, "Apakah anda yakin ingin menghapus data buku?", "Warning!!!.", JOptionPane.YES_NO_OPTION);
				
				try {
					String query = "delete from databuku where idbuku='"+id.getText()+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(query);
					
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(hapus, "Tidak ada data yang dipilih");
					} else if (confirm==0) {
						st.executeUpdate();
						JOptionPane.showMessageDialog(hapus, "Berhasil menghapus data buku...");
						showData();
						clearForm();
						id.setEditable(true);
						
						if (conn != null) {
	        	        	try {
	        	        		conn.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (st != null) {
	        	        	try {
	        	        		st.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(hapus, "Gagal Hapus");
					System.out.println(e2);
				}
			}
		});
		hapus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hapus.setForeground(Color.WHITE);
				hapus.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hapus.setForeground(new Color(0,0,0));
				hapus.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				hapus.setForeground(Color.WHITE);
				hapus.setBackground(Color.BLACK);
			}
		});
		hapus.setBackground(new Color(255, 255, 255));
		hapus.setFont(new Font("Tahoma", Font.BOLD, 11));
		hapus.setBounds(347, 308, 89, 29);
		panelTambah.add(hapus);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				id.setEditable(true);
			}
		});
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRefresh.setForeground(Color.WHITE);
				btnRefresh.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRefresh.setForeground(new Color(0,0,0));
				btnRefresh.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnRefresh.setForeground(Color.WHITE);
				btnRefresh.setBackground(Color.BLACK);
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(469, 308, 89, 29);
		panelTambah.add(btnRefresh);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(211, 21, 210, 43);
		panelTambah.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_6_1 = new JLabel("INPUT DATA BUKU");
		lblNewLabel_6_1.setBounds(0, 0, 210, 43);
		panel_4.add(lblNewLabel_6_1);
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
		
		JPanel panelBeli = new JPanel();
		panelBeli.setBackground(new Color(0, 139, 139));
		tabbedPane.addTab("New tab", null, panelBeli, null);
		panelBeli.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(127, 255, 0));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(234, 11, 175, 37);
		panelBeli.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Pembelian Buku");
		lblNewLabel_6.setForeground(new Color(0, 128, 128));
		lblNewLabel_6.setBounds(0, 0, 175, 37);
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(new Color(0, 206, 209));
		panel_3.setBounds(73, 60, 496, 205);
		panelBeli.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Masukkan ID Buku");
		lblNewLabel_7.setBounds(43, 51, 140, 14);
		panel_3.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		cari = new JTextField();
		cari.setBounds(43, 76, 140, 27);
		panel_3.add(cari);
		cari.setColumns(10);
		
		JButton btnCek = new JButton("Cek Buku");
		btnCek.setFocusable(false);
		btnCek.setBackground(new Color(255, 255, 255));
		
		/*
		 *  Fungsi untuk mengecek ada atau tidaknya buku
		 */
		btnCek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFunction sf = new searchFunction();
				ResultSet rs = null;
				
				rs = sf.find(cari.getText());
				
				try {
					if (rs.next()) {
						textAreaCek.setText("Data buku ditemukan....\nRincian buku:\n"+"ID Buku\t: "+rs.getString("idbuku")+"\nJudul Buku\t: "+rs.getString("judul")+"\nPenerbit\t: "+rs.getString("penerbit")+"\nHarga Buku\t: "+String.valueOf(rs.getString("harga"))+"\nStok Buku\t: "+String.valueOf(rs.getString("stok"))+"");
					} else {
						JOptionPane.showMessageDialog(null, "Data buku tidak ditemukan");
						cari.setText("");
						textAreaCek.setText("");
					
	        		    if (rs != null) {
	        	        	try {
	        	        		rs.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					cari.setText("");
					textAreaCek.setText("");
				  }
			}
		});
		btnCek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCek.setForeground(Color.WHITE);
				btnCek.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCek.setForeground(new Color(0,0,0));
				btnCek.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnCek.setForeground(Color.WHITE);
				btnCek.setBackground(Color.BLACK);
			}
		});
		btnCek.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCek.setBounds(43, 114, 140, 32);
		panel_3.add(btnCek);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(211, 28, 249, 155);
		panel_3.add(scrollPane_1);
		
		textAreaCek = new JTextArea();
		textAreaCek.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane_1.setViewportView(textAreaCek);
		
		JLabel lblNewLabel_7_1 = new JLabel("Jumlah buku dibeli");
		lblNewLabel_7_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7_1.setBounds(102, 288, 157, 29);
		panelBeli.add(lblNewLabel_7_1);
		
		beli = new JTextField();
		beli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}
		});
		beli.setColumns(10);
		beli.setBounds(257, 289, 140, 29);
		panelBeli.add(beli);
		
		JButton btnBeli = new JButton("Beli");
		btnBeli.setFocusable(false);
		
		/*
		 *  Fungsi untuk menampilkan data buku yang ingin dibeli pada text area
		 */
		btnBeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFunction sf = new searchFunction();
				ResultSet rs = null;
				
				rs = sf.find(cari.getText());
				
				try {
					if (rs.next()) {
						int sisaStok = rs.getInt("stok") - Integer.valueOf(beli.getText());
						long totalBayar = Long.valueOf(beli.getText()) * rs.getLong("harga");
						
						if (Integer.valueOf(beli.getText()) <= rs.getInt("stok")) {
							textAreaBeli.setText("ID Buku\t: "+rs.getString("idbuku")+"\nJudul Buku\t: "+rs.getString("judul")+"\nPenerbit\t: "+rs.getString("penerbit")+"\nHarga satuan\t: Rp "+String.valueOf(rs.getString("harga"))+"\nStok Buku\t: "+String.valueOf(rs.getString("stok"))+" buku\n=============================="+"\nPembelian.....\nTotal Biaya\t: Rp"+totalBayar+"\nSisa Stok\t: "+sisaStok+" buku");
						} else if (beli.getText().equals("")) {
							JOptionPane.showMessageDialog(beli, "Cek dulu ya....");
						}else {
							JOptionPane.showMessageDialog(beli, "Stok barang tidak cukup");
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					cari.setText("");
					textAreaCek.setText("");
				  }	finally {
	        		    if (rs != null) {
	        	        	try {
	        	        		rs.close();
	        				} catch (SQLException e5) {}
	        	        }
				  }
			}
		});
		btnBeli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBeli.setForeground(Color.WHITE);
				btnBeli.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBeli.setForeground(new Color(0,0,0));
				btnBeli.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnBeli.setForeground(Color.WHITE);
				btnBeli.setBackground(Color.BLACK);
			}
		});
		btnBeli.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBeli.setBackground(Color.WHITE);
		btnBeli.setBounds(419, 292, 110, 25);
		panelBeli.add(btnBeli);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(102, 328, 295, 147);
		panelBeli.add(scrollPane_2);
		
		textAreaBeli = new JTextArea();
		textAreaBeli.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane_2.setViewportView(textAreaBeli);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Nominal Bayar");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7_1_1.setBounds(408, 367, 126, 29);
		panelBeli.add(lblNewLabel_7_1_1);
		
		nominal = new JTextField();
		nominal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}
		});
		nominal.setColumns(10);
		nominal.setBounds(408, 397, 133, 27);
		panelBeli.add(nominal);
		
		JButton btnBayar = new JButton("Bayar");
		btnBayar.setFocusable(false);
		
		/*
		 *  KODINGAN UNTUK FUNGSI TOMBOL BAYAR DIMANA KETIKA NOMINAL BAYAR LEBIH DARI TOTAL BIAYA,
		 *  MAKA AKAN SUKSES TERJUAL...DAN DATA STOK BARANG DIPERBAHARUI
		 *  
		 *  KETIKA NOMINAL BAYAR TIDAK MENCUKUPI, MAKA AKAN MUNCUL PESAN NOMINAL TIDAK CUKUP
		 *  KETIKA BARANG DIBELI HABIS MAKA DATA OTOMATIS TERHAPUS DARI DATABASE
		 */
		btnBayar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFunction sf = new searchFunction();
				ResultSet rs = null;
				
				rs = sf.find(cari.getText());
				
				try {
					if (rs.next()) {
						int sisaStok = rs.getInt("stok") - Integer.valueOf(beli.getText());
						long totalBayar = Long.valueOf(beli.getText()) * rs.getLong("harga");
						
						if (Long.valueOf(nominal.getText()) >= totalBayar && sisaStok > 0) {
							int con = JOptionPane.showConfirmDialog(textAreaBeli, "Lanjutkan membayar?", "Confirm", JOptionPane.YES_NO_OPTION);
							if (con == 0) {
								JOptionPane.showMessageDialog(textAreaBeli, "Pembayaran Berhasil");
								try {
									String query = "update databuku set stok='"+(rs.getInt("stok") - Integer.valueOf(beli.getText()))+"' where idbuku='"+cari.getText()+"'";
									Connection conn = (Connection)JDBC.koneksi();
									PreparedStatement st = conn.prepareStatement(query);
				                    st.executeUpdate();
				                    showData();
				                    textAreaCek.setText("");
				                    textAreaBeli.setText("");
				                    cari.setText("");
				                    beli.setText("");
				                    nominal.setText("");
				                    
				                    if (conn != null) {
				        	        	try {
				        	        		conn.close();
				        				} catch (SQLException e4) {}
				        	        }
				        		 
				        		    if (st != null) {
				        	        	try {
				        	        		st.close();
				        				} catch (SQLException e5) {}
				        	        }
				        		    
				        		    if (rs != null) {
				        	        	try {
				        	        		rs.close();
				        				} catch (SQLException e5) {}
				        	        }
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(textAreaBeli, "Pembayaran Gagal!!! Periksa koneksi Database");
								}
							}
						} else if (Long.valueOf(nominal.getText()) >= totalBayar && sisaStok == 0) {
							int con = JOptionPane.showConfirmDialog(textAreaBeli, "Lanjutkan membayar?", "Confirm", JOptionPane.YES_NO_OPTION);
							if (con == 0) {
								JOptionPane.showMessageDialog(textAreaBeli, "Pembayaran Berhasil");
								try {
									String query = "delete from barang where id='"+cari.getText()+"'";
									Connection conn = (Connection)JDBC.koneksi();
									PreparedStatement st = conn.prepareStatement(query);
									st.executeUpdate();
									showData();
									textAreaCek.setText("");
				                    textAreaBeli.setText("");
				                    cari.setText("");
				                    beli.setText("");
				                    nominal.setText("");
				                    
				                    if (conn != null) {
				        	        	try {
				        	        		conn.close();
				        				} catch (SQLException e4) {}
				        	        }
				        		 
				        		    if (st != null) {
				        	        	try {
				        	        		st.close();
				        				} catch (SQLException e5) {}
				        	        }
				        		    
				        		    if (rs != null) {
				        	        	try {
				        	        		rs.close();
				        				} catch (SQLException er) {}
				        	        }
				                    
								} catch (Exception e3) {
									JOptionPane.showMessageDialog(textAreaBeli, "Pembayaran Gagal!!! Periksa koneksi Database");
									
								}
							}
						} else if (Long.valueOf(nominal.getText()) < totalBayar) {
							JOptionPane.showMessageDialog(textAreaBeli, "Nominal Bayar tidak cukup!!!");
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					cari.setText("");
					textAreaCek.setText("");
				  }	finally {
					    if (rs != null) {
				        	try {
				        		rs.close();
							} catch (SQLException e5) {
								
							}
				        }
				  }
			}
		});
		btnBayar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBayar.setForeground(Color.WHITE);
				btnBayar.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBayar.setForeground(new Color(0,0,0));
				btnBayar.setBackground(new Color(224,255,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnBayar.setForeground(Color.WHITE);
				btnBayar.setBackground(Color.BLACK);
			}
		});
		btnBayar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBayar.setBackground(Color.WHITE);
		btnBayar.setBounds(408, 435, 133, 38);
		panelBeli.add(btnBayar);
	}
}
