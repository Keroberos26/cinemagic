package controller;

import dao.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Showtime;

public class ChooseComboServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ShowtimeDAO showDao = new ShowtimeDAO();
        
        Showtime st = showDao.getShowtimeById(id);
        req.setAttribute("st", st);
        req.getRequestDispatcher("combo-selection.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
