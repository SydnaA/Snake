/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author andysheng
 */
public class single extends JPanel implements ActionListener {

    public int width = 600;
    public int height = 650;
    private int all_dots = 900;
    public int dotSize = 20;
    private int rand_pos = 29;
    public int delay = 140;
    private int x_A[] = new int[all_dots];
    private int y_A[] = new int[all_dots];
    //private int x_B[]=new int[all_dots];
    //private int y_B[]=new int[all_dots];
    private int dots_A;
    //private int dots_B;
    private int Xfood_A;
    private int Yfood_A;
    private int Xobs[] = new int[5];
    private int Yobs[] = new int[5];
    //private int Xfood_B;
    //private int Yfood_B;
    private boolean left_A = false;
    private boolean right_A = true;
    private boolean up_A = false;
    private boolean down_A = false;
    private boolean inGame = true;
    public static Timer timer;
    private Image ball;
    private Image food;
    private Image head_AE;
    private Image head_AN;
    private Image head_AW;
    private Image head_AS;
    private Image powerFrame;
    private Image obstacle;
    private Image background;
    private Image pow;
    private Image heart;
    private boolean hasPow;
    private boolean isInv;
    private boolean invOnIter2 = false;
    private int invTimer;
    private int Xpow;
    private int Ypow;
    private int health;
    private String powType;
    private int direction; //1: E, 2: N, 3: W, 4: S
    //private Image head_B;
    //private Image head_E;
    //private Image head_W;
    //private boolean moved=false;
    public static int speed;
    public static String gameMode;
    //public static String speedIncrement;
    //public static String growthIncrement;
    //public static String scoreIncrement;
    public static int score;
    private String scoreString;
    public static String movement="normal";
    private boolean move;
    public int normalSpeed=140;
    public int fastSpeed=12;
    private boolean paused=false;
    JLabel text;
    private String highScore;
    public static JLabel label;
    public boolean game = true;
    private int combo = 0;
    private String comboString = "0";
    private boolean debugD;
    private boolean debugE;
    private boolean debugB;
    private boolean debugMode;

    public single() {
        this.setLayout(null);
        label=new JLabel("Paused");
        label.setBounds((int)(width/2), (int)(height/2), 100, 25);
        label.setVisible(false);
        this.add(label);
        score=0;
        scoreString = "0";
        addKeyListener(new TAdapter());
        text = new JLabel("Hit \"enter\" to continue...");
        text.setBounds(190, 100, 200, 50);
        text.setVisible(false);
        this.add(text);
        

        ImageIcon a = new ImageIcon(this.getClass().getResource("New_food.png"));
        food = a.getImage();

        ImageIcon c_1 = new ImageIcon(this.getClass().getResource("New_head_E.png"));
        head_AE = c_1.getImage();
        move=true;
        
        ImageIcon c_2 = new ImageIcon(this.getClass().getResource("New_head_N.png"));
        head_AN = c_2.getImage();
        move=true;
        
        ImageIcon c_3 = new ImageIcon(this.getClass().getResource("New_head_W.png"));
        head_AW = c_3.getImage();
        move=true;
        
        ImageIcon c_4 = new ImageIcon(this.getClass().getResource("New_head_S.png"));
        head_AS = c_4.getImage();
        move=true;
        
        ImageIcon f = new ImageIcon(this.getClass().getResource("powerFrame.png"));
        powerFrame = f.getImage();
        
        ImageIcon h = new ImageIcon(this.getClass().getResource("heart.png"));
        heart = h.getImage();
        
        ImageIcon o = new ImageIcon(this.getClass().getResource("obs.png"));
        obstacle = o.getImage();
        
        ImageIcon bg = new ImageIcon(this.getClass().getResource("background.png"));
        background = bg.getImage();
        
        
        setBackground(Color.WHITE);
        setFocusable(true);
        initGame();
        //initGame_B();

    }

