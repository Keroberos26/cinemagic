package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Movie;
import model.Review;

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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Movie> getMoviesByStatus(String status, String title) {
        List<Movie> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"MovieWithGenres\"";
                if (!status.isBlank() || !title.isBlank()) {
                    sql += " where ";

                    if (!status.isBlank()) {
                        sql += "status = '" + status + "' and ";
                    }

                    if (!title.isBlank()) {
                        sql += "lower(title) like '%" + title + "%' and ";
                    }

                    sql = sql.substring(0, sql.length() - 5);
                }
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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Movie> getMoviesByGenres(String genre, String country, int year, String title, int offset, int itemsOfPage) {
        List<Movie> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"MovieWithGenres\"";
                if (!genre.isBlank() || !title.isBlank() || !country.isBlank() || year >= 2000) {
                    sql += " where ";

                    if (!genre.isBlank()) {
                        sql += "genres like '%" + genre + "%' and ";
                    }

                    if (!country.isBlank()) {
                        sql += "country = '" + country + "' and ";
                    }
                    
                    if (year > 2000) {
                        sql += "extract(year from releasedate) = " + year +" and ";
                    }

                    if (!title.isBlank()) {
                        sql += "lower(title) like '%" + title + "%' and ";
                    }

                    sql = sql.substring(0, sql.length() - 5);
                }
                sql += " offset ? limit ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, offset);
                stm.setInt(2, itemsOfPage);
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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public int getNumberOfMovie(String genre, String country, int year, String title) {
        int count = 0;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select count(*) from \"MovieWithGenres\"";
                if (!genre.isBlank() || !title.isBlank() || !country.isBlank() || year >= 2000) {
                    sql += " where ";

                    if (!genre.isBlank()) {
                        sql += "genres like '%" + genre + "%' and ";
                    }

                    if (!country.isBlank()) {
                        sql += "country = '" + country + "' and ";
                    }
                    
                    if (year > 2000) {
                        sql += "extract(year from releasedate) = " + year +" and ";
                    }

                    if (!title.isBlank()) {
                        sql += "lower(title) like '%" + title + "%' and ";
                    }

                    sql = sql.substring(0, sql.length() - 5);
                }
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }
    
    public List<Review> getReviewsOfMovie(String movieId, int offset) {
        List<Review> list = new LinkedList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Review\" r join \"Account\" a on r.accid = a.accid\n"
                        + "where movieid = '" + movieId + "'\n"
                        + "order by date offset ? limit 3";
                stm = con.prepareStatement(sql);
                stm.setInt(1, offset);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Account acc = new Account(rs.getString("accid"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("avatar"));
                    list.add(new Review(acc,
                            rs.getDate("date"),
                            rs.getInt("rating"),
                            rs.getString("comment")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<String> getAllGenres() {
        List<String> list = new ArrayList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Genre\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("name"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<String> getAllCountries() {
        List<String> list = new ArrayList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select distinct country from \"Movie\" order by country";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Review getReviewOfAccount(String movieId, String accId) {
        Review review = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Review\" r join \"Account\" a on r.accid = a.accid\n"
                        + "where movieid = '" + movieId + "' and a.accid = '" + accId + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Account acc = new Account(rs.getString("accid"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("avatar"));
                    review = new Review(acc,
                            rs.getDate("date"),
                            rs.getInt("rating"),
                            rs.getString("comment"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return review;
    }

    public boolean boughtTicket(String movieId, String accId) {
        boolean check = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Ticket\" t join \"Account\" a on t.accid = a.accid\n"
                        + "join \"Showtime\" st on t.showid = st.showid\n"
                        + "where movieid = '" + movieId + "' and a.accid = '" + accId + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                check = rs.next();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
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
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String addMovie(String title, String des, String poster, int duration, Date releaseDate, double rating, String actors, String directors, String country, String trailer, String status, int ageRestricted) {
        String id = UUID.randomUUID().toString();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Movie\"(movieid, title, description, poster, duration, releasedate, rating, actors, directors, country, trailer, status, ageRestricted) values ('" + id + "', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public void addGenresToMovie(String genid, String movieid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Movie_Genre\"(genid, movieid) values ('" + genid + "', '" + movieid + "')";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateMovie(String movieid, String title, String des, String poster, int duration, Date releaseDate, double rating, String actors, String directors, String country, String trailer, String status, int ageRestricted) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Movie\" set title = ?, description = ?, poster =?, duration=?, releasedate =?, rating=?, actors=?, directors =?, country=?, trailer=?, status =? ,agerestricted =? where movieid ='" + movieid + "'";
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
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
