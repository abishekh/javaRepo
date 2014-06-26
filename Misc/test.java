/* single inheritance*/

class Room
{
int length;
int breath;
Room (int x,int y)
{
length=x;
breath=y;
}
int area()
{
return (length*breath);
}
}
class bedroom extends Room
{
int height;
bedroom(int x, int y, int z)
{
super(x,y);
height=z;
}
int volume()
{
return(length * breath * height);
}
}
class test
{
public static void main(String S[])
{
bedroom room1=new
bedroom(14,12,10);
int area = room1.area();
int volume=room1.volume();
System.out.println("area="+area+"/nvolume="+volume);
}
}


OUTPUT:----

D:\Documents and Settings\pradeep>x:

X:\>path
PATH=D:\WINDOWS\system32;D:\WINDOWS;D:\WINDOWS\System32\Wbem;c:\java\bin;

X:\>javac test.java
X:\>java test
area=168/nvolume=1680


