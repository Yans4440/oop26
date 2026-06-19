package paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel {
    public static final int WIDTH  = 640;
    public static final int HEIGHT = 800;

    private final Paddle paddle;

    private final Ball ball;

    private boolean gameStarted = false;
    private Timer timer;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);


        Graphicsitem.setCanvasSize(WIDTH, HEIGHT);
        paddle = new Paddle();
        ball = new Ball();

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                paddle.updatePosition(mouseEvent.getX());
                if(!gameStarted){
                    ball.setInitialPosition(paddle);
                }
                repaint();
            }


        });
        timer = new Timer(10, e->{update();});

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameStarted = true;
                timer.start();

            }


        });



    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D Graphics2D = (Graphics2D) g;

        paddle.draw(Graphics2D);
        ball.draw(Graphics2D);
    }

    private void update(){
        if(gameStarted){
            ball.move();
            checkCollision();

            if(ball.isOutBounds()){
                timer.stop();
                gameStarted = false;
            }
            repaint();
        }
    }

    private void checkCollision(){
        if(ball.getY() + ball.getHeight()/2 >= paddle.getY() && ball.getX() >= paddle.getX() && ball.getX() <= paddle.getX()+ paddle.getWidth()){
            ball.bounce();
        };
    }

}