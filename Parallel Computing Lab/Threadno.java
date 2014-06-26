import java.io.*;
import java.util.*;
import java.lang.*;


public class Threadno implements Runnable
{
		int j;
		Threadno(int k)
		{
			j=k;
		}
		public void run()
		{
			System.out.println("Thread no."+j);
			
		}
		public static void main(String[] args) throws Exception
		{
			Thread[] t= new Thread[4];
			for(int i=0;i<t.length;i++)
			{
				t[i]=new Thread(new Threadno(i));
			}
			for(int i=0;i<t.length;i++)
				t[i].start();
			for(int i=0;i<t.length;i++)
				t[i].join();
			
			
		}
}