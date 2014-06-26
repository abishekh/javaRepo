import java.io.*;
import java.util.*;
import java.lang.*;
public class psum implements Runnable
{
		int j;
		static volatile int i,p,n;
		static volatile int[] a=new int[100];
		static volatile int[] o=new int[100];
		psum(int k)
		{ 
			j=k;
		}
		public void run()
		{
			int b,e;
			b=j*n/p;
			e=((j+1)*(n/p));
			for(i=b;i<e;i++)
				o[j]+=a[i];
		}
		public static void main(String[] args) throws Exception
		{
			int i,sum=0;
			DataInputStream S = new DataInputStream(System.in);
			System.out.println("Enter the number of processors");
			p=Integer.parseInt(S.readLine());
			System.out.println("Enter the limit");
			n=Integer.parseInt(S.readLine());
			for(i=0;i<n;i++)
			{
				a[i]=i;
			}
		  	Thread[] t= new Thread[p];
			for(i=0;i<p;i++)
			{
				t[i]=new Thread(new psum(i));
			}
			for(i=0;i<p;i++)
				t[i].start();
				for(i=0;i<p;i++)
					t[i].join();
			for(i=0;i<p;i++)
				sum+=o[i];
			System.out.println("The Sum is " +sum);
		}
}