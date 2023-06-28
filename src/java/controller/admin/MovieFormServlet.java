package controller.admin;

import dao.GenreDAO;
import dao.MovieDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class MovieFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieDAO dao = new MovieDAO();
        GenreDAO genDao = new GenreDAO();

        String id = req.getParameter("id");

        if (id != null) {
            req.setAttribute("m", dao.getMovieById(id));
        }

        req.setAttribute("genreList", genDao.getAllGenres());
        req.getRequestDispatcher("/admin/movie-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String id = req.getParameter("movieId");
        String title = req.getParameter("txtTitle");
        String directors = req.getParameter("txtDirectors");
        String[] genre = req.getParameterValues("chkGenres");
        String actors = req.getParameter("txtActors");
        String txtReleaseDate = req.getParameter("txtReleaseDate");
        Date releaseDate = Date.valueOf(txtReleaseDate);
        String txtDuration = req.getParameter("txtDuration");
        int duration = Integer.parseInt(txtDuration);
        String txtAge = req.getParameter("txtAge");
        int age = Integer.parseInt(txtAge);
        String country = req.getParameter("txtCountry");
        String description = req.getParameter("txtDescription");
        String trailer = req.getParameter("txtTrailer");
        Part part = req.getPart("fileImg");
        String filename = part.getSubmittedFileName();

        MovieDAO dao = new MovieDAO();

        switch (action) {
            case "add":
                id = UUID.randomUUID().toString();
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = id + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "/assets/img/poster/" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                } else {
                    filename = "/assets/img/no-poster.jpg";
                }
                String movieId = dao.addMovie(id, title, description, filename, duration, releaseDate, actors, directors, country, trailer, age);
                for (int i = 0; i < genre.length; i++) {
                    dao.addGenresToMovie(genre[i], movieId);
                }
                break;
            case "update":
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = id + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "/assets/img/poster/" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();

                }
                dao.updateMovie(id, title, description, duration, releaseDate, actors, directors, country, trailer, age, filename);
                break;
            case "delete":
                dao.deleteMoviesById(id);
                break;
            default:
                throw new AssertionError();

        }
        resp.sendRedirect("/admin/movies");

    }

}
