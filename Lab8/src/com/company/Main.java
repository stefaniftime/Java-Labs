package com.company;

import com.company.Database;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try {
            AlbumController album = new AlbumController();
            ArtistController artist = new ArtistController();
         //   artist.create("The Weeknd","Canada");
         //   artist.create("Dua Lipa", "United Kingdom");
         //   album.create("After Hours",6,2020); //Cei doi artisti sunt inregistrati in baza mea de date cu id-urile 6,7
         //   album.create("Future Nostalgia",7,2020);
          System.out.println(artist.findByName("Dua Lipa"));
          System.out.println(album.findByArtist(1));
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}