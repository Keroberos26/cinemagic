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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Movie;
import model.Showtime;
import model.Theater;

public class ShowtimesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CinemaDAO cineDao = new CinemaDAO();
        TheaterDAO theDao = new TheaterDAO();

        Account acc = (Account) req.getSession().getAttribute("acc");
        String city = null;
        if (acc != null && acc.getCity() != null) {
            city = acc.getCity();
        } else {
            city = "Đà Nẵng";
        }

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

        req.setAttribute("theaterList", theDao.getTheatersByCinemaIdAndCity(null, city));
        req.setAttribute("cinema", cineDao.getAllCinemas());
        req.setAttribute("dateList", dateList);
        req.getRequestDispatcher("showtime.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String city = req.getParameter("city");
        String cineid = req.getParameter("cinema");

        TheaterDAO theDao = new TheaterDAO();
        PrintWriter out = resp.getWriter();

        switch (action) {
            case "getTheaters":
                List<Theater> theaterList = theDao.getTheatersByCinemaIdAndCity(cineid, city);
                if (!(theaterList == null || theaterList.isEmpty())) {
                    String active = " class=\"active\"";
                    for (Theater t : theaterList) {
                        out.write("<a href=\"#\" theater-id=\"" + t.getId() + "\"" + active + ">\n"
                                + "     <div class=\"theater-avt\">\n"
                                + "         <img src=\"" + t.getImage() + "\">\n"
                                + "     </div>\n"
                                + "     <div class=\"theater-name\">" + t.getName() + "</div>\n"
                                + "</a>");
                        active = "";
                    }
                } else {
                    out.write("<div class=\"text-center\">\n"
                            + "     <img src=\"/assets/img/not-found.svg\" width=\"120\" height=\"120\">\n"
                            + "     <h5>Không tìm thấy cụm rạp nào.</h5>\n"
                            + "     <div class=\"mini-text\">Bạn hãy thử lại với phim khác hoặc rạp khác nha!</div>\n"
                            + "</div>");
                }
                break;

            case "getTheaterInfo":
                String id = req.getParameter("theater");
                Theater theater = theDao.getTheaterById(id, false);
                out.write("<div class=\"left\">\n"
                        + "     <img src=\"" + theater.getImage() + "\">"
                        + "</div>\n"
                        + "<div class=\"right\">\n"
                        + "     <h4>Lịch chiếu phim " + theater.getName() + "</h4>\n"
                        + "     <span class=\"mini-text\">" + theater.getStreet() + ", " + theater.getWard() + ", \n"
                        + "     " + theater.getDistrict() + ", " + theater.getCity() + "<a href=\"#\">[ Bản đồ]</a></span>\n"
                        + "</div>");
                break;

            case "getShowtimes":
                String theaterId = req.getParameter("theater");
                String dateInput = req.getParameter("date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = new Date(dateFormat.parse(dateInput).getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ShowtimesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                            out.print("<div class=\"col\">\n"
                                    + "     <a href=\"choose-seat?id=" + st.getId() + "\" class=\"btn btn-outline-secondary btn-sm d-block\">"
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
                break;
            default:
                throw new AssertionError();
        }

    }

}
