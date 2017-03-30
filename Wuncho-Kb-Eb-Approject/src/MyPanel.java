import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.Clip;

/**
 * Created by kaire_bernier on 10/5/16.
 */
// array of clicker
// for each clicker intersects ball
// if ball inters
// ball . remove
// clicker. add
public class MyPanel extends JPanel {
    private ArrayList<Ball> theBalls;// can hold an abritrary # of elemetns
    private ArrayList<Clicker> click;
    private int countclick;
    private int level;
    private int ballcount;


    private Timer timer;

    public MyPanel(int w0, int h0) {
        setSize(w0, h0);
        // Create the array list Object and add 10 ball objects to it
        theBalls = new ArrayList<Ball>();
        click = new ArrayList<Clicker>();
        countclick = 0;
        level = 0;
        ballcount = 0;



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

//        for (int i = 0; i < 15; i++) {
//            int x = (int) (Math.random() * getWidth());
//            int y = (int) (Math.random() * getHeight());
//            theBalls.add(new Ball(x, y, 5, 10));
//
//
//
//        }}
//        if ( level ==1 ){
//            for (int i = 0; i < 30; i++) {
//                int x = (int) (Math.random() * getWidth());
//                int y = (int) (Math.random() * getHeight());
//                theBalls.add(new Ball(x, y, 5, 10));
//
//
//            }
//
//        }
        loadLevel(0);



        timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // levels
                for (Ball b : theBalls) {
                    b.move(getWidth(), getHeight());
                }
                for (int i = 0; i < click.size(); i++) {
                    Clicker c = click.get(i);

                    c.move(getWidth(), getHeight());

                    if(c.getCounter() > 30){
                        click.remove(i);



                    }
                    if (c.getCounter()>40){
                        if (ballcount <5){
                            System.exit(0);
                        }
                    }
                }


                for (int i = 0; i < theBalls.size(); i++) {
                    Ball b = theBalls.get(i);
                    for (int j = 0; j < click.size(); j++) {
                        Clicker c = click.get(j);
                        if (b.intersects(c)) {
                            theBalls.remove((i));
                            Clicker newClicker = new Clicker(b.getX(), b.getY());
                            click.add(newClicker);

                            j = click.size(); //kill the clicker loop
                            i = theBalls.size();
                            ballcount ++;
                            System.out.println(ballcount);

                        }
                    }
                }

                if (ballcount == 2) {
                    loadLevel(1);
                }


                repaint();


            }
        });
        timer.start();
        System.out.println(ballcount);




        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getX());
                System.out.println(mouseEvent.getY());

                int x = (mouseEvent.getX());
                int y = (mouseEvent.getY());

//                updateClicker(x, y);
                if (countclick < 1) {


                    click.add(new Clicker(x, y));
                    countclick++;
                }


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


        for (Ball j : theBalls) {
            j.draw(g2);


        }
        for (Clicker f : click) {
            f.draw(g2);


        }
//        if (ballcount == 5){
//            level = 1;
//            g2.setBackground(Color.black);
//            for (Ball j : theBalls) {
//                j.draw(g2);
//
//
//            }
//            for (Clicker f : click) {
//                f.draw(g2);
//
//
//            }





        //  you cna code movement in the paint with the ball.move However, is not reccomended to code inside the paint
    }

    public Ball randBall() {
        // makes a random ball at random location, with a random velocity
//        int x = (int)(Math.random()*getWidth()-50);
//        int y = (int)(Math.random()*getHeight()-50);
        int x = getWidth() / 2 - 25;
        int y = getHeight() / 2 - 25;
        int vx = (int) (Math.random() * 21 - 10);
        int vy = (int) (Math.random() * 21 - 10);
        return new Ball(x, y, vx, vy);
    }

    public void loadLevel(int levelNum){
        theBalls.clear();
        if (levelNum == 0){
            for (int i = 0; i < 15; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(new Ball(x, y, 5, 10));



            }
        }
        if (levelNum ==1) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            for (int i = 0; i < 30; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(new Ball(x, y, 5, 10));
            }
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("On Our Way To Viridian City");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBounds(500, 200, 600, 600); //(x, y, w, h)

        MyPanel panel = new MyPanel(600, 600);

        panel.setFocusable(true);

        panel.grabFocus();

        window.add(panel);

        window.setVisible(true);

        File mus = new File("Kahoot.wav");
        PlaySound("Kahoot.wav");



    }
    static void PlaySound(String fileName){//File Sound){
        try{
//            Clip slip = AudioSystem.getClip();
//            slip.open(AudioSystem.getAudioInputStream(Sound));
//            slip.start();
//
//            Thread.sleep(slip.getMicrosecondLength()/1000);
             InputStream in = new FileInputStream(fileName);
             AudioStream ain = new AudioStream(in);
            AudioPlayer.player.start(ain);
            final String name = fileName;

            Timer t = new Timer(40000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
//                    AudioPlayer.player.stop(ain);
                    try {

                        InputStream in = new FileInputStream(name);
                        AudioStream ain = new AudioStream(in);

                        AudioPlayer.player.start(ain);
                    }catch(Exception e){e.printStackTrace();}
                }
            });
            t.start();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}