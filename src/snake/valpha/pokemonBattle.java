/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andysheng
 */
public class pokemonBattle {
    public static final int PORTNUM=1123;
    private ServerSocket serverSock;
    private Socket sock;
    public pokemonBattle() {
        
    }
    public void host() {
        try {
            serverSock=new ServerSocket(PORTNUM);
            sock=serverSock.accept();
        } catch (IOException ex) {
            Logger.getLogger(pokemonBattle.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
    
    }
}
