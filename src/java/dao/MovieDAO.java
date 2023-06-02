package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
                String sql = "select * from \"MovieWithGenres\" where movieid = '" + id + "'";
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
        }finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        }finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        }finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void deleteMoviesById(String movieid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Movie\" where movieid = '" + movieid + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String addMovie(String title, String des, String poster, int duration, Date releaseDate, double rating, String actors, String directors, String country, String trailer, String status, int ageRestricted) {
        String id = UUID.randomUUID().toString();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Movie\"(movieid, title, description, poster, duration, releasedate, rating, actors, directors, country, trailer, status, ageRestricted) values ('"+id +"', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, title);
                stm.setString(2, des);
                stm.setString(3, poster);
                stm.setInt(4, duration);
                stm.setDate(5, releaseDate);
                stm.setDouble(6, rating);
                stm.setString(7, actors);
                stm.setString(8, directors);
                stm.setString(9, country);
                stm.setString(10, trailer);
                stm.setString(11, status);
                stm.setInt(12, ageRestricted);
                stm.executeUpdate();

               
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public void addGenreToMovie(String genid, String movieid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Movie_Genre\"(genid, movieid) values ('"+genid +"', '"+movieid +"')";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateMovie( String movieid, String title, String des, String poster, int duration, Date releaseDate, double rating, String actors, String directors, String country, String trailer, String status, int ageRestricted){
        try {
            con = DbContext.getConnection();
            if(con!=null){
                String sql = "update \"Movie\" set title = ?, description = ?, poster =?, duration=?, releasedate =?, rating=?, actors=?, directors =?, country=?, trailer=?, status =? ,agerestricted =? where movieid ='"+ movieid +"'";
                stm = con.prepareStatement(sql);
                stm.setString(1, title);
                stm.setString(2, des);
                stm.setString(3, poster);
                stm.setInt(4, duration);
                stm.setDate(5, releaseDate);
                stm.setDouble(6, rating);
                stm.setString(7, actors);
                stm.setString(8, directors);
                stm.setString(9, country);
                stm.setString(10, trailer);
                stm.setString(11, status);
                stm.setInt(12, ageRestricted);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
