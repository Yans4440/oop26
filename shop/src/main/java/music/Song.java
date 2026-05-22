package music;

import java.util.Objects;

public class Song{
    private String artist;
    private String title;
    private int length;

    public Song(String artist, String title, int length) {
        this.artist = artist;
        this.title = title;
        this.length = length;
    }

    public String getArtist(){
        return artist;
    }
    public String getTitle(){
        return title;
    }
    public int getLength(){
        return length;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return length == song.length && Objects.equals(artist, song.artist) && Objects.equals(title, song.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title, length);
    }
}
