package paddle;

import java.awt.*;
import java.awt.geom.Point2D;



public class Ball extends Graphicsitem{
    double width;
    double height;
    private Point2D.Double moveVector = new Point2D.Double(1, -1);

    public Ball(){
        height = 0.018 * canvasHeight;
        width = 0.018 * canvasWidth;
    }

    public void move(){
        x += moveVector.x;
        y += moveVector.y;

        if(x <= width/2 || x >= canvasWidth - width/2){
            moveVector.x *= -1;
        }
    }

    public void setInitialPosition(Paddle paddle){
    x = paddle.getX() + paddle.getWidth()/2;
    y = paddle.getY() - (height/2)-1;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        graphics2D.fillOval(
                (int) x - (int) width/2,
                (int) y - (int) height/2,
                (int) width,
                (int) height
        );
    }
}
