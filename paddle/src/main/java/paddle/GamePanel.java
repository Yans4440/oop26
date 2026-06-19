package paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel {
    public static final int WIDTH  = 640;
    public static final int HEIGHT = 800;

    private final Paddle paddle;

    private final Ball ball;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);


        Graphicsitem.setCanvasSize(WIDTH, HEIGHT);
        paddle = new Paddle();

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                paddle.updatePosition(mouseEvent.getX());
                ball.setInitialPosition(paddle);
                repaint();
            }
        });

        ball = new Ball();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D Graphics2D = (Graphics2D) g;

        paddle.draw(Graphics2D);
        ball.draw(Graphics2D);
    }
}