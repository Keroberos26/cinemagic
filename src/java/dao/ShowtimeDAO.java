package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import model.Room;
import model.Showtime;

public class ShowtimeDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public Showtime getShowtimeById(String id) {
        Showtime showtime = null;
                
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where showid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Movie movie = new Movie(rs.getString("movieid"),
                                            rs.getString("title"),
                                            rs.getString("description"), 
                                            rs.getString("poster"), 
                                            rs.getInt("duration"), 
                                            rs.getDate("releaseDate"), 
                                            rs.getDouble("rating"), 
                                            rs.getString("genres"), 
                                            rs.getString("actors"), 
                                            rs.getString("directors"), 
                                            rs.getString("country"), 
                                            rs.getString("trailer"), 
                                            rs.getInt("ageRestricted"), 
                                            rs.getString("status"));
                    Room room = new Room(rs.getString("roomid"),
                                        rs.getString("name"),
                                        rs.getString("theaterid"));
                    showtime = new Showtime(rs.getString("showid"), 
                                            rs.getDate("showdate"), 
                                            rs.getTime("starttime"), 
                                            rs.getTime("endtime"), 
                                            rs.getInt("basePrice"),
                                            movie,
                                            room);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return showtime;
    }
    
    public List<Showtime> getShowtimesByTheaterId(String id)  {
        List<Showtime> list = new ArrayList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where theaterid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Movie movie = new Movie(rs.getString("movieid"),
                                            rs.getString("title"),
                                            rs.getString("description"), 
                                            rs.getString("poster"), 
                                            rs.getInt("duration"), 
                                            rs.getDate("releaseDate"), 
                                            rs.getDouble("rating"), 
                                            rs.getString("genres"), 
                                            rs.getString("actors"), 
                                            rs.getString("directors"), 
                                            rs.getString("country"), 
                                            rs.getString("trailer"), 
                                            rs.getInt("ageRestricted"), 
                                            rs.getString("status"));
                    Room room = new Room(rs.getString("roomid"),
                                        rs.getString("name"),
                                        rs.getString("theaterid"));
                    list.add(new Showtime(rs.getString("showid"), 
                                        rs.getDate("showdate"), 
                                        rs.getTime("starttime"), 
                                        rs.getTime("endtime"), 
                                        rs.getInt("basePrice"),
                                        movie,
                                        room));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public Map<Movie, List<Showtime>> getShowtimesByTheaterIdAndDate(String id, Date date)  {
        Map<Movie, List<Showtime>> map = new LinkedHashMap<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where theaterid = '"+ id +"' and showdate = ? order by starttime";
                stm = con.prepareStatement(sql);
                stm.setDate(1, date);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Movie movie = new Movie(rs.getString("movieid"),
                                            rs.getString("title"),
                                            rs.getString("description"), 
                                            rs.getString("poster"), 
                                            rs.getInt("duration"), 
                                            rs.getDate("releaseDate"), 
                                            rs.getDouble("rating"), 
                                            rs.getString("genres"), 
                                            rs.getString("actors"), 
                                            rs.getString("directors"), 
                                            rs.getString("country"), 
                                            rs.getString("trailer"), 
                                            rs.getInt("ageRestricted"), 
                                            rs.getString("status"));
                    List<Showtime> list = null;
                    if (map.containsKey(movie)) {
                        list = map.get(movie);
                    } else {
                        list = new LinkedList<>();
                    }
                    Room room = new Room(rs.getString("roomid"),
                                        rs.getString("name"),
                                        rs.getString("theaterid"));
                    list.add(new Showtime(rs.getString("showid"), 
                                        rs.getDate("showdate"), 
                                        rs.getTime("starttime"), 
                                        rs.getTime("endtime"), 
                                        rs.getInt("basePrice"),
                                        movie,
                                        room));
                    map.put(movie, list);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }
}
