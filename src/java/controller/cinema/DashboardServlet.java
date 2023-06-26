package controller.cinema;

import com.google.gson.Gson;
import dao.MovieDAO;
import dao.ReportDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Calendar;
import model.Chart;
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
        HttpSession session = req.getSession();
        String chartBy = req.getParameter("chart");
        Theater theater = (Theater) session.getAttribute("theater");
        ReportDAO rDao = new ReportDAO();

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        Chart chart = null;

        if (chartBy != null) {
            switch (chartBy) {
                case "year":
                    chart = rDao.chartByYearTheater(theater.getId(), String.valueOf(currentYear));
                    break;
                case "month":
                    chart = rDao.chartByMonthTheater(theater.getId());
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
