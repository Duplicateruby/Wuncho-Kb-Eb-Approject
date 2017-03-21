

/**
 * Created by ethan_batt on 3/16/17.
 */
public class Clicker extends Ball {

    public Clicker( int x, int y){
        super(x,y,0,0);





    }

    @Override
    public void move(int width, int Height){
        setDiameter(getDiameter() + 2);
        if ( getDiameter()>75){
            setDiameter(75);
            // t
        }


    }


}
