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
public abstract class TabelMod_DetBarangMasuk extends AbstractTableModel{
    
    private List<Model_DetBarangMasuk> list = new ArrayList<>();
    
    public void tambahData(Model_DetBarangMasuk mod_detMasuk){
        list.add(mod_detMasuk);
        fireTableRowsInserted(list.size() - 1, list.size() -1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_DetBarangMasuk mod_detMasuk){
        list.add(row, mod_detMasuk);
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
    
    public void setData(List<Model_DetBarangMasuk> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_DetBarangMasuk mod_detMasuk){
        list.set(index, mod_detMasuk);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_DetBarangMasuk getData (int index){
      return list.get(index);
    }
    
    public int getRowCount() {
        return list.size();
    }
    public int getColomnCount(){
        return 5;
    }
    
    
   public Object getValueAt (int rowIndex, int columnIndex) {
       switch(columnIndex){
           case 1: return list.get(rowIndex).getNo_masuk();
           case 2: return list.get(rowIndex).getKode_barang();
           case 3: return list.get(rowIndex).getJml_masuk();
           case 4: return list.get(rowIndex).getSubtotal_masuk();
           
           default:return null;
       }
   }
   
   public String getColumnName(int Column){
       switch (Column){
           case 0: return "Nomor Barang Masuk";
           case 1: return "Kode Barang Masuk";
           case 2: return "Jumlah Barang Masuk";
           case 3: return "Subtotal Masuk";
           
           
               default: return null;
       }
   }
   
}
