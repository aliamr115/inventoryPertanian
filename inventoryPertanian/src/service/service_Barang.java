/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import Class.Model_Barang;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_Barang {  //Berisi method" apa saja yang akan dibuat
    void tambahData (Model_Barang moBar);
    void perbaruiData (Model_Barang moBar);
    void hapusData (Model_Barang moBar);
    
    Model_Barang getByid (String id);
     
    List<Model_Barang> ambilData();
    List<Model_Barang> ambilData2();
    
    List<Model_Barang> pencarian(String id);
    List<Model_Barang> pencarian2(String id);
    
    String nomor();
    String nomor2();
            
            
}
