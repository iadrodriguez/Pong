package pong4;

import java.awt.*;

/**
 * Created by icel on 10/21/2016.
 */
public class Ball {
    private static final int DIAMETER = 30;//size of ball

    private Pong pong;
    private Racquet racquet1;
    private Racquet racquet2;
    int x = (pong.WIDTH - DIAMETER) / 2;//post xasis
    int y = (pong.HEIGHT - DIAMETER) / 2;//post y axis
    int xa = 1;//pong.WIDTH / 2 + 1;//speed or movement
    int ya = 1;//pong.HEIGHT / 2 + 1;//speed or movemnt

    public Ball(Pong pong) {
        this.pong = pong;
    }

    void move() {
        boolean changeDirection = true;
        if (y + ya < 0)
            ya = pong.speed;
        else if (y + ya > pong.HEIGHT - DIAMETER)
            ya = -pong.speed;
        else if (x + xa < 0)
            xa = pong.speed;
        else if (x + xa > pong.WIDTH - DIAMETER)
            xa = -pong.speed;
        else if ((pong.score1 == 3) || (pong.score2 == 3)) {
            pong.gameOver();
        }
        else if ((collision(pong.racquet2, 2) == 1) || (collision(pong.racquet1, 1) == 1)) {
             if (collision(pong.racquet2, 2) == 1) {
                 System.out.println(collision(pong.racquet2, 2) + "two sub");
                 xa = -pong.speed;
                 x = pong.racquet2.getTopX(2) - DIAMETER;
                 pong.speed++;
                 pong.score2++;
            } else if (collision(pong.racquet1, 1) == 1) {
                 xa = pong.speed;
                 x = pong.racquet1.getTopX(1) + DIAMETER;
                 pong.speed++;
                 pong.score1++;
            }
        }
         else
            changeDirection = false;

        if (changeDirection)
            Sound.BALL.play();
        x = x + xa;
        y = y + ya;
    }

    public int collision(Racquet racquet, int racquetNo)
    {
        if (racquetNo == 1){
            if (pong.racquet1.getBounds(1).intersects(getBounds()))
            {
                return 1; //bounce
            }
        } else if (racquetNo == 2) {
            if (pong.racquet2.getBounds(2).intersects(getBounds()))
            {
                return 1; //bounce
            }
        }

        return 0; //nothing
    }

    public void paint(Graphics2D g)
    {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
