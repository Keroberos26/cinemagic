package controller;

import dao.CinemaDAO;
import dao.ShowtimeDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import model.Movie;
import model.Showtime;
import model.Theater;

public class TheaterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        TheaterDAO theDao = new TheaterDAO();
        CinemaDAO cineDao = new CinemaDAO();

        // Lấy ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Tạo danh sách chứa 7 ngày
        List<Date> dateList = new ArrayList<>();

        // Thêm 7 ngày kể từ ngày hôm nay vào danh sách
        for (int i = 0; i < 7; i++) {
            calendar.setTime(currentDate);
            calendar.add(calendar.DAY_OF_YEAR, i);
            java.util.Date date = calendar.getTime();
            Date sqlDate = new Date(date.getTime());
            dateList.add(sqlDate);
        }

        Theater t = theDao.getTheaterById(id, true);
        req.setAttribute("t", t);
        req.setAttribute("c", cineDao.getCinemaByTheaterId(t.getId()));
        req.setAttribute("dateList", dateList);
        req.getRequestDispatcher("theater.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theaterId = req.getParameter("theater");
        String date = req.getParameter("date");
        java.util.Date currentDate = new java.util.Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        PrintWriter out = resp.getWriter();
        ShowtimeDAO showDao = new ShowtimeDAO();
        Map<Movie, List<Showtime>> map = showDao.getShowtimesByMovie(theaterId, date);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if (!(map == null || map.isEmpty())) {
            for (Map.Entry<Movie, List<Showtime>> entry : map.entrySet()) {
                Movie movie = entry.getKey();
                List<Showtime> list = entry.getValue();
                out.print("<div class=\"movie-card showtimes-card\">\n"
                        + "     <div class=\"poster\">\n"
                        + "         <a data-fslightbox=\"" + movie.getId() + "\" href=\"" + movie.getTrailer() + "\">\n"
                        + "             <div class=\"bg-img thumbnail\"\n"
                        + "                 style=\"background-image: url('" + movie.getPoster() + "');\">\n"
                        + "             </div>\n"
                        + "             <div class=\"age-restricted age-" + movie.getAge() + "\"><span class=\"badge\">" + movie.getAge() + "</span></div>\n"
                        + "         </a>\n"
                        + "     </div>\n"
                        + "     <div class=\"content\">\n"
                        + "         <a href=\"movie?id=" + movie.getId() + "\">\n"
                        + "             <h4 class=\"title\">" + movie.getTitle() + "</h4>\n"
                        + "             <p class=\"mini-text\">" + movie.getGenres() + "</p>\n"
                        + "         </a>\n"
                        + "         <div class=\"row row-cols-lg-4 row-cols-md-3 row-cols-2 g-3\">\n");
                for (Showtime st : list) {
                    String disabled = "";
                    if (currentTimestamp.after(st.getStarttime())) {
                        disabled = "disabled";
                    }
                    out.print("<div class=\"col\">\n"
                            + "     <a href=\"choose-seat?id=" + st.getId() + "\" class=\"btn btn-outline-secondary btn-sm d-block " + disabled + "\">"
                            + "         <strong>" + timeFormat.format(st.getStarttime()) + "</strong> ~ " + timeFormat.format(st.getEndtime()) + "\n"
                            + "     </a>\n"
                            + "</div>");
                }
                out.print("         </div>\n"
                        + "     </div>\n"
                        + "</div>");
            }
        } else {
            out.write("<div class=\"text-center\">\n"
                    + "     <img src=\"/assets/img/not-found.svg\" width=\"120\" height=\"120\">\n"
                    + "     <h5>Úi, Suất chiếu không tìm thấy.</h5>\n"
                    + "     <div class=\"mini-text\">Bạn hãy thử tìm ngày khác nhé</div>\n"
                    + "</div>");
        }
    }

}
