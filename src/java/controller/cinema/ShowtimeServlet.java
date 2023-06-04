package controller.cinema;

import dao.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Theater;

public class ShowtimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater =  (Theater)session.getAttribute("theater");
        
        ShowtimeDAO showDao = new ShowtimeDAO();
        
        req.setAttribute("showList", showDao.getShowtimesByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/showtimes-mng.jsp").forward(req, resp);
        
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
