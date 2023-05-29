package controller;

import dao.CinemaDAO;
import dao.MovieDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieDAO movDao = new MovieDAO();
        CinemaDAO cineDao = new CinemaDAO();
        req.setAttribute("inTheater", movDao.getMoviesByStatus("I"));
        req.setAttribute("comingSoon", movDao.getMoviesByStatus("S"));
        req.setAttribute("system", cineDao.getAllCinemas());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + req.getParameter("action"));
    }
    
}
