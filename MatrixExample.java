import java.io.*;


/** Question 5
 * Matrix row reversal program
 * 
 * Errors Expected (Lines 68 to 76)
 * @author Abishek Hariharan &lt;&gt;
 * @version $Rev$
 */





class MatrixExample{
    public static void main(String[] args) throws IOException,ArrayIndexOutOfBoundsException
 {
      
int x,y,row,col;
DataInputStream input=new DataInputStream(System.in);

    System.out.println("Enter Row size ") ;
row=Integer.parseInt(input.readLine());
    System.out.println("Enter Column size "); 
col=Integer.parseInt(input.readLine());

 int array[][]=new int [row][col];

 for( x = 0; x <= (row-1); x++) {
      
      for(y = 0; y <= (col-1); y++) {
        System.out.print(" Enter the element" + x+y+ " : ");
		array[x][y]=Integer.parseInt(input.readLine());
      }
      
    }  

outputArray(array,row,col);
 
 System.out.print(" \n\n\n\n");

reverserow(array,row,col);

 }
    
   public static void outputArray(int[][] array,int row,int col)throws ArrayIndexOutOfBoundsException
 {
     
     for(int i = 0; i <=(row-1) ; i++) {
       System.out.print("[");
       for(int j = 0; j <=(col-1) ; j++) {
         System.out.print(" " + array[i][j]);
       }
       System.out.println(" ]");
     }
     System.out.println();
   }


public static void reverserow(int[][] array,int row,int col) throws ArrayIndexOutOfBoundsException
{

	int q=0,p=0;
	
	int revarray[][]=new int [row][col];
	
	for( p = 0; p <= (row-1); p++) {

	      for(q = 0; q<=(col-1); q++) {
	        
	revarray[p][q]=array[p][col-q];
	
	      }

	    }


	outputArray(revarray,row,col);


	
	
}


}