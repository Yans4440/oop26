import java.util.Locale;

public class Elipes extends Shape{
    private Point center;
    private float rx,ry;

    public Elipes(Point center,float rx, float ry, Style style){
        super(style);
        this.center = center;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSVG(){
        return String.format(Locale.ENGLISH, "<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\" style=\"%s\" />",
                rx,ry,center.getX(),center.getY(), style.toSVG());
    }

}
