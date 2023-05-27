package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        try {
            String url = "jdbc:postgresql://db.gxnfzmriumnkwdsbjcqb.supabase.co:5432/postgres?user=postgres&password=Nguyendactamminh123";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url);
            System.out.println("Connect Successful.");
            return con;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
}
