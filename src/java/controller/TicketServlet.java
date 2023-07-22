package controller;

import dao.TheaterDAO;
import dao.TicketDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Combo;
import model.Seat;
import model.Theater;
import model.Ticket;
import util.Email;

public class TicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ticket t = (Ticket) req.getSession().getAttribute("ticket");
        Account acc = (Account) req.getSession().getAttribute("acc");

        TicketDAO dao = new TicketDAO();
        dao.addTicket(t.getId(), t.getShowtime().getId(), acc != null ? acc.getId() : null, t.getName(), t.getEmail(), t.getPhone(), t.getSeatPrice() + t.getCombosPrice());
        HttpSession session = req.getSession();
        session.setAttribute("order", session.getAttribute("ticket"));
        session.removeAttribute("ticket");
        for (Seat s : t.getSeats()) {
            dao.addSeatBooking(t.getId(), s.getId());
        }
        if (t.getCombos() != null) {
            for (Map.Entry<Combo, Integer> entry : t.getCombos().entrySet()) {
                Combo combo = entry.getKey();
                int quantity = entry.getValue();
                dao.addComboOrder(t.getId(), combo.getId(), quantity);
            }
        }
        TheaterDAO theDao = new TheaterDAO();
        Theater th = theDao.getTheaterById(t.getShowtime().getRoom().getTheaterid(), false);
        req.setAttribute("theater", th);

        String orderInfo = req.getParameter("vnp_OrderInfo");
        String vnpPayDate = req.getParameter("vnp_PayDate");

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date payDate = null;
        try {
            payDate = format.parse(vnpPayDate);
        } catch (ParseException ex) {
            Logger.getLogger(TicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Email.send(t.getEmail(), "Thông tin vé đã đặt", Email.ticket(t, th));
        
        req.setAttribute("orderInfo", orderInfo);
        req.setAttribute("payDate", payDate);
        req.getRequestDispatcher("ticket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
