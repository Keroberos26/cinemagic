package controller;

import dao.RoomDAO;
import dao.SeatDAO;
import dao.ShowtimeDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import model.Seat;
import model.Showtime;
import model.Ticket;

public class ChooseSeatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        RoomDAO roomDao = new RoomDAO();
        ShowtimeDAO showDao = new ShowtimeDAO();

        Showtime st = showDao.getShowtimeById(id);
        Ticket ticket = (Ticket) session.getAttribute("ticket");

        if (ticket == null || !ticket.getShowtime().equals(st)) {
            ticket = new Ticket(st);
            session.setAttribute("ticket", ticket);
        }
        
        TheaterDAO theDao = new TheaterDAO();
        req.setAttribute("theater", theDao.getTheaterById(st.getRoom().getTheaterid()));
        req.setAttribute("seatMap", roomDao.getSeatsByShowtime(st));
        req.getRequestDispatcher("seats-selection.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        
        String[] seatId = req.getParameterValues("seatId[]");
        String[] seatNum = req.getParameterValues("seatNum[]");
        String[] seatType = req.getParameterValues("seatType[]");
        SeatDAO seatDao = new SeatDAO();

        List<Seat> list = null;
        if (seatId != null) {
            list = new LinkedList<>();
            
            for (int i = 0; i < seatId.length; i++) {
                Seat s = new Seat(seatId[i], seatNum[i], seatType[i], false);
                list.add(s);
            }
        }
        ticket.setSeats(list);

        DecimalFormat priceFormat = new DecimalFormat("#,###");
        PrintWriter out = resp.getWriter();

        out.print("<p>Chỗ ngồi <span class=\"fw-semibold\">" + ticket.getSeatNum() + "</span></p>\n"
                + "<hr>\n"
                + "<h4>Tạm tính <span class=\"price float-end\">" + priceFormat.format(ticket.getSeatPrice()) + "</span></h4>");
    }

}
