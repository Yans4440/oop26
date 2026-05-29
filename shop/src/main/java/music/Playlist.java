package music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {
    public Song atSecond(int second){
        int offset = 0;
        for(Song song : this){
            int end = offset + song.getLength();
            if(offset <= second && second < end){
                return song;
            }else{
                offset = end;
            }
        }
        return null;
    }
}
