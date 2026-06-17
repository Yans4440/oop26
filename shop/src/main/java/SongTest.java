import com.sun.source.tree.AssertTree;
import database.DatabaseConnection;
import music.Song;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.invokers.Arguments;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

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

    @Test
    public void testReadFail() throws SQLException{
        DatabaseConnection.connect("songs.db");
        Optional<Song> song = Song.Persistence.read(68);
        assertTrue(song.isEmpty());
    }

    private static Stream<Arguments> args(){
        return Stream.of(
                argumemts(40, "The Beatles","Help!",138),
                argumemts(37,"The Doors","Hello, I Love You",136)
        );

    }

    @ParameterizedTest
    @MethodSource("args")
    public void testReadMany(int id, String artist, String title, int length){
        DababaseConnection.connect("songs.db", "");

        Optional<Song> song = Song.Persistence.read(id);
        Song expectedSong = new Song(artist,title,length);

        assertTrue(song.isPresent());
        assertEquals(expectedSong, song.get());
    }
}
