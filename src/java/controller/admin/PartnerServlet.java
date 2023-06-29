package controller.admin;

import dao.AccountDAO;
import dao.CinemaDAO;
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
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class PartnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CinemaDAO cineDao = new CinemaDAO();
        AccountDAO accDao = new AccountDAO();

        req.setAttribute("listAcc", accDao.getAccountByRole("C"));
        
        req.setAttribute("cinemaList", cineDao.listCinema());
        req.getRequestDispatcher("/admin/partner.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        String cinemaid = req.getParameter("cinemaId");
        String name = req.getParameter("txtName");
        String accid = req.getParameter("sltAccount");
        String des = req.getParameter("txtDescription");
        Part part = req.getPart("fileImg");
        String filename = part.getSubmittedFileName();

        CinemaDAO cineDao = new CinemaDAO();

        switch (action) {
            case "add":
                cinemaid = UUID.randomUUID().toString();
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = cinemaid + "." + ext;

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
                } else {
                    filename = "/assets/img/no-theater.jpg";
                }
                cineDao.addCinemaSystem(cinemaid, name, filename, des, accid);
                break;
            case "update":
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = cinemaid + "." + ext;

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
                System.out.println(cineDao.updateCinemaSystem(cinemaid, name, filename, des, accid));
                break;
            case "delete":
                cineDao.deleteCinemaSystemById(cinemaid);
                break;
            default:
                throw new AssertionError();
        }
        resp.sendRedirect("/admin/cinema");
    }

}
