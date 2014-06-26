import java.net.*;
import java.io.*;
public class EchoClient {
    public static void main(String [] args) {
        Socket theSocket;
        String hostName;
        BufferedReader fromSocket;
        BufferedReader userInput;
        PrintWriter toSocket;
        String aLine;
        try {
            if(args.length > 0) {
                hostName = args[0];
            }
            else {
                hostName = "localhost";
            }
            theSocket = new Socket(hostName, 1001);  // echo server on 7
            fromSocket = new BufferedReader(
                            new InputStreamReader(
                                    theSocket.getInputStream()));
            toSocket = new PrintWriter(theSocket.getOutputStream());
            userInput = new BufferedReader(
                            new InputStreamReader(System.in));
            while(true) {
                aLine = userInput.readLine();
                if(aLine.equals(".")) break;
                toSocket.println(aLine);
                toSocket.flush();   // NB!
                System.out.println(fromSocket.readLine());
            }
            toSocket.close();
            fromSocket.close();
        }
        catch (UnknownHostException e) {
            System.err.println(e);
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

}