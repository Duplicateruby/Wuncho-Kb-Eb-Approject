import java.awt.*;

/**
 * Created by ethan_batt on 3/16/17.
 */
public class Clicker extends Ball {

    int counter;
    public Clicker( int x, int y){
        super(x,y,0,0);


counter=0;


    }

    @Override
    public void move(int width, int Height) {
        setDiameter(getDiameter() + 2);
        if (getDiameter() > 130) {
            setDiameter(130);

        }
        counter++;
        // try to find if the difference between two radi are of two different balls iner




    }
    @Override
    public void draw(Graphics2D g2){

        g2.setColor(new Color(63, 171, 230,90));
        super.draw(g2);
    }

    public int getCounter() {
        return counter;
    }
}
