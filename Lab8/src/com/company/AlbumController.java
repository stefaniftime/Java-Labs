package com.company;
import com.company.Database;

import java.sql.*;

public class AlbumController {
    public String findByArtist(int artistId) throws SQLException {
        Connection conn = Database.getConnection();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select name from albums where id = " + artistId)){
            return rs.next() ? rs.getString(1):null;
        }
    }
    public void create (String name, int artistId, int releaseYear) throws SQLException{
        Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into albums(name,artist_id,release_year) values(?,?,?)");
            pstmt.setString(1,name);
            pstmt.setInt(2,artistId);
            pstmt.setInt(3,releaseYear);
            pstmt.executeUpdate();
    }
}
