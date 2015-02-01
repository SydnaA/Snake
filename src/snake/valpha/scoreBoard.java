/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author andysheng
 */
public class scoreBoard {
    public static String currentVersion="2.7.5.7";
    public static int classicScore=0;
    public static int lightningScore=0;
    public static int enduranceScore=0;
    public static int DoubleScore=0;
    public static int inverseScore=0;
    public String version;
    File Win7_savLoc=new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav");
    public static File OLD_Win7_savLoc=new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt");
    public static File OLD_Mac_savLoc=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt");
    
   
    public scoreBoard() throws IOException {
        this.transferData();
        if(this.isMac())
        {
        if((Mac_savLoc.exists())&&Mac_savLoc.canRead()&&Mac_savLoc.canWrite())
        {
          this.loadFile(Mac_savLoc);
        }
        else
        {

          this.Mac_createSav();
        }
        }
        else if(System.getProperty("os.name").toLowerCase().equals("windows 7"))
        {
            if(Win7_savLoc.exists()&&Win7_savLoc.canRead())
            {
                this.loadFile(Win7_savLoc);
            }
            else
            {
                this.Win7_createFile();
            }
            
        }
             
   }
    
    
    
    public void transferData() throws FileNotFoundException, IOException {
        if(this.isMac())
        {
            if((OLD_Mac_savLoc.exists())&&OLD_Mac_savLoc.canRead()&&OLD_Mac_savLoc.canWrite())
             {
                 this.transcription(OLD_Mac_savLoc);
                 this.saveFile();
             }
        }
        else if(System.getProperty("os.name").toLowerCase().equals("windows 7")) {
             if(OLD_Win7_savLoc.exists()&&OLD_Win7_savLoc.canRead())
            {
                this.transcription(OLD_Win7_savLoc);
                this.saveFile();
            }
        }    
    }
    
    public void transcription(File loc) throws FileNotFoundException, IOException {
         Scanner scanner=new Scanner(loc);
         int maxIndx=-1;
        String scores[]=new String[10];
        
        while(scanner.hasNext())
        {
            maxIndx++;
            
            scores[maxIndx]=scanner.nextLine();
        }
        
        scanner.close();
        
        version=scores[0];
        int pVersion=this.processVersion(version);
        if((pVersion<=2757)&&(pVersion>2741))
        {     
             classic=scores[1];
             lightning=scores[2];
             endurance=scores[3];
             Double=scores[4];
             inverse=scores[5];
        }
        else
        { 
            classic=scores[0];
            lightning=scores[1];
            endurance=scores[2];
            Double=scores[3];
            inverse=scores[4];
            
        }
        
         String[] mod=this.scoreChanges(classic, lightning, endurance, Double, inverse);
        classicScore=Integer.parseInt(mod[0]);
        lightningScore=Integer.parseInt(mod[1]);
        enduranceScore=Integer.parseInt(mod[2]);
        DoubleScore=Integer.parseInt(mod[3]);
        inverseScore=Integer.parseInt(mod[4]);
        
        loc.delete();
        
        this.saveFile();
  
         
    }
    
    
    
    public void Win7_createFile() throws IOException {
        if(!(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\").exists()))
        {
            this.Win7_newFolder();
            this.newFile(Win7_savLoc);
        }
        else
        {
             this.newFile(Win7_savLoc);
        }
            
    }

    
    public void newFile(File savLoc) throws IOException {
        file=new FileWriter(savLoc, true);
        PrintWriter output=new PrintWriter(file);
        output.println(currentVersion+"");
        output.println(classicScore+"");
        output.println(lightningScore+"");
        output.println(enduranceScore+"");
        output.println(DoubleScore+"");
        output.println(inverseScore+"");
        output.println(" ");
        output.println(" ");
        output.println(" ");
        output.println(" ");
        output.close();
        file.close();
    }
    public void Win7_newFolder() {
        new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\").mkdir();
    }
    
    public boolean Mac_newFolder() {
        boolean sucess=(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/").mkdir());
        return sucess;
    }
    public File Mac_savLoc=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/save.sav");
    public File savFil=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/");
    public String classic, lightning, endurance, Double, inverse;
    public static FileWriter file;
  
    public void loadFile(File savLoc) throws FileNotFoundException, IOException{
        if(savLoc.equals(Mac_savLoc)) {
           new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/save.sav").renameTo(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt"));
           savLoc=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt");
        } else if(savLoc.equals(Win7_savLoc)) {
            new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav").renameTo(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt"));
            savLoc=new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt");
        }
        Scanner scanner=new Scanner(savLoc);
        int maxIndx=-1;
        String scores[]=new String[10];
        
