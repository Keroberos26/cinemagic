package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chart;
import model.Ticket;

public class TicketDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean addTicket(String id, String showid, String accid, String name, String email, String phone, int price) {
        boolean check = false;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Ticket\"(ticketid, showid, accid, name, email, phone, price) "
                        + "values ('" + id + "', '" + showid + "', " + (accid == null ? "null" : "'" + accid + "'") + ", ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, email);
                stm.setString(3, phone);
                stm.setInt(4, price);
                stm.execute();
                check = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public void addSeatBooking(String ticketid, String seatid) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Booking\"(ticketid, seatid) values ('" + ticketid + "', '" + seatid + "')";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addComboOrder(String ticketid, String comboid, int quantity) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Order\"(ticketid, comboid, quantity) values ('" + ticketid + "', '" + comboid + "', ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                        + "  where extract(month from timestamp) = extract(month from current_timestamp) AND EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)  and m.cineid = '" + cineid +
                "' group by day, month";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().set(rs.getInt("day") - 1, rs.getString("day"));
                    chart.getData().set(rs.getInt("day") - 1, rs.getInt("income"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                    chart.getLabels().set(rs.getInt("month") - 1, rs.getString("month"));
                    chart.getData().set(rs.getInt("month") - 1, rs.getInt("income"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return chart;
    }

    //Cinema - Dashboard Income
    public int getIncomeByCine(String cineid) {
        int income = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT\n"
                        + "  SUM(t.price) AS total\n"
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
                        + "  WHERE\n"
                        + "  m.cineid = '" + cineid + "';";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    income = rs.getInt("total");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return income;
    }

    //Cinema - Dashboard NumberTicket
    public int getNumberTicketByCine(String cineid) {
        int number = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT\n"
                        + "  COUNT(*) AS numberCount\n"
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
                        + "  WHERE\n"
                        + "  m.cineid = '" + cineid + "';";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    number = rs.getInt("numberCount");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return number;
    }

    public Map<Integer, Ticket> getTicket7Days() {
        Map<Integer, Ticket> map = new HashMap<>();
        int count = 1;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "SELECT *\n"
                        + "FROM \"Ticket\"\n"
                        + "WHERE \"timestamp\" >= CURRENT_TIMESTAMP - INTERVAL '7 days';";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(rs.getString("ticketid"));
                    ticket.setPrice(rs.getInt("price"));
                    ticket.setEmail(rs.getString("email"));
                    ticket.setName(rs.getString("name"));
                    map.put(count, ticket);
                    count = count + 1;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return map;
    }
}
