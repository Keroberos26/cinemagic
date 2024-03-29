package dao;

import context.DbContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Account\"(email, password, role) values (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, encrypPassword(password));
                stm.setString(3, role);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }
    
    public boolean addAccount(String email, String password, String role, String name, String phone) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Account\"(email, password, role, name, phone) values (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, encrypPassword(password));
                stm.setString(3, role);
                stm.setString(4, name);
                stm.setString(5, phone);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    public boolean updateAccount(String id, String name, String phone, String ward, String district, String city, String avatar) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Account\" set name = ?, phone = ?, ward = ?, district =?, city = ?, avatar = ? where accid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setString(3, ward);
                stm.setString(4, district);
                stm.setString(5, city);
                stm.setString(6, avatar);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }
    
    public boolean updateAccount(String name, String phone, String role, String id) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Account\" set name = ?, phone = ?, role = ? where accid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setString(3, role);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }
     
    public boolean deleteAccount(String id){
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "delete from \"Account\" where accid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.executeUpdate();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(ComboDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    } 

    public boolean changePassword(String id, String password) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Account\" set password = ? where accid = '" + id + "'";
                stm = con.prepareStatement(sql);
                stm.setString(1, encrypPassword(password));
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }
    
    public boolean resetPassword(String email, String password) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "update \"Account\" set password = ? where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, encrypPassword(password));
                stm.setString(2, email);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    public boolean addAccountByGoogleId(String googleid, String name, String avatar) {
        boolean success = false;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "insert into \"Account\"(googleid, name, role, avatar) values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, googleid);
                stm.setString(2, name);
                stm.setString(3, "U");
                stm.setString(4, avatar);
                stm.execute();
                success = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    public Account loginGmail(String googleid) {
        Account acc = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Account\" where googleid = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, googleid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    acc = new Account(rs.getString("accid"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("district"),
                            rs.getString("ward"),
                            rs.getString("avatar"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return acc;

    }

    public Account login(String email, String password, boolean encrypted) {
        Account acc = null;

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Account\" where email = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                if (encrypted) {
                    stm.setString(2, password);
                } else {
                    stm.setString(2, encrypPassword(password));
                }
                rs = stm.executeQuery();
                if (rs.next()) {
                    acc = new Account(rs.getString("accid"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("district"),
                            rs.getString("ward"),
                            rs.getString("avatar"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return acc;
    }
    
    public List<Account> getAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Account\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Account(rs.getString("accid"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("district"),
                            rs.getString("ward"),
                            rs.getString("avatar")));
                    }
                }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
        
    public List<Account> getAccountByRole(String role){
        List<Account> account = new ArrayList();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select accid, password, name, email, role, phone, ward, district, city, avatar  from \"Account\" where role ='"+role+"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    account.add(new Account(rs.getString("accid"),
                                            rs.getString("email"),
                                            rs.getString("password"),
                                            rs.getString("role"),
                                            rs.getString("name"),
                                            rs.getString("phone"),
                                            rs.getString("city"),
                                            rs.getString("district"),
                                            rs.getString("ward"),
                                            rs.getString("avatar")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return account;
    }
}
