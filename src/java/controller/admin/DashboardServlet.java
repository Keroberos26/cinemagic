package controller.admin;

import com.google.gson.Gson;
import dao.MovieDAO;
import dao.TicketDAO;
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

        TicketDAO tDao = new TicketDAO();

        req.setAttribute("incomeCine", tDao.getAllIncome());

        req.setAttribute("countCine", tDao.getAllNumberTicket());

        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chartBy = req.getParameter("chart");
        TicketDAO tDao = new TicketDAO();

        Chart chart = null;

        if (chartBy != null) {
            switch (chartBy) {
                case "year":
                    chart = tDao.chartByYear();
                    break;
                case "month":
                    chart = tDao.chartByMonths();
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
