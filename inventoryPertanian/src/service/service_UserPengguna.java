/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;


import classClass.Model_User;
import java.util.List;

/**
 *
 * @author user
 */
public interface service_UserPengguna {
    void tambahData (Model_User mod_user);
    void perbaruiData (Model_User mod_user);
    void hapusData (Model_User mod_user);
    
    Model_User getByid (String id);
    
    List<Model_User> ambilData();
    List<Model_User> pencarian(String id);
    String nomor();
}
