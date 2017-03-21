import java.awt.*;

/**
 * Created by kaire_bernier on 3/16/17.
 */
public class Ball {
    private int vx,vy, x,y, diameter;
    private int count;
    private Color color;

    public Ball(int x, int y, int vx, int vy){
        this.x = x;
        this.y = y;
        this.vy = vy;
        this.vx = vx;
        diameter = 25;


    }
    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval(x,y,diameter,diameter);

    }
    public void move(int w, int h){
////        int ax = (int)(Math.random()*4-2);
////        int ay = (int)(Math.random()*4-2);
//        vx+=ax;
//        vy+=ay;

        if(x + diameter >= w ){
            vx = -vx;
            x = w -diameter;



        }
        if(y + diameter >= h){
            vy = -vy;
            y = h - diameter;


        }
        if(y <= 0){
            vy = -vy;
            y = 0;


        }
        if(x <= 0){
            vx = -vx;
            x = 0;

        }

        x +=vx;
        y+=vy;


    }
}
