package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import model.Seat;
import model.Showtime;

public class RoomDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Seat[][] getSeatsByRoomId(String id) {
        Seat[][] map = new Seat[maxRowByRoomId(id)][maxColByRoomId(id)];

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Seat\" where roomid = '" + id + "' order by row, col";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map[rs.getInt("row") - 1][rs.getInt("col") - 1]
                            = new Seat(rs.getString("seatid"),
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
        return map;
    }

    public Seat[][] getSeatsByShowtime(Showtime st) {
        Seat[][] map = new Seat[maxRowByRoomId(st.getRoom().getId())][maxColByRoomId(st.getRoom().getId())];

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select s.*, b.ticketid from \"Seat\" s\n"
                        + "join \"Room\" r on s.roomid = r.roomid\n"
                        + "join \"Showtime\" st on r.roomid = st.roomid\n"
                        + "left join \"Ticket\" t on st.showid = t.showid\n"
                        + "left join \"Booking\" b on b.ticketid = t.ticketid and b.seatid = s.seatid\n"
                        + "where st.showid = '"+ st.getId() +"'\n"
                        + "order by row, col";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map[rs.getInt("row") - 1][rs.getInt("col") - 1]
                            = new Seat(rs.getString("seatid"),
                                    rs.getString("seatnum"),
                                    rs.getString("type"),
                                    rs.getString("ticketid") != null);
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
        return map;
    }

    public int maxRowByRoomId(String id) {
        int row = 0;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select max(row) from \"Seat\" where roomid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    row = rs.getInt(1);
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
        return row;
    }

    public int maxColByRoomId(String id) {
        int col = 0;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select max(col) from \"Seat\" where roomid = '" + id + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    col = rs.getInt(1);
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
        return col;
    }

    public List<Room> getRoomsByTheaterId(String id) {
        List<Room> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Room\" where theaterid = '" + id + "' order by name";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Room(rs.getString("roomid"),
                            rs.getString("name"),
                            id));
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

    public boolean addRoom(String theaterid) {
        boolean success = false;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Room\"(name, theaterid) values ('', '" + theaterid + "')";
                stm = con.prepareStatement(sql);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public void deleteRoomById(String roomid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Room\" where roomid = '" + roomid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateRoom(String roomid, String name, String theaterid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Room\" set theaterid = '" + theaterid + "', name = ? where roomid ='" + roomid + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
