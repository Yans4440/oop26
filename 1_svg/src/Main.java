import com.sun.source.tree.NewArrayTree;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        Point p = new Point();
        p.x = 50F;
        p.y = 70.5F;

        System.out.println(p);
        p.setX(5);
        System.out.println(p);

        Point p2 = new Point(x=2.5F, y=12.F);
        System.out.println(p2);

    }

}