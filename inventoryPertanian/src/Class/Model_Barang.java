/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Class.koneksi;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class Model_Barang { //Model : digunakan untuk mendeklarasikan atribut" apa saja yang ada diDB
    private String kode_barang,  nama_barang, satuan, jns_barang;
    private int stok;
    private int harga;
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    

    public Model_Barang() {
        this.conn = new koneksi().configDB();
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getJns_barang() {
        return jns_barang;
    }

    public void setJns_barang(String jns_barang) {
        this.jns_barang = jns_barang;
    }

    
   
    public ResultSet tampilBarang() {
        try {
            query = "SELECT * FROM barang";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error tampil data: " + e.getMessage());
            return null;
        }
    }
}

