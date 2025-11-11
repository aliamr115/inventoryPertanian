/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;


import Class.Model_SemBarangMasuk;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_SemBarangMasuk {
    void tambahData (Model_SemBarangMasuk mod_masuk);
    void perbaruiData (Model_SemBarangMasuk mod_masuk);
    void hapusData (Model_SemBarangMasuk mod_masuk);
    
    Model_SemBarangMasuk Model_SemBarangKeluargetByid (String id);
    List<Model_SemBarangMasuk> ambilData();
}
