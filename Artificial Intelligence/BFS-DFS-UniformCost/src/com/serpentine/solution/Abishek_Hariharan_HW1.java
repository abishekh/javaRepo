package com.serpentine.solution;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

@SuppressWarnings("deprecation")


public class Abishek_Hariharan_HW1
{
	   @SuppressWarnings({ "rawtypes", "unchecked" })
public static void main(String[] args)
   {
		   //Data structures to handle  various operations , its just easier to create new ones than to clean up and to reuse previous versions
	       
			MultiMap myMap = new MultiHashMap();
			
			MultiMap myMap3 = new MultiHashMap();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			Map colorMap = new TreeMap();
			ArrayList<String> myArr = new ArrayList<String>();
			Queue<String> bfsqueue = new LinkedList<String>();
	   
	     	String[] input1 = new String[100];
		  	String[] input2 = new String[100];
			String[] input3 = new String[100];
		  	String[] input4 = new String[100];
		  	
		  	
		  	//initial conditions 
		  	String goal= null ;
		  	String rootnode= null;
		  	
		  	System.out.println("Start Node: " +args[1]);  //getting the command line arguments IF theres an exception here , it means you havent provided arguments 
		  	rootnode = args[1];								// Read the README if theres an exception here. 
		  	System.out.println("Goal Node: "+ args[2]);   //getting the command line arguments
		  	goal = args[2];
	   
		  	if (rootnode == null)
				System.out.println("Failed : No Start node defined!");
		  	
	   try{
		   
       File file = new File(args[0]);   //getting the command line arguments
      
       BufferedReader reader = null;
	      int count = 0;

       try
        {
           reader = new BufferedReader(new FileReader(file));
           String text = null;
		   
		  
		   
		 
            // repeat until all lines is read
            while ((text = reader.readLine()) != null)
            {
            	//Splitting the input by delimiter
				String limiter[] = text.split(" ");
				input1[count] = limiter[0];
				input2[count] = limiter[1];
				input3[count] = limiter[2];
				input4[count] = limiter[3];
				
				count++;
				
            }
         
            if (reader != null)
            {
               reader.close();
             }
			
        } 
		
		catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } 
		
		catch (IOException e)
        {
            e.printStackTrace();
        } 
		
     
		// Generating Adjacency List  
		for(int i=0;i<count;i++)
		{
			myMap.put(input1[i], input2[i]);
			myMap.put(input2[i], input1[i]);
			
			
		}
		
	//Generating Visited Node List
		String[] dump= new String[myMap.size()];
		myMap.keySet().toArray(dump);
		
		for(int x= 0;x<myMap.size();x++){
			colorMap.put(dump[x], "white");
		}
		
//BFS Begins **************************************************************************************************
		
		
		
		
		
		
		colorMap.put(rootnode,"gray");
		Map previous = new TreeMap(); //Only one value per key , old values are replaced upon new values for the same key 
		bfsqueue.add(rootnode);
		previous.put(rootnode, "nothing");
		
		
while (! bfsqueue.isEmpty())
		{
		String query = bfsqueue.peek();
		String append,temp = goal;
			if (query.equals(goal))
				{
				PrintWriter out= null;
			
				while(!previous.get(query).toString().equalsIgnoreCase("nothing"))
				{
					append = (String) previous.get(query);
					temp = append + "-" + temp;
					query=append;
					
				}
			try{	
				
					out = new PrintWriter("breadth-first.result.txt");
					out.println(temp);
					System.out.println("Breadth First Search finished successfully!");
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				finally{
					if(out!=null) 
						out.close();
				}
				
				}		 
			
		
		String[] adj = myMap.get(query).toString().replaceAll("[\\[,\\]]","").split(" "); //get children !!

	

try{
		
			for(int x = 0;x<adj.length;x++ )
				{
				
					String color = colorMap.get(adj[x]).toString();
				
					if(color.equalsIgnoreCase("white"))
					{
			
					colorMap.put(adj[x], "gray"); //Coloring for visited nodes
					previous.put(adj[x], query); //Setting  previous of visited nodes
					bfsqueue.add(adj[x]);   //Adding to queue
				
					}	
				
				}
			
			
			colorMap.put(bfsqueue.remove(),"black");
			
			
		}
		catch(NullPointerException e){
		e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			}
	
	
			
		}
//BFS Ends************************************************************************************************************		
	
//Resetting color map to all white 

for(int x= 0;x<myMap.size();x++){
	colorMap.put(dump[x], "white");
}




//DFS -  begins ******************************************************************************************************

		
Stack<String> dfsstack = new Stack();


previous.clear();

colorMap.put(rootnode,"gray");

dfsstack.push(rootnode);
previous.put(rootnode, "nothing");



while (! dfsstack.empty() )
{
String query = dfsstack.peek();
String append,temp = goal;
	if (query.equals(goal))
		{
		PrintWriter out= null;
	
		while(!previous.get(query).toString().equalsIgnoreCase("nothing"))
		{
			append = (String) previous.get(query);
			temp = append + "-" + temp;
			query=append;
			
		}
	try{	
		
			out = new PrintWriter("depth-first.result.txt");
			out.println(temp);
			System.out.println("Depth First Search finished successfully!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	finally{
			if(out!=null) 
				out.close();
		}
		
		}		 
	

String[] adj = myMap.get(query).toString().replaceAll("[\\[,\\]]","").split(" ");  //stripping the formatting 

try{

	for(int x = 0;x<adj.length;x++ )
		{
		
			String color = colorMap.get(adj[x]).toString();
		
		
			if(color.equalsIgnoreCase("white"))
			{
			
			colorMap.put(adj[x], "gray");
		
			previous.put(adj[x], query);
			
			dfsstack.push(adj[x]);
		
			}	
		
		}
	
	
	colorMap.put(dfsstack.pop(),"black");
	
	
}
catch(NullPointerException e){
e.printStackTrace();
}
catch(ArrayIndexOutOfBoundsException e){
	e.printStackTrace();
	}
	
}	
//DFS Ends ********************************************************************************************************
pq.clear();
colorMap.clear();
//Resetting color map to all white 
for(int x= 0;x<myMap.size();x++){
	colorMap.put(dump[x], "white");
}

//Creating initial weight map
Map weightMap = new TreeMap();
for(int x= 0;x<myMap.size();x++){
	weightMap.put(dump[x], 99999); //99999 = Sufficiently Large , could have used POSITIVE.INFINITY but that requires Double and complicates things.
}

//Uniform Cost search-Time Begins **************************************************************************************
String nodetoexpand = rootnode;

Queue<String> ucsqueue = new LinkedList<String>();

//Generating Adjacency List  
for(int i=0;i<count;i++)
{
	myMap3.put(input1[i], input3[i].concat("_").concat(input2[i]));
	myMap3.put(input2[i], input3[i].concat("_").concat(input1[i]));
	
	
}

colorMap.put(rootnode,"gray");
ucsqueue.add(rootnode);
previous.put(rootnode, "nothing");
weightMap.put(rootnode, 0);

boolean check= true;

while (check !=false )
{
	
String append,temp = goal;
	if (nodetoexpand.equals(goal) )
		{
		check =false;
		PrintWriter out= null;
		while(!previous.get(nodetoexpand).toString().equalsIgnoreCase("nothing"))
		{
			append = (String) previous.get(nodetoexpand);
			//System.out.println(append);
			temp = append + "-" + temp;
			nodetoexpand=append;
			
		}
	try{	
		
			out = new PrintWriter("uniform-cost.time.result.txt");
			out.println(temp);
			System.out.println("Uniform Cost search for time value finished successfully!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		finally{
			if(out!=null) 
				out.close();
		}
		
		}		 
	



//changing color to black here 
colorMap.put(nodetoexpand,"black");

String temp1 = myMap3.get(nodetoexpand).toString().replaceAll("[\\[,\\]]","");
String[] array1 = temp1.split(" ");
int pathcost = (Integer)weightMap.get(nodetoexpand); // just use this for now 


//adding previous weights to new weights

for(int x=0; x<array1.length;x++)   //for each child 
{
	String temparray[] = array1[x].split("_");        // ~~~~~~~~~~~~~~~~Splitting array1 here~~~~~~~~~~~~~~~~~~~~~
	int new_weight = Integer.parseInt(temparray[0]) + pathcost;  //compounded weight
	if (new_weight < (Integer)weightMap.get(temparray[1]))
		{ 
		//if the new weight is better than what we have for that element already 
		 weightMap.put(temparray[1], new_weight);
		 previous.put(temparray[1],nodetoexpand);
		 }
	
	
	String color = (String) colorMap.get(temparray[1]);	
	
	if(color.equalsIgnoreCase("white"))   // color the children as gray=frontier
	{   pq.add(new_weight);
		colorMap.put(temparray[1], "gray");
		array1[x] = Integer.toString(new_weight).concat("_").concat(temparray[1]);
	}
	else if (color.equalsIgnoreCase("black"))
	{
	// 	x++;
	}

		
	
	
	   // ~~~~~~~~~~~~~~~~Reconstructing array1 here~~~~~~~~~~~~~~~~~~~~~
}



myArr.addAll(Arrays.asList(array1)); //adding un-compounded weighted-children to global child list , used to obtain ordered pair of weights and children


int winner = pq.poll(); // getting the compounded weight with the least cost 

//figuring out node to expand next 

//Debugging  System.out.println("Size of myArr is : "+myArr.size());
int y=0;
boolean found=false;

while(y<myArr.size() && found==false)
{
		String[] temp2 = myArr.get(y).split("_");	
		// Have to remove the node from myArr as well IMP!!!
		int test = Integer.parseInt(temp2[0]);
	//Debugging 	System.out.println("if " +test  + " is "+ winner + "for node "+temp2[1]);
		
		if( test == winner )//check the uncompounded weights
			{
			found=true;
			myArr.remove(y);
			nodetoexpand = temp2[1];                //***********************node to expand obtained   HERE is where it changes 
	//Debugging 		System.out.println("Node chosen to expand next is " +nodetoexpand);
				}
		else{
			y++;
		}
	
	}

}

//Uniform Cost Search Ends*****************************************************************************************
//resetting all parameters for a fresh run 
nodetoexpand = rootnode;
pq.clear();
previous.clear();
colorMap.clear();
myMap3.clear();
myArr.clear();
//Resetting color map to all white 
for(int x= 0;x<myMap.size();x++){
	colorMap.put(dump[x], "white");
}

//Creating initial weight map by replacing values
weightMap.clear();
for(int x= 0;x<myMap.size();x++){
	weightMap.put(dump[x], 99999); //99999 = Sufficiently Large , could have used POSITIVE.INFINITY but that requires Double and complicates things.
}

//Uniform Cost Search Risk Begins*************************************************************************************


//Generating Adjacency List  
for(int i=0;i<count;i++)
{
	myMap3.put(input1[i], input4[i].concat("_").concat(input2[i]));
	myMap3.put(input2[i], input4[i].concat("_").concat(input1[i]));
	
	
}


colorMap.put(rootnode,"gray");
ucsqueue.add(rootnode);
previous.put(rootnode, "nothing");
weightMap.put(rootnode, 0);

 check= true;

while (check !=false )
{
	
String append,temp = goal;
	if (nodetoexpand.equals(goal) )
		{
		check =false;
		PrintWriter out= null;
		while(!previous.get(nodetoexpand).toString().equalsIgnoreCase("nothing"))
		{
			append = (String) previous.get(nodetoexpand);
			
			temp = append + "-" + temp;
			nodetoexpand=append;
			
		}
	try{	
		
			out = new PrintWriter("uniform-cost.risk.result.txt");
			out.println(temp);
			System.out.println("Uniform Cost search for risk value finished successfully!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		finally{
			if(out!=null) 
				out.close();
		}
		
		}		 
	



//changing color to black here 
colorMap.put(nodetoexpand,"black");

String temp1 = myMap3.get(nodetoexpand).toString().replaceAll("[\\[,\\]]","");
String[] array1 = temp1.split(" ");
int pathcost = (Integer)weightMap.get(nodetoexpand); // just use this for now 


//adding previous weights to new weights

for(int x=0; x<array1.length;x++)   //for each child 
{
	String temparray[] = array1[x].split("_");        // ~~~~~~~~~~~~~~~~Splitting array1 here~~~~~~~~~~~~~~~~~~~~~
	int new_weight = Integer.parseInt(temparray[0]) + pathcost;  //compounded weight
	if (new_weight < (Integer)weightMap.get(temparray[1]))
		{ 
		//if the new weight is better than what we have for that element already 
		 weightMap.put(temparray[1], new_weight);
		 previous.put(temparray[1],nodetoexpand);
		 }
	
	
	String color = (String) colorMap.get(temparray[1]);	
	
	if(color.equalsIgnoreCase("white"))   // color the children as gray=frontier
	{   pq.add(new_weight);
		colorMap.put(temparray[1], "gray");
		array1[x] = Integer.toString(new_weight).concat("_").concat(temparray[1]);
	}
	else if (color.equalsIgnoreCase("black"))
	{
	// 	x++;
	}

		
	
	
	   // ~~~~~~~~~~~~~~~~Reconstructing array1 here~~~~~~~~~~~~~~~~~~~~~
}



myArr.addAll(Arrays.asList(array1)); //adding un-compounded weighted-children to global child list , used to obtain ordered pair of weights and children


int winner = pq.poll(); // getting the compounded weight with the least cost 

//figuring out node to expand next 
//Debugging  System.out.println("Size of myArr is : "+myArr.size());
int y=0;
boolean found=false;

while(y<myArr.size() && found==false)
{
		String[] temp2 = myArr.get(y).split("_");	
		
		int test = Integer.parseInt(temp2[0]);
		//Debugging	System.out.println("if " +test  + " is "+ winner + "for node "+temp2[1]);
		
		if( test == winner )//check the uncompounded weights
			{
			found=true;
			myArr.remove(y);
			nodetoexpand = temp2[1];                //***********************node to expand obtained   HERE is where it changes 
			//Debugging		System.out.println("Node chosen to expand next is " +nodetoexpand);
				}
		else{
			y++;
		}
	
	}

}

//Uniform Cost Search-Risk Ends***************************************************************************************
	   }
	catch(ArrayIndexOutOfBoundsException e)
		{	e.printStackTrace();
			System.out.println("Please Enter File path as arguement ");
		}
	   catch(NullPointerException e)
		{	e.printStackTrace();
			
		}
		
}
}