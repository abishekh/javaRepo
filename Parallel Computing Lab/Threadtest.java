import java.io.*;
import java.util.*;
import java.lang.*;


public class Threadtest implements Runnable
{
		int i;
		public void run()
		{
			for(i=0;i<3;i++)
			{
				System.out.println(i);
				System.out.println("\n");
			}
		}
		public static void main(String[] args)
		{
			new Thread(new Threadtest()).start();
			new Thread(new Threadtest()).start();
			System.out.println("Done");
		}
}