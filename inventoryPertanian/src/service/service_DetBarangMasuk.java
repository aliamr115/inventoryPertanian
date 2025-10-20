/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;


import Class.Model_DetBarangMasuk;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_DetBarangMasuk {
    void tambahData(Model_DetBarangMasuk mod_detMasuk);
    void sumTotal (Model_DetBarangMasuk mod_detMasuk);
    //void hapusSementara (Model_DetBarangKeluar mod_detKeluar);
    
    Model_DetBarangMasuk getByid (String id);
    List<Model_DetBarangMasuk> ambilData(String id);
    
}
