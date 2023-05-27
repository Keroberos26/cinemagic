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
import model.Movie;

public class MovieDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Movie getMovieById(String id) {
        Movie movie = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"MovieWithGenres\" where movieid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    movie = new Movie(
                            rs.getString("movieid"),
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
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"MovieWithGenres\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Movie(
                            rs.getString("movieid"),
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
                            rs.getString("status")
                    ));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Movie> getMoviesByStatus(String status) {
        List<Movie> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"MovieWithGenres\" where status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Movie(
                            rs.getString("movieid"),
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
                            rs.getString("status")
                    ));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
