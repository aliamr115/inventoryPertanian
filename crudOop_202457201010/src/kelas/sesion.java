
package kelas;

public class sesion { //static =  bisa dipanggil  diframe manapun
    
    private static String username, email, fullName,status;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        sesion.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        sesion.email = email;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        sesion.fullName = fullName;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        sesion.status = status;
    }
    
    
    
    
}
