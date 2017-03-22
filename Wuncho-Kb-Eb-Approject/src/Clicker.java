import java.awt.*;

/**
 * Created by ethan_batt on 3/16/17.
 */
public class Clicker extends Ball {

    public Clicker( int x, int y){
        super(x,y,0,0);





    }

    @Override
    public void move(int width, int Height) {
        setDiameter(getDiameter() + 2);
        if (getDiameter() > 130) {
            setDiameter(130);

        }



    }
    @Override
    public void draw(Graphics2D g2){

        g2.setColor(new Color(63, 171, 230,90));
        super.draw(g2);
    }


}