        while(scanner.hasNext())
        {
            maxIndx++;
            
            scores[maxIndx]=scanner.nextLine();
        }
        
        scanner.close();
        
        version=scores[0];
        int pVersion=this.processVersion(version);
        if((pVersion<=this.processVersion(currentVersion))&&(pVersion>2741))
        {     
             classic=scores[1];
             lightning=scores[2];
             endurance=scores[3];
             Double=scores[4];
             inverse=scores[5];
        }
        else
        { 
            classic=scores[0];
            lightning=scores[1];
            endurance=scores[2];
            Double=scores[3];
            inverse=scores[4];
            
        }

        
        String[] mod=this.scoreChanges(classic, lightning, endurance, Double, inverse);
        classicScore=Integer.parseInt(mod[0]);
        lightningScore=Integer.parseInt(mod[1]);
        enduranceScore=Integer.parseInt(mod[2]);
        DoubleScore=Integer.parseInt(mod[3]);
        inverseScore=Integer.parseInt(mod[4]);
        
        
        if(savLoc.equals(Mac_savLoc)) {
           new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt").renameTo(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/save.sav"));
           //savLoc=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt");
        } else if(savLoc.equals(Win7_savLoc)) {
            if(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav").exists())
            new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav").delete();
            new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt").renameTo(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav"));
           // savLoc=new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt");
        }
        
        //if(!(version.equals(currentVersion))) {
            this.saveFile();
        //}
            
    }
    
    private String[] scoreChanges(String clas, String light, String endur, String doub, String inver) {
        if(Integer.parseInt(light)==2147483647)
                light="6480";
        String[] mod=new String[5];
        mod[0]=clas;
        mod[1]=light;
        mod[2]=endur;
        mod[3]=doub;
        mod[4]=inver;
        
        return mod;
    }
    
    
     
    //File win7_tempSav = new File("c:/program files/ProtoStar Softwares/sav2.txt");
    //File Mac_tempSav=new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav2.txt");
    public static void saveFile() throws IOException {
        FileWriter Sfile=null;
        if((System.getProperty("os.name").toLowerCase()).substring(0, 3).equals("mac")) {
            Sfile=new FileWriter("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt");
        }
        else if((System.getProperty("os.name").toLowerCase()).equals("windows 7")) {
            Sfile=new FileWriter("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt");
        }
        PrintWriter output=new PrintWriter(Sfile);
        output.println(currentVersion+"");
        output.println(classicScore+"");
        output.println(lightningScore+"");
        output.println(enduranceScore+"");
        output.println(DoubleScore+"");
        output.println(inverseScore+"");
        output.println(" ");
        output.println(" ");
        output.println(" ");
        output.println(" ");
        
        output.close();
        Sfile.close();
        if((System.getProperty("os.name").toLowerCase()).substring(0, 3).equals("mac")) {
             new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/sav.txt").renameTo(new File("/Users/"+System.getProperty("user.name")+"/Library/Application Support/ProtoStar Softwares/save.sav"));
        } else if((System.getProperty("os.name").toLowerCase()).equals("windows 7")) {
            new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav").delete();
             new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\sav.txt").renameTo(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\ProtoStar Softwares\\save.sav"));
        }
        
        if(OLD_Mac_savLoc.exists())
            OLD_Mac_savLoc.delete();
        if(OLD_Win7_savLoc.exists())
            OLD_Win7_savLoc.delete();
       
    }
    
    private void Mac_createSav() throws IOException {
        
        if(!savFil.exists())
        {
        this.Mac_newFolder();
        }
        this.newFile(Mac_savLoc);
        
    }
    
    private boolean isMac() {
        String os=System.getProperty("os.name").toLowerCase();
        if("mac".equals(os.substring(0,3)))
        {
         return true;
        }
        return false;
        
    }
    
    private int processVersion(String a) {
        for(int x=0; x<a.length()-1;x++)
        {
            if(a.substring(x,x+1).equals("."))
            {
                a=a.substring(0,x)+a.substring(x+1);
            }
            
        }
        return Integer.parseInt(a);
    }
    
    public static void main(String args[]) throws IOException {
        new scoreBoard();
    }
}
