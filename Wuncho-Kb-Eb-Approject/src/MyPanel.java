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
// array of clicker
// for each clicker intersects ball
// if ball inters
// ball . remove
// clicker. add
public class MyPanel extends JPanel {
    private ArrayList <Ball> theBalls;// can hold an abritrary # of elemetns
    private ArrayList<Clicker>click;


    private Timer timer;
    public MyPanel(int w0, int h0) {
        setSize(w0,h0);
        // Create the array list Object and add 10 ball objects to it
        theBalls = new ArrayList<Ball>();
        click = new ArrayList<Clicker>();

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
//        theBalls.add(new Ball(350,350,5, 10));
        int n = 10;
        for (int i = 0; i < 15 ; i++) {
            int x = (int)(Math.random()*getWidth());
            int y = (int)(Math.random()*getHeight());
            theBalls.add(new Ball(x, y,5,10));



        }

        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Ball b: theBalls){
                    b.move(getWidth(),getHeight());
                }
                for (int i = 0; i < theBalls.size(); i++) {
                    Ball b = theBalls.get(i);
                    for (int j = 0; j < click.size(); j++) {
                        Clicker  c = click.get(j);
                          if (b.intersects(c)) {
                                theBalls.remove(i);
                              Clicker newClicker = new Clicker(b.getX(), b.getY());
                              click. add(newClicker);
                            }
                    }
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

               int  x=(mouseEvent.getX());
               int  y=(mouseEvent.getY());
//                updateClicker(x, y);




                theBalls.add(new Clicker(x,y));

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.black);
        g2.clearRect(0, 0, getWidth(), getHeight());
        int n = 0;
//        int r = (int)(Math.random() *256) ;
//        int l = (int)(Math.random() *256) ;
//        int b = (int)(Math.random() *256) ;
//        g2.setColor(new Color(r, l, b));




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


    public static void main(String[] args) {
        JFrame window = new JFrame("On Our Way To Viridian City");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBounds(500, 200, 600, 600); //(x, y, w, h)

        MyPanel panel = new MyPanel(600,600);

        panel.setFocusable(true);

        panel.grabFocus();

        window.add(panel);

        window.setVisible(true);


    }
}