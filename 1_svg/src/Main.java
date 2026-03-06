import com.sun.source.doctree.EscapeTree;
import com.sun.source.tree.NewArrayTree;

import java.awt.*;

public class Main {
    public static void main(String[] args){
    /*    Point p = new Point();


        System.out.println(p);
        p.setX(5);
        p.setY(70.5F);
        System.out.println(p);

        Point p2 = new Point(2.5F, 12.F);
        //p2.setX(2);
        //p2.setY(20.5F);
        System.out.println(p2);

        Segment s1 = new Segment(new Point(p),new Point(p2));
        System.out.println(s1);
        p.setX(100);
        System.out.println(s1);*/
        Point[] points = new Point[4];
        points[0] = new Point(32.3F,15.6F);
        points[1] = new Point(42.0F,42.6F);
        points[2] = new Point(10.5F,32.6F);
        points[3] = new Point(0.3F,1.6F);

        Polygon p1 = new Polygon(points);
        Polygon p2 = new Polygon(new Point[]{
                new Point(0, 0),
                new Point(50, 240),
                new Point(112,110)
        });

        SvgScene scene = new SvgScene();
        scene.addPolygn(p1);
        scene.addPolygn(p2);
        System.out.println(scene.toSVG());

        System.out.println(p1.boundingBox());


    }

}