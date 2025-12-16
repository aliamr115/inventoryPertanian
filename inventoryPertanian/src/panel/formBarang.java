/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Class.koneksi;
import java.awt.CardLayout;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Class.Model_Barang;
import java.sql.Statement;




/**
 *
 * @author Ainiha
 */
public class formBarang extends javax.swing.JPanel {

  private Connection conn;
  private DefaultTableModel model;
  private boolean isEdit = false;

    /**
     * Creates new form formBarang
     */
    public formBarang() {
        initComponents();
        
        conn = new koneksi().configDB(); //membuat koneksi ke database

        //card layout
        mainPanel.setLayout(new CardLayout()); //mengatur layout panel utama
        mainPanel.add(dataBarang, "dataBarang"); //menambahkan panel data (panel tabel baranng), nama kartu 
        mainPanel.add(tambahBarang, "tambahBarang"); //menambahkan panel form tambah / edit, digunakan saat klik tambah?edit
        
        setTableModel(); //struktur tabel
        loadDataBarang(); //mengisi tabel dari database, menjalankan Select*From barang, dan memasukkan data ke jtable
        loadComboBarang(); //mengisi combobox, kodeJenisBarang & namajenisbarang supaya user tinggal pilih, ngga ngetik manual
    }
    
    
    private void setTableModel() {
        String[] kolom = {"Kode Barang", "Kode Jenis", "Nama Jenis", "Nama Barang", "Satuan", "Harga", "Stok"};
        
        model = new DefaultTableModel(kolom, 0); //membuat model tabel dengan jumlah baris awal (kosong)
        tblDataBarang.setModel(model); 
    }
    
    private void loadDataBarang() { //method untuk mengambil & menampilkan data barang
        try {
            model.setRowCount(0); //mengosongkan isi tabel (menghapus semua baris lama, supaya tidak double saat data di load ulang)
            
        String sql = "SELECT b.kode_barang, b.kode_jenis, j.nama_jenis, b.nama_barang, b.satuan, b.harga, b.stok "
                + "FROM barang b JOIN jenisbarang j ON j.kode_jenis = b.kode_jenis "
                + "ORDER BY LENGTH(b.kode_barang), b.kode_barang ASC"; //mengurutkan kode barang
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            model.addRow(new Object[]{ 
                rs.getString("kode_barang"),
                rs.getString("kode_jenis"),
                rs.getString("nama_jenis"),
                rs.getString("nama_barang"),
                rs.getString("satuan"),
                rs.getString("harga"),
                rs.getString("stok")
            });
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    
    //untuk ganti panel 
    private void showPanel(String name) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, name);
    }
    
    private void reset() { //mengosongkan form input
        tKodeJenisBarang.setText("");
        cNamaJnsBrng.setSelectedItem(null);
        tNamaBarang.setText("");
        cSatuan.setSelectedIndex(0);
        tHarga.setText("");
        tStok.setText("");
    }
    
    private void cariData(){ //mencari data
        String cari = tCari.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblDataBarang.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT * FROM barang WHERE kode_barang LIKE ? OR nama_barang LIKE ? OR satuan LIKE ? OR stok LIKE ? OR harga LIKE ?";
            PreparedStatement ps = koneksi.configDB().prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");
            ps.setString(3, "%" + cari + "%");
            ps.setString(4, "%" + cari + "%");
            ps.setString(5, "%" + cari + "%");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("satuan"),
                    rs.getString("harga"),
                    rs.getString("stok")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
}
     
