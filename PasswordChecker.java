import java.io.*;
import java.util.*;

/**
 * Password Checker Program
 *Question 6
 * @author Abishek Hariharan &lt;&gt;
 * @version $Rev$
 */
public class PasswordChecker
{
public static void main(String args[]) throws IOException
{
	String pword;
	int caseok=0,num=0,caps=0,small=0;
DataInputStream input =new DataInputStream(System.in);
System.out.println("Please Enter a password:");
pword=input.readLine();

if (pword.length()>=7)
{
	
	int i;
int x=0;
char pwdarray[]=pword.toCharArray();

	CharArrayReader aro=new CharArrayReader(pwdarray);
while ((i=aro.read())!=-1)
{	
	if ((int)pwdarray[x]>=48 && (int)pwdarray[x]<=57 )
     {num=1; 
	}
	
 if ((int)pwdarray[x]>=65 && (int)pwdarray[x]<=90 )
{caps=1;} 	
	
	if((int)pwdarray[x]>=97 && (int)pwdarray[x]<=122 ) 
	 {small=1;
	}
x++;
}


 if (caps==1 && small==1) 
caseok=1;

else if (caps==0 || small==0)
System.out.println("\n\tPassword must have both uppercase and lower case characters.");



if(caseok==1 && num==1)
System.out.println("\n\tPassword is Acceptable");   


}
else
{
	System.out.println("\n\tPassword must be atleast 7 characters long.");  
}



}
}
