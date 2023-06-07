package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                String sql = "select * from \"Theater\" t join \"CinemaSystem\" c on c.cineid = t.cineid where theaterid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()) {
                    theater = new Theater(rs.getString("theaterid"),
                                            rs.getString("name"),
                                            rs.getString("street"),
                                            rs.getString("ward"),
                                            rs.getString("district"),
                                            rs.getString("city"),
                                            rs.getString("logo"));
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return theater;
    }
    
    public List<Theater> getTheatersByCinemaId(String id) {
        List<Theater> list = new ArrayList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Theater\" where cineid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Theater(rs.getString("theaterid"),
                                            rs.getString("name"),
                                            rs.getString("street"),
                                            rs.getString("ward"),
                                            rs.getString("district"),
                                            rs.getString("city"),
                                            rs.getString("image")));
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<Theater> getTheatersByCinemaIdAndCity(String id, String city) {
        List<Theater> list = new ArrayList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Theater\" t join \"CinemaSystem\" c on c.cineid = t.cineid where city = ?";
                if (!(id == null || id.isBlank())) {
                    sql += " and c.cineid = '" + id + "'";
                }
                sql += " order by t.name";
                stm = con.prepareStatement(sql);
                stm.setString(1, city);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Theater(rs.getString("theaterid"),
                                            rs.getString("name"),
                                            rs.getString("street"),
                                            rs.getString("ward"),
                                            rs.getString("district"),
                                            rs.getString("city"),
                                            rs.getString("logo")));
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TheaterDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}
