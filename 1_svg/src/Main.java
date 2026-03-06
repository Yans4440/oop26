import com.sun.source.tree.NewArrayTree;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        Point p = new Point();


        System.out.println(p);
        p.setX(5);
        p.setY(70.5F);
        System.out.println(p);

        Point p2 = new Point(2.5F, 12.F);
        //p2.setX(2);
        //p2.setY(20.5F);
        System.out.println(p2);

    }

}