package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean addTicket(String id, String showid, String accid, String name, String email, String phone, int price) {
        boolean check = false;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Ticket\"(ticketid, showid, accid, name, email, phone, price) "
                        + "values ('" + id + "', '" + showid + "', "+(accid == null ? "null" : "'" + accid + "'")+", ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, email);
                stm.setString(3, phone);
                stm.setInt(4, price);
                stm.execute();
                check = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public void addSeatBooking(String ticketid, String seatid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Booking\"(ticketid, seatid) values ('" + ticketid + "', '" + seatid + "')";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addComboOrder(String ticketid, String comboid, int quantity) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Order\"(ticketid, comboid, quantity) values ('" + ticketid + "', '" + comboid + "', ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
