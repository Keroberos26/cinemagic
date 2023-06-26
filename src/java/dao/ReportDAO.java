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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chart;

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
                rs.close();
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
                rs.close();
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

    //Cinema - Dashboard IncomeCine
    public int getIncomeByCine(String cineid) {
        int income = 0;
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select sum(income) as total from \"Income\" where theaterid = '" + cineid + "'";
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

    //Theater - Chart - 7Day
    public Chart chartBy7DayTheater(String theaterid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                String formattedMonth = String.format("%02d", month);
                String sql = "SELECT day, SUM(income) AS income\n"
                        + "FROM \"Income\"\n"
                        + "WHERE theaterid = '"+theaterid+"' and year='"+year+"' and month ='"+formattedMonth+"' AND day >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'DD')\n"
                        + "GROUP BY day\n"
                        + "\n"
                        + "UNION\n"
                        + "\n"
                        + "SELECT TO_CHAR(date_trunc('day', (CURRENT_DATE - INTERVAL '6 days')::date) + (n || ' days')::interval, 'DD') AS day, 0 AS income\n"
                        + "FROM generate_series(0, 6) AS n\n"
                        + "WHERE TO_CHAR(date_trunc('day', (CURRENT_DATE - INTERVAL '6 days')::date) + (n || ' days')::interval, 'DD') NOT IN (\n"
                        + "    SELECT day\n"
                        + "    FROM \"Income\"\n"
                        + "    WHERE theaterid = '"+theaterid+"' and year ='"+year+"' and month ='"+formattedMonth+"'  AND day >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'DD')\n"
                        + "    GROUP BY day\n"
                        + ")\n"
                        + "ORDER BY day ASC;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().add(rs.getString("day"));
                    chart.getData().add(rs.getInt("income"));
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
        return chart;
    }
    
    //Cinema - Chart - 7Day
    public Chart chartBy7DayCine(String cineid) {
        Chart chart = new Chart();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                String formattedMonth = String.format("%02d", month);
                String sql = "SELECT day, SUM(income) AS income\n"
                        + "FROM \"Income\"\n"
                        + "WHERE cineid = '"+cineid+"' and year='"+year+"' and month ='"+formattedMonth+"' AND day >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'DD')\n"
                        + "GROUP BY day\n"
                        + "\n"
                        + "UNION\n"
                        + "\n"
                        + "SELECT TO_CHAR(date_trunc('day', (CURRENT_DATE - INTERVAL '6 days')::date) + (n || ' days')::interval, 'DD') AS day, 0 AS income\n"
                        + "FROM generate_series(0, 6) AS n\n"
                        + "WHERE TO_CHAR(date_trunc('day', (CURRENT_DATE - INTERVAL '6 days')::date) + (n || ' days')::interval, 'DD') NOT IN (\n"
                        + "    SELECT day\n"
                        + "    FROM \"Income\"\n"
                        + "    WHERE cineid = '"+cineid+"' and year='"+year+"' and month ='"+formattedMonth+"' AND day >= TO_CHAR(CURRENT_DATE - INTERVAL '6 days', 'DD')\n"
                        + "    GROUP BY day\n"
                        + ")\n"
                        + "ORDER BY day ASC;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    chart.getLabels().add(rs.getString("day"));
                    chart.getData().add(rs.getInt("income"));
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
        return chart;
    }
}
