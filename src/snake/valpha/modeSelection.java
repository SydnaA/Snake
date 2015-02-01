/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author andysheng
 */
public class modeSelection implements ActionListener {
    public static JFrame frame;
    JPanel panel;
    private JButton button1, button2, button3, button4, button5, button6, button1Info, button2Info, button3Info, button4Info, button5Info;
    
    
    public modeSelection() {
        panel=new JPanel();
        panel.setLayout(null);
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 450);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mode Selection");
        
        button1=new JButton("Classic");
        button1.setBounds(77, 50, 100, 40);
        button1.setVisible(true);
        button1.addActionListener(this);
        button1.setActionCommand("normal");
        
        button1Info=new JButton("?");
        button1Info.setBounds(183, 50, 40, 40);
        button1Info.setVisible(true);
        button1Info.addActionListener(this);
        button1Info.setActionCommand("normalInfo");
        
        button2=new JButton("Lightning");
        button2.setBounds(77, 100, 100, 40);
        button2.setVisible(true);
        button2.addActionListener(this);
        button2.setActionCommand("lightning");
        
        button2Info=new JButton("?");
        button2Info.setBounds(183, 100, 40, 40);
        button2Info.setVisible(true);
        button2Info.addActionListener(this);
        button2Info.setActionCommand("lightningInfo");
        
        button3=new JButton("Endurance");
        button3.setBounds(77, 150, 100, 40);
        button3.setVisible(true);
        button3.addActionListener(this);
        button3.setActionCommand("endurance");
        
        button3Info=new JButton("?");
        button3Info.setBounds(183, 150, 40, 40);
        button3Info.setVisible(true);
        button3Info.addActionListener(this);
        button3Info.setActionCommand("enduranceInfo");
        
        button4=new JButton("Double");
        button4.setBounds(77, 200, 100, 40);
        button4.setVisible(true);
        button4.addActionListener(this);
        button4.setActionCommand("double");
        
        button4Info=new JButton("?");
        button4Info.setBounds(183, 200, 40, 40);
        button4Info.setVisible(true);
        button4Info.addActionListener(this);
        button4Info.setActionCommand("doubleInfo");
        
        button5=new JButton("cissalC");
        button5.setBounds(77, 250, 100, 40);
        button5.setVisible(true);
        button5.addActionListener(this);
        button5.setActionCommand("inverse");
        
        button5Info=new JButton("?");
        button5Info.setBounds(183, 250, 40, 40);
        button5Info.setVisible(true);
        button5Info.addActionListener(this);
        button5Info.setActionCommand("inverseInfo");
        
        button6=new JButton("Back to Menu");
        button6.setBounds(77, 300, 100, 40);
        button6.setVisible(true);
        button6.addActionListener(this);
        button6.setActionCommand("back");
        
        panel.add(button6);
        panel.add(button5);
        panel.add(button4);
        panel.add(button3);
        panel.add(button2);
        panel.add(button1);
        panel.add(button1Info);
        panel.add(button2Info);
        panel.add(button3Info);
        panel.add(button4Info);
        panel.add(button5Info);
        panel.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if("normal".equals(e.getActionCommand()))
        {
            /*
            single.speedIncrement="(int)(timer.getDelay()*0.95)";
            single.growthIncrement="3";
            single.scoreIncrement="1";
             */
            single.gameMode="normal";
            frame.setVisible(false);
            single.movement="normal";
            new Solo();
        } else if("lightning".equals(e.getActionCommand())) {
            single.gameMode="lightning";
            frame.setVisible(false);
            single.movement="normal";
            new Solo();
            
        } else if("endurance".equals(e.getActionCommand())) {
            single.gameMode="endurance";
            frame.setVisible(false);
            single.movement="normal";
            new Solo();
        } else if("double".equals(e.getActionCommand())) {
            single.gameMode="double";
            single.movement="normal";
            frame.setVisible(false);
            new Solo();
        } else if("inverse".equals(e.getActionCommand())) {
            single.gameMode="inverse";
            single.movement="inverse";
            frame.setVisible(false);
            new Solo();
        } else if ("back".equals(e.getActionCommand())) {
            frame.setVisible(false);
            SnakeVAlpha.appear();
            frame = null;
        } else if ("normalInfo".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "The classic snake! You'll increase in length and speed"
                    + " as you collect food. \n If you eat yourself, you'll be cut short, "
                    + "and the lost weight lets you move faster. \n"
                    + "Careful- Don't crash into walls!", "Classic Mode Info", JOptionPane.PLAIN_MESSAGE);
        } else if ("lightningInfo".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "Lightning mode lets you play a blitz of a game- your speed "
                    + "is DOUBLED with every food pellet! \n If you find yourself running out of space "
                    + "to maneuver, you can always eat yourself. \n There is no speed penalty in this mode!", "Lightning Mode Info", JOptionPane.PLAIN_MESSAGE);
        } else if ("enduranceInfo".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "This mode is going to last you a while. \n You get a whopping +5"
                    + " for every food pellet, which means that you're going to get pretty long. \n Don't worry though, for "
                    + "the only way to gain speed in this mode "
                    + "is to eat yourself. ", "Endurance Mode Info", JOptionPane.PLAIN_MESSAGE);
        } else if ("doubleInfo".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "If you like a good challenge, this mode is for YOU! \n"
                    + "Both your length and speed are DOUBLED for every pellet, AND you're not allowed to"
                    + "eat yourself! \n You'll still get a whole bunch of points, though, because your score is "
                    + "SQUARED for every food pellet.", "Double Mode Info", JOptionPane.PLAIN_MESSAGE);
        } else if ("inverseInfo".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "Exactly like the good old classic mode, but with an extra twist- \n"
                    + "your controls are inversed! ", "cissalC Mode Info", JOptionPane.PLAIN_MESSAGE);
        }
    }
        
    public static void main(String args[]) {
        new modeSelection();
    }
}