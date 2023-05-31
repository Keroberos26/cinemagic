package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Seat;

public class SeatDAO {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public Seat[][] getSeatsByRoomId(String id) {
        Seat[][] map = new Seat[maxRowByRoomId(id)][maxColByRoomId(id)];

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Seat\" where roomid = '"+ id +"' order by row, col";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map[rs.getInt("row") - 1][rs.getInt("col") - 1] 
                        = new Seat(rs.getString("seatid"),
                                    rs.getString("seatnum"),
                                    rs.getString("type"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }
    
    public int maxRowByRoomId(String id) {
        int row = 0;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select max(row) from \"Seat\" where roomid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    row = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;
    }
    
    public int maxColByRoomId(String id) {
        int col = 0;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select max(col) from \"Seat\" where roomid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    col = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return col;
    }
}
