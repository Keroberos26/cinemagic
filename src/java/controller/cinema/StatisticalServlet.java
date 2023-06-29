package controller.cinema;

import com.google.gson.Gson;
import dao.ReportDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Map;
import model.Chart;
import model.CinemaSystem;

public class StatisticalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CinemaSystem cinema = (CinemaSystem) session.getAttribute("cinema");

        ReportDAO rDao = new ReportDAO();

        int incomeCine = rDao.getIncomeByCine(cinema.getId());
        req.setAttribute("incomeCine", incomeCine);

        int countCine = rDao.getNumberTicketByCine(cinema.getId());
        req.setAttribute("countCine", countCine);

        Map theaterList = rDao.getTopTheaterByCine(cinema.getId());
        req.setAttribute("theaterList", theaterList);
        
        req.getRequestDispatcher("/cinema/statistical.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String chartBy = req.getParameter("chart");
        CinemaSystem cinema = (CinemaSystem) session.getAttribute("cinema");
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
                case "day":
                    chart = rDao.chartBy7DayCine(cinema.getId());
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
