/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classClass;

/**
 *
 * @author user
 */
public class Model_Barang { //Model : digunakan untuk mendeklarasikan atribut" apa saja yang ada diDB
    private String kode_barang,  nama_barang, satuan;
    private int stok;
    private int harga;
    private Model_JenisBarang jns_barang; //memanggil model jenisbarang karena berelasi(FK)

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

   

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

   

    public Model_JenisBarang getJns_barang() {
        return jns_barang;
    }

    public void setJns_barang(Model_JenisBarang jns_barang) {
        this.jns_barang = jns_barang;
    }

     
}
