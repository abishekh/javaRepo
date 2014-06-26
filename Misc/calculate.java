import java.io.*;
class calculate
{
public static void main(String s[])
{
float dep,purchase_price,salvage_value,yrs_service;
DataInputStream d=new DataInputStream(System.in);

purchase_price=Float.valueOf(in.readline());
System.out.println("Enter the Purchase price:");
dep=Float.valueOf(in.readline());
System.out.println("Enter the Depreciation:");

yrs_service=Float.valueOf(in.readline());
System.out.println("Enter the number of years:");


salvage_value=(dep*yrs_service)-purchase_price
System.out.println("The Salvage value is:"+salvage_value);

}
}