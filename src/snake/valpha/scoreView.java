/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author andysheng
 */
public class scoreView implements ActionListener {
 public static JFrame frame;

    public scoreView() {
        frame = new JFrame();
        JPanel panel=new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        JLabel label_1, label_2, label_3, label_4, label_5;
         
        label_1=new JLabel("Classic Score: "+scoreBoard.classicScore);
        label_1.setBounds(100, 30, 200, 25);
        label_1.setVisible(true);
         
        label_2=new JLabel("Lightning Score: "+scoreBoard.lightningScore);
        label_2.setBounds(100, 70, 200, 50);
        label_2.setVisible(true);
         
        label_3=new JLabel("Endurance Score: "+scoreBoard.enduranceScore);
        label_3.setBounds(100, 120, 200, 50);
        label_3.setVisible(true);
         
        label_4=new JLabel("Double Score: "+scoreBoard.DoubleScore);
        label_4.setBounds(100, 170, 200, 50);
        label_4.setVisible(true);
         
        label_5=new JLabel("cissalC Score: "+scoreBoard.inverseScore);
        label_5.setBounds(100, 220, 200, 50);
        label_5.setVisible(true);
        
        JButton button=new JButton("Back");
        button.setBounds(100, 270, 100, 50);
        button.setVisible(true);
        button.setActionCommand("back");
        button.addActionListener(this);
        
        
        panel.add(button);
        panel.add(label_5);
        panel.add(label_4);
        panel.add(label_3); 
        panel.add(label_2);
        panel.add(label_1);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 340);
        frame.setLocationRelativeTo(null);
        frame.setTitle("ScoreBoard");
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("back".equals(e.getActionCommand()))
        {
            frame.setVisible(false);
            SnakeVAlpha.appear();
        }
    }
    
  

    public static void main(String[] args) {
        // TODO code application logic here
        new scoreView();


    }
}
