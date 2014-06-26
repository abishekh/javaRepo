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
			DataInputStream S=new DataInputStream(System:in)
			System.out.println("Enter the number of processors");
			p=Integer.parseInt(S.readLine());
			System.out.println("Enter the limit");
			n=Integer.parseInt(S.readLine())
			
			for(int i=0;i<n;i++)
			{
				a[i]=i;
			}
		
			Thread[] t= new Thread[5];
			for(int i=0;i<t.length;i++)
			{
				t[i]=new Thread(new Threadtest1(i));
			}
			for(int i=0;i<t.length;i++)
				t[i].start();
			for(int i=0;i<t.length;i++)
				t[i].join();
			for(i=b;i<=e;i++)
			{
				O[j]+=A[i];
			}
			
			
		}
		
}