    private void initGame() {
        dots_A = 3;
        direction = 1;
        health = 2;
        hasPow = false;
        if(gameMode.equals("normal")) {
            highScore=Integer.toString(scoreBoard.classicScore);
        } else if(gameMode.equals("lightning")) {
            highScore=Integer.toString(scoreBoard.lightningScore);
        } else if(gameMode.equals("endurance")) {
            highScore=Integer.toString(scoreBoard.enduranceScore);
        } else if(gameMode.equals("double")) {
            highScore=Integer.toString(scoreBoard.DoubleScore);
        } else if(gameMode.equals("inverse")) {
            highScore=Integer.toString(scoreBoard.inverseScore);
        }
        for (int z = 0; z < dots_A; z++) {
            x_A[z] = 60 - z * 10;
            y_A[z] = 60;
        }
        if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance")){
            locateObs();
        }
        locateFood();
        if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance") || gameMode.equals("lightning")){
            locatePow();
        }

        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {
            g.drawImage(background,0 ,0 , this);
            g.drawImage(powerFrame, 0, 600, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Consolas", Font.BOLD, 22));
            g.drawString(scoreString, 140, 635);
            g.drawString(highScore, 188, 658);
            g.drawImage(food, Xfood_A, Yfood_A, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas", Font.BOLD, 40));
            comboString = Integer.toString(combo);
            if (combo/10 < 1) {
                comboString = "0" + comboString;
            }
            g.drawString(comboString, 11, 647);
            
            if (health >= 2) {
                g.drawImage(heart, 522, 612, this);
                g.drawImage(heart, 584, 612, this);
            } else if (health == 1) {
                g.drawImage(heart, 522, 612, this);
            }
            
            if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance")){
                g.drawImage(obstacle, Xobs[0], Yobs[0], this);
                g.drawImage(obstacle, Xobs[1], Yobs[1], this);
                g.drawImage(obstacle, Xobs[2], Yobs[2], this);
                g.drawImage(obstacle, Xobs[3], Yobs[3], this);
                g.drawImage(obstacle, Xobs[4], Yobs[4], this);
            }
            if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance") || gameMode.equals("lightning")){
                if (hasPow == true) {
                    ImageIcon scP = new ImageIcon(this.getClass().getResource("scorePow.png"));
                    ImageIcon sc2P = new ImageIcon(this.getClass().getResource("score2Pow.png"));
                    ImageIcon spP = new ImageIcon(this.getClass().getResource("spdPow.png"));
                    ImageIcon lfP = new ImageIcon(this.getClass().getResource("lifePow.png"));
                    ImageIcon invP = new ImageIcon(this.getClass().getResource("invPow1.png"));
                    ImageIcon invP2 = new ImageIcon(this.getClass().getResource("invPow2.png"));

                    if (powType.equals("inv") && invOnIter2 == false) {
                        pow = invP.getImage();
                        invOnIter2 = true;
                    } else if (powType.equals("inv") && invOnIter2 == true) {
                        pow = invP2.getImage();
                        invOnIter2 = false;
                    } else if (powType.equals("spd")) {
                        pow = spP.getImage();
                    } else if (powType.equals("life")) {
                        pow = lfP.getImage();
                    } else if (powType.equals("score")) {
                        pow = scP.getImage();
                    } else if (powType.equals("score2")) {
                        pow = sc2P.getImage();
                    }
                    g.drawImage(pow, Xpow, Ypow, this);
                }
            }
            for (int z = 0; z < dots_A; z++) {
                if (z == 0 && direction == 1) {
                    g.drawImage(head_AE, x_A[z], y_A[z], this);
                } else if (z == 0 && direction == 2) {
                    g.drawImage(head_AN, x_A[z], y_A[z], this);
                } else if (z == 0 && direction == 3) {
                    g.drawImage(head_AW, x_A[z], y_A[z], this);
                } else if (z == 0 && direction == 4) {
                    g.drawImage(head_AS, x_A[z], y_A[z], this);
                } else {
                    ImageIcon invB = new ImageIcon(this.getClass().getResource("body_AInv1.png"));
                    ImageIcon invB2 = new ImageIcon(this.getClass().getResource("body_AInv2.png"));
                    ImageIcon b = new ImageIcon(this.getClass().getResource("New_body_A.png"));
                    if (isInv) {
                        if (invOnIter2) {
                            ball = invB2.getImage();
                            invOnIter2 = false;
                        } else {
                            ball = invB.getImage();
                            invOnIter2 = true;
                        }
                    } else {
                        ball = b.getImage();
                    }
                    g.drawImage(ball, x_A[z], y_A[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } else {
            try {
                gameOver(g);
                
            } catch (IOException ex) {
                Logger.getLogger(single.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }

    public void gameOver(Graphics g) throws IOException {
        String msg = "Score: " + score;
        int gameScore=0;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics met = this.getFontMetrics(small);
        boolean newHigh=false;
        if(gameMode.equals("normal")) {
            gameScore=scoreBoard.classicScore;
        if(score>scoreBoard.classicScore)
            {
                scoreBoard.classicScore=score;
                newHigh=true;
            }
        } else if(gameMode.equals("lightning"))
        {
            gameScore=scoreBoard.lightningScore;
            if(score>scoreBoard.lightningScore)
            {
                scoreBoard.lightningScore=score;
                newHigh=true;
            }
        } else if(gameMode.equals("endurance")) {
            gameScore=scoreBoard.enduranceScore;
            if(score>scoreBoard.enduranceScore)
            {
                scoreBoard.enduranceScore=score;
                newHigh=true;
            }
        } else if(gameMode.equals("double")) {
            gameScore=scoreBoard.DoubleScore;
            if(score>scoreBoard.DoubleScore)
            {
                scoreBoard.DoubleScore=score;
                newHigh=true;
            }
        } else if(gameMode.equals("inverse")) {
            gameScore=scoreBoard.inverseScore;
            if(score>scoreBoard.inverseScore) {
                scoreBoard.inverseScore=score;
                newHigh=true;
            }
        }
        String high="NEW HighScore!!!";
        
        
        String msg2="HighScore: "+gameScore;
  
        
        g.setColor(Color.BLUE);
        g.setFont(small);
        g.drawString(msg, ((width - met.stringWidth(msg))/2), (height/2)-20);
        /*
        if(newHigh) {
            g.drawString(high, ((width - met.stringWidth(high))/2), (height/2)-50);
        }
         */
        g.drawString(msg2, ((width - met.stringWidth(msg2))/2), (height/2)+10);
        scoreBoard.saveFile();
        text.setVisible(true);

    }

    public boolean backout() {
        Solo.invis();
        SnakeVAlpha.appear();
        return game;
    }

    public static void appear() {
        Solo.frame.setVisible(true);
    }
    
    public static void hid() {
        Solo.frame.setVisible(false);
        single.pause();
    }

    public void checkFood() {

        if ((x_A[0] == Xfood_A) && (y_A[0] == Yfood_A)) {
            if(gameMode.equals("normal"))
            {
                dots_A += 1;
                timer.setDelay((int)(timer.getDelay()*0.95));
                score += 1000 + (10*dots_A);
                scoreString = Integer.toString(score);
                combo++;
            } else if(gameMode.equals("lightning")) {
                dots_A += 1;
                if((timer.getDelay())==3||(timer.getDelay())==2)
                {
                    timer.setDelay(timer.getDelay()-1);
                }
                timer.setDelay((int)(timer.getDelay()*0.5));
                score += 1000 + (10*(Math.pow(2.5, dots_A)));
                scoreString = Integer.toString(score);
                combo++;
            } else if(gameMode.equals("endurance")) {
                dots_A+=5;
                score += 800 + (5*dots_A);
                scoreString = Integer.toString(score);
                combo++;
            } else if(gameMode.equals("double")) {
                dots_A*=2;
                timer.setDelay((int)(timer.getDelay()*0.75));
                if(score<=1)
                    score++;
                else
                score = (int)(1.4*((int)Math.pow(score, 2)));
                scoreString = Integer.toString(score);
                combo++;
            } else if(gameMode.equals("inverse")) {
                dots_A += 1;
                timer.setDelay((int)(timer.getDelay()*0.95));
                score += 1000 + (10*dots_A);
                scoreString = Integer.toString(score);
                combo++;
            }   
            locateFood();
        }
    }
    public void checkPow() {
        if ((x_A[0] == Xpow) && (y_A[0] == Ypow)) {
            if (powType.equals("inv")) {
                isInv = true;
            } else if (powType.equals("score")) {
                score += 5000;
                scoreString = Integer.toString(score);
            } else if (powType.equals("score2")) {
                score += 10000;
                scoreString = Integer.toString(score);
            } else if (powType.equals("spd")) {
                timer.setDelay((int)(timer.getDelay()*1.5));
            } else if (powType.equals("life")) {
                health++;
            }
            hasPow = false;
            locatePow();
        }
    }
    
    public static void pause() {
        label.setVisible(true);
        
        timer.stop();
    }
    public static void unpause() {
        label.setVisible(false);
        timer.start();
    }

    public void move() {
        for (int z = dots_A; z > 0; z--) {
            x_A[z] = x_A[(z - 1)];
            y_A[z] = y_A[(z - 1)];
        }
        if (left_A) {
            x_A[0] -= dotSize;
            move=true;
        }
        if (right_A) {
            x_A[0] += dotSize;
            move=true;
        }
        if (up_A) {
            y_A[0] -= dotSize;
            move=true;
        }
        if (down_A) {
            y_A[0] += dotSize;
            move=true;
        }

    }

    public void checkCollision() {
        if (!isInv && health == 0) {
            for (int z = dots_A; z > 0; z--) {
                if ((z > 4) && (x_A[0] == x_A[z]) && (y_A[0] == y_A[z])) {
                    if (gameMode.equals("normal") || gameMode.equals("endurance") || gameMode.equals("inverse")) {
                        dots_A = dots_A - (dots_A - z);
                        combo = 0;
                        timer.setDelay((int)(timer.getDelay()*0.75));
                    } else if (gameMode.equals("lightning")){
                        dots_A = dots_A - (dots_A - z);
                        combo = 0;
                    } else {
                    inGame = false;
                    }
                }
            }
            if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance")) {
                    for (int y = 4; y >= 0; y--) {
                        if ((x_A[0] == Xobs[y]) && (y_A[0] == Yobs[y])) {
                            inGame = false;
                        }
                    }
            }
            
            if (y_A[0] > height - 55) {
                inGame = false;
            }
            if (y_A[0] < 0) {
                inGame = false;
            }
            if (x_A[0] > width) {
                inGame = false;
            }
            if (x_A[0] < 0) {
                inGame = false;
            }

        /*
        for(int z=dots_B;z>0;z--) {
        if((z>4)&&(x_B[0]==x_B[z])&&(y_B[0]==y_B[z])) {
        inGame=false;
        }
        }
        if(y_B[0]>height) {
        inGame=false;
        }
        if(y_B[0]<0) {
        inGame=false;
        }
        if(x_B[0]>width) {
        inGame=false;
        }
        if(x_B[0]<0) {
        inGame=false;
        }
         */

        } else if (isInv) {
            if (y_A[0] > height - 75) {
                y_A[0] = 0;
            }
            if (y_A[0] < 0) {
                y_A[0] = height - 75;
            }
            if (x_A[0] > width) {
                x_A[0] = 0;
            }
            if (x_A[0] < 0) {
                x_A[0] = width;
            }
            
        } else if (health > 0) {
            for (int z = dots_A; z > 0; z--) {
                if ((z > 4) && (x_A[0] == x_A[z]) && (y_A[0] == y_A[z])) {
                    if (gameMode.equals("normal") || gameMode.equals("endurance") || gameMode.equals("inverse")) {
                        dots_A = dots_A - (dots_A - z);
                        timer.setDelay((int)(timer.getDelay()*0.75));
                        combo = 0;
                    } else if (gameMode.equals("lightning")){
                        dots_A = dots_A - (dots_A - z);
                        combo = 0;
                    } else {
                    health--;
                    combo = 0;
                    }
                }
            }
            if (gameMode.equals("normal") || gameMode.equals("inverse") || gameMode.equals("endurance")) {
                    for (int y = 4; y >= 0; y--) {
                        if ((x_A[0] == Xobs[y]) && (y_A[0] == Yobs[y])) {
                            health--;
                            combo = 0;
                        }
                    }
            }
            
            if (y_A[0] > height - 55) {
                y_A[0] = 0;
                health--;
                combo = 0;
            }
            if (y_A[0] < 0) {
                y_A[0] = height - 75;
                health--;
                combo = 0;
            }
            if (x_A[0] > width) {
                x_A[0] = 0;
                health--;
                combo = 0;
            }
            if (x_A[0] < 0) {
                x_A[0] = width;
                health--;
                combo = 0;
            }
        }
    }

    public void locateFood() {
        int r_A = (int) (Math.random() * rand_pos);   
        if (r_A < 15) {
           r_A++;
        }
        else if (r_A > 15) {
        r_A--;
         }
        Xfood_A = ((r_A * dotSize));
        r_A = (int) (Math.random() * rand_pos);
        if (r_A < 15) {
           r_A++;
        }
        else if (r_A > 15) {
        r_A--;
         }
        Yfood_A = ((r_A * dotSize));
        if (Xfood_A == Xobs[0] && Yfood_A == Yobs[0]) {
            Xfood_A+=20;
            Yfood_A+=20;
        } else if (Xfood_A == Xobs[1] && Yfood_A == Yobs[1]) {
            Xfood_A+=20;
            Yfood_A+=20;
        } else if (Xfood_A == Xobs[2] && Yfood_A == Yobs[2]) {
            Xfood_A+=20;
            Yfood_A+=20;
        } else if (Xfood_A == Xobs[3] && Yfood_A == Yobs[3]) {
            Xfood_A+=20;
            Yfood_A+=20;
        } else if (Xfood_A == Xobs[4] && Yfood_A == Yobs[4]) {
            Xfood_A+=20;
            Yfood_A+=20;
        }
    }
    
    public void locateObs() {
        int r_A = (int) (Math.random() * rand_pos);
        Xobs[0] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Xobs[1] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Xobs[2] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Xobs[3] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Xobs[4] = r_A* dotSize;
                
        r_A = (int) (Math.random() * rand_pos);
        Yobs[0] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Yobs[1] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Yobs[2] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Yobs[3] = r_A* dotSize;
        r_A = (int) (Math.random() * rand_pos);
        Yobs[4] = r_A* dotSize;
    }
    
    public void locatePow() {
        double powRand = Math.random();
        double powRandC;
        if (combo > 0) {
            powRandC = Math.pow(powRand, 1/(combo*0.17));
        } else {
            powRandC = powRand;
        }
        if (powRandC  > 0.995) {
            int r_A = (int) (Math.random() * rand_pos);
            Xpow = r_A* dotSize;
            r_A = (int) (Math.random() * rand_pos);
            Ypow = r_A* dotSize;
            powRand = (Math.random());
            if (powRand > 0.95) {
                powType = "inv";
            } else if (powRand > 0.85) {
                powType = "score2";
            } else if (powRand > 0.7) {
                powType = "life";
            } else if (powRand > 0.45) {
                powType = "spd";
            } else {
                powType = "score";
            }
            if (Xpow == Xobs[0] && Ypow == Yobs[0]) {
                Xpow+=20;
                Ypow+=20;
            } else if (Xpow == Xobs[1] && Ypow == Yobs[1]) {
                Xpow+=20;
                Ypow+=20;
            } else if (Xpow == Xobs[2] && Ypow == Yobs[2]) {
                Xpow+=20;
                Ypow+=20;
            } else if (Xpow == Xobs[3] && Ypow == Yobs[3]) {
                Xpow+=20;
                Ypow+=20;
            } else if (Xpow == Xobs[4] && Ypow == Yobs[4]) {
                Xpow+=20;
                Ypow+=20;
            }
            hasPow = true;
        } else {
            Xpow = 700;
            Ypow = 700;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            checkCollision();
            checkPow();
            if (!hasPow) {
                locatePow(); 
            }
            move();
        }
        repaint();
        if (isInv && !debugMode){
            invTimer++;
        }
        if (invTimer > 30) {
            isInv = false;
            invTimer = 0;
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (inGame) {
                if(key==KeyEvent.VK_P) {
                    if(paused==false) {
                        single.pause();
                        paused=true;
                    } else {
                        single.unpause();
                        paused=false;
                    }
                }
                
                if (key == KeyEvent.VK_C){
                    debugMode = true;
                }
                if(debugMode) {
                    if (key == KeyEvent.VK_X)
                        score += 5000;
                        scoreString = Integer.toString(score);
                    if (key == KeyEvent.VK_L)
                        dots_A++;
                    if (key == KeyEvent.VK_H)
                        health++;
                    if (key == KeyEvent.VK_V)
                        timer.setDelay((int)(timer.getDelay()*0.5));
                    if (key == KeyEvent.VK_B)
                        timer.setDelay((int)(timer.getDelay()*1.5)); 
                }
                
                if(move) {
                    if((movement.equals("inverse"))) {
                    
                            if ((key == KeyEvent.VK_LEFT) && (!left_A)) {
                                left_A = false;
                                right_A=true;
                                up_A = false;
                                down_A = false;
                                direction = 1;
                            }
                            if ((key == KeyEvent.VK_RIGHT) && (!right_A)) {
                                right_A = false;
                                left_A=true;
                                up_A = false;
                                down_A = false;
                                direction = 3;
                            }
                            if ((key == KeyEvent.VK_UP) && (!up_A)) {
                                up_A = false;
                                down_A=true;
                                right_A = false;
                                left_A = false;
                                direction = 4;
                            }
                            if ((key == KeyEvent.VK_DOWN) && (!down_A)) {
                                down_A = false;
                                up_A=true;
                                right_A = false;
                                left_A = false;
                                direction = 2;
                            }
                      
                    }
                    else
                    {
                        if ((key == KeyEvent.VK_LEFT) && (!right_A)) {
                            left_A = true;
                            up_A = false;
                            down_A = false;
                            direction = 3;
                        }
                        if ((key == KeyEvent.VK_RIGHT) && (!left_A)) {
                            right_A = true;
                            up_A = false;
                            down_A = false;
                            direction = 1;
                        }
                        if ((key == KeyEvent.VK_UP) && (!down_A)) {
                            up_A = true;
                            right_A = false;
                            left_A = false;
                            direction = 2;
                        }
                        if ((key == KeyEvent.VK_DOWN) && (!up_A)) {
                            down_A = true;
                            right_A = false;
                            left_A = false;
                            direction = 4;
                        }
                    }
                move=false;
                }
            } else {
                if (key == KeyEvent.VK_ENTER) {
                    game = false;
                    backout();
                }
            }




        }
    }
}
