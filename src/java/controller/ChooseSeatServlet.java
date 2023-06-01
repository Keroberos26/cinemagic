package controller;

import dao.RoomDAO;
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
        RoomDAO roomDao = new RoomDAO();
        ShowtimeDAO showDao = new ShowtimeDAO();
        
        Showtime st = showDao.getShowtimeById(id);
        req.setAttribute("st", st);
        req.setAttribute("seatMap", roomDao.getSeatsByRoomId(st.getRoom().getId()));
        req.getRequestDispatcher("seats-selection.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String[] seats = req.getParameterValues("chkSeats");
        resp.sendRedirect("choose-combo?id="+id);
    }
    
}
