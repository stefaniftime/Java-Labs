package com.company;

import java.sql.*;

public class Chart {
   public void create(String album_name, int position) throws SQLException {
       Connection con = Database.getConnection();
       try (PreparedStatement pstmt = con.prepareStatement("insert into chart(album_name, position) values(?, ?)")) {
           pstmt.setString(1,album_name);
           pstmt.setInt(2,position);
           pstmt.executeUpdate();
       }
   }
   public int displayRanking(String artist_name) throws SQLException{ // Functia pt afisarea pozitiei in clasament, cu parametru numele artistului
       int singer_id;
       String album_name;
       Connection conn = Database.getConnection();
       Statement stmt = conn.createStatement();
       ArtistController singer = new ArtistController();
       AlbumController currentAlbum= new AlbumController();
       singer_id = singer.findByName(artist_name);
       album_name = currentAlbum.findByArtist(singer_id);
       ResultSet rs = stmt.executeQuery("select position from chart where album_name like '%" + album_name + "%'");
       return rs.next() ? rs.getInt(1) : null;
   }
}
