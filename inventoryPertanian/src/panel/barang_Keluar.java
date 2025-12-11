/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Class.koneksi;
import Class.Model_BarangKeluar;
import Class.Model_User;
import Class.Model_Barang;
import Class.Model_DetBarangKeluar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import javax.swing.JTable;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author asus
 */
public class barang_Keluar extends javax.swing.JPanel {
 
     String noEdit = "";
    String idEdit = "";
    String tglEdit = "";
    String totalEdit = "";
    
    /**
     * Creates new form barang_Masuk
     */
    public barang_Keluar() {
        initComponents();
        
        showDataBK();
         tampilkanTblAtas(noEdit);
        tampilkanTblBawah(noEdit);
       
        
    }
    
    private void showDataBK() { //menampilkan panel utama dengan button TAMBAH
        mainPanel.removeAll();
        mainPanel.add(dataBK);
        mainPanel.repaint();
        mainPanel.revalidate();

        
        btnTambah.setVisible(true);
        btnUbah.setVisible(true);
        btnHapus.setVisible(true);
        
        load_table_BK();
    }
    
    private void showDetailBK() { //menampilkan panel utama dengan button TAMBAH
        mainPanel.removeAll();
        mainPanel.add(detailBK);
        mainPanel.repaint();
        mainPanel.revalidate();

        eventTableClick();
        btnKembali.setVisible(true);
        
        
        load_table_BK();
    }
    
    private void showTambahBK() { //menampilkan panel untuk input tambah jenis
        mainPanel.removeAll();
        mainPanel.add(tambahBK);
        mainPanel.repaint();
        mainPanel.revalidate();

        reset();
    }
    
