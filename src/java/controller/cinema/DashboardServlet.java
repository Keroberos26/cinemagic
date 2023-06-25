package controller.cinema;

import com.google.gson.Gson;
import dao.MovieDAO;
import dao.ReportDAO;
import dao.TicketDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.Chart;
import model.CinemaSystem;
import model.Theater;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");
        MovieDAO movDao = new MovieDAO();
        req.setAttribute("movieList", movDao.getMoviesByStatus("I"));

        ReportDAO rDao = new ReportDAO();

        int incomeTheater = rDao.getIncomeByTheater(theater.getId());
        req.setAttribute("incomeCine", incomeTheater);

        int countTheater = rDao.getNumberTicketByTheater(theater.getId());
        req.setAttribute("countCine", countTheater);

        req.getRequestDispatcher("/cinema/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chartBy = req.getParameter("chart");
        CinemaSystem cinema = (CinemaSystem) req.getSession().getAttribute("cinema");
        ReportDAO rDao = new ReportDAO();
        
        Chart chart = null;

        if (chartBy != null) {
            switch (chartBy) {
                case "year":
                    chart = rDao.chartByYearCine(cinema.getId());
                    break;
                case "month":
                    chart = rDao.chartByMonthCine(cinema.getId());
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        if (chart != null) {
            PrintWriter out = resp.getWriter();
            Gson gson = new Gson();
            out.write(gson.toJson(chart));
        }
    }

}
