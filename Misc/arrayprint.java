import java.lang.*;


public class arrayprint{



public static void main(String[] args) throws ArrayIndexOutOfBoundsException
{


char[] a ={'1','2','\0','3','4'};

int[] b = {1,1,2,3,5,8};
printarray(4);
findmissing(b,1,2);


}

public static void printarray(int a)
{

String str = Integer.toBinaryString(a);
String notstr = Integer.toBinaryString(~a);
System.out.println(str);
System.out.println(notstr.substring(29));
}


public static int  findmissing(int[] a , int size, int max ){

for (int i : a){
System.out.println(i);
}

return 0;

}



}
