package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Combo;

public class ComboDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<Combo> getCombosByTheaterId(String id) {
        List<Combo> list = new LinkedList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Combo\" where theaterid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Combo(rs.getString("comboid"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("price"),
                            rs.getString("image")));
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
        return list;
    }

    public Combo getComboById(String id) {
        Combo combo = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Combo\" where comboid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    combo = new Combo(rs.getString("comboid"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("price"),
                            rs.getString("image"));
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
        return combo;
    }

    public boolean addCombo(String id, String name, int price, String description, String img, String theaterId) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Combo\"(comboid, name, price, description, image, theaterid) "
                        + "values ('" + id + "', ?, ?, ?, ?, '" + theaterId + "')";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setInt(2, price);
                stm.setString(3, description);
                stm.setString(4, img);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean updateCombo(String id, String name, int price, String description, String img, String theaterId) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Combo\" set name = ?, price = ?, description = ?";
                        
                if (!img.isBlank()) {
                    sql += ", img = ?";
                }
                sql += " where comboid ='" + id + "' and theaterid ='" + theaterId + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setInt(2, price);
                stm.setString(3, description);
                if (!img.isBlank()) {
                    stm.setString(4, img);
                }
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean deleteComboById(String comboid) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Combo\" where comboid = '" + comboid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

}
