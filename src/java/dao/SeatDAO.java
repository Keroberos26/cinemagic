/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class SeatDAO {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public boolean addSeat(String seatNum, int col, String row, String type, String roomid) {
        boolean success = false;
        String id = UUID.randomUUID().toString();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Seat\"(seatid, seatnum, col, row, type, roomid) values ('"+id +"', ?, ?, ?, ?, '" + roomid + "')";
                stm = con.prepareStatement(sql);
                stm.setString(1, seatNum);
                stm.setInt(2, col);
                stm.setString(3, row);
                stm.setString(4, type);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }
    
    public void deleteSeatById(String seatid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Seat\" where roomid = '" + seatid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateSeat(String seatid, String seatNum, int col, String row, String type, String roomid){
        try {
            con = DbContext.getConnection();
            if(con!=null){
                String sql = "update \"Seat\" set roomid = '" + roomid +"', seatnum = ?, col =?, row =?, type = ? where seatid ='"+ seatid +"'";
                stm = con.prepareStatement(sql);
                stm.setString(1, seatNum);
                stm.setInt(2, col);
                stm.setString(3, row);
                stm.setString(4, type);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
