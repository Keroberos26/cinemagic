package controller.cinema;

import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CinemaSystem;

public class CinemaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String doPost = req.getParameter("post");
        HttpSession session = req.getSession();
        CinemaSystem cinema = (CinemaSystem) session.getAttribute("cinema");
        
        if (doPost == null) {
            TheaterDAO dao = new TheaterDAO();
            req.setAttribute("theaterList", dao.getTheatersByCinemaId(cinema.getId()));
            req.getRequestDispatcher("cinema.jsp").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        TheaterDAO theDao = new TheaterDAO();
        HttpSession session = req.getSession();
        session.setAttribute("theater", theDao.getTheaterById(id));
        resp.sendRedirect("/cinema/dashboard");
    }

}
