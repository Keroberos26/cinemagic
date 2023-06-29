package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CinemaSystem;

public class CinemaDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<CinemaSystem> getAllCinemas() {
        List<CinemaSystem> list = new ArrayList<>();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT c.*, count(t.theaterid) as numOfTheater\n"
                        + "FROM \"CinemaSystem\" AS c \n"
                        + "LEFT JOIN \"Theater\" AS t ON c.cineid = t.cineid\n"
                        + "GROUP BY c.cineid";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            rs.getInt("numOfTheater")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public CinemaSystem getCinemaByAccountId(String id) {
        CinemaSystem cinema = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT c.*, count(t.theaterid) as numOfTheater\n"
                        + "FROM \"CinemaSystem\" AS c \n"
                        + "LEFT JOIN \"Theater\" AS t ON c.cineid = t.cineid\n"
                        + "WHERE accid = '" + id + "'GROUP BY c.cineid";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    cinema = new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            rs.getInt("numOfTheater"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cinema;
    }

    public CinemaSystem getCinemaById(String id) {
        CinemaSystem cinema = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT c.*, count(t.theaterid) as numOfTheater\n"
                        + "FROM \"CinemaSystem\" AS c \n"
                        + "LEFT JOIN \"Theater\" AS t ON c.cineid = t.cineid\n"
                        + "WHERE c.cineid = '" + id + "' GROUP BY c.cineid";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    cinema = new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            rs.getInt("numOfTheater"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cinema;
    }

    public CinemaSystem getCinemaByTheaterId(String id) {
        CinemaSystem cinema = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select c.* from \"CinemaSystem\" c join \"Theater\" t on c.cineid = t.cineid\n"
                        + "where theaterid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    cinema = new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            0);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cinema;
    }

    public boolean addCinemaSystem(String id, String name, String logo, String description, String accid) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"CinemaSystem\"(cineid, name, logo, description, accid)";
                
                sql += "values ('" + id + "', ?, ?, ?, '" + accid + "')";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, logo);
                stm.setString(3, description);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean deleteCinemaSystemById(String cineid) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"CinemaSystem\" where cineid = '" + cineid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public boolean updateCinemaSystem(String id, String name, String logo, String description, String accid) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"CinemaSystem\" set name = ?, description = ?, accid = '"+accid+"'";

                if (!logo.isBlank()) {
                    sql += ", logo = ?";
                }
                sql += " where cineid ='" + id + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, description);
                if (!logo.isBlank()) {
                    stm.setString(3, logo);
                }
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CinemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }
    
    public Map<CinemaSystem, String> listCinema(){
        Map<CinemaSystem, String> map = new LinkedHashMap();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                String sql = "select cineid, name, description, logo, accid  from \"CinemaSystem\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map.put(new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            0), rs.getString("accid"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }
}
