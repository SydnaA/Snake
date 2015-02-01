/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andysheng
 */
public class splashScreen {
   ;
    public splashScreen() throws NullPointerException, IOException {
        final SplashScreen splash = SplashScreen.getSplashScreen();
       splash.setImageURL(this.getClass().getResource("ProtoStar.gif"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(splashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
       splash.close();  
       
       
        
    }
 

    
    public static void main(String args[]) throws NullPointerException, IOException {
        new splashScreen();
    }
}
