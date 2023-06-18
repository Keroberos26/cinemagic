package controller;

import dao.CinemaDAO;
import dao.MovieDAO;
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
import model.Review;
import model.Showtime;
import model.Theater;

public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        MovieDAO dao = new MovieDAO();
        Movie movie = dao.getMovieById(id);

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
        for (int i = 0; i < 10; i++) {
            calendar.setTime(currentDate);
            calendar.add(calendar.DAY_OF_YEAR, i);
            java.util.Date date = calendar.getTime();
            Date sqlDate = new Date(date.getTime());
            dateList.add(sqlDate);
        }

        if (acc != null) {
            req.setAttribute("accReview", dao.getReviewOfAccount(id, acc.getId()));
            req.setAttribute("bought", dao.boughtTicket(id, acc.getId()));
        }
        req.setAttribute("m", movie);
        req.setAttribute("reviews", dao.getReviewsOfMovie(id, 0));
        req.setAttribute("theaterList", theDao.getTheatersByCinemaIdAndCity(null, city));
        req.setAttribute("cinema", cineDao.getAllCinemas());
        req.setAttribute("dateList", dateList);
        req.getRequestDispatcher("movie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String movieId = req.getParameter("movieId");

        MovieDAO dao = new MovieDAO();
        TheaterDAO theDao = new TheaterDAO();
        ShowtimeDAO showDao = new ShowtimeDAO();
        PrintWriter out = resp.getWriter();

        switch (action) {
            case "getShowtimes":
                String city = req.getParameter("city");
                String cinema = req.getParameter("cinema");
                String dateInput = req.getParameter("date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = new Date(dateFormat.parse(dateInput).getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(ShowtimeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                Map<Theater, List<Showtime>> theaterList = showDao.getShowtimesByTheater(movieId, city, cinema, date);
                if (theaterList != null && !theaterList.isEmpty()) {
                    for (Map.Entry<Theater, List<Showtime>> entry : theaterList.entrySet()) {
                        Theater t = entry.getKey();
                        List<Showtime> showList = entry.getValue();
                        if (showList != null && !showList.isEmpty()) {
                            out.write("<div class=\"accordion-item showtimes-cinema\">\n"
                                    + "     <h2 class=\"accordion-header\">\n"
                                    + "         <button class=\"accordion-button collapsed\" type=\"button\"\n"
                                    + "             data-bs-toggle=\"collapse\" data-bs-target=\"#" + t.getId() + "\"\n"
                                    + "             aria-expanded=\"false\" aria-controls=\"" + t.getId() + "\">\n"
                                    + "             <div class=\"address bg-transparent\">\n"
                                    + "                 <div class=\"left\">\n"
                                    + "                     <img src=\"" + t.getImage() + "\" alt=\"" + t.getName() + "\">\n"
                                    + "                 </div>\n"
                                    + "                 <div class=\"right\">\n"
                                    + "                     <h4>" + t.getName() + "</h4>\n"
                                    + "                     <span class=\"mini-text\">" + t.getStreet() + ", " + t.getWard() + ", " + t.getDistrict() + ", " + t.getCity() + "<a href=\"#\">[ Bản đồ ]</a></span>\n"
                                    + "                 </div>\n"
                                    + "             </div>\n"
                                    + "         </button>\n"
                                    + "     </h2>\n"
                                    + "     <div id=\"" + t.getId() + "\" class=\"accordion-collapse collapse\" data-bs-parent=\"#showtimes-cinema\">\n"
                                    + "         <div class=\"accordion-body\">\n"
                                    + "             <div class=\"row row-cols-md-5 row-cols-sm-3 row-cols-2 g-3\">\n");
                            for (Showtime st : showList) {
                                out.write("<div class=\"col\">\n"
                                        + "     <a href=\"choose-seat?id=" + st.getId() + "\" class=\"btn btn-outline-secondary btn-sm d-block\"><strong>" + timeFormat.format(st.getStarttime()) + "</strong> ~ " + timeFormat.format(st.getEndtime()) + "</a>\n"
                                        + "</div>");
                            }
                            out.write("             </div>\n"
                                    + "         </div>\n"
                                    + "     </div>\n"
                                    + "</div>");
                        }
                    }
                } else {
                    out.write("<div class=\"text-center\">\n"
                            + "     <img src=\"/assets/img/not-found.svg\" width=\"120\" height=\"120\">\n"
                            + "     <h5>Úi, Suất chiếu không tìm thấy.</h5>\n"
                            + "     <div class=\"mini-text\">Bạn hãy thử tìm ngày khác hoặc rạp khác nhé</div>\n"
                            + "</div>");
                }

                break;
            case "getMoreReviews":
                int count = Integer.parseInt(req.getParameter("count"));
                List<Review> reviews = dao.getReviewsOfMovie(movieId, count);
                if (reviews != null && !reviews.isEmpty()) {
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    for (Review r : reviews) {
                        out.write("<div class=\"d-flex gap-3 review\">\n"
                                + "     <div class=\"avatar avatar-xl flex-shrink-0\">\n"
                                + "         <img src=\"" + (r.getAcc().getAvatar() != null ? r.getAcc().getAvatar() : "/assets/img/no-avatar.png") + "\" alt=\"Avatar\"\n"
                                + "             class=\"avatar-img rounded-circle border border-4 d-block\">\n"
                                + "     </div>\n"
                                + "     <div class=\"flex-grow-1\">\n"
                                + "         <div class=\"fw-bold mb-2\">" + r.getAcc().getName() + " <span class=\"mini-text fw-normal\"> · " + dateFormat2.format(r.getDate()) + "</span></div>\n"
                                + "             <div class=\"stars mb-2\">\n");
                        for (int i = 1; i <= 10; i++) {
                            if (r.getRating() >= i) {
                                out.write("<i class=\"ri-star-fill\"></i>\n");
                            } else {
                                out.write("<i class=\"ri-star-line\"></i>\n");
                            }
                        }
                        out.write("             </div>\n"
                                + "         <div class=\"comment\">" + r.getComment() + "</div>\n"
                                + "     </div>\n"
                                + "</div>");
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
