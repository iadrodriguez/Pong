package pong4;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by icel on 10/21/2016.
 */
public class Racquet {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 60;
    private Pong pong;
    public int racquetNo;
    int x1 = 0;
    int y = (pong.HEIGHT) / 2 - HEIGHT / 2; //not fixed x since there are 2 racquets at diff post, y is not fixed also since racquets move up or down
    int x2 = pong.WIDTH - WIDTH;
    int ya1 = 0;
    int ya2 = 0;

    public boolean moveUp, moveDown;

    public Racquet(Pong pong, int racquetNo) {
        this.pong = pong;
        this.racquetNo = racquetNo;

        if (racquetNo == 1) {
            this.x1 = 0;
        }

        if (racquetNo == 2) {
            this.x2 = pong.WIDTH - WIDTH;
        }

        this.y = pong.HEIGHT / 2 - HEIGHT / 2;
    }

    public void paint(Graphics2D g2d, int racquetNo) {
        if (racquetNo == 1) {
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(x1, y, WIDTH, HEIGHT);
        }
        if (racquetNo == 2) {
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(x2, y, WIDTH, HEIGHT);
        }
    }

    public void move() {
        if (y + ya1 > 0 && y + ya1 < pong.getHeight() - HEIGHT) {
            y = y + ya1;
        }
        if (y + ya2 > 0 && y + ya2 < pong.getHeight() - HEIGHT) {
            y = y + ya2;
        }
    }

    public void keyPressed(KeyEvent e) {//released
        if (e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = true;
            ya1 = -pong.speed;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp = true;
            ya2 = -pong.speed;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = true;
            ya1 = pong.speed;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown = true;
            ya2 = pong.speed;

        }
    }

    public void keyReleased(KeyEvent e) {//released
        if (e.getKeyCode() == KeyEvent.VK_W) {
            moveUp = true;
            ya1 = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp = true;
            ya2 = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            moveDown = true;
            ya1 = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown = true;
            ya2 = 0;
        }
    }

    public Rectangle getBounds(int racquetNo) { //boundaries surrounding the racquet
        if (racquetNo == 2) {
            return new Rectangle(x2, y, WIDTH, HEIGHT);
        }
        return new Rectangle(x1, y, WIDTH, HEIGHT);
    }

    public int getTopX(int racquetNo) {//boundary/side of racket facing the ball
        if (racquetNo == 1) {
            return WIDTH;//assuming pong1 is pid pid sa left side sa pong
        } else if (racquetNo == 2) {
            return pong.getWidth() - WIDTH;//assuming pong2 is pidpid right side sa dialog
        }
        return 0;
    }
}
