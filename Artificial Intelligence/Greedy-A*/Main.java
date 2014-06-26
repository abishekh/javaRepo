import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.apache.commons.collections.*;

public class Main {

	/**
	 * @param args
	 */
	
	
	@SuppressWarnings({ "deprecation", "unused", "unchecked", "rawtypes" })

	
	
	public static void main(String[] args)  {
				
		
        String[] input1 = new String[100];
	  	String[] input2 = new String[100];
		String[] input3 = new String[100];
	  	String[] input4 = new String[100];
	  	String[] input5 = new String[100];
	  	Integer[] input6 = new Integer[100];
	  	Integer[] input7 = new Integer[100];
	  	String delimiter[];
	  	String rootnode = null;
	  	String goal = null;
	  
	  	Map<String, String> previous = new TreeMap();
	  	MultiMap childmap = new MultiHashMap();
	  	Map<String, Integer> directtime = new TreeMap();
	  	MultiMap reverselookup =new MultiHashMap();
	  	Map<String, Integer> directrisk = new TreeMap();
	  	Map<String, Integer> weightmap = new TreeMap();
	  	Map<Integer,String> evalfuncmap = new TreeMap();
		weightmap.clear();
		
		
	  	
	  	Map<String, String> colorMap = new TreeMap();
	  	
		File file = new File(args[0]);
		File file2 = new File(args[1]);
		rootnode = args[2];
		goal = args[3];
		
		System.out.println("Startnode is: "+rootnode);
		System.out.println("Goal is: "+goal);
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
          int mastercount = count;
          
         childmap.clear();
       
          for(int i=0;i<input1.length;i++)
  		{
  			childmap.put(input1[i], input2[i]);
  			childmap.put(input2[i], input1[i]);
  			
  		}
          
          
          // Initialize weightmap values
         
          childmap.remove(null);    //This shit is happening again.
          String[] dump= new String[childmap.size()];
  		  childmap.keySet().toArray(dump);
  		//Debug  System.out.println("The key set is: "+childmap.keySet());
  		// DeBUG  System.out.println("Dump begins :");
  		  for(int x= 0;x<dump.length;x++){ 
  			// DeBUG  System.out.println(dump[x]);
  		  weightmap.put(dump[x], 99999); //99999 = Sufficiently Large , could have used POSITIVE.INFINITY but that requires Double and complicates things.
		}
  		 weightmap.put(rootnode, 0); 
  		// DeBUG  System.out.println("Dump Ended");
  		  
  		
  		
  		//initialize reverselook up values for time 
  		  reverselookup.clear();
         
          for(int i=0;i<count;i++)
  		{
  			reverselookup.put(input1[i],input3[i].concat("_").concat(input2[i]));
  			reverselookup.put(input2[i], input3[i].concat("_").concat(input1[i]));
  			
  			
  		}
          
          //initializing color map
      	
		//System.out.println("Has null ? "+childmap.containsKey(null));
		//System.out.println("Keyst of the childmap is : " +childmap.keySet());
		
	
          if(reader!=null)
        	  reader.close();
          
          
          reader = new BufferedReader(new FileReader(file2));
          text = null;
          count = 0;
          while ((text = reader.readLine()) != null)
          {
        	 // System.out.println("Value of text is : "+text);
          	//Splitting the input by delimiter
				String limit[] = text.split(" ");
				input5[count] = limit[0];
				//System.out.println("Value of input5 is : "+input5[count]);
				input6[count] = Integer.parseInt(limit[1]);
				//System.out.println("Value of input6 is : "+input6[count]);
				input7[count] = Integer.parseInt(limit[2]);
				//System.out.println("Value of input7 is : "+input7[count]);
				count++;
				
          }
          for(int i=0;i<count;i++)
    		{ //  System.out.println("inserting "+input5[i]+ " and "+ input6[i]);
    			directtime.put(input5[i], input6[i]);
    			
    			
    			directrisk.put(input5[i], input7[i]);
    			
    			
    		}
         
          // This is important , because we need to complete the information available for the direct costs 
          // Cost to the goal from itself should be set to zero.
         
          directtime.put(goal, 0); 
          directrisk.put(goal,0);
         
          
          //DEbugging  System.out.println("Does direct time contain null : " +directtime.containsValue(null));
          //DEbugging  gSystem.out.println("Does direct risk contain null : " +directrisk.containsValue(null));
          
         if(reader!=null)
        	  reader.close();
        
         clearcolor(colorMap,childmap);
         /**
          * Greedy Direct Time 
          */
        greedy(childmap,directtime,previous,colorMap,rootnode,goal,"Greedy.time.result.txt");
         
         
         clearcolor(colorMap,childmap);
         previous.clear();
         /**
          * Greedy Direct Risk
          */
        
         greedy(childmap,directrisk,previous,colorMap,rootnode,goal,"Greedy.risk.result.txt");
      // DeBUG    System.out.println(weightmap.values());
        
         clearcolor(colorMap,childmap);
         previous.clear();
         evalfuncmap.clear();
        
         Astar(reverselookup, directrisk, previous, colorMap, weightmap, evalfuncmap, rootnode, goal, "A-star.time.result.txt");
        
        clearcolor(colorMap,childmap);
        previous.clear();
        reverselookup.clear();
        evalfuncmap.clear();
        
        for(int i=0;i<mastercount;i++)
		{
			reverselookup.put(input1[i],input4[i].concat("_").concat(input2[i]));
			reverselookup.put(input2[i], input4[i].concat("_").concat(input1[i]));
			
			
		}
        
      
       /** DEbugging 
        System.out.println("Output of direct time entry sets: " +directtime.entrySet());
        System.out.println("Output of direct risk entry sets: " +directrisk.entrySet());
        System.out.println("Output of reverselookup entry sets: " +reverselookup.entrySet());
        System.out.println("Output of weightmap entry sets: " +weightmap.entrySet());
        System.out.println("Does rvlkp contain null : " +weightmap.containsValue(null));
        **/
        
        
        Astar(reverselookup, directrisk, previous, colorMap, weightmap, evalfuncmap, rootnode, goal, "A-star.risk.result.txt");
          
       }
      catch(FileNotFoundException e){
    	  e.printStackTrace();
      }
      catch(IOException e){
    	  e.printStackTrace();
      }
      
