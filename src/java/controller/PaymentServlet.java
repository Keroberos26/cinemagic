package controller;

import dao.ShowtimeDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Showtime;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ShowtimeDAO showDao = new ShowtimeDAO();
        TheaterDAO theDao = new TheaterDAO();
        
        Showtime st = showDao.getShowtimeById(id);
        
        req.setAttribute("st", st);
        req.setAttribute("theater", theDao.getTheaterById(st.getRoom().getTheaterid()));
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
