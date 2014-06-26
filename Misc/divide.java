class divide
{

public static void main(String s[])
{
int sum=0,rem,i,count=0;
System.out.println("Count:"+"\t"+"Multiples(7):"+"\t"+"Sum:");
for (i=100;i<=200;i++)
{
rem=i%7;
if (rem==0)
{
count++;
sum=sum+i;

System.out.println(count+"\t"+i+"\t\t"+sum);



}


}



}

}