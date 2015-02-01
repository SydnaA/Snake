/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Sydna Agnehs
 */
public class check {
    
    public static File mac_checkLoc=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sys.exe");
    public check() {
        
    }
    
    public static boolean checkExist() {
        
        if(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sys.exe").exists()) {
             
            return true;
             
        }
           
        return false;
               
    }
    
    public static boolean checkWord() throws FileNotFoundException {
        mac_checkLoc.renameTo(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sys.txt"));
        Scanner scanner=new Scanner(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sys.txt"));
        
        int maxIndx=-1;
        String code="";
        while(scanner.hasNext())
        {
            maxIndx++;
            
            code=scanner.nextLine();
        }
        
        scanner.close();
        
        if(code.equals("proto   ")) {
            return true;
        }
        
        return false;
    }
}
