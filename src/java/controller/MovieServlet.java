package controller;

import dao.MovieDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import model.Movie;

public class MovieServlet extends HttpServlet {

    private final int itemsOfPage = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieDAO dao = new MovieDAO();

        req.setAttribute("genreList", dao.getAllGenres());
        req.setAttribute("countries", dao.getAllCountries());
        req.setAttribute("totalPage", Math.ceil(dao.getNumberOfMovie("", "", 0, "") * 1.0 / itemsOfPage));
        req.setAttribute("movieList", dao.getMoviesByGenres("", "", 0, "", 0, itemsOfPage));
        req.getRequestDispatcher("movies.jsp").forward(req, resp);
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
        out.write("<div class=\"row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-4 py-5\">");
        for (Movie m : movies) {
            out.write("<div class=\"col\">\n"
                    + "     <div class=\"movie-card\">\n"
                    + "         <div class=\"poster\">\n"
                    + "             <a data-fslightbox=\"" + m.getId() + "\" href=\"" + m.getTrailer() + "\">\n"
                    + "                 <div class=\"bg-img thumbnail\"\n"
                    + "                     style=\"background-image: url(" + m.getPoster() + ");\">\n"
                    + "                 </div>\n"
                    + "                 <div class=\"play-btn\">\n"
                    + "                     <svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\"\n"
                    + "                         class=\"jsx-314b02cb997a0a18\">\n"
                    + "                         <g fill=\"none\" fill-rule=\"evenodd\" class=\"jsx-314b02cb997a0a18\">\n"
                    + "                             <circle stroke=\"#FFF\" stroke-width=\"2\" fill-opacity=\".24\"\n"
                    + "                                 fill=\"#000\" cx=\"24\" cy=\"24\" r=\"23\"\n"
                    + "                                 class=\"jsx-314b02cb997a0a18\"></circle>\n"
                    + "                             <path\n"
                    + "                                 d=\"M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z\"\n"
                    + "                                 fill=\"#FFF\" fill-rule=\"nonzero\"\n"
                    + "                                 class=\"jsx-314b02cb997a0a18\"></path>\n"
                    + "                         </g>\n"
                    + "                     </svg>\n"
                    + "                 </div>\n"
                    + "                 <div class=\"age-restricted age-" + m.getAge() + "\"><span class=\"badge\">" + m.getAge() + "</span></div>\n"
                    + "             </a>\n"
                    + "         </div>\n"
                    + "         <div class=\"content main-links\">\n"
                    + "             <a href=\"movie?id=" + m.getId() + "\">\n"
                    + "                 <h4 class=\"title\">" + m.getTitle() + "</h4>\n"
                    + "                 <p class=\" genre mini-text\">" + m.getGenres() + "</p>\n"
                    + "             </a>\n"
                    + "         </div>\n"
                    + "     </div>\n"
                    + "</div>");
        }
        out.write("</div>"
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