      catch(NullPointerException e){
    	  e.printStackTrace();
      }
 	   
	}
	
	
	//Here is where the fun begins ..
	
	@SuppressWarnings("unchecked")
	static void clearcolor(Map<String, String> colorMap ,MultiMap childmap)
	{
		
		String[] dump= new String[childmap.size()];
	      //	System.out.println("Size of the childmap is: " +childmap.size());
			childmap.remove(null);												//why is this nonsense happening !!!!!????
			childmap.keySet().toArray(dump);
			
			for(int x= 0;x<childmap.size();x++){
			
			colorMap.put(dump[x], "white");
		}
		
	}
	
	
	
	static void greedy(MultiMap childmap ,Map<String, Integer> direct,Map<String,String> previous,Map<String, String> colorMap,String rootnode, String goal,String outfile){
	previous.put(rootnode, "nothing");
	String nodetoexpand = rootnode;
	boolean check = true;
	while(check)
	{
		String append,temp = goal;
		
	
		// insert code here
		//colorMap.put(nodetoexpand, "black");
		String temp1 = childmap.get(nodetoexpand).toString().replaceAll("[\\[,\\]]","");
		String[] array1 = temp1.split(" ");
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.clear();
	
		try{
		for(int i=0;i<array1.length;i++) // for each child
		 {
			
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			if (array1[i].equals(goal) )
			{
			previous.put(array1[i], nodetoexpand);
			nodetoexpand=array1[i];
			check =false;
			PrintWriter out= null;
			while(!previous.get(nodetoexpand).toString().equalsIgnoreCase("nothing"))
			{
				append = (String) previous.get(nodetoexpand);
				
				temp = append + "-" + temp;
				nodetoexpand=append;
				
			}
		try{	
			
				out = new PrintWriter(outfile);
				out.println(temp);
				System.out.println("Greedy search finished successfully!");
			} catch (FileNotFoundException e) {
				//  Auto-generated catch block
				e.printStackTrace();
				
			}
		catch (NullPointerException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
			finally{
				if(out!=null) 
					out.close();
			}
		
		}
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			String color = (String) colorMap.get(array1[i]);	
			//System.out.println(array1.length);   
			
			if(!color.equalsIgnoreCase("black"))
			{
				if (direct.get(array1[i]) != null)
				{
			 pq.add(direct.get(array1[i]));
			 previous.put(array1[i], nodetoexpand);
			 colorMap.put(array1[i], "gray");
				}
			}
			
			
			else if (color.equalsIgnoreCase("black"))
			{
			// 	i++;
			}
			
		 }
		 colorMap.put(nodetoexpand, "black");
		 for(int i=0;i<array1.length;i++)
		 {
			 if( direct.get(array1[i])==pq.peek() )
			 {
				 nodetoexpand=array1[i];
				 pq.poll();
			 }
				 
		 }
		 
		// nodetoexpand = reversedirect.get(pq.poll()); //need to check with current children , not with the entire reverse lookup
		//DEbug System.out.println(nodetoexpand);
		 
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			
		}
		 
		
	}

}
static void Astar(MultiMap reverselookup, Map<String, Integer> direct,Map<String,String> previous,Map<String, String> colorMap,Map<String, Integer> weightmap,Map<Integer,String>evalfuncmap,String rootnode, String goal,String outfile){
		
		previous.put(rootnode, "nothing");
		String nodetoexpand = rootnode;
		
		boolean check = true;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.clear();
		
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
				
					out = new PrintWriter(outfile);
					out.println(temp);
					System.out.println("A* search finished successfully!");
				} catch (FileNotFoundException e) {
					//  Auto-generated catch block
					e.printStackTrace();
					
				}
			catch (NullPointerException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
			
				finally{
					if(out!=null) 
						out.close();
				}
				
				}	
			
			//insert code body here
			
			colorMap.put(nodetoexpand,"black");
			String temp1 = reverselookup.get(nodetoexpand).toString().replaceAll("[\\[,\\]]","");
			String[] array1 = temp1.split(" ");
			int pathcost = (Integer)weightmap.get(nodetoexpand);
			//System.out.println(weightmap.entrySet());
			int evaluationfunc=0;
			for(int x=0; x<array1.length;x++)   //for each child 
			{
				String temparray[] = array1[x].split("_");        // ~~~~~~~~~~~~~~~~Splitting array1 here~~~~~~~~~~~~~~~~~~~~~
				
				int cost = Integer.parseInt(temparray[0]);
				int new_weight = cost + pathcost;  //compounded weight
				//System.out.println("Code has reached here");
				if (new_weight < (Integer)weightmap.get(temparray[1]))
					{ 
					
					//if the new weight is better than what we have for that element already 
					 weightmap.put(temparray[1], new_weight);
					 previous.put(temparray[1],nodetoexpand);
					// System.out.println("The evaluationfunc for "+ temparray[1]+" is "  +new_weight + " plus " +direct.get(temparray[1]) );
					 evaluationfunc = new_weight + direct.get(temparray[1]); 
					// System.out.println("The evaluationfunc for "+ temparray[1]+" is "  +evaluationfunc);
					 evalfuncmap.put(evaluationfunc, temparray[1]);
					 }
			
				//DEbug System.out.println("Code has reached here1");
				
				String color = (String) colorMap.get(temparray[1]);	
				
				//TODO Theres a problem with the color setting 
				
				
				if(color.equalsIgnoreCase("white"))   // color the children as gray=frontier
				{ //System.out.println("Code has reached here2");
					//System.out.println("pq contains :" + pq.contains(null) );
					pq.add(evaluationfunc);
					colorMap.put(temparray[1], "gray");
					array1[x] = Integer.toString(new_weight).concat("_").concat(temparray[1]);
				}
				else if (color.equalsIgnoreCase("black"))
				{
				// 	x++;
				}
			//System.out.println("Value in evaluationfunc is: "+ evaluationfunc);
			}
			
			int winner = pq.poll();
			nodetoexpand=evalfuncmap.get(winner);
			//DEbug System.out.println("Node to expand is: " + nodetoexpand);
			
			
		}
		
		
	}
} //extra i think...
