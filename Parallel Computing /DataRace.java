import java.io.*;
import java.util.*;
import java.lang.*;


public class DataRace implements Runnable
{
	static volatile int x;
		static int i;
		public void run()
		{
			for(i=0;i<1000000;i++)
			{
				x++;
				x--;
			}
		}
		public static void main(String[] args) throws Exception
		{
			Thread[] threads=new Thread[1000];
			for(i=0;i<threads.length;i++)
				threads[i]=new Thread(new DataRace());
			for(i=0;i<threads.length;i++)
				threads[i].start();
			for(i=0;i<threads.length;i++)
				threads[i].join();
			System.out.println("x");
			
		}
}