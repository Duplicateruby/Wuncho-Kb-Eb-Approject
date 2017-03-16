import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by kaire_bernier on 10/5/16.
 */
public class MyPanel extends JPanel {
    private ArrayList <Ball> theBalls;// can hold an abritrary # of elemetns
    private Timer timer;
    public MyPanel(int w0, int h0) {
        setSize(w0,h0);
        // Create the array list Object and add 10 ball objects to it
        theBalls = new ArrayList<Ball>();
//        for (int i = 0; i < 3; i++) {
//            theBalls.add(randBall());
//
////        }
//        theBalls.add(new FaceBall(200,100,-4,10));
//        theBalls.add(new RandomWalkB(400,400));
//        for (int i = 0; i < 8; i++) {
//            theBalls.add(new RaveBall());
//
//
//        }
//        theBalls.add(new Squareball(50,50,30));
//        theBalls.add(new Borderball(350,350,10,5));
        theBalls.add(new Ball(350,350,100));
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Ball b: theBalls){
                    b.move(getWidth(),getHeight());
                }
                repaint();

            }
        });
        timer.start();


        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getX());
                System.out.println(mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball j: theBalls){
            j.draw(g2);
        }
        //  you cna code movement in the paint with the ball.move However, is not reccomended to code inside the paint
    }
    public Ball randBall(){
        // makes a random ball at random location, with a random velocity
//        int x = (int)(Math.random()*getWidth()-50);
//        int y = (int)(Math.random()*getHeight()-50);
        int x = getWidth()/2 -25;
        int y = getHeight()/2 -25;
        int vx = (int)(Math.random()*21-10);
        int vy = (int)(Math.random()*21-10);
        return new Ball(x,y,vx,vy);
    }
}