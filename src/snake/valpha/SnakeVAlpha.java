/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author andysheng
 */
public class SnakeVAlpha implements ActionListener {

    /**
     * @param args the command line arguments
     */
    JButton button_1, button_2, button_3;
    JPanel panel;
    JLabel version;
    public Image snake;
    JOptionPane op;
    JDialog dialog;
  // private JLayeredPane layeredPane;
    
    

    
    
    public SnakeVAlpha() throws IOException {
        //String str=JOptionPane.showInputDialog(null, "Key Code", "", 1);
        //op=new JOptionPane("Key Code", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_OPTION);
        //dialog=new JDialog(frame, "Click", true);
        /*
        if(str==null) {
            JOptionPane.showMessageDialog(null, "Correct", 
"Key", 1);
        }
        else {
        if(str.equals("protostar"))
  JOptionPane.showMessageDialog(null, "Correct", 
"Key", 1);
  else
  JOptionPane.showMessageDialog(null, "You pressed cancel button.", 
"Key", 1);
        }
        
        
        dialog.setContentPane(op);
        * 
        */
        
        
        
//        frame.addKeyListener(new TAdapter());
        new scoreBoard();
        frame=new JFrame("Snake Beta v2.5");
        //layeredPane = new JLayeredPane();
        
        
        //this.setComponentZOrder(this, 1);
        panel=new JPanel();
        panel.setLayout(null);
        version=new JLabel("v"+scoreBoard.currentVersion);
        version.setBounds(250, 290, 100, 30);
        version.setVisible(true);
        panel.add(version);
        
        ImageIcon a=new ImageIcon(this.getClass().getResource("Snake1.png"));
        snake=a.getImage();
        
        
        
        
        JLabel label=new JLabel(a);
        label.setBounds(0, 0, 320, 340);
        label.setVisible(true);
        
        
        button_1=new JButton("Solo");
        button_1.setBounds(100, 110, 100, 25);
        button_1.setVisible(true);
        button_1.setActionCommand("solo");
        button_1.addActionListener(this);
        //panel.setComponentZOrder(panel, 1);
        //panel.setComponentZOrder(button_1, 0);
        panel.add(button_1);

        button_2=new JButton("Co-op");
        button_2.setBounds(100, 190, 100, 25);
        button_2.setVisible(true);
        button_2.setActionCommand("Co-op");
        button_2.addActionListener(this);
        panel.add(button_2);
        
        button_3=new JButton("Scoreboard");
        button_3.setBounds(100, 270, 110, 25);
        button_3.setVisible(true);
        button_3.setActionCommand("scoreBoard");
        button_3.addActionListener(this);
        panel.add(button_3);
        
        

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 340);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.setVisible(true);
        
        //newGame();
        panel.setVisible(true);
        panel.add(label);
        
        
        
       // mul.setVisible(false);
        
        
    }
    
    
    
    /*
      
    @Override
public void paint(Graphics g) {
             g.drawImage(snake, 0, 0, this);
}
    */
    
    public void isDone() {
        frame.setVisible(true);
    }
    
    Multi mul;
    modeSelection mode;
    scoreView scoreboard;
    
    public static JFrame frame;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
     
        if("Co-op".equals(e.getActionCommand())) {
           frame.setVisible(false);
            mul=new Multi();
        }
        if("solo".equals(e.getActionCommand())) {
            frame.setVisible(false);
            mode=new modeSelection();  
        }
        if("scoreBoard".equals(e.getActionCommand())) {
            frame.setVisible(false);
            scoreboard=new scoreView();

        }
    }
    
    
    public static void appear() {
        frame.setVisible(true);
    }

    
    
    /*
    public void newCo() {
        
        // Co_op a=new Co_op();
         frame.getContentPane().add(new Co_op());
         //this.getContentPane().add(panel);
         
         
         
    }
    /*
     private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            

        }
    }
    * 
    */

    
    
    
   
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        Thread.sleep(3000);
        SnakeVAlpha snake=new SnakeVAlpha();
        
        
    }
}
