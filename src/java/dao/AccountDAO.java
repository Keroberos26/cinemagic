package dao;

import context.DbContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;

public class AccountDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean isExisted(String email) {
        boolean existed = true;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Account\" where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                existed = rs.next();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existed;
    }

    public String encrypPassword(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Mã hóa dữ liệu đầu vào
            byte[] hashedBytes = md.digest(password.getBytes());

            // Chuyển đổi dữ liệu đã mã hóa thành chuỗi hex
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public boolean validatePassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,32}$";
        // Tạo đối tượng Pattern từ pattern
        Pattern regexPattern = Pattern.compile(pattern);
        // Tạo đối tượng Matcher để so khớp password với pattern
        Matcher matcher = regexPattern.matcher(password);    
        return matcher.matches();
    } 
    
    public boolean addAccount(String email, String password, String role) {
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Account\"(email, password, role) values (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, encrypPassword(password));
                stm.setString(3, role);
                stm.execute();
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Account login(String email, String password) {
        Account acc = null;
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Account\" where email = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, encrypPassword(password));
                rs = stm.executeQuery();
                if (rs.next()) {
                    acc = new Account(rs.getString("accid"),
                                    rs.getString("email"),
                                    rs.getString("password"),
                                    rs.getString("role"),
                                    rs.getString("name"), 
                                    rs.getString("city"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
}
