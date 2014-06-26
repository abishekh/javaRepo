import java.io.*;
import java.util.*;

/** Question 4
 * Division Program 
 *
 * @author Abishek Hariharan &lt;&gt;
 * @version $Rev$
 */
public class Division
{
	static void divider(String x ,String y) throws ArithmeticException
	{
	
		int a,b;
		a=Integer.parseInt(x);
		b=Integer.parseInt(y);
		int result;
		result =(a/b);
		System.out.println(" "+x+"/"+y+"  is =" +result );
		
	} 
public static void main(String x[]) throws IOException
{ 
	DataInputStream scan=new DataInputStream(System.in);
	int flag=0;
	String num,denom;


while(flag!=1)
{
		System.out.println("Enter the numerator: ");
	num=scan.readLine();
		if (num.equals("q") || num.equals("Q"))
		{
			System.out.println("You have Exitted the program ");
 System.exit(0);
		}
	System.out.println("Enter the denominator: ");
	denom=scan.readLine();
	
try {
	
	divider(num,denom);
	
	
	
}	
catch(ArithmeticException e)
{
	System.out.println("You cannot divide by zero!");
}
	
	
}





}
}
