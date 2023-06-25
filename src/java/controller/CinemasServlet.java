package controller;

import dao.CinemaDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import model.Theater;

public class CinemasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CinemaDAO cineDao = new CinemaDAO();
        req.setAttribute("cinema", cineDao.getAllCinemas());
        req.getRequestDispatcher("cinemas.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "getShowtimes":
                String city = req.getParameter("city");
                String cinema = req.getParameter("cinema");
                TheaterDAO theDao = new TheaterDAO();
                List<Theater> list = theDao.getTheatersByCinemaIdAndCity(cinema, city);
                PrintWriter out = resp.getWriter();
                if (list != null && !list.isEmpty()) {
                    for (Theater t : list) {
                        out.write("<a href=\"theater?id=" + t.getId() + "\">\n"
                                + "     <div class=\"address bg-transparent\">\n"
                                + "         <div class=\"left\">\n"
                                + "             <img src=\"" + t.getImage() + "\" alt=\"" + t.getName() + "\">\n"
                                + "         </div>\n"
                                + "         <div class=\"right\">\n"
                                + "             <h4>" + t.getName() + "</h4>\n"
                                + "             <span class=\"mini-text\">" + t.getStreet() + ", " + t.getWard() + ", " + t.getDistrict() + ", " + t.getCity() + "</span>\n"
                                + "         </div>\n"
                                + "     </div>\n"
                                + "</a>");
                    }
                } else {
                    out.write("<div class=\"text-center\">\n"
                            + "     <img src=\"/assets/img/not-found.svg\" width=\"120\" height=\"120\">\n"
                            + "     <h5>Không tìm thấy cụm rạp nào.</h5>\n"
                            + "     <div class=\"mini-text\">Bạn hãy thử lại với phim khác hoặc rạp khác nha!</div>\n"
                            + "</div>");
                }
                break;
            default:
                throw new AssertionError();
        }
    }

}
