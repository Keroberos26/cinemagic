/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;

/**
 *
 * @author nguye
 */
public class MngMovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        String movieId = req.getParameter("movieId");
        String title = req.getParameter("txtTitle");
        String des = req.getParameter("txtDescription");
        String poster = req.getParameter("txtPoster");
        String durationInput = req.getParameter("txtDuration");
        int duration = Integer.parseInt(durationInput);
        String releaseDateInput = req.getParameter("txtReleaseDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = null;
        try {
            releaseDate = (Date) dateFormat.parse(releaseDateInput);
        } catch (ParseException ex) {
            Logger.getLogger(MngMovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ratingInput = req.getParameter("txtRating");
        double rating = Double.parseDouble(ratingInput);
        String actors = req.getParameter("txtActor");
        String directors = req.getParameter("txtDirector");
        String country = req.getParameter("txtCountry");
        String trailer = req.getParameter("txtTrailer");
        String status = req.getParameter("txtStatus");
        String ageRestricted = req.getParameter("txtAge");
        int age = Integer.parseInt(ageRestricted);

        MovieDAO dao = new MovieDAO();

        if (action.equals("deleteMovie")) {
            dao.deleteMoviesById(movieId);
        }
        if (action.equals("addMovie")) {
            //dao.addMovie(title, des, poster, duration, releaseDate, rating, actors, directors, country, trailer, status, age);
            String lastMovieId = dao.addMovie("tamminh777", null, null, 0, null, 0, null, null, null, null, null, 0);
            String[] genre = req.getParameterValues("chkGenre");
            for (int i = 0; i < genre.length; i++) {
                dao.addGenreToMovie(genre[i], lastMovieId);
            }
        }
        if (action.equals("updateMovie")) {
            dao.updateMovie(movieId, title, des, poster, duration, releaseDate, rating, actors, directors, country, trailer, status, age);
        }
        resp.sendRedirect("/");
    }

}
