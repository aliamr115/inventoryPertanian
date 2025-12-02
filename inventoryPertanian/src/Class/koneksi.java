package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class koneksi {
    public static Connection mysqlConfig;
    
    public static Connection konek(){
        try {
            String url = "jdbc:mysql://localhost:3306/inventory_pertanian2";
            String user = "root";
            String pass = "";
            
            mysqlConfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        
        return mysqlConfig;
    }
    
}