    private void loadComboBarang() { //isi combobox
    try {
        String sql = "SELECT kode_jenis, nama_jenis FROM jenisbarang";
        Connection con = koneksi.configDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        cNamaJnsBrng.removeAllItems();
        while (rs.next()) {
            cNamaJnsBrng.addItem(rs.getString("nama_jenis"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
        
        reset();
    }
}
    
    private void autoKodeBarang() { //membuat kode barang otomatis
        try {
            String sql = "SELECT kode_barang FROM barang ORDER BY kode_barang DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String kodeLama = rs.getString("kode_barang").trim(); //Hapus spasi
                kodeLama = kodeLama.replace("BR", "").trim(); 
                int angka = Integer.parseInt(kodeLama) + 1;
                tKodeBarang.setText("BR" + angka);
            } else {
                //jika tabel masii kosong
                tKodeBarang.setText("BR01");
            }
            
            tKodeBarang.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
}
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        dataBarang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        btnUbah = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        tCari = new javax.swing.JTextField();
        tambahBarang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tKodeBarang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tKodeJenisBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cNamaJnsBrng = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tNamaBarang = new javax.swing.JTextField();
        cSatuan = new javax.swing.JComboBox<>();
        tHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tStok = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        mainPanel.setLayout(new java.awt.CardLayout());

        tblDataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDataBarang);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Data Barang");

        btnHapus.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-20.png"))); // NOI18N

        btnUbah.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorderPainted(false);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout dataBarangLayout = new javax.swing.GroupLayout(dataBarang);
        dataBarang.setLayout(dataBarangLayout);
        dataBarangLayout.setHorizontalGroup(
            dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataBarangLayout.createSequentialGroup()
                        .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataBarangLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1))
                        .addContainerGap())
                    .addGroup(dataBarangLayout.createSequentialGroup()
                        .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataBarangLayout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus)
                                .addGap(18, 18, 18)
                                .addComponent(btnUbah)
                                .addGap(0, 701, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBarangLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBarangLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        dataBarangLayout.setVerticalGroup(
            dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tCari))
                .addGap(14, 14, 14)
                .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus)
                    .addComponent(btnUbah)
                    .addComponent(btnTambah))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        mainPanel.add(dataBarang, "card2");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setText("Tambah Data Barang");

        btnSimpan.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorderPainted(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setBorderPainted(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel3.setText("Kode Barang");

        tKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodeBarangActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel4.setText("Kode Jenis Barang");

        tKodeJenisBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodeJenisBarangActionPerformed(evt);
            }
        });
        tKodeJenisBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tKodeJenisBarangKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel5.setText("Nama Jenis Barang");

        cNamaJnsBrng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNamaJnsBrngActionPerformed(evt);
            }
        });
        cNamaJnsBrng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cNamaJnsBrngKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel6.setText("Nama Barang");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel7.setText("Harga");

        cSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gr", "Kg", "Sak", "Liter", "Karung", "Botol", "Buah" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel8.setText("Stok");

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel9.setText("Satuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tKodeJenisBarang)
                    .addComponent(cNamaJnsBrng, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tStok)
                    .addComponent(tHarga)
                    .addComponent(tKodeBarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNamaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cSatuan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tKodeJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(11, 11, 11)
                .addComponent(cNamaJnsBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahBarangLayout = new javax.swing.GroupLayout(tambahBarang);
        tambahBarang.setLayout(tambahBarangLayout);
        tambahBarangLayout.setHorizontalGroup(
            tambahBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tambahBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(tambahBarangLayout.createSequentialGroup()
                        .addGroup(tambahBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tambahBarangLayout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 908, Short.MAX_VALUE))))
        );
        tambahBarangLayout.setVerticalGroup(
            tambahBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tambahBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(tambahBarang, "card2");

        add(mainPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int row = tblDataBarang.getSelectedRow();
        
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }
        
        int konfirmasi = JOptionPane.showConfirmDialog(
                this, "Yakin ingin menghapus data ini?", "Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_NO_OPTION) {
            try {
                String kode = model.getValueAt(row, 0).toString();
                String sql = "DELETE FROM barang WHERE kode_barang=?";
                
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, kode);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                loadDataBarang();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        showPanel("dataBarang");
        reset();
        isEdit = false;
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
        if (isEdit) {
            String sql = "UPDATE barang SET kode_jenis=?, nama_barang=?, satuan=?, harga=?, stok=? WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tKodeJenisBarang.getText());
            ps.setString(2, tNamaBarang.getText());
            ps.setString(3, cSatuan.getSelectedItem().toString());
            ps.setString(4, tHarga.getText());
            ps.setString(5, tStok.getText());
            ps.setString(6, tKodeBarang.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "DATA BERHASIL DIUBAH!");
        } else {
            String sql = "INSERT INTO barang (kode_barang, kode_jenis, nama_barang, satuan, harga, stok) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tKodeBarang.getText());
            ps.setString(2, tKodeJenisBarang.getText());
            ps.setString(3, tNamaBarang.getText());
            ps.setString(4, cSatuan.getSelectedItem().toString());
            ps.setString(5, tHarga.getText());
            ps.setString(6, tStok.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "DATA BERHASIL DITAMBAHKAN!");
        }  
        
            loadDataBarang();
            reset();
            showPanel("dataBarang");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        int row = tblDataBarang.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }
        
        isEdit = true;
        
            tKodeBarang.setText(model.getValueAt(row, 0).toString());
            tKodeJenisBarang.setText(model.getValueAt(row, 1).toString());
            cNamaJnsBrng.setSelectedItem(model.getValueAt(row, 2).toString());
            tNamaBarang.setText(model.getValueAt(row, 3).toString());
            cSatuan.setSelectedItem(model.getValueAt(row, 4).toString());
            tHarga.setText(model.getValueAt(row, 5).toString());
            tStok.setText(model.getValueAt(row, 6).toString());
        
            tKodeBarang.setEditable(false);
        showPanel("tambahBarang");
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataBarangMouseClicked
        // TODO add your handling code here:
        int row = tblDataBarang.getSelectedRow();
            
            tKodeBarang.setText(model.getValueAt(row, 0).toString());
            tKodeJenisBarang.setText(model.getValueAt(row, 1).toString());
            cNamaJnsBrng.setSelectedItem(model.getValueAt(row, 2).toString());
            tNamaBarang.setText(model.getValueAt(row, 3).toString());
            cSatuan.setSelectedItem(model.getValueAt(row, 4).toString());
            tHarga.setText(model.getValueAt(row, 5).toString());
            tStok.setText(model.getValueAt(row, 6).toString());
        
    }//GEN-LAST:event_tblDataBarangMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        reset();
        autoKodeBarang();
        showPanel("tambahBarang");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tKodeJenisBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeJenisBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeJenisBarangActionPerformed

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        cariData();
    }//GEN-LAST:event_tCariKeyReleased

    private void cNamaJnsBrngKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cNamaJnsBrngKeyReleased
        // TODO add your handling code here: 
    }//GEN-LAST:event_cNamaJnsBrngKeyReleased

    private void tKodeJenisBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tKodeJenisBarangKeyReleased
        // TODO add your handling code here:
        //otomatis terisi saat ketik kode barang
        try {
        String sql = "SELECT nama_jenis FROM jenisbarang WHERE kode_jenis = ?";
        Connection con = koneksi.configDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tKodeJenisBarang.getText());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cNamaJnsBrng.setSelectedItem(rs.getString("nama_jenis"));
        }
    } catch (Exception e) {
        // dikosongkan jika tidak ditemukan
            System.out.println(e);
    }
    }//GEN-LAST:event_tKodeJenisBarangKeyReleased

    private void cNamaJnsBrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNamaJnsBrngActionPerformed
        // TODO add your handling code here:
        //digunakan untuk isi otomatis kode barang & harga saat nama barang dpilih
        try {
        if (cNamaJnsBrng.getSelectedItem() == null) return;

        String namaJenis = cNamaJnsBrng.getSelectedItem().toString();

        String sql = "SELECT kode_jenis FROM jenisbarang WHERE nama_jenis = ?";
        Connection con = koneksi.configDB();  // ← WAJIB ADA INI!
        PreparedStatement ps = con.prepareStatement(sql);  // ← GANTI conn → con
        ps.setString(1, namaJenis);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            tKodeJenisBarang.setText(rs.getString("kode_jenis"));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_cNamaJnsBrngActionPerformed

    private void tKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeBarangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cNamaJnsBrng;
    private javax.swing.JComboBox<String> cSatuan;
    private javax.swing.JPanel dataBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tKodeBarang;
    private javax.swing.JTextField tKodeJenisBarang;
    private javax.swing.JTextField tNamaBarang;
    private javax.swing.JTextField tStok;
    private javax.swing.JPanel tambahBarang;
    private javax.swing.JTable tblDataBarang;
    // End of variables declaration//GEN-END:variables
}
