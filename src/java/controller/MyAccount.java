package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import model.Account;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class MyAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String active = "accInfo";
        if (req.getParameter("changePass") != null) {
            active = "changePass";
        }
        req.setAttribute(active, "show active");
        req.getRequestDispatcher("my-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        AccountDAO dao = new AccountDAO();
        Account acc = (Account) req.getSession().getAttribute("acc");

        if (action.equals("updateInfo")) {
            String name = req.getParameter("txtName");
            String city = req.getParameter("sltCity");
            String district = req.getParameter("sltDistrict");
            String ward = req.getParameter("sltWard");
            String phone = req.getParameter("txtPhone");
            Part part = req.getPart("fileAvt");
            String avatar = acc.getAvatar();

            // Xử lý tên file thêm account id
            String originalFilename = part.getSubmittedFileName();
            int index = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(index + 1);
            String filename = acc.getId() + "." + ext;

            if (!filename.endsWith(".")) {
                String appPath = getServletContext().getRealPath("");
                File rootDir = new File(appPath).getParentFile().getParentFile();
                avatar = "\\assets\\img\\avt\\" + filename;

                String uploadPath = rootDir.getAbsolutePath() + "\\web" + avatar;

                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = part.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
            }

            if (dao.updateAccount(acc.getId(), name, phone, ward, district, city, avatar)) {
                acc.setName(name);
                acc.setPhone(phone);
                acc.setCity(city);
                acc.setDistrict(district);
                acc.setWard(ward);
                acc.setAvatar(avatar);
            }
            doGet(req, resp);
        }
        
        if (action.equals("changePass")) {
            String curPass = req.getParameter("txtCurPassword");
            String newPass = req.getParameter("txtPassword");
            String cfmPass = req.getParameter("txtConfirm");

            String error = "";
            if (dao.login(acc.getEmail(), curPass, false) != null) {
                if (dao.validatePassword(newPass)) {
                    if (newPass.equals(cfmPass)) {
                        dao.changePassword(acc.getId(), newPass);
                        acc.setPassword(dao.encrypPassword(newPass));
                        Cookie[] cookies = req.getCookies();
                        if (cookies != null) {
                            for (Cookie cook : cookies) {
                                String cookieName = cook.getName();
                                if (cookieName.equals("cPass")) {
                                    if (cook.getValue() != null) {
                                        cook.setValue(dao.encrypPassword(newPass));
                                        resp.addCookie(cook);
                                    }
                                }
                            }
                        }
                    } else {
                        error = "errorCfmPass";
                    }
                } else {
                    error = "errorNewPass";
                }
            } else {
                error = "errorCurPass";
            }
            req.setAttribute(error, "is-invalid");
            resp.sendRedirect("my-account?changePass=true");
        }

    }

}
