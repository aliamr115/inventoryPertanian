/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import Class.koneksi;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Model_DetBarangKeluar extends koneksi{
    private String no_keluar, kode_barang;
    private int jml_keluar;
    private double subtotal_keluar;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;

    public Model_DetBarangKeluar() {
        koneksi = super.configDB();
    }
    
    public String getNo_keluar() {
        return no_keluar;
    }

    public void setNo_keluar(String no_keluar) {
        this.no_keluar = no_keluar;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public int getJml_keluar() {
        return jml_keluar;
    }

    public void setJml_keluar(int jml_keluar) {
        this.jml_keluar = jml_keluar;
    }

    public double getSubtotal_keluar() {
        return subtotal_keluar;
    }

    public void setSubtotal_keluar(double subtotal_keluar) {
        this.subtotal_keluar = subtotal_keluar;
        
        
    }
      public void TambahDetBarangKeluar() {
        query = "INSERT INTO detail_barangkeluar VALUES(?,?);";
        try {
             ps = koneksi.prepareStatement(query);
             ps.setString(1, no_keluar);
             ps.setInt(2,jml_keluar );
             ps.setDouble(3,subtotal_keluar );
             ps.executeUpdate();
             ps.close();
             JOptionPane.showMessageDialog(null, "Berhasil");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal" + e);
            }
      }
   
          public void HapusDetBarangKeluar() {
        query = "DELETE FROM detail_barangkeluar WHERE no_keluar = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, no_keluar);
            ps.setInt(0, jml_keluar);
            ps.setDouble(3,subtotal_keluar );
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e);
        }
          }
           public void UbahDetBarangKeluar(){
        query = "update detail_barangkeluar set jml_keluar=?, subtotal_keluar =? where no_keluar=?;";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, no_keluar);
            ps.setInt(2, jml_keluar);
            ps.setDouble(3,subtotal_keluar );
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
           }
        public ResultSet TampilDetBarangKeluar(){
        query = "select * from detail_barangkeluar;";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
        return rs;
    }
}
