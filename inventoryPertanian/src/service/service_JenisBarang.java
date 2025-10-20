/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import classClass.Model_JenisBarang;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_JenisBarang {
    void tambahData (Model_JenisBarang mod_jb);
    void perbaruiData (Model_JenisBarang mod_jb);
    void hapusData (Model_JenisBarang mod_jb);
    
    Model_JenisBarang getByid (String id);
    
    List<Model_JenisBarang> ambilData();
    List<Model_JenisBarang> pencarian();
    
    String nomor();
    
    boolean validasiNamaJenisBarang (Model_JenisBarang mod_jb);
    
}
