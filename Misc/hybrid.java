//implementation of multiple inheritance.


class student1
{
int rollnumber;
void getnumber(int x)
{
rollnumber=x;
}
void putnumber()
{
System.out.println("rollnumber="+rollnumber);
}
}

class test extends student1
{
float m1,m2;
void getmarks(float x,float y)
{
m1=x;m2=y;
}
void putmarks()
{
System.out.println("mark1="+m1);
System.out.println("mark2="+m2);
}
}

interface sports
{
float sportswt=6.0f;
public void display();
}

class results extends test implements sports
{
public void display()
{
System.out.println("sports weight="+sportswt);
}

void display_results()
{
float total;
total=m1+m2+sportswt;
putnumber();
putmarks();
display();
System.out.println("total="+total);
}
}

class hybrid
{
public static void main(String s[])
{
results r1 = new results();
r1.getnumber(1011);
r1.getmarks(25.75f,35.5f);
r1.display_results();
}
}



output


X:\java>javac hybrid.java

X:\java>java hybrid
rollnumber=1011
mark1=25.75
mark2=35.5
sports weight=6.0
total=67.25