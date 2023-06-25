package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import model.Room;
import model.Showtime;
import model.Theater;

public class ShowtimeDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Showtime getShowtimeById(String id) {
        Showtime showtime = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where showid = '" + id + "'";
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
                            rs.getTimestamp("starttime"),
                            rs.getTimestamp("endtime"),
                            rs.getInt("price_n"),
                            rs.getInt("price_v"),
                            rs.getInt("price_c"),
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

    public List<Showtime> getShowtimesByTheaterId(String id, String date, String sortRoom, String time, String title) {
        List<Showtime> list = new LinkedList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where theaterid = '" + id + "'  and TO_CHAR(starttime, 'YYYY-MM-DD') = ?";
                if (!(sortRoom.isBlank() && title.isBlank() && time.isBlank())) {
                    sql += " order by ";

                    if (!sortRoom.isBlank()) {
                        sql += "name " + sortRoom + ",";
                    }

                    if (!time.isBlank()) {
                        sql += "starttime " + time + ",";
                    }

                    if (!title.isBlank()) {
                        sql += "title " + title + ",";
                    }

                    sql = sql.substring(0, sql.length() - 1);
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, date);
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
                            rs.getTimestamp("starttime"),
                            rs.getTimestamp("endtime"),
                            rs.getInt("price_n"),
                            rs.getInt("price_v"),
                            rs.getInt("price_c"),
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

    public Map<Movie, List<Showtime>> getShowtimesByMovie(String id, String date) {
        Map<Movie, List<Showtime>> map = new LinkedHashMap<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"ShowtimeDetail\" where theaterid = '" + id + "' and TO_CHAR(starttime, 'YYYY-MM-DD') = ? order by starttime";
                stm = con.prepareStatement(sql);
                stm.setString(1, date);
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
                            rs.getTimestamp("starttime"),
                            rs.getTimestamp("endtime"),
                            rs.getInt("price_n"),
                            rs.getInt("price_v"),
                            rs.getInt("price_c"),
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

    public Map<Theater, List<Showtime>> getShowtimesByTheater(String movieId, String city, String cinema, String date) {
        Map<Theater, List<Showtime>> map = new LinkedHashMap<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select st.*, t.theaterid, t.name as thename, t.street, t.ward, t.district, t.city, t.cineid, c.logo from \"ShowtimeDetail\" st \n"
                        + "join \"Theater\" t on st.theaterid = t.theaterid\n"
                        + "join \"CinemaSystem\" c on t.cineid = c.cineid\n"
                        + "where movieid = '" + movieId + "' and TO_CHAR(starttime, 'YYYY-MM-DD') = ? \n"
                        + "and city = ?";
                if (cinema != null && !cinema.isBlank()) {
                    sql += " and t.cineid = '" + cinema + "'";
                }
                sql += " order by thename";
                stm = con.prepareStatement(sql);
                stm.setString(1, date);
                stm.setString(2, city);
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
                    Theater theater = new Theater(rs.getString("theaterid"),
                            rs.getString("thename"),
                            rs.getString("street"),
                            rs.getString("ward"),
                            rs.getString("district"),
                            rs.getString("city"),
                            rs.getString("logo"),
                            rs.getString("cineid")
                    );
                    List<Showtime> list = null;
                    if (map.containsKey(theater)) {
                        list = map.get(theater);
                    } else {
                        list = new LinkedList<>();
                    }
                    list.add(new Showtime(rs.getString("showid"),
                            rs.getTimestamp("starttime"),
                            rs.getTimestamp("endtime"),
                            rs.getInt("price_n"),
                            rs.getInt("price_v"),
                            rs.getInt("price_c"),
                            movie,
                            room));
                    map.put(theater, list);
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

    public String addShowtime(String movieid, Timestamp time, int priceN, int priceV, int priceC, String roomid) {
        String error = "Đã có lỗi xảy ra!";

        try {
            con = DbContext.getConnection();

            if (con != null) {
                String sql = "insert into \"Showtime\" (movieid, starttime, price_n, price_v, price_c, roomid) "
                        + "values('" + movieid + "', ?, ?, ?, ?, '" + roomid + "')";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, time);
                stm.setInt(2, priceN);
                stm.setInt(3, priceV);
                stm.setInt(4, priceC);
                stm.execute();
                error = "";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("check_time_range_constraint()")) {
                error = "Thời gian và lịch chiếu này đã bị chiếm dụng!";
            }
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return error;
    }

    public String updateShowtime(String id, String movieid, Timestamp time, int priceN, int priceV, int priceC, String roomid) {
        String error = "Đã có lỗi xảy ra!";

        try {
            con = DbContext.getConnection();

            if (con != null) {
                String sql = "update \"Showtime\" set movieid = '" + movieid + "', roomid = '" + roomid + "',\n"
                        + "starttime = ?, price_n = ?, price_v = ?, price_c = ? where showid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, time);
                stm.setInt(2, priceN);
                stm.setInt(3, priceV);
                stm.setInt(4, priceC);
                stm.execute();
                error = "";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("check_time_range_constraint()")) {
                error = "Thời gian và lịch chiếu này đã bị chiếm dụng!";
            }
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return error;
    }

    public boolean deleteShowtime(String id) {
        boolean check = false;

        try {
            con = DbContext.getConnection();

            if (con != null) {
                String sql = "delete from \"Showtime\" where showid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.execute();
                check = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ShowtimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }
}
