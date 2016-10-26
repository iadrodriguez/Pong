package pong4;

/**
 * Created by icel on 10/21/2016.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pong extends JPanel{
    protected static final int WIDTH = 376;
    protected static final int HEIGHT = 362;
    Ball ball = new Ball(this); //Q: I don't get the this here.
    Racquet racquet1 = new Racquet(this, 1);
    Racquet racquet2 = new Racquet(this, 2);
    int speed = 2; //will keep the speed of the game, increases every time we hit the ball with a racquet
    public boolean playAgain, exit;
    public int score1;
    public int score2;

    public Pong() {
        addKeyListener(new KeyListener() { //anonymous class
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if ((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_S)) {
                    racquet1.keyReleased(e);
                }
                if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
                    racquet2.keyReleased(e);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_S)) {
                    racquet1.keyPressed(e);
                }
                if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {
                    racquet2.keyPressed(e);
                }
            }
        });
        setFocusable(true);//TODO review this again why set focusable true
        Sound.BACK.loop();//play the background music until game over
    }

    private void move() {
        ball.move();//calls the move method of ball
        racquet1.move();
        racquet2.move();
    }

    public void gameOver() {//public, because it will be called from the sprite "Ball" when it detects that it has got to the lower border of the canvas.
        Sound.BACK.stop();//stops the background music
        Sound.GAMEOVER.play(); //play gameover sound
        int highScore = Math.max(score1, score2);
        String playerWins;
        if (score1 > score2) {
            playerWins = new String("Player1 ");
        }else {
            playerWins = new String("Player2 ");
        }
        JOptionPane.showMessageDialog(this, playerWins + "wins! " + "Score: " + highScore,
                "Game Over", JOptionPane.YES_NO_OPTION);//present game over and the score
        System.exit(ABORT);

    }

    @Override
    public void paint(Graphics g) { //draws an object, Q: I don't get this "At the beginning of the execution of the main method, there is only one thread."?
        super.paint(g);// cleans the screen
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // makes the borders of the figure smoother.
        ball.paint(g2d);//calls the paint method of ball
        racquet1.paint(g2d, 1); //calls the paint method of racquet
        racquet2.paint(g2d, 2); //calls the paint method of racquet

        //the following code will paint the punctuation or scores
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(score1), 10, 30);
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(score2), WIDTH - 10, 30);
//        g2d.drawString();

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis"); //create the frame or window
        Pong pong = new Pong();
        frame.add(pong);
        frame.setSize(WIDTH + 15, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(pong.getWidth());

        while(true) {//game loop that change the position of circle and repaint it.
            pong.move();
            pong.repaint();
            Thread.sleep(10); //tells the processor that the thread which is being run must sleep for 10 milliseconds, which allows the processor to execute other threads(paint method)
        }
    }
}
