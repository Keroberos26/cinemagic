package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            } catch (SQLException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return showtime;
    }
}
