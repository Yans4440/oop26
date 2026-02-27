public class Segment {
    public Point p, q;
    public float lengh(){
        return (float) Math.hypot(p.x-q.x, p.y-q.y);
    }
    public static Segment findMax(Segment[] segments){
        Segment maxSeg =segments[0];
        for (Segment s: segments){
            if(s.lengh()>maxSeg.lengh()) {
                maxSeg = s;
            }

        }
        return maxSeg;
    }
}
