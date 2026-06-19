package paddle;

import java.awt.*;

public abstract class Graphicsitem {
    protected static double canvasWidth;
    protected static double canvasHeight;

    protected double x;
    protected double y;

    public static void setCanvasSize(double canvasWidth, double canvasHeight){
        Graphicsitem.canvasWidth = canvasWidth;
        Graphicsitem.canvasHeight = canvasHeight;
    }
    public abstract void draw(Graphics2D graphics2D);

    public static double getCanvasWidth() {
        return canvasWidth;
    }

    public static double getCanvasHeight() {
        return canvasHeight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
