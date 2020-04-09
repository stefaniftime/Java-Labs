package com.company;
import com.company.Database;

import java.sql.*;

public class ArtistController {
    public void create(String name, String country) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into artists(name,country) values(?,?)");
        pstmt.setString(1, name);
        pstmt.setString(2, country);
        pstmt.executeUpdate();
    }

    public Integer findByName(String name) throws SQLException {
        Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id from artists where name like '%" + name + "%'");
        return rs.next() ? rs.getInt(1) : null;
    }

}
