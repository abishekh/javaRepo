import java.io.*;
import java.util.*;
import java.lang.*;
public class matsum implements Runnable
{
		int j;
		static volatile int i,r,l;
		static volatile int[][] a=new int[100][100];
		static volatile int[][] o=new int[100][100];
		static volatile int[][] b=new int[100][100];
		arraysum(int k)
		{ 
			j=k;
		}
		public void run()
		{
			
			for(i=0;i<r;i++)
				o[j]=a[i]+b[i];
		}
		public static void main(String[] args) throws Exception
		{
			int i;
			DataInputStream S = new DataInputStream(System.in);
			Random generator=new Random();
			
			System.out.println("Enter the number of elements to add");
			n=Integer.parseInt(S.readLine());
			System.out.println("Enter the number of processors");
			p=Integer.parseInt(S.readLine());
			for(i=0;i<n;i++)
			{
				a[i]=generator.nextInt(100);
				b[i]=generator.nextInt(100);
			}
			System.out.println("The two arrays generated are: ");
			
			for(i=0;i<n;i++)o
				
			
		  	Thread[] t= new Thread[p];
			for(i=0;i<p;i++)
			{
				t[i]=new Thread(new arraysum(i));
			}
			for(i=0;i<p;i++)
				t[i].start();
			for(i=0;i<p;i++)
				t[i].join();
			System.out.println("\n");
			System.out.println("The Sum of array is: ");
			
			for(i=0;i<n;i++)
				System.out.println(o[i]+"\t");
		}
}