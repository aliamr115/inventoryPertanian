/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import Class.Model_DetBarangKeluar;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_DetBarangKeluar {
    void tambahData(Model_DetBarangKeluar mod_detKeluar);
    void sumTotal (Model_DetBarangKeluar mod_detKeluar);
    //void hapusSementara (Model_DetBarangKeluar mod_detKeluar);
    
    Model_DetBarangKeluar getByid (String id);
    List<Model_DetBarangKeluar> ambilData(String id);
    List<Model_DetBarangKeluar> pencarian(String id);
    
    boolean validasiStok (Model_DetBarangKeluar mod_detKeluar);
    
}
