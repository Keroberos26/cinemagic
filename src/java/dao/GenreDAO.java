package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Genre;

public class GenreDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<Genre> getAllGenres() {
        List<Genre> list = new ArrayList<>();

        try {
            con = DbContext.getConnection();
            if (con != null) {
                String sql = "select * from \"Genre\"";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Genre(rs.getString("genid"), rs.getString("name")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
