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
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author andysheng
 */
public class Co_op extends JPanel implements ActionListener {

    public int width = 300;
    public int height = 300;
    private int all_dots = 900;
    public int dotSize = 10;
    private int rand_pos = 29;
    private int rand_pos_B = 28;
    public int delay = 140;
    //private int score=0;
    private int score_A = 0;
    private int score_B = 0;
    private int x_A[] = new int[all_dots];
    private int y_A[] = new int[all_dots];
    private int x_B[] = new int[all_dots];
    private int y_B[] = new int[all_dots];
    private int dots_A;
    private int dots_B;
    private int Xfood_A;
    private int Yfood_A;
    private int Xfood_B;
    private int Yfood_B;
    private boolean left_A = false;
    private boolean right_A = true;
    private boolean up_A = false;
    private boolean down_A = false;
    private boolean inGame = true;
    private boolean left_B = false;
    private boolean right_B = true;
    private boolean up_B = false;
    private boolean down_B = false;
    private Timer timer_A;
    //private Timer timer_B;
    private Image ball_A;
    private Image ball_B;
    private Image food_A;
    private Image food_B;
    private Image head_A;
    private Image head_B;
    private boolean move_A;
    private boolean move_B;

    public Co_op() {
        this.addKeyListener(new TAdapter());
        
        ImageIcon a = new ImageIcon(this.getClass().getResource("food.png"));
        food_A = a.getImage();

        ImageIcon a1 = new ImageIcon(this.getClass().getResource("food_B.png"));
        food_B = a1.getImage();

        ImageIcon b = new ImageIcon(this.getClass().getResource("body.png"));
        ball_A = a.getImage();
        ImageIcon b_b = new ImageIcon(this.getClass().getResource("body_B.png"));
        ball_B = b_b.getImage();

        ImageIcon c_1 = new ImageIcon(this.getClass().getResource("head_UP.png"));
        head_A = a.getImage();

        ImageIcon c_2 = new ImageIcon(this.getClass().getResource("head_B.png"));
        head_B = a.getImage();

        // ImageIcon c_2=new ImageIcon(this.getClass().getResource("head_RIGHT.png"));
        // head_E=a.getImage();

        //ImageIcon c_3=new ImageIcon(this.getClass().getResource("head_BOT.png"));
        //head_S=a.getImage();

        //ImageIcon c_4=new ImageIcon(this.getClass().getResource("head_LEFT.png"));
        //head_W=a.getImage();
        move_A=true;
        move_B=true;

        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.initGame();
        this.initGame_B();

    }

    private void initGame() {
        dots_A = 3;
        for (int z = 0; z < dots_A; z++) {
            x_A[z] = 50 - z * 10;
            y_A[z] = 50;
        }
        locateFood();
        timer_A = new Timer(delay, this);
        timer_A.start();
    }

