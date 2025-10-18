/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import classClass.Model_Barang;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_Barang {
    void tambahData (Model_Barang mobar);
    void perbaruiData (Model_Barang mobar);
    void hapusData (Model_Barang mobar);
    
    List<Model_Barang> ambilData();
}
