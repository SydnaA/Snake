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
public class Multi {
    public static JFrame frame;
    public Multi() {
        frame=new JFrame();
        frame.add(new Co_op());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 340);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Multi");
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void invis() {
        frame.setVisible(false);
    }
    
    
       public static void main(String[] args) {
        // TODO code application logic here
        new Multi();
    }
}
