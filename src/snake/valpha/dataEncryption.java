/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.valpha;

/**
 *
 * @author andysheng
 */

public class dataEncryption {
    public String encrypt(String a[]) {
        for(int x=0;x<a.length;x++) {
            score[x]=Integer.parseInt(a[x]);
            sum+=score[x];
        }
        sum=Math.pow((sum*7), 2);
        sum+=(System.getProperty("user.name").charAt(0));
        return sum+"";
    }
    private int[] score=new int[6];
    private double sum;
    public String decrypt(String a[]) {
        
        for(int x=0;x<a.length;x++) {
            score[x]=Integer.parseInt(a[x]);
            sum+=score[x];
        }
         sum=Math.pow((sum*7), 2);
        
        String c="";
        return c;
    }
    
    public boolean security() {
        return true;
    }
}
