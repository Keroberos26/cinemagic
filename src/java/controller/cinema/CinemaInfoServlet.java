package controller.cinema;

import dao.CinemaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import model.Account;
import model.CinemaSystem;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CinemaInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cinema/cinema-info.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CinemaSystem cinema = (CinemaSystem) session.getAttribute("cinema");
        Account acc = (Account) session.getAttribute("acc");
        
        String action = req.getParameter("action");

        String name = req.getParameter("txtName");
        String des = req.getParameter("txtDescription");
        Part part = req.getPart("fileImg");
        String filename = part.getSubmittedFileName();
        
        CinemaDAO cineDao = new CinemaDAO();
        
        switch (action) {
            case "update":
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = cinema.getId() + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "/assets/img/cinema/" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                }
                cineDao.updateCinemaSystem(cinema.getId(), name, filename, des, acc.getId());
                break;
            case "delete":
                cineDao.deleteCinemaSystemById(cinema.getId());
                break;
            default:
                throw new AssertionError();

        }
        resp.sendRedirect("/cinema/info");
    }
    
}
