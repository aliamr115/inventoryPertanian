/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classClass;

/**
 *
 * @author user
 */
public class Model_BarangKeluar {
    private String no_keluar, tgl_keluar;
    private Long total_keluar;
    private Model_User idUser;

    public String getNo_keluar() {
        return no_keluar;
    }

    public void setNo_keluar(String no_keluar) {
        this.no_keluar = no_keluar;
    }

    public String getTgl_keluar() {
        return tgl_keluar;
    }

    public void setTgl_keluar(String tgl_keluar) {
        this.tgl_keluar = tgl_keluar;
    }

    public Long getTotal_keluar() {
        return total_keluar;
    }

    public void setTotal_keluar(Long total_keluar) {
        this.total_keluar = total_keluar;
    }

    public Model_User getIdUser() {
        return idUser;
    }

    public void setIdUser(Model_User idUser) {
        this.idUser = idUser;
    }

   
}
