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
public abstract class TabelMod_SembarangKeluar extends AbstractTableModel{
    
    private List<Model_Barang> list = new ArrayList<>();
    
    public void tambahData(Model_Barang moBar){
        list.add(moBar);
        fireTableRowsInserted(list.size() - 1, list.size() -1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_Barang moBar){
        list.add(row, moBar);
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
    
    public void setData(List<Model_Barang> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Barang moBar){
        list.set(index, moBar);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_Barang getData (int index){
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
           case 0: return list.get(rowIndex).getKode_barang();
           case 1: return list.get(rowIndex).getNama_barang();
           case 2: return list.get(rowIndex).getSatuan();
           case 3: return list.get(rowIndex).getStok();
           case 4: return list.get(rowIndex).getJns_barang();
           
           default:return null;
       }
   }
   
   public String getColumnName(int Column){
       switch (Column){
           case 0: return "Kode Barang";
           case 1: return "Nama Barang";
           case 2: return "Satuan";
           case 3: return "Stok";
           case 4: return "Jenis Barang";
           
               default: return null;
       }
   }
   
}