    void load_table_BK(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No keluar");
        model.addColumn("ID User");
        model.addColumn("Tanggal");
        model.addColumn("Total Keluar");
        
        
        try {
            //membuat objek user dan mengambil data dari database
            Model_BarangKeluar bk = new Model_BarangKeluar();
            ResultSet result =  bk.TampilBarangKeluar();
            //loop data baris per baris
            while (result.next()){
                //Tambahkan baris ke dalam tabel model
            model.addRow(new Object[]{
                 result.getString("no_keluar"),
                 result.getString("id_user"),
                 result.getString("tgl_keluar"),
                 result.getString("total_keluar"),
                  });
        }
           //set model ke JTable
        tblBK.setModel(model);
        
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
     void load_table_datailBK(String noMasuk){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Masuk");
        model.addColumn("Kode Barang");
        model.addColumn("Jumlah");
        model.addColumn("Subtotal");
        
        
        try {
            //membuat objek user dan mengambil data dari database
            Model_DetBarangKeluar detbk = new Model_DetBarangKeluar();
            ResultSet result =  detbk.TampilDetailBK(noMasuk);
            //loop data baris per baris
            while (result.next()){
                //Tambahkan baris ke dalam tabel model
            model.addRow(new Object[]{
                 result.getString("no_masuk"),
                 result.getString("kode_barang"),
                 result.getInt("jml_masuk"),
                 result.getBigDecimal("subtotal_masuk"),
                  });
        }
           //set model ke JTable
        tblDetail.setModel(model);
        
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void loadKodeBarang() {
    try {
        String sql = "SELECT kode_barang FROM barang";
        Connection conn = koneksi.configDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.close();
        st.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    
    private void reset(){
        tNoKeluar.setText("");
        tIdUser.setText("");
        cKodeBarang.setSelectedItem("");
        jcTglKeluar.setDate(null); //menggunakan jCalender
        tTotalKeluar.setText("");

        btnSimpan.setText("Simpan");
        
    }
    
        private void cariData(){ //textfield untuk mencari data
        String cari = tCari.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblBK.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT * FROM barangkeluar WHERE no_masuk LIKE ? OR id_user LIKE ? OR tgl_keluar LIKE ? OR total_keluar LIKE ?";
            PreparedStatement ps = koneksi.configDB().prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");
            ps.setString(3, "%" + cari + "%");
            ps.setString(4, "%" + cari + "%");
           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("no_keluar"),
                    rs.getString("id_user"),
                    rs.getString("tgl_keluar"),
                    rs.getString("total_keluar")
                    
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
         
       
}
    
    private void eventTableClick(){ //untuk menampilkan panel UBAH,HAPUS,BATAL saat salah satu data pada JTable diklik 
        tblBK.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            int baris = tblBK.getSelectedRow();
            
            if (baris != -1){
                //ambil data dari tabel
                String noMasuk = tblBK.getValueAt(baris, 0).toString();
                String id = tblBK.getValueAt(baris, 1).toString();
                String tgl = tblBK.getValueAt(baris, 2).toString();
                String total = tblBK.getValueAt(baris, 3).toString();
                
                //simpan data
                noEdit    = noMasuk;
                idEdit    = id;
                tglEdit   = tgl;
                totalEdit = total;
                
                dataBK.setVisible(true); //tampilkan panel dataBM(TAMBAH,UBAH,HAPUS)
                
                //otomatis mengisi variabel / textfield
                tNoKeluar.setText(noMasuk);
                tNoKeluar.setEditable(false);
                tIdUser.setText(id);
                tIdUser.setEditable(false);
                
                try{
                    //konversi string ke date
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
                    jcTglKeluar.setDate(date);
                } catch (Exception ex){
                    System.out.println("Error parsing tanggal: " + ex.getMessage());
                }
                
                tTotalKeluar.setText(total);
                
                tampilkanTblAtas(noMasuk);
                tampilkanTblBawah(noMasuk);
                btnSimpan.setText("Ubah");
                showDetailBK();
        }
        }
        });
    }
    
    public void tampilkanTblAtas(String no_masuk) { //menampilkan tabel  atas
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("No Masuk");
    model.addColumn("Tanggal");
    model.addColumn("Total");

    try {
        String sql = "SELECT * FROM barangmasuk WHERE no_masuk = ?";
        PreparedStatement ps = koneksi.configDB().prepareStatement(sql);
        ps.setString(1, no_masuk);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("no_masuk"),
                rs.getString("tgl_masuk"),
                rs.getBigDecimal("total_masuk")
            });
        }

        tblBK2.setModel(model);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void tampilkanTblBawah(String no_masuk) { //menampilkan tabel det  bawah
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Jumlah");
    model.addColumn("Subtotal");

    try {
        String sql = "SELECT d.kode_barang, b.nama_barang, d.jml_keluar, d.subtotal_keluar "
                    + "FROM detail_barangkeluar d "
                    + "JOIN barang b ON d.kode_barang = b.kode_barang "
                    + "WHERE d.no_keluar = ?";
        
        PreparedStatement ps = koneksi.configDB().prepareStatement(sql);
        ps.setString(1, no_masuk);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jml_keluar"),
                rs.getBigDecimal("subtotal_keluar")
            });
        }

        tblDetail.setModel(model);

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
    public void autoID(){
        try {
            Connection mysqlConfig = koneksi.configDB();
            Statement st = mysqlConfig.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(no_masuk) AS max_id FROM barangkeluar");
            
            if (rs.next()){
                String maxID = rs.getString("max_id");
                
                if(maxID == null){
                    tNoKeluar.setText("BM001");
                } else {
                    int nomor = Integer.parseInt(maxID.substring(2));
                    nomor ++;
                    
                    String kodeBaru = String.format("BM%03d", nomor);
                    tNoKeluar.setText(kodeBaru);
                }
            }
            
            rs.close();
            st.close();
        } catch (Exception e){
            e.printStackTrace();
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
        dataBK = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btnTambah = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBK = new javax.swing.JTable();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        tCari = new javax.swing.JTextField();
        tambahBK = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        tNoKeluar = new javax.swing.JTextField();
        tIdUser = new javax.swing.JTextField();
        tTotalKeluar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cKodeBarang = new javax.swing.JComboBox<>();
        jcTglKeluar = new com.toedter.calendar.JCalendar();
        detailBK = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        btnKembali = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblBK2 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();

        setLayout(new java.awt.CardLayout());

        mainPanel.setLayout(new java.awt.CardLayout());

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel9.setText("Data Barang Masuk");

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        btnTambah.setBackground(new java.awt.Color(153, 255, 0));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        tblBK.setBackground(new java.awt.Color(204, 255, 204));
        tblBK.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBKMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBK);

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        tCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCariActionPerformed(evt);
            }
        });
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout dataBKLayout = new javax.swing.GroupLayout(dataBK);
        dataBK.setLayout(dataBKLayout);
        dataBKLayout.setHorizontalGroup(
            dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBKLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dataBKLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataBKLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(dataBKLayout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addGap(32, 32, 32)
                        .addComponent(btnUbah)
                        .addGap(37, 37, 37)
                        .addComponent(btnHapus)
                        .addGroup(dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataBKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataBKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))))))
        );
        dataBKLayout.setVerticalGroup(
            dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataBKLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(dataBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambah)
                        .addComponent(btnUbah)
                        .addComponent(btnHapus)
                        .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainPanel.add(dataBK, "card2");

        jLabel11.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel11.setText("Tambah Barang Masuk");

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        btnSimpan.setBackground(new java.awt.Color(153, 255, 0));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        tNoKeluar.setText("jTextField1");
        tNoKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNoKeluarActionPerformed(evt);
            }
        });

        tIdUser.setText("jTextField2");
        tIdUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIdUserActionPerformed(evt);
            }
        });

        tTotalKeluar.setText("jTextField4");

        jLabel1.setText("no masuk");

        jLabel2.setText("id user");

        jLabel3.setText("kode barang");

        jLabel4.setText("tgl masuk");

        jLabel5.setText("total masuk");

        cKodeBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cKodeBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambahBKLayout = new javax.swing.GroupLayout(tambahBK);
        tambahBK.setLayout(tambahBKLayout);
        tambahBKLayout.setHorizontalGroup(
            tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahBKLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahBKLayout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tambahBKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(tambahBKLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnBatal)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(tambahBKLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(tambahBKLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tNoKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(tTotalKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cKodeBarang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tIdUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
                            .addComponent(jcTglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(tambahBKLayout.createSequentialGroup()
                .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahBKLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tambahBKLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel5)))
                .addContainerGap(300, Short.MAX_VALUE))
        );
        tambahBKLayout.setVerticalGroup(
            tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahBKLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(tambahBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpan)
                        .addComponent(btnBatal)))
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNoKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(cKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcTglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(tTotalKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 71, Short.MAX_VALUE))
        );

        mainPanel.add(tambahBK, "card2");

        jLabel13.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel13.setText("Detail Barang Masuk");

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));

        btnKembali.setBackground(new java.awt.Color(153, 255, 0));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        tblBK2.setBackground(new java.awt.Color(204, 255, 204));
        tblBK2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBK2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBK2MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblBK2);

        jLabel15.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel15.setText("Data Barang Masuk");

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetail);

        javax.swing.GroupLayout detailBKLayout = new javax.swing.GroupLayout(detailBK);
        detailBK.setLayout(detailBKLayout);
        detailBKLayout.setHorizontalGroup(
            detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailBKLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(300, Short.MAX_VALUE))
            .addGroup(detailBKLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailBKLayout.createSequentialGroup()
                        .addComponent(btnKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(detailBKLayout.createSequentialGroup()
                        .addGroup(detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        detailBKLayout.setVerticalGroup(
            detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailBKLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(detailBKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(btnKembali))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        mainPanel.add(detailBK, "card2");

        add(mainPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        autoID();
        showTambahBK();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
       Model_BarangKeluar bk = new Model_BarangKeluar();
       bk.UbahBarangKeluar();
       eventTableClick();
       autoID();
       showTambahBK();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        Model_BarangKeluar bk = new Model_BarangKeluar();
       bk.setNo_keluar(tNoKeluar.getText());
       
       bk.HapusBarangKeluar();
       load_table_BK();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
  
    Model_BarangKeluar bk = new Model_BarangKeluar();

    // --- SET No Masuk ---
    bk.setNo_keluar(tNoKeluar.getText());

    // --- SET ID USER (HARUS OBJECT Model_User) ---
    Model_User usr = new Model_User();
    usr.setId_user(tIdUser.getText());
    bk.setIdUser(usr);
    
    // --- SET TANGGAL MASUK ---
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String tglKeluar = sdf.format(jcTglKeluar.getDate());
    bk.setTgl_keluar(tglKeluar);

    // --- SET TOTAL MASUK (HARUS INT) ---
    bk.setTotal_keluar(Long.MAX_VALUE);

    // --- SIMPAN / UBAH ---
    if (btnSimpan.getText().equals("Ubah")) {
        bk.UbahBarangKeluar();
    } else {
        bk.TambahBarangKeluar();
    }

    // reload table
    showDataBK();
    load_table_BK();




    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        showDataBK();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tblBKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBKMouseClicked
        int baris = tblBK.getSelectedRow();
        
        if (baris != -1){
            String noKelString = tblBK.getValueAt(baris, 0).toString();
            load_table_datailBK(noKelString);
            String id = tblBK.getValueAt(baris, 1).toString();
            String tgl = tblBK.getValueAt(baris, 2).toString();
            String total = tblBK.getValueAt(baris, 3).toString();
           
            tNoKeluar.setText(noKelString);
            tIdUser.setText(id);
            
            try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        java.util.Date date = sdf.parse(tgl);
        jcTglKeluar.setDate(date);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
            tTotalKeluar.setText(total);
            eventTableClick();
           
            
            
        }
    }//GEN-LAST:event_tblBKMouseClicked

    private void tCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCariActionPerformed
         
    }//GEN-LAST:event_tCariActionPerformed

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
         cariData();
           
    }//GEN-LAST:event_tCariKeyReleased

    private void tNoKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNoKeluarActionPerformed
        
        Model_BarangKeluar bk = new Model_BarangKeluar
        ();
        autoID();
    }//GEN-LAST:event_tNoKeluarActionPerformed

    private void tIdUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIdUserActionPerformed
       Model_User usr = new Model_User();
       autoID();
    }//GEN-LAST:event_tIdUserActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        showDataBK();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void cKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cKodeBarangActionPerformed
        Model_Barang brg = new Model_Barang();
        autoID();
    }//GEN-LAST:event_cKodeBarangActionPerformed

    private void tblBK2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBK2MouseClicked
         
    }//GEN-LAST:event_tblBK2MouseClicked

    private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked
  
        
    }//GEN-LAST:event_tblDetailMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cKodeBarang;
    private javax.swing.JPanel dataBK;
    private javax.swing.JPanel detailBK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator9;
    private com.toedter.calendar.JCalendar jcTglKeluar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField tCari;
    private javax.swing.JTextField tIdUser;
    private javax.swing.JTextField tNoKeluar;
    private javax.swing.JTextField tTotalKeluar;
    private javax.swing.JPanel tambahBK;
    private javax.swing.JTable tblBK;
    private javax.swing.JTable tblBK2;
    private javax.swing.JTable tblDetail;
    // End of variables declaration//GEN-END:variables
}
