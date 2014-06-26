import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	ArrayList clientOutputStreams;
	
	public void go()
	{
		clientOutputStreams = new ArrayList();
		
	try
	{
	ServerSocket serverSock = new ServerSocket(4999);
			
	while (true)
		{ 					// set up the server writer function and then begin at the same
			  				// the listener using the Runnable and Thread
				

	Socket clientSock = serverSock.accept();
	PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
	clientOutputStreams.add(writer);
				
				


							// use a Runnable to start a 'second main method that will run
							// the listener
	Thread listener = new Thread(new ClientHandler(clientSock));
	listener.start();
	System.out.println("got a connection");
			}				 // end while
	}						// end try
	catch (Exception ex)
		{
			System.out.println("error making a connection");
		} 					// end catch
		
	} 						// end go()

	




public class ClientHandler implements Runnable
	{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket)
		{ // new inputStreamReader and then add it to a BufferedReader
			
			try
			{
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			}
						// end try
			catch (Exception ex)
			{
				System.out.println("error beginning StreamReader");
			} // end catch
			
		} // end ClientHandler()
		
		public void run()
		{
			String message;
			
			try
			{
				while ((message = reader.readLine()) != null)
				{
					System.out.println("read" + message);
					tellEveryone(message);
				} // end while
			} // end try
			catch (Exception ex)
			{
				System.out.println("lost a connection");
			} // end catch
		} // end run()
	} // end class ClientHandler
	
	public void tellEveryone(String message)
	{ // sends message to everyone connected to server
		Iterator it = clientOutputStreams.iterator();
		
		while (it.hasNext())
		{
			try
			{
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} // end try
			catch (Exception ex)
			{
				System.out.println("error telling everyone");
			} // end catch
		} // end while
	} // end tellEveryone()



} // end class Server


public class executeServer extends Server
{
public static void main(String s[])
{
Server obj;
obj.go();
}


}