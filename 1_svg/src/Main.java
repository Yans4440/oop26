import com.sun.source.tree.NewArrayTree;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello word");
        Point p = new Point();
        p.x = 50F;
        p.y = 70.5F;

        System.out.println(p);
        p.translate(20, -5);
        System.out.println(p);

        Point p2 = p.translated(-30,-0.5F);
        System.out.println(p2);

        Segment s = new Segment();
        s.p = p;
        s.q = p2;
        System.out.println(s.lengh());

        Point p3 = new Point();
        p3.x = 12;
        p3.y = 7;
        Point p4 = new Point();
        p4.x = 8;
        p4.y = 15;
        Segment s2 = new Segment();
        s2.q = p3;
        s2.p = p4;

        Point p5 = new Point();
        p3.x = 42;
        p3.y = 17;
        Point p6 = new Point();
        p4.x = 82;
        p4.y = 25;
        Segment s3 = new Segment();
        s3.q = p5;
        s3.p = p6;

        Segment[] segments = new Segment[3];
        segments[0] = s;
        segments[1] = s2;
        segments[2] = s3;

        System.out.println(findMax(segments).p);

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