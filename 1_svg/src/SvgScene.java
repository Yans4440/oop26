import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class SvgScene {
    private Polygon[] polygons = new Polygon[3];
    private int index = 0;

    public void addPolygn(Polygon p){
        polygons[index] = p;
        index++;

        if(index==3){
            index = 0;
        }
    }
    public String toSVG(){
        StringBuilder polygonsString = new StringBuilder();
        for(Polygon p: polygons){
            if(p!=null){
                polygonsString.append(p.toSVG()).append("\n");
            }

        }
        return String.format(Locale.ENGLISH,
                "<svg height=\"220\" width=\"500\" xmlns=\"http://www.w3.org/2000/svg\">%s</svg>", polygonsString);
    }
    public void save(String path) throws IOException{
        FileWriter writer= new FileWriter(path);
        writer.write(toSVG());
        writer.close();
    }

}
