import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlaylistTest {

    @Test
    public void testEmptyPlaylist(){
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }

    @Test
    public void testSingleElement(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Muse", "Hysteria", 183));
        assertEquals(1, playlist.size());
    }
    @Test
    public void  testSameElement(){
        Playlist playlist = new Playlist();
        Song song = new Song("Muse", "Hysteria", 183);
        playlist.add(song);
        assertTrue(playlist.contains(song));
    }
    @Test
    public void testEqualElement(){
        Playlist playlist = new Playlist();
        Song song = new Song("Muse", "Hysteria", 183);
        Song samesong = new Song("Muse", "Hysteria", 183);

        playlist.add(song);

        assertTrue(playlist.contains(samesong));
        assertEquals(playlist.get(0), samesong);
    }

    @Test
    public void testAtSecond(){
        Playlist playlist = new Playlist();
        Song song1 = new Song("Deftones", "Genisis", 150);
        Song song2 = new Song("Muse", "test2", 112);
        Song song3 = new Song("Lavina", "Kraj mene", 181);

        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);
        assertEquals(song1, playlist.atSecond(0));
        assertEquals(song1, playlist.atSecond(50));
        assertEquals(song2, playlist.atSecond(200));
        assertEquals(song3, playlist.atSecond(300));
    }
}

