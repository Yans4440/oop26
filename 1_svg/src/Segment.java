public class Segment {
    public Point p, q;
    public float lengh(){
        return (float) Math.hypot(p.x-q.x, p.y-q.y);
    }
}
