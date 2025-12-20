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
    
    private void loadComboBarang() { 
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
        jLabel10 = new javax.swing.JLabel();
        tambahBarang = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnSimpan1 = new javax.swing.JButton();
        btnBatal1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        tKodeBarang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tKodeJenisBarang = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cNamaJnsBrng = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tNamaBarang = new javax.swing.JTextField();
        cSatuan = new javax.swing.JComboBox<>();
        tHarga = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tStok = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        dataBarang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 140, 1021, 270));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel1.setText("Data Barang");
        dataBarang.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 99, -1));

        btnHapus.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        dataBarang.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));
        dataBarang.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 33, 1079, 10));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-20.png"))); // NOI18N
        dataBarang.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 64, -1, 22));

        btnUbah.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorderPainted(false);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        dataBarang.add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        btnTambah.setFont(new java.awt.Font("Franklin Gothic Book", 1, 13)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        dataBarang.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 100, 90, -1));

        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        dataBarang.add(tCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 64, 186, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_panel (1).png"))); // NOI18N
        dataBarang.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 600));

        mainPanel.add(dataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 1050, 440));

        tambahBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel4.setText("Tambah Data Barang");
        tambahBarang.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 940, 40));

        btnSimpan1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 13)); // NOI18N
        btnSimpan1.setText("Simpan");
        btnSimpan1.setBorderPainted(false);
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });
        tambahBarang.add(btnSimpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

        btnBatal1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 13)); // NOI18N
        btnBatal1.setText("Batal");
        btnBatal1.setBorderPainted(false);
        btnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatal1ActionPerformed(evt);
            }
        });
        tambahBarang.add(btnBatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 90, 30));

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel19.setText("Kode Barang");
        tambahBarang.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1020, -1));

        tKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodeBarangActionPerformed(evt);
            }
        });
        tambahBarang.add(tKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1000, 30));

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel20.setText("Kode Jenis Barang");
        tambahBarang.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 1050, -1));

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
        tambahBarang.add(tKodeJenisBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 1000, 30));

        jLabel21.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel21.setText("Nama Jenis Barang");
        tambahBarang.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 1060, -1));

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
        tambahBarang.add(cNamaJnsBrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 1000, 30));

        jLabel22.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel22.setText("Nama Barang");
        tambahBarang.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 1030, -1));

        jLabel23.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel23.setText("Harga");
        tambahBarang.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 990, -1));
        tambahBarang.add(tNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 1000, 30));

        cSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gr", "Kg", "Sak", "Liter", "Karung", "Botol", "Buah" }));
        tambahBarang.add(cSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 1000, 30));
        tambahBarang.add(tHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 1000, 30));

        jLabel24.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel24.setText("Stok");
        tambahBarang.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 980, -1));

        jLabel25.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        jLabel25.setText("Satuan");
        tambahBarang.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 990, -1));
        tambahBarang.add(tStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 1000, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        tambahBarang.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 37, 950, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_panel (1).png"))); // NOI18N
        jLabel5.setText("jLabel3");
        tambahBarang.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 580));

        mainPanel.add(tambahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -580, 0, -1));

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

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        cariData();
    }//GEN-LAST:event_tCariKeyReleased

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void btnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatal1ActionPerformed

    private void tKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeBarangActionPerformed

    private void tKodeJenisBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeJenisBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeJenisBarangActionPerformed

    private void tKodeJenisBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tKodeJenisBarangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeJenisBarangKeyReleased

    private void cNamaJnsBrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNamaJnsBrngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cNamaJnsBrngActionPerformed

    private void cNamaJnsBrngKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cNamaJnsBrngKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cNamaJnsBrngKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal1;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan1;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cNamaJnsBrng;
    private javax.swing.JComboBox<String> cSatuan;
    private javax.swing.JPanel dataBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
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
