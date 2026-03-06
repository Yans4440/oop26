import java.util.Locale;

public class Point {
    private float x;
    private float y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }
    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y=y;
    }
    public String toString(){
        return "Point {x="+x+",y="+y+"}";
    };
    public String toSVG(){
        return String.format(Locale.ENGLISH,
                "<circle r=\"45\" cx=\"%f\" cy=\"%f\" fill=\"red\" />",x, y);
    }
    public void translate(float dx, float dy){
        x += dx;
        y += dy;

    }
    public Point translated(float dx,float dy){
        Point newPoint = new Point();
        newPoint.x = this.x + dx;
        newPoint.y = this.y + dy;
        return newPoint;
    }
}
