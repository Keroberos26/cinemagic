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

    public boolean addSeat(String roomid, int row, int col, String seatNum, String type) {
        boolean success = false;
        
        if (type.equals("C")) {
            Seat s = getSeatByRowCol(roomid, row, col + 1);
            if (s != null)
                return false;
        }
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Seat\"(roomid, row, col, seatnum, type) values  ('" + roomid + "', ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, row);
                stm.setInt(2, col);
                stm.setString(3, seatNum);
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
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean deleteSeatById(String seatid) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Seat\" where seatid = '" + seatid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean updateSeat(String seatid, String roomid, int row, int col, String seatNum, String type) {
        boolean success = false;
        
        if (type.equals("C")) {
            Seat s = getSeatByRowCol(roomid, row, col + 1);
            if (s != null)
                return false;
        }
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Seat\" set seatnum = ?, type = ? where seatid ='" + seatid + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, seatNum);
                stm.setString(2, type);
                stm.executeUpdate();
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

    public Seat getSeatById(String id) {
        Seat seat = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Seat\" where seatid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    seat = new Seat(rs.getString("seatid"),
                            rs.getString("seatnum"),
                            rs.getString("type"),
                            false);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return seat;
    }

    public Seat getSeatByRowCol(String roomid, int row, int col) {
        Seat seat = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Seat\" where roomid = '" + roomid + "' and row = ? and col = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, row);
                stm.setInt(2, col);
                rs = stm.executeQuery();
                if (rs.next()) {
                    seat = new Seat(rs.getString("seatid"),
                            rs.getString("seatnum"),
                            rs.getString("type"),
                            false);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return seat;
    }
}
