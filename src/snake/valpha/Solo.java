/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import javax.swing.JFrame;

/**
 *
 * @author andysheng
 */
public class Solo {

    public static JFrame frame;

    public Solo() {
        frame = new JFrame();
        //s=new single();
        frame.add(new single());




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 685);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Solo");
        frame.setResizable(false);
        frame.setVisible(true);



    }

    public static void invis() {
        frame.setVisible(false);
    }
    single s;

    public static void main(String[] args) {
        // TODO code application logic here
        new Solo();


    }
}
