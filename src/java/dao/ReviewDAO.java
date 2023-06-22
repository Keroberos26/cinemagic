package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Review;

public class ReviewDAO {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public boolean addReview(String accid, String movieid, int rating, String comment){
        boolean check = false;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Review\"(accid, movieid, rating, comment) values ('" + accid + "', '" + movieid + "', '" + rating + "', '" + comment + "' )";
                stm = con.prepareStatement(sql);
                stm.execute();
                check = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }
    
    public List<Review> getReviewsOfMovie(String movieId, int offset) {
        List<Review> list = new LinkedList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Review\" r join \"Account\" a on r.accid = a.accid\n"
                        + "where movieid = '" + movieId + "'\n"
                        + "order by date desc offset ? limit 3";
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
                            rs.getString("district"),
                            rs.getString("ward"),
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
                            rs.getString("district"),
                            rs.getString("ward"),
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
    
     public void updateReview(String accid, String movieid,int rating, String comment) {
         Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Review\" set rating = ?, comment = ?, date =? where accid ='" + accid + "' and movieid = '" + movieid + "'" ;
                stm = con.prepareStatement(sql);
                stm.setInt(1, rating);
                stm.setString(2, comment);
                stm.setTimestamp(3, currentTimestamp);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
