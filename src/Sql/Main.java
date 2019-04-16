package Sql;

import Sql.Model.Artist;
import Sql.Model.Datasource;
import Sql.Model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Connect to a database
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        // Retrieve data from the database
        List<Artist> artists = datasource.queryArtists(5);
        if(artists == null) {
            System.out.println("No artists!");
            return;
        }
        // Display all Artists sorted by artist's name
        for(Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        // Query albums by artist's name
        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_ASC);

        // Display albums by artist name
        for(String album : albumsForArtist) {
            System.out.println(album);
        }

        // Display artist by song typed
        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for(SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }

         // Display artist by song title
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();
        for(SongArtist artist : songArtists) {
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                " Album name = " + artist.getAlbumName() +
                " Track number = " + artist.getTrack());
        }

        // insert a new song
        datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);
        datasource.close();

    }
}
