/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Class.koneksi;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Ainiha
 */
public class formBarang extends javax.swing.JPanel {

  private Connection conn;

    /**
     * Creates new form formBarang
     */
    public formBarang() {
        initComponents();
        conn = new koneksi().configDB();
        
        showTampilBarang();
        tampilPanel(tampilBarang);
        mainPanel.setLayout(new CardLayout());
        
        mainPanel.add(tampilBarang, "tampilBarang");
        mainPanel.add(dataBarang, "dataBarang");
        mainPanel.add(tambahBarang, "tambahBarang");
    }
    
    private void showPanel(String namaPanel) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, namaPanel);
    }
    
    private void tampilPanel(javax.swing.JPanel panel) {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    private void showTampilBarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Harga");
        model.addColumn("Stok");
        
        try {
            String sql = "SELECT kode_barang, nama_barang, satuan, harga, stok FROM barang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("satuan"),
                    rs.getString("harga"),
                    rs.getInt("stok")
                }) ;
                
            }
            
            tblTampilBarang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error tampil data: " + e.getMessage());
        }
    }
    
    private void resetForm() {
        tKodeBarang.setText("");
        tNamaBarang.setText("");
        tHarga.setText("");
        tStok.setText("");
        cSatuan.setSelectedItem("");
    }
    
    private void simpanBarang() {
        try {
            String sql = "INSERT INTO barang (kode_barang, nama_barang, satuan, harga, stok) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tKodeBarang.getText());
            ps.setString(2, tNamaBarang.getText());
            ps.setString(3, cSatuan.getSelectedItem().toString());
            ps.setString(4, tHarga.getText());
            ps.setInt(5, Integer.parseInt(tStok.getText()));
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            resetForm();
            showTampilBarang();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error simpan: " + e.getMessage());
        }
    }
    
    private void updateBarang() {
        try {
            String sql = "UPDATE barang SET nama_barang=?, satuan=?, harga=?, stok=? WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, tNamaBarang.getText());
            ps.setString(2, cSatuan.getSelectedItem().toString());
            ps.setString(3, tHarga.getText());
            ps.setInt(4, Integer.parseInt(tStok.getText()));
            ps.setString(5, tKodeBarang.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            resetForm();
            showTampilBarang();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error update: " + e.getMessage());
        }
    }
    
    private void hapusBarang() {
        try {
            String sql = "DELETE FROM barang WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tKodeBarang.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            showTampilBarang();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error hapus: " + e.getMessage());
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
        tampilBarang = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTampilBarang = new javax.swing.JTable();
        dataBarang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnbatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnUbah = new javax.swing.JButton();
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
        tNamaJenisBarang = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
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

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel12.setText("Data Barang");

        jLabel13.setText("Pencarian");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-20.png"))); // NOI18N

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        tblTampilBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTampilBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTampilBarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblTampilBarangMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblTampilBarang);

        javax.swing.GroupLayout tampilBarangLayout = new javax.swing.GroupLayout(tampilBarang);
        tampilBarang.setLayout(tampilBarangLayout);
        tampilBarangLayout.setHorizontalGroup(
            tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tampilBarangLayout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 723, Short.MAX_VALUE)
                        .addGroup(tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tampilBarangLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilBarangLayout.createSequentialGroup()
                        .addGroup(tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tampilBarangLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        tampilBarangLayout.setVerticalGroup(
            tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tampilBarangLayout.createSequentialGroup()
                        .addGroup(tampilBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTambah))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        mainPanel.add(tampilBarang, "card2");

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

        btnbatal.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setBorderPainted(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel10.setText("Pencarian");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-20.png"))); // NOI18N

        btnUbah.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorderPainted(false);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
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
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 544, Short.MAX_VALUE)
                        .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dataBarangLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBarangLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
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
                .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus)
                        .addComponent(btnbatal)
                        .addComponent(btnUbah)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
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

        tKodeBarang.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel4.setText("Kode Jenis Barang");

        tKodeJenisBarang.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel5.setText("Nama Jenis Barang");

        tNamaJenisBarang.setText("jTextField1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel6.setText("Nama Barang");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel7.setText("Harga");

        tNamaBarang.setText("jTextField1");

        cSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tHarga.setText("jTextField1");

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel8.setText("Stok");

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel9.setText("Satuan");

        tStok.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tStok, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tHarga, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tKodeBarang)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tKodeJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tNamaBarang)
                    .addComponent(cSatuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tNamaJenisBarang)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(0, 968, Short.MAX_VALUE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tKodeJenisBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
        // TODO add your handling code here:
        hapusBarang();
        tampilPanel(tampilBarang);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        tampilPanel(tampilBarang);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        tampilPanel(tampilBarang);
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        resetForm();
        tKodeBarang.setEditable(true);
        btnSimpan.setVisible(true);
        btnUbah.setVisible(false);
        btnHapus.setVisible(false);
        
        tampilPanel(tambahBarang);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        simpanBarang();
        tampilPanel(tampilBarang);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        updateBarang();
        tampilPanel(tampilBarang);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataBarangMouseClicked
        // TODO add your handling code here:
        int row = tblDataBarang.getSelectedRow();
        if (row >= 0) {
            tKodeBarang.setText(tblDataBarang.getValueAt(row, 0).toString());
            tNamaJenisBarang.setText(tblDataBarang.getValueAt(row, 1).toString());
            tNamaBarang.setText(tblDataBarang.getValueAt(row, 2).toString());
            cSatuan.setSelectedItem(tblDataBarang.getValueAt(row, 3).toString());
            tHarga.setText(tblDataBarang.getValueAt(row, 4).toString());
            tStok.setText(tblDataBarang.getValueAt(row, 5).toString());

            ///setKodeJenisByNama(tNamaJenisBarang.getText());

            btnUbah.setVisible(true);
            btnHapus.setVisible(true);
            btnbatal.setVisible(true);

            showPanel("dataBarang");
        }
    }//GEN-LAST:event_tblDataBarangMouseClicked

    private void tblTampilBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTampilBarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTampilBarangMouseEntered

    private void tblTampilBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTampilBarangMouseClicked
        // TODO add your handling code here:
        int row = tblTampilBarang.getSelectedRow();
        
        tKodeBarang.setText(tblTampilBarang.getValueAt(row, 0).toString());
        tNamaBarang.setText(tblTampilBarang.getValueAt(row, 1).toString());
        cSatuan.setSelectedItem(tblTampilBarang.getValueAt(row, 2).toString());
        tHarga.setText(tblTampilBarang.getValueAt(row, 3).toString());
        tStok.setText(tblTampilBarang.getValueAt(row, 4).toString());
        
        tKodeBarang.setEditable(false);
        btnSimpan.setVisible(false);
        btnUbah.setVisible(true);
        btnHapus.setVisible(true);
        
        tampilPanel(dataBarang);
    }//GEN-LAST:event_tblTampilBarangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnbatal;
    private javax.swing.JComboBox<String> cSatuan;
    private javax.swing.JPanel dataBarang;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tKodeBarang;
    private javax.swing.JTextField tKodeJenisBarang;
    private javax.swing.JTextField tNamaBarang;
    private javax.swing.JTextField tNamaJenisBarang;
    private javax.swing.JTextField tStok;
    private javax.swing.JPanel tambahBarang;
    private javax.swing.JPanel tampilBarang;
    private javax.swing.JTable tblDataBarang;
    private javax.swing.JTable tblTampilBarang;
    // End of variables declaration//GEN-END:variables
}
