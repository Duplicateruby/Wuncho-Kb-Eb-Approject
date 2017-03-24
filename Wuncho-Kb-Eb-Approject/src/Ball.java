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
        int r = (int)(Math.random() *256) ;
        int l = (int)(Math.random() *256) ;
        int b = (int)(Math.random() *256) ;
        color = (new Color(r, l, b));

    }


    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
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
//    public boolean intersects(Ball other){
//        int r = diameter/2;
//        int D = diameter* diameter;
//        int z = (this.x -other.x) *(this.x - other.x);
//        int c = (a.y - b.y) *(a.y - b.y);
//        D = z*c;
//
//    }
}
