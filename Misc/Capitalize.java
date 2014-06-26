/*************************************************************************
 * Question 1
 *
 *  String Capitalization Program
 *
 *************************************************************************/
import java.io.*;
public class Capitalize {

    public static String printCapitalized(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws IOException {
  		DataInputStream d =new DataInputStream(System.in);
            String line = d.readLine();
            String[] words = line.split("\\s");
            for (String s : words) {
                System.out.print( printCapitalized(s) + " \t\n");
            }
            
     }  
 }
