/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author user
 */
public abstract class TabelMod_DetBarangKeluar extends AbstractTableModel{
    
    private List<Model_DetBarangKeluar> list = new ArrayList<>();
    
    public void tambahData(Model_DetBarangKeluar mod_detKeluar){
        list.add(mod_detKeluar);
        fireTableRowsInserted(list.size() - 1, list.size() -1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_DetBarangKeluar mod_detKeluar){
        list.add(row, mod_detKeluar);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
    }
    
    public void hapusData(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Model_DetBarangKeluar> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index,  Model_DetBarangKeluar mod_detKeluar){
        list.set(index, mod_detKeluar);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_DetBarangKeluar getData (int index){
      return list.get(index);
    }
    
    public int getRowCount() {
        return list.size();
    }
    public int getColomnCount(){
        return 4;
    }
    
    
   public Object getValueAt (int rowIndex, int columnIndex) {
       switch(columnIndex){
           case 0: return list.get(rowIndex).getNo_keluar();
           case 1: return list.get(rowIndex).getKode_barang();
           case 2: return list.get(rowIndex).getJml_keluar();
           case 3: return list.get(rowIndex).getSubtotal_keluar();
       
           
           default:return null;
       }
   }
   
   public String getColumnName(int Column){
       switch (Column){
           case 0: return "Nomor Barang Keluar";
           case 1: return "Kode Barang Keluar";
           case 2: return "Jumlah Barang Keluar";
           case 3: return "Subtotal Keluar";
           
           
               default: return null;
       }
   }
   
}

