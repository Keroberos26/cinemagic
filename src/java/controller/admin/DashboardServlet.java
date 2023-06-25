package controller.admin;

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

        req.setAttribute("incomeCine", rDao.getAllIncome());

        req.setAttribute("countCine", rDao.getAllNumberTicket());

        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chartBy = req.getParameter("chart");
        ReportDAO rDao = new ReportDAO();
        Chart chart = null;

        if (chartBy != null) {
            switch (chartBy) {
                case "year":
                    chart = rDao.chartByYear();
                    break;
                case "month":
                    chart = rDao.chartByMonths();
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
