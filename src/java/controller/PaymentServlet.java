package controller;

import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Ticket;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        
        TheaterDAO theDao = new TheaterDAO();
        req.setAttribute("theater", theDao.getTheaterById(ticket.getShowtime().getRoom().getTheaterid(), false));
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
