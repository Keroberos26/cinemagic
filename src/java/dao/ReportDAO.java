package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chart;
import model.CinemaSystem;
import model.Movie;
import model.Theater;

public class ReportDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    //Admin - Dashboard Income
    public int getAllIncome() {
        int income = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT SUM(price) AS total FROM \"Ticket\" ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    income = rs.getInt("total");
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
        return income;
    }

    //Admin - Dashboard NumberTicket
    public int getAllNumberTicket() {
        int count = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(*) AS ticket_count\n"
                        + "FROM \"Ticket\"; ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("ticket_count");
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
        return count;
    }

    //Admin - Dashboard NumberAccount
    public int getAllNumberAccount() {
        int count = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select COUNT(*) as number from \"Account\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("number");
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
        return count;
    }

    //Admin - Chart - Year
    public Chart chartByYear() {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                for (int i = 1; i <= 12; i++) {
                    chart.getLabels().add(Integer.toString(i));
                    chart.getData().add(0);
                }
                String sql = "SELECT EXTRACT(MONTH FROM timestamp) AS month, SUM(price) AS income\n"
                        + "FROM \"Ticket\" t\n"
                        + "WHERE EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_TIMESTAMP)\n"
                        + "GROUP BY month;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("month") - 1, rs.getString("month"));
                    chart.getData().set(rs.getInt("month") - 1, rs.getInt("income"));
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

        return chart;

    }

    //Admin - Chart - Months
    public Chart chartByMonths() {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                List<Date> daysOfMonth = new ArrayList<>();
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);

                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day = 1; day <= lastDay; day++) {
                    cal.set(Calendar.DAY_OF_MONTH, day);
                    daysOfMonth.add(cal.getTime());
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                for (Date day : daysOfMonth) {
                    String formattedDay = dateFormat.format(day);
                    chart.getLabels().add(formattedDay);
                    chart.getData().add(0);
                }
                String sql = "select extract(day from timestamp) as day, extract(month from timestamp) as month, sum(price) as income from \"Ticket\" t\n"
                        + "where extract(month from timestamp) = extract(month from current_timestamp) AND EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)\n"
                        + "group by day, month";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("day") - 1, rs.getString("day"));
                    chart.getData().set(rs.getInt("day") - 1, rs.getInt("income"));
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

        return chart;
    }

    //Cinema - Chart - Month
    public Chart chartByMonthCine(String cineid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                List<Date> daysOfMonth = new ArrayList<>();
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);

                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day = 1; day <= lastDay; day++) {
                    cal.set(Calendar.DAY_OF_MONTH, day);
                    daysOfMonth.add(cal.getTime());
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                for (Date day : daysOfMonth) {
                    String formattedDay = dateFormat.format(day);
                    chart.getLabels().add(formattedDay);
                    chart.getData().add(0);
                }
                String sql = "select extract(day from timestamp) as day, extract(month from timestamp) as month, sum(price) as income \n"
                        + "FROM\n"
                        + "  \"Ticket\" AS t\n"
                        + "JOIN\n"
                        + "  \"Showtime\" AS s ON t.showid = s.showid\n"
                        + "JOIN\n"
                        + "  \"Room\" AS r ON s.roomid = r.roomid\n"
                        + "  JOIN\n"
                        + "  \"Theater\" AS th ON r.theaterid = th.theaterid\n"
                        + "  JOIN\n"
                        + "  \"CinemaSystem\" AS m ON th.cineid = m.cineid\n"
                        + "  where extract(month from timestamp) = extract(month from current_timestamp) AND EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)  and m.cineid = '" + cineid
                        + "' group by day, month";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("day") - 1, rs.getString("day"));
                    chart.getData().set(rs.getInt("day") - 1, rs.getInt("income"));
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

        return chart;
    }

    //Theater - Chart - Month
    public Chart chartByMonthTheater(String theaterid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                List<Date> daysOfMonth = new ArrayList<>();
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                String formattedMonth = String.format("%02d", month);

                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day = 1; day <= lastDay; day++) {
                    cal.set(Calendar.DAY_OF_MONTH, day);
                    daysOfMonth.add(cal.getTime());
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                for (Date day : daysOfMonth) {
                    String formattedDay = dateFormat.format(day);
                    chart.getLabels().add(formattedDay);
                    chart.getData().add(0);
                }
                String sql = "select day, sum(income) as income from \"Income\" where theaterid= '" + theaterid + "' and year = '" + year + "' and month='" + formattedMonth + "' group by day";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("day") - 1, rs.getString("day"));
                    chart.getData().set(rs.getInt("day") - 1, rs.getInt("income"));
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

        return chart;
    }

    //Cinema - Chart - Year
    public Chart chartByYearCine(String cineid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                for (int i = 1; i <= 12; i++) {
                    chart.getLabels().add(Integer.toString(i));
                    chart.getData().add(0);
                }
                String sql = "SELECT EXTRACT(MONTH FROM timestamp) AS month, SUM(price) AS income\n"
                        + "FROM\n"
                        + "  \"Ticket\" AS t\n"
                        + "JOIN\n"
                        + "  \"Showtime\" AS s ON t.showid = s.showid\n"
                        + "JOIN\n"
                        + "  \"Room\" AS r ON s.roomid = r.roomid\n"
                        + "  JOIN\n"
                        + "  \"Theater\" AS th ON r.theaterid = th.theaterid\n"
                        + "  JOIN\n"
                        + "  \"CinemaSystem\" AS m ON th.cineid = m.cineid\n"
                        + "  WHERE EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_TIMESTAMP) and m.cineid = '" + cineid + "' GROUP BY month;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("month") - 1, Integer.toString(rs.getInt("month")));
                    chart.getData().set(rs.getInt("month") - 1, rs.getInt("income"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return chart;
    }

    //Theater - Chart - Year
    public Chart chartByYearTheater(String theaterid, String year) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                for (int i = 1; i <= 12; i++) {
                    chart.getLabels().add(Integer.toString(i));
                    chart.getData().add(0);
                }
                String sql = "select  month as month, sum(income) as income from \"Income\" where theaterid= '" + theaterid + "' and year = '" + year + "' group by month";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("month") - 1, Integer.toString(rs.getInt("month")));
                    chart.getData().set(rs.getInt("month") - 1, rs.getInt("income"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return chart;
    }

    //Cinema - Dashboard IncomeCine
    public int getIncomeByCine(String cineid) {
        int income = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select sum(income) as total from \"Income\" where cineid = '" + cineid + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    income = rs.getInt("total");
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
        return income;
    }

    //Cinema - Dashboard IncomeTheater
    public int getIncomeByTheater(String theaterid) {
        int income = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select sum(income) as total from \"Income\" where theaterid = '" + theaterid + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    income = rs.getInt("total");
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
        return income;
    }

    //Cinema - Dashboard NumberTicket Cine
    public int getNumberTicketByCine(String cineid) {
        int number = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select sum(numofticket) as numTicket from \"Income\" where cineid= '" + cineid + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    number = rs.getInt("numTicket");
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
        return number;
    }

    //Cinema - Dashboard NumberTicket Theater
    public int getNumberTicketByTheater(String theaterid) {
        int number = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select sum(numofticket) as numTicket from \"Income\" where theaterid= '" + theaterid + "'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    number = rs.getInt("numTicket");
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
        return number;
    }

    //Theater - Chart - 7Day
    public Chart chartBy7DayTheater(String theaterid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            LocalDate currentDate = LocalDate.now();

            // Tạo định dạng cho ngày
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");

            // Lấy 7 ngày gần nhất
            for (int i = 6; i >= 0; i--) {
                LocalDate previousDate = currentDate.minusDays(i);
                String formattedDate = previousDate.format(dateFormatter);
                chart.getLabels().add(formattedDate);
                chart.getData().add(0);
            }
            if (con != null) {
                String sql = "select concat(day, '/', month) as date, income from \"Income\"\n"
                        + "where concat(year, '/', month, '/', day) >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'yyyy/mm/dd')\n"
                        + "and theaterid = '" + theaterid + "'\n"
                        + "order by day, month, year";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int index = chart.getLabels().indexOf(rs.getString("date"));
                    chart.getData().set(index, rs.getInt("income"));
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
        return chart;
    }

    //Cinema - Chart - 7Day
    public Chart chartBy7DayCine(String cineid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                LocalDate currentDate = LocalDate.now();

                // Tạo định dạng cho ngày
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");

                // Lấy 7 ngày gần nhất
                for (int i = 6; i >= 0; i--) {
                    LocalDate previousDate = currentDate.minusDays(i);
                    String formattedDate = previousDate.format(dateFormatter);
                    chart.getLabels().add(formattedDate);
                    chart.getData().add(0);
                }
                String sql = "select concat(day, '/', month) as date, sum(income) as income from \"Income\"\n"
                        + "where concat(year, '/', month, '/', day) >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'yyyy/mm/dd')\n"
                        + "and cineid = '" + cineid + "'\n"
                        + "group by day, month, year\n"
                        + "order by day, month, year";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int index = chart.getLabels().indexOf(rs.getString("date"));
                    chart.getData().set(index, rs.getInt("income"));
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
        return chart;
    }

    //Admin - Chart - 7Day
    public Chart chartBy7Day() {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                LocalDate currentDate = LocalDate.now();

                // Tạo định dạng cho ngày
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");

                // Lấy 7 ngày gần nhất
                for (int i = 6; i >= 0; i--) {
                    LocalDate previousDate = currentDate.minusDays(i);
                    String formattedDate = previousDate.format(dateFormatter);
                    chart.getLabels().add(formattedDate);
                    chart.getData().add(0);
                }
                String sql = "select concat(day, '/', month) as date, sum(income) as income from \"Income\"\n"
                        + "where concat(year, '/', month, '/', day) >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'yyyy/mm/dd')\n"
                        + "group by day, month, year\n"
                        + "order by day, month, year";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int index = chart.getLabels().indexOf(rs.getString("date"));
                    chart.getData().set(index, rs.getInt("income"));
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
        return chart;
    }

    //Theater - List - Top5Movie
    public List<Movie> getTop5MovieByTheater(String theaterid) {
        List list = new ArrayList();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                String formattedMonth = String.format("%02d", month);
                String sql = "SELECT m.*, sum(numofticket) as ticket_count\n"
                        + "FROM \"Income\" i join \"MovieWithGenres\" m on i.movieid = m.movieid\n"
                        + "where theaterid = '" + theaterid + "' and year='" + year + "' and month='" + formattedMonth + "'\n"
                        + "GROUP BY m.movieid, m.title, m.description, m.poster, m.duration, m.directors, m.actors, m.rating, m.country, m.status, m.genres, m.releasedate, m.agerestricted, m.maxdate, m.trailer\n"
                        + "ORDER BY ticket_count DESC\n"
                        + "LIMIT 5;";
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
        return list;
    }

    //Cinema - List - Top5Movie
    public List<Movie> getTop5MovieByCine(String cineid) {
        List list = new ArrayList();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                String formattedMonth = String.format("%02d", month);
                String sql = "SELECT m.*, sum(numofticket) as ticket_count\n"
                        + "FROM \"Income\" i join \"MovieWithGenres\" m on i.movieid = m.movieid\n"
                        + "where cineid = '" + cineid + "' and year='" + year + "' and month='" + formattedMonth + "'\n"
                        + "GROUP BY m.movieid, m.title, m.description, m.poster, m.duration, m.directors, m.actors, m.rating, m.country, m.status, m.genres, m.releasedate, m.agerestricted, m.maxdate, m.trailer\n"
                        + "ORDER BY ticket_count DESC\n"
                        + "LIMIT 5;";
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
        return list;
    }

    //Cinema - List - Top10Theater
    public Map<Theater, Integer> getTopTheaterByCine(String cineid) {
        Map<Theater, Integer> map = new LinkedHashMap<>();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                String sql = "SELECT th.*, sum(income) as income, sum(numofticket) as ticket_count\n"
                        + "FROM \"Income\" i join \"Theater\" th on i.theaterid = th.theaterid\n"
                        + "where i.cineid = '" + cineid + "'\n"
                        + "GROUP BY th.theaterid, th.name, th.street, th.ward, th.district, th.city, th.cineid, th.image\n"
                        + "ORDER BY income DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map.put(new Theater(rs.getString("theaterid"),
                            rs.getString("name"),
                            rs.getString("street"),
                            rs.getString("ward"),
                            rs.getString("district"),
                            rs.getString("city"),
                            rs.getString("image"),
                            rs.getString("cineid")), rs.getInt("income"));
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

    //Admin - List - Top5Theater
    public Map<Theater, Integer> getTop5TheaterByAdmin() {
        Map<Theater, Integer> map = new LinkedHashMap<>();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                String sql = "SELECT th.*, sum(income) as income, sum(numofticket) as ticket_count\n"
                        + "FROM \"Income\" i join \"Theater\" th on i.theaterid = th.theaterid\n"
                        + "GROUP BY th.theaterid, th.name, th.street, th.ward, th.district, th.city, th.cineid, th.image\n"
                        + "ORDER BY income DESC\n"
                        + "limit 5;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map.put(new Theater(rs.getString("theaterid"),
                            rs.getString("name"),
                            rs.getString("street"),
                            rs.getString("ward"),
                            rs.getString("district"),
                            rs.getString("city"),
                            rs.getString("image"),
                            rs.getString("cineid")), rs.getInt("income"));
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

    //Admin - List - Top5Cinema
    public Map<CinemaSystem, Integer> getTop5CineByAdmin() {
        Map<CinemaSystem, Integer> map = new LinkedHashMap<>();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                con = DbContext.getConnection();
                String sql = "SELECT c.*, sum(income) as income, sum(numofticket) as ticket_count\n"
                        + "FROM \"Income\" i join \"CinemaSystem\" c on i.cineid = c.cineid\n"
                        + "GROUP BY c.cineid, c.name, c.logo, c.description, c.accid\n"
                        + "ORDER BY income DESC\n"
                        + "limit 5;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    map.put(new CinemaSystem(rs.getString("cineid"),
                            rs.getString("name"),
                            rs.getString("logo"),
                            rs.getString("description"),
                            0), rs.getInt("income"));
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
