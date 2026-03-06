public class Segment {
    public Point p, q;

    public Segment(Point p, Point q){
        this.p = p;
        this.q = q;
    }

    @Override
    public String toString(){
        return "Segment{p="+p+" q="+q+"}";
    }
    public float length(){
        return (float) Math.hypot(p.getX()-q.getX(), p.getY()-q.getY());
    }
    public static Segment findMax(Segment[] segments){
        Segment maxSeg =segments[0];
        for (Segment s: segments){
            if(s.length()>maxSeg.length())
                maxSeg = s;
            }

        return maxSeg;
    }

}
