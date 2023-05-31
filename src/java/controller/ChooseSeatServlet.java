package controller;

import dao.SeatDAO;
import dao.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import model.Showtime;

public class ChooseSeatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        SeatDAO seatDao = new SeatDAO();
        ShowtimeDAO showDao = new ShowtimeDAO();
        
        Showtime st = showDao.getShowtimeById(id);
        req.setAttribute("st", st);
        req.setAttribute("seatMap", seatDao.getSeatsByRoomId(st.getRoomId()));
        req.getRequestDispatcher("seats-selection.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] seats = req.getParameterValues("chkSeats");
        System.out.println(Arrays.toString(seats));
    }
    
}
