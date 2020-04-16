package com.company;

import com.company.Database;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            AlbumController album = new AlbumController();
            ArtistController artist = new ArtistController();
            Chart chart = new Chart();
         //   artist.create("The Weeknd","Canada");
         //   artist.create("Dua Lipa", "United Kingdom");
         //   album.create("After Hours",6,2020); //Cei doi artisti sunt inregistrati in baza mea de date cu id-urile 6,7
         //   album.create("Future Nostalgia",7,2020);
         //   chart.create(album.findByArtist(7),1); //adaug in chart dupa id-ul artistului specificand pozitia
         //   chart.create(album.findByArtist(6),2);
            Artist artist1 = new Artist(8,"Lil Baby");
            Album album1 = new Album(3,"My Turn");
            artist.create(artist1.getName(),"America");
            album.create(album1.getName(),artist1.getId(),2020);
           // chart.create(album.findByArtist(8),3);
          System.out.println("Pozitia in clasament este " + chart.displayRanking("Lil Baby"));
          System.out.println(artist.findByName("Dua Lipa"));
          System.out.println(album.findByArtist(7));
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}