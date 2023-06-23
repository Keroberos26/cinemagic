package controller.admin;

import dao.GenreDAO;
import dao.MovieDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Movie;

public class MovieServlet extends HttpServlet {

    private final int itemsOfPage = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieDAO dao = new MovieDAO();
        GenreDAO genDao = new GenreDAO();

        req.setAttribute("genreList", genDao.getAllGenres());
        req.setAttribute("countries", dao.getAllCountries());
        req.getRequestDispatcher("/admin/movie-mng.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genre = req.getParameter("genre");
        String country = req.getParameter("country");
        String txtYear = req.getParameter("year");
        String search = req.getParameter("search");
        String txtPage = req.getParameter("page");

        int year = 0, page = 0;
        if (txtYear != null && !txtYear.isBlank()) {
            year = Integer.parseInt(txtYear);
        }
        if (txtPage != null && !txtPage.isBlank()) {
            page = Integer.parseInt(txtPage);
        }
        int offset = page * itemsOfPage;

        MovieDAO dao = new MovieDAO();
        PrintWriter out = resp.getWriter();
        List<Movie> movies = dao.getMoviesByGenres(genre, country, year, search, offset, itemsOfPage);
        int totalPage = (int) Math.ceil(dao.getNumberOfMovie(genre, country, year, search) * 1.0 / itemsOfPage);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Movie m : movies) {
            out.write("<tr>\n"
                    + "     <td><div class=\"bg-img thumbnail\" style=\"width: 5em; background-image: url(" + m.getPoster() + ");\"></div></td>\n"
                    + "     <td class=\"text-start\">\n"
                    + "         <h3><a href=\"/admin/movie-form?id=" + m.getId() + "\">" + m.getTitle() + "</a></h3>\n"
                    + "         <span class=\"mini-text\">" + m.getGenres() + "</span>\n"
                    + "     </td>\n"
                    + "     <td class=\"text-start\">" + m.getDirectors() + "</td>\n"
                    + "     <td class=\"text-start\">" + m.getActors() + "</td>\n"
                    + "     <td>" + dateFormat.format(m.getReleaseDate()) + "</td>\n"
                    + "     <td>" + m.getDuration() + "</td>\n"
                    + "     <td>" + m.getCountry() + "</td>\n"
                    + "     <td>" + m.getAge() + "</td>\n"
                    + "</tr>");
        }
        out.write("-----"
                + "</div>"
                + "<nav aria-label=\"Page navigation example\">\n"
                + "     <ul class=\"pagination justify-content-center\">\n"
                + "         <li class=\"page-item " + (page == 0 ? "disabled" : "") + "\">\n"
                + "             <button type=\"button\" class=\"page-link\" aria-label=\"Previous\" value=\"" + (page - 1) + "\">\n"
                + "                 <span aria-hidden=\"true\"><i class=\"fa-solid fa-caret-left\"></i></span>\n"
                + "             </button>\n"
                + "         </li>");
        for (int p = 0; p < totalPage;) {
            out.write("<li class=\"page-item " + (p == page ? "active" : "") + "\">\n"
                    + "     <button type=\"button\" class=\"page-link\" value=\"" + p++ + "\">" + p + "</button>\n"
                    + "</li>");
        }
        out.write("         <li class=\"page-item " + (page == totalPage - 1 ? "disabled" : "") + "\">\n"
                + "             <button type=\"button\" class=\"page-link\" aria-label=\"Next\" value=\"" + (page + 1) + "\">\n"
                + "                 <span aria-hidden=\"true\"><i class=\"fa-solid fa-caret-right\"></i></span>\n"
                + "             </button>\n"
                + "         </li>\n"
                + "     </ul>\n"
                + "</nav>");
    }

}
