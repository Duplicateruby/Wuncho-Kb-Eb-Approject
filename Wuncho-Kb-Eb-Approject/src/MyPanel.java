import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private int target;


    private Timer timer;

    public MyPanel(int w0, int h0) {
        setSize(w0, h0);
        // Create the array list Object and add 10 ball objects to it
        theBalls = new ArrayList<Ball>();
        click = new ArrayList<Clicker>();
        countclick = 0;
        level = 0;
        ballcount = 0;
        target=0;



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
        loadLevel();



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

                    if(c.getCounter() > 50){
                        click.remove(i);



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

                if (ballcount >= target) {

                    loadLevel();
                }





                repaint();


            }
        });
        timer.start();
        System.out.println(ballcount);




        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {



            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
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
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                int code = keyEvent.getKeyChar();
                if (code== 'r'){
                    level = 0;
                    loadLevel();
                    countclick =0;
                    ballcount=0;

                    level=1;
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

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
        g2.setColor(Color.white);
        g2.drawString("Level = "+level,41 ,17);
        g2.drawString("Press r To Reset",460,560);
        if (level==8){
            g2.drawString("You Win",300,300);
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
        while(vx == 0 || vy == 0){
             vx = (int) (Math.random() * 21 - 10);
             vy = (int) (Math.random() * 21 - 10);
        }
        return new Ball(x, y, vx , vy);
    }

    public void loadLevel(){
        theBalls.clear();
        if (level == 0){
            for (int i = 0; i < 10; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
                target = 2;



            }
        }
        if (level ==1) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target = 7;
            for (int i = 0; i < 15; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if (level ==2) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target=15;
            for (int i = 0; i < 30; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if (level ==3) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target= 20;
            for (int i = 0; i < 35; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if (level ==4) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target=24;
            for (int i = 0; i < 40; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if (level ==5) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target=32;
            for (int i = 0; i < 45; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if (level ==6) {
            theBalls.clear();
            click.clear();
            ballcount = 0;
            countclick -- ;
            target = 42;
            for (int i = 0; i < 50; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                theBalls.add(randBall());
            }
        }
        if(level==9){
            System.exit(0);
        }

        level++;
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