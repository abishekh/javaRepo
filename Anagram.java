import java.io.*;
/**
 * Anagram Program
 *Question 2
 * @author Abishek Hariharan &lt;&gt;
 * @version $Rev$
 */


public class Anagram


    {
    public static boolean areAnagrams(String string1,
    String string2)


        {
        				 String workingCopy1 = removeunwanted(string1);
        	 			 String workingCopy2 = removeunwanted(string2);
        		workingCopy1 = workingCopy1.toLowerCase();
        		workingCopy2 = workingCopy2.toLowerCase();
        		workingCopy1 = sort(workingCopy1);
        		workingCopy2 = sort(workingCopy2);
        return workingCopy1.equals(workingCopy2);
    }
    protected static String removeunwanted(String string)


        {
        int i, len = string.length();
        	StringBuffer dest = new StringBuffer(len);
        	char c;
        	for (i = (len - 1); i >= 0; i--)


            		{
            			c = string.charAt(i);
            		if (Character.isLetterOrDigit(c))


                		{
                				dest.append(c);
                		}
                		}
                return dest.toString();
            }
            protected static String sort(String string)


                {
                		int length = string.length();
                	char[] charArray = new char[length];
                			string.getChars(0, length, charArray, 0);
                		java.util.Arrays.sort(charArray);
                	 return new String(charArray);
            }
            public static void main(String[] args) throws IOException


                {
						DataInputStream input=new DataInputStream(System.in);
	 			System.out.println("Enter the First String: ");
                String string1 = input.readLine();
				System.out.println("Enter the Second String: ");
                String string2 = input.readLine();
                System.out.println();
                System.out.println("Testing whether the following "
                + "strings are anagrams:");
                System.out.println("String 1: " + string1);
                System.out.println("String 2: " + string2);
                System.out.println();
                if (areAnagrams(string1, string2))


                    {
                    	System.out.println("They are anagrams!");
                }
                	else


                    	{
                    	 System.out.println("They are not anagrams!");
                    	}
                    System.out.println();
                }
            }

