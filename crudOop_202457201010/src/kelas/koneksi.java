/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class koneksi {
    private Connection mysqlconfig; //hanya bisa dipakai diclass koneksi saja
    public Connection configDB(){ //agar method configDB untuk menghubungjan DB, dan bisa dipanggil dihalaman login, user dll
        try{
            String url = "jdbc:mysql://localhost:3306/crudoop_202457201010";
            String user = "root"; //user DB
            String pass = "";  //password DB
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return mysqlconfig; //kalo ada return berati bukan method VOID
    }
}
