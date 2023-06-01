package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Combo;

public class ComboDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public List<Combo> getCombosByTheaterId(String id) {
        List<Combo> list = new LinkedList<>();
        
        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Combo\" where theaterid = '"+ id +"'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Combo(rs.getString("comboid"),
                                        rs.getString("name"), 
                                        rs.getString("description"),
                                        rs.getInt("price"),
                                        rs.getString("image")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}
