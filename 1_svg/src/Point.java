import java.util.Locale;

public class Point {
    public float x;
    public float y;

    public String toString(){
        return "Point {x="+x+",y="+y+"}";
    };
    public String toSVG(){
        return String.format(Locale.ENGLISH,
                "<circle r=\"45\" cx=\"%f\" cy=\"%f\" fill=\"red\" />",x, y);
    }
}