    private void initGame_B() {
        dots_B = 3;
        for (int z = 0; z < dots_B; z++) {
            x_B[z] = 70 - z * 10;
            y_B[z] = 70;
        }
        locateFood();
        timer_A = new Timer(delay, this);
        timer_A.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {
            g.drawImage(food_A, Xfood_A, Yfood_A, this);
            g.drawImage(food_B, Xfood_B, Yfood_B, this);
            for (int z = 0; z < dots_A; z++) {
                if (z == 0) {
                    g.drawImage(head_A, x_A[z], y_A[z], this);
                } else {
                    g.drawImage(ball_A, x_A[z], y_A[z], this);
                }

            }
            for (int z = 0; z < dots_B; z++) {
                if (z == 0) {
                    g.drawImage(head_B, x_B[z], y_B[z], this);
                } else {
                    g.drawImage(ball_B, x_B[z], y_B[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } else {
            gameOver(g);
        }

        /*
        if(inGame) {
        g.drawImage(food_B, Xfood_B, Yfood_B, this);
        for(int z=0;z<dots_B;z++) {
        if(z==0)
        g.drawImage(head_B, x_B[z], y_B[z], this);
        else g.drawImage(ball_B, x_B[z], y_B[z], this);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        } else {
        gameOver(g);
        }
         *
         */


    }

    public void gameOver(Graphics g) {
        String msg = "Score A: " + score_A + "    Score B: " + score_B;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics met = this.getFontMetrics(small);
        g.setColor(Color.BLUE);
        g.setFont(small);
        g.drawString(msg, (width - met.stringWidth(msg)) / 2, height / 2);



    }

    public void checkFood() {

        if ((x_A[0] == Xfood_A) && (y_A[0] == Yfood_A)) {
            dots_A++;
            locateFood();
            score_A++;
        }

        if ((x_B[0] == Xfood_B) && (y_B[0] == Yfood_B)) {
            dots_B++;
            locateFood();
            score_B++;
        }

    }

    public void move() {
        for (int z = dots_A; z > 0; z--) {
            x_A[z] = x_A[(z - 1)];
            y_A[z] = y_A[(z - 1)];
        }
        
        
        
        if (left_A) {
            x_A[0] -= dotSize;
            move_A=true;
        }
        if (right_A) {
            x_A[0] += dotSize;
            move_A=true;
        }
        if (up_A) {
            y_A[0] -= dotSize;
            move_A=true;
        }
        if (down_A) {
            y_A[0] += dotSize;
            move_A=true;
        }



        for (int z = dots_B; z > 0; z--) {
            x_B[z] = x_B[(z - 1)];
            y_B[z] = y_B[(z - 1)];
        }
        if (left_B) {
            x_B[0] -= dotSize;
            move_B=true;
        }
        if (right_B) {
            x_B[0] += dotSize;
            move_B=true;
        }
        if (up_B) {
            y_B[0] -= dotSize;
            move_B=true;
        }
        if (down_B) {
            y_B[0] += dotSize;
            move_B=true;
        }





    }

    public void checkCollision() {
        for (int z = dots_A; z > 0; z--) {
            if ((z > 4) && (x_A[0] == x_A[z]) && (y_A[0] == y_A[z])) {
                inGame = false;
            }
        }
        if (y_A[0] > height) {
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


        for (int z = dots_B; z > 0; z--) {
            if ((z > 4) && (x_B[0] == x_B[z]) && (y_B[0] == y_B[z])) {
                inGame = false;
            }
        }
        if (y_B[0] > height) {
            inGame = false;
        }
        if (y_B[0] < 0) {
            inGame = false;
        }
        if (x_B[0] > width) {
            inGame = false;
        }
        if (x_B[0] < 0) {
            inGame = false;




        }





    }

    public void backout() {
        this.setVisible(false);
        Multi.invis();
        SnakeVAlpha.appear();

    }

    public void locateFood() {
        int r_A = (int) (Math.random() * rand_pos);
        Xfood_A = ((r_A * dotSize));
        r_A = (int) (Math.random() * rand_pos);
        Yfood_A = ((r_A * dotSize));


        int r_B = (int) (Math.random() * rand_pos_B);
        Xfood_B = ((r_B * dotSize));
        r_B = (int) (Math.random() * rand_pos_B);
        Yfood_B = ((r_B * dotSize));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            checkCollision();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (inGame) {
                if(move_A)
                {
                    if ((key == KeyEvent.VK_LEFT) && (!right_A)) {
                        left_A = true;
                        up_A = false;
                        down_A = false;
                        move_A=false;
                    }
                    if ((key == KeyEvent.VK_RIGHT) && (!left_A)) {
                        right_A = true;
                        up_A = false;
                        down_A = false;
                        move_A=false;
                    }
                    if ((key == KeyEvent.VK_UP) && (!down_A)) {
                        up_A = true;
                        right_A = false;
                        left_A = false;
                        move_A=false;
                    }
                    if ((key == KeyEvent.VK_DOWN) && (!up_A)) {
                        down_A = true;
                        right_A = false;
                        left_A = false;
                        move_A=false;
                    }
                }
                if(move_B)
                {

                    if ((key == KeyEvent.VK_A) && (!right_B)) {
                        left_B = true;
                        up_B = false;
                        down_B = false;
                        move_B=false;
                    }
                    if ((key == KeyEvent.VK_D) && (!left_B)) {
                        right_B = true;
                        up_B = false;
                        down_B = false;
                        move_B=false;
                    }
                    if ((key == KeyEvent.VK_W) && (!down_B)) {
                        up_B = true;
                        right_B = false;
                        left_B = false;
                        move_B=false;
                    }
                    if ((key == KeyEvent.VK_S) && (!up_B)) {
                        down_B = true;
                        right_B = false;
                        left_B = false;
                        move_B=false;
                    }
                }
            } else {
                if (key == KeyEvent.VK_ENTER) {
                    // game=false;
                    backout();
                }
            }



        }
    }
}
