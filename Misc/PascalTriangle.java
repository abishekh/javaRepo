import java.io.*;
class factorial 
{
long fact(int x)
{
if ((x==0) || (x==1))
 return 1;
else
 return x*fact(x-1);
}
} 

class PascalTriangle
{
public static void main(String s[]) throws IOException
{
int n,i,j,k;
long fa;
factorial f = new factorial();

DataInputStream d = new DataInputStream(System.in);
System.out.println("Enter the number of levels");
n=Integer.parseInt(d.readLine());

for(i=0;i<n;i++)
{
 System.out.print("\n");
 //for(k=n/2;k>i;k--)
   //  {
  //    System.out.print("\t");
    // } 
 for(j=0;j<=i;j++)
   {
     fa= f.fact(i)/(f.fact(j) * f.fact(i-j));
      System.out.print(fa +"\t");
      }
 }
}
}
