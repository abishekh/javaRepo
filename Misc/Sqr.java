import java.io.*;
import java.util.*;

/**
 * Question 3 
 * Square method Recursion Program
 *
 * @author Abishek Hariharan &lt;&gt;
 * @version $Rev$
 */
public class Sqr
{
public static void main(String x[])
{


System.out.println(" \nThe square of 1 is : " + square(1));
System.out.println(" \nThe square of 2 is : " + square(2));
System.out.println(" \nThe square of 3 is : " + square(3));


}

public static int square(int x)
{
	
	int result;
	
	if (x==1)
	return 1;
	
	result=square(x-1) + ((2*x)-1);
	
	return result;
	
	
}


}
