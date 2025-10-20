 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import classClass.Model_BarangKeluar;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_BarangKeluar {
    void tambahData (Model_BarangKeluar mod_keluar);
    
    Model_BarangKeluar getByid (String id);
    
    List<Model_BarangKeluar> ambilData();
    List<Model_BarangKeluar> pencarian(String id);
    String nomor();
    
}
