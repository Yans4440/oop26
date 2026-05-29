import music.Song;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class SongTest {

    @Test
    public void testRead() throws SQLException {
        DatabaseConnection.connect("songs.db");

        Optional<Song> song = Song.Persistence.read(5);
        Song expected = new Song("Queen","Bohemian Rhapsody",355);
        assertTrue(song.isPresent());
        assertEquals(expected, song.get());
    }
}
