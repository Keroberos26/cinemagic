package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Theater;

public class TheaterDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public Theater getTheaterById(String id) {
        Theater theater = null;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Theater\" where theaterid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()) {
                    theater = new Theater(rs.getString("theaterid"),
                                            rs.getString("name"),
                                            rs.getString("street"),
                                            rs.getString("ward"),
                                            rs.getString("district"),
                                                rs.getString("city"));
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return theater;
    }
}
