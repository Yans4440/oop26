import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

public class Polygon {
    private Point[] points;
    public Polygon(Point[] points){
        this.points= new Point[points.length];
        for(int i = 0; i<points.length; i++){
            this.points[i] = new Point(points[i]);

        }

    }
    public Polygon(Polygon p){
        this(p.points);
    }
    @Override
    public String toString(){
        return "Polygon{Points="+ Arrays.toString(points)+"}";
    };
    //<polygon points="100,10 150,190 50,190" style="fill:lime;stroke:purple;stroke-width:3" />
    public String toSVG(){
        String pointstring =  "";
        for (Point point : points){
            pointstring += point.getX()+","+point.getY()+" ";
        }
        return String.format(Locale.ENGLISH,
                "<polygon points=\"%s\" style=\"fill:lime;stroke:purple;stroke-width:3\" />",pointstring);
    }
    public BoundingBox boundingBox(){
        if(points.length == 0)return new BoundingBox(0, 0, 0 , 0);
        float minX =points[0].getX();
        float maxX =points[0].getX();
        float minY =points[0].getY();
        float maxY =points[0].getY();
        for(Point p : points){
            if(p.getX()<minX) minX = p.getX();
            if(p.getX()<maxX) maxX = p.getX();
            if(p.getY()<minY) minY = p.getY();
            if(p.getY()<maxY) maxY = p.getY();

        }
        return new BoundingBox(minX,minY,maxX-minX,maxY-minY);
    }

}
