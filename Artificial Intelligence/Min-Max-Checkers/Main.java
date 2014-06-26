
import java.io.*;
import java.util.ArrayList;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;


@SuppressWarnings("deprecation")
public class Main {

	public static int eva=Integer.MIN_VALUE;
	public static String nextmove="";
	public static String movethisrun="";

	/**
	 * @param args
	 */
	@SuppressWarnings({ "resource", "rawtypes","unchecked", "unused","deprecation" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String,Character>[][] board=new TreeMap[5][5000];  //board
		int[] numberofboards = new int[6];

		int[][] boardvalues = new int[10][5000];
		int[][] alphatoroot = new int[10][5000];
		int[][] betatoroot = new int[10][5000];
		Map nextmove=new TreeMap();


		ArrayList<String>[][] normalmoves = new ArrayList[10][5000];
		ArrayList<String>[][] jumpmoves = new ArrayList[10][5000];

		ArrayList<String> firstmoves=new ArrayList();
		ArrayList<Integer> heuristics=new ArrayList();

		MultiMap childboards = new MultiHashMap();
		Map previous = new TreeMap();
		Map movethatcreated = new TreeMap();
		int newcurrentBatD;



		try {

			int playerAcount=0;
			int playerBcount=0;


			board[0][0] = new TreeMap();



			System.out.println(args[0]);

			File testfile =new File(args[0]);
			PrintWriter out = new PrintWriter(args[1]);  //takes string file name
			BufferedReader reader = null;


			String text = null;

			reader = new BufferedReader(new FileReader(testfile));
			while((text = reader.readLine()) != null){

				String casenumber = "not";


				if (text.contains("case"))
				{
					out.println(text);
					for(int j=0;j<8;j++){

						String text2=reader.readLine();

						for (int i=0;i<8;i++){


							char mychar = text2.charAt(i);
							//System.out.println("Character at : "+i+" "+j+" is "+mychar);
							int switcher = mychar;
							switch(switcher){

							case 65:  //A
								playerAcount++;
								break;

							case 66: //B
								playerBcount++;
								break;

							case 75:   //K
								playerBcount++;
								playerBcount++;
								break;

							case 107: //k
								playerBcount++;
								playerBcount++;
								break;
							default:
								break;



							}
							try {
								//
								board[0][0].put("X"+i+j, mychar);
							}
							catch(NullPointerException e){
								e.printStackTrace();
							}
						}
					}

					//anything else to do while the case block is running
					border(board[0][0]); //This needs to be done only once , since subsequent boards are copies and will retain the bordering
					numberofboards[0] = 1;
					//for each board at a particular depth generate child boards 
					for(int i=0;i<=4;i++)          //from depth 0 to 4
					{
						boolean turn;            //A's tun or B's turn 
						if(i%2==0)
						{
							turn=true;
						}
						else{
							turn=false;
						}

						newcurrentBatD=0;
						for (int j=0;j<numberofboards[i];j++){ //for branching factor 0 to whatever  | this basically iterates over the values of newcurrentBatD

							
							normalmoves[i][j] = new ArrayList();
							jumpmoves[i][j] = new ArrayList();
							//open up the board piece by piece 
							for(int x=0;x<8;x++){
								for(int y=0;y<8;y++)
								{
									char piece = board[i][j].get("X"+x+y);
									if(piece=='A' || piece=='B'|| piece=='k' ||piece=='K'){
										
										validMoves(x,y,piece,turn,board[i][j],normalmoves[i][j],jumpmoves[i][j]); //find possible moves for a board
									}
								}
							}

							//then make children from those valid moves

							if(jumpmoves[i][j].isEmpty() && normalmoves[i][j].isEmpty()){
								boardvalues[i][j]=evaluation(board[i][j]);
							}
							else if(jumpmoves[i][j].isEmpty()){//proceed with normal moves list
						
								for(int nummoves=0;nummoves<normalmoves[i][j].size();nummoves++) 
								{

									String move = normalmoves[i][j].get(nummoves);


									generatechildboards(board,previous,movethatcreated,childboards,alphatoroot,betatoroot,i,j,newcurrentBatD,move, false);
									newcurrentBatD++;
								}
							}
							else{     //compulsory jump 
								
								for(int nummoves=0;nummoves<jumpmoves[i][j].size();nummoves++) 
								{
									String move = jumpmoves[i][j].get(nummoves);
									generatechildboards(board,previous,movethatcreated,childboards,alphatoroot,betatoroot,i,j,newcurrentBatD,move, true);
									newcurrentBatD++;
								}

							}
						}
						
						numberofboards[i+1]=newcurrentBatD;  //number of boards at a particular level
					}


					System.out.println("Finished generating all boards");

					//TODO
					int player=1;
					int val=maxvalue("B0.0",board,movethatcreated,normalmoves,jumpmoves,0,Integer.MIN_VALUE,Integer.MAX_VALUE,childboards,boardvalues,player,out);

					out.println("\nfirst move:" +  Main.nextmove +"\n");
					//out.println("\nfirst move:" +Main.nextmove+"\n");

				}
				if (text.isEmpty()) //check for blank line in inputfile
				{														//If you encounter a blank , just clear everything
					board[0][0].clear();
					//	board=new TreeMap[5][5000];
					childboards.clear();
					movethatcreated.clear();
					previous.clear();
					normalmoves = new ArrayList[10][5000];
					jumpmoves = new ArrayList[10][5000];
					numberofboards = new int[6];
					eva=Integer.MIN_VALUE;
					Main.nextmove="";
					Main.movethisrun="";
					firstmoves.clear();
					heuristics.clear();

				}


			}//while ends
			if (reader != null)
			{
				reader.close();
				out.close();
			}

		} //try ends

		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		catch (IOException e) {

			e.printStackTrace();
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}

		//Insert code here





		//Insert code here		
	}







	static void validMoves(int posX,int posY,char piece,boolean turn,Map<String,Character> board,ArrayList<String> normalmove,ArrayList<String> jumpmoves){

		int switchvar = piece;
		char alterego;
		char alterkingego;
		if (turn){
			switch(switchvar){
			case 65:  //A
				alterego = 'B' ;
				alterkingego='K';
				//check only two  diagonal squares
				TLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//left diagonal is open

				TRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 
				break;

			case 107: //k
				alterego = 'B' ;
				alterkingego='K';
				BLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//left diagonal is open

				BRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 


				TLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves); //left diagonal is open

				TRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 


				break;

			}
		}
		else{  //if it isnt A's turn 
			switch(switchvar){   
			case 66: //B
				alterego = 'A' ;
				alterkingego='k';
				//check only two  diagonal squares
				BLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//left diagonal is open

				BRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 
				break;

			case 75:   //K
				alterego = 'A' ;
				alterkingego='k';
				BLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves); //left diagonal is open


				BRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 


				TLdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//left diagonal is open

				TRdiagonal_Open(posX,posY,alterego,alterkingego,board,normalmove,jumpmoves);//right diagonal is open 

				break;
			default:
				break;
			} //switch ends

		}




	}
	static boolean TLdiagonal_Open(int X ,int Y,char alterego,char alterkingego,Map<String,Character> board,ArrayList<String> normalmoves,ArrayList<String> jumpmoves)
	{



		int tempX=X-1;
		int tempY=Y-1;
		//System.out.println("Checking position TL for piece" + X+ " "+ Y+"| piece found "+board.get("X"+X+Y));

		if(board.get("X"+tempX+tempY).equals('O')){
			//System.out.println("Adding nomal move");
			normalmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
			return true;
		}


		else if(board.get("X"+tempX+tempY).equals(alterego) || board.get("X"+tempX+tempY).equals(alterkingego))
		{ 
			if(tempY!=0 && tempX!=0){

				tempX=X-2;
				tempY=Y-2;
				if(board.get("X"+tempX+tempY).equals('O')){
					//	System.out.println("\t\t\t\t\t\tAdding jump move");
					jumpmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
					return true;
				}

			}
		}

		return false;

	}
	static boolean BLdiagonal_Open(int X,int Y,char alterego,char alterkingego,Map<String,Character> board,ArrayList<String> normalmoves,ArrayList<String> jumpmoves)
	{
		int tempX=X-1;
		int tempY=Y+1;
		//System.out.println("Checking position BL for piece " + X+ " "+ Y+"| piece found "+board.get("X"+X+Y));

		if(board.get("X"+tempX+tempY).equals('O'))
		{

			//	System.out.println("Adding move to normal ");
			normalmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
			return true;
		}

		else if(board.get("X"+tempX+tempY).equals(alterego) || board.get("X"+tempX+tempY).equals(alterkingego))
		{
			if(tempX!=0 && tempY!=7){	//the enemy is not on the left extreeme

				//System.out.println("Code reached here");
				tempX=tempX-1;
				tempY=tempY+1;
				if(board.get("X"+tempX+tempY).equals('O')){
					//	System.out.println("\t\t\t\t\tAdding jump move");
					jumpmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
					return true;
				}
			}
		}

		return false;

	}
	static boolean TRdiagonal_Open(int X,int Y,char alterego,char alterkingego,Map<String,Character> board,ArrayList<String> normalmoves,ArrayList<String> jumpmoves)
	{
		int tempX=X+1;
		int tempY=Y-1;
		//	System.out.println("Checking position TR for piece" + X+ " "+ Y+"| piece found "+board.get("X"+tempX+tempY));

		if(board.get("X"+tempX+tempY).equals('O'))
		{
			//System.out.println("Adding nomal move");
			normalmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
			return true;
		}

		else if(board.get("X"+tempX+tempY).equals(alterego)||board.get("X"+tempX+tempY).equals(alterkingego))
		{
			if (tempY!=0 && tempX!=7){

				//	System.out.println("Code reached here to process a TR jump");
				tempX=tempX+1;
				tempY=tempY-1;
				if(board.get("X"+tempX+tempY).equals('O')){
					//System.out.println("\t\t\t\t\t\tAdding jump move");
					jumpmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
					return true;
				}
			}
		}
		return false;

	}
	static boolean BRdiagonal_Open(int X,int Y,char alterego,char alterkingego,Map<String,Character> board,ArrayList<String> normalmoves,ArrayList<String> jumpmoves)
	{
		int tempX=X+1;
		int tempY=Y+1;
		//System.out.println("Checking position  BR for piece" + X+ " "+ Y+"| piece found "+board.get("X"+X+Y));
		if (board.get("X"+tempX+tempY).equals('O'))
		{
			//System.out.println("Adding nomal move");
			normalmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
			return true;
		}
		else if(board.get("X"+tempX+tempY).equals(alterego)||board.get("X"+tempX+tempY).equals(alterkingego))
		{ 
			if(tempY!=7 && tempX!=7){

				//	System.out.println("Code reached here");
				tempX=tempX+1;
				tempY=tempY+1;
				if(board.get("X"+tempX+tempY).equals('O')){
					//	System.out.println("\t\t\t\t\tAdding jump move");
					jumpmoves.add("("+X +","+Y+")"+"=>"+"("+tempX+","+tempY+")");
					return true;
				}
			}
		}
		return false;

	}

	static int evaluation(Map<String,Character> board){   //takes a board and returns its heuristic value

		int heuristic=0;
		int playerAcount=0;
		int playerBcount=0;
		for(int i = 0; i<8 ;i++)
		{
			for(int j = 0;j<8;j++)
			{
				char element =	board.get("X"+i+j);
				int value = element;

				switch(value){

				case 65:  //A
					playerAcount++;
					break;

				case 66: //B
					playerBcount++;
					break;

				case 75:   //K
					playerBcount++;
					playerBcount++;
					break;

				case 107: //k
					playerBcount++;
					playerBcount++;
					break;
				default:
					break;
				}//switch ends
			}//inner for ends
		}//outer for ends

		heuristic = playerAcount - playerBcount;
		return heuristic;
	}

	static void generatechildboards(Map<String,Character>[][] board,Map<String,String> previous,Map<String,String> movethatcreated,MultiMap childboards,int[][] alphatoroot,int[][]betatoroot,int depth,int currentboardatdepth,int newcurrentBatD,String move,boolean isjump){     //for a particular depth and board number  - either the normal moves array or the jump array depending on whether the relevant jump array is empty

		if(depth!=4){
			int i,j,x,y;  //i,j=>x,y	//Right now only a single move is passed so you will need to use a for loop to get all the relevant moves and call this function accordingly 
			int newdepth=depth+1;

			char kingpiece='x';
			i=Character.digit(move.charAt(1), 10);
			j=Character.digit(move.charAt(3), 10);
			x=Character.digit(move.charAt(8), 10);
			y=Character.digit(move.charAt(10), 10);
			board[newdepth][newcurrentBatD] = new TreeMap<String,Character>();
			board[newdepth][newcurrentBatD].clear();

			alphatoroot[newdepth][newcurrentBatD]=Integer.MIN_VALUE;

			betatoroot[newdepth][newcurrentBatD]=Integer.MAX_VALUE;

			for(int z=-1;z<=8;z++){
				for(int b=-1;b<=8;b++){
					board[newdepth][newcurrentBatD].put("X"+z+b, board[depth][currentboardatdepth].get("X"+z+b));
				}
			}
			//	board[newdepth][newcurrentBatD] = board[depth][currentboardatdepth];

			//System.out.println("Generating board number "+newdepth+" "+newcurrentBatD);

			String key = "B"+newdepth+"."+newcurrentBatD;
			String value = "B"+depth+"."+currentboardatdepth ;

			previous.put(key, value);       	//set the parent of the new board
			childboards.put(value, key);		//set the child boards
			movethatcreated.put(key, move);
			char piece = board[newdepth][newcurrentBatD].get("X"+i+j);
			//System.out.println("Well the code came by here atleast 1");

			int piecevalue = piece;
			switch(piecevalue){
			case 65:  //Check A
				kingpiece='k';
				break;
			case 66: //Check B
				kingpiece='K';
				break;
			default:
				break;
			}

			// you need to add the rule where if an opposing piece reaches the kings row it turns into a king  , this is the only place in the code where this can happen

			if(y==0 || y==7){

				piece=kingpiece;
			}

			board[newdepth][newcurrentBatD].put("X"+i+j, 'O'); //first clear the space the piece occupied


			//next check if it is a jump or a regular move
			if(isjump)
			{
				if(x+2==i && y+2==j ) //jumping to TL
				{
					int tempx=x+1;
					int tempy=y+1;
					board[newdepth][newcurrentBatD].put("X"+tempx+tempy, 'O'); //the jumped piece is replaced by an empty square
					board[newdepth][newcurrentBatD].put("X"+x+y,piece);
					//	System.out.println("\t\tJUMP MOVE HAPPENED!!!");
				}

				if(x-2==i && y+2==j ) //jumping to TR
				{
					int tempx=x-1;
					int tempy=y+1;
					board[newdepth][newcurrentBatD].put("X"+tempx+tempy, 'O'); //the jumped piece is replaced by an empty square
					board[newdepth][newcurrentBatD].put("X"+x+y,piece);
					//	System.out.println("\t\tJUMP MOVE HAPPENED!!!");
				}
				if(x+2==i && y-2==j ) //jumping to BL
				{
					int tempx=x+1;
					int tempy=y-1;
					board[newdepth][newcurrentBatD].put("X"+tempx+tempy, 'O'); //the jumped piece is replaced by an empty square
					board[newdepth][newcurrentBatD].put("X"+x+y,piece);
					//		System.out.println("\t\tJUMP MOVE HAPPENED!!!");
				}
				if(x-2==i && y-2==j ) //jumping to BL
				{
					int tempx=x-1;
					int tempy=y-1;
					board[newdepth][newcurrentBatD].put("X"+tempx+tempy, 'O'); //the jumped piece is replaced by an empty square
					board[newdepth][newcurrentBatD].put("X"+x+y,piece);


				}

				//remove the jumped piece
			}
			else{ //if it is a regular move 

				board[newdepth][newcurrentBatD].put("X"+x+y,piece);


			}



		}

	}
	static void border(Map<String,Character> board){ //takes a board and fills the borders
		for(int pos=-1;pos<9;pos++){
			board.put("X"+-1+pos,'+');
			board.put("X"+8+pos,'+');
			board.put("X"+pos+8,'+');
			board.put("X"+pos+-1,'+');
		}
	}

	static int maxvalue(String boardname,Map<String,Character>[][] board,Map<String,String> movethatcreated,ArrayList<String>[][] normalmoves,ArrayList<String>[][] jumpmoves,int depth,int alphavalues,int betavalues,MultiMap childboards,int[][] boardvalues,int player,PrintWriter out){
		player++;
		int var1=Character.getNumericValue(boardname.charAt(1));
		int var2=Integer.parseInt(boardname.substring(3));	
		int alpha=alphavalues;
		int beta=betavalues;
		

		if(!childboards.containsKey(boardname) || depth==4){                
			//System.out.println("Code reached inside max leaf check");
			int eval=evaluation(board[var1][var2]);
			boardvalues[var1][var2]=eval;

		

			if(eval>eva){
				Main.eva=eval;
				Main.nextmove="";
				

			}

			return eval;

		}
		else
			if(childboards.containsKey(boardname))
			{
				String[] adj = childboards.get(boardname).toString().replaceAll("[\\[,\\]]","").split(" ");

				try{
					//System.out.println("Code reached inside max get kids");
					int childvar1;
					int childvar2;


					for(int x = 0;x<adj.length;x++ ) //for each of the successors do 
					{
						String child = adj[x];


						childvar1=Character.getNumericValue(child.charAt(1));
						childvar2=Integer.parseInt(child.substring(3));



						for(int level=0;level<depth;level++)
						{
							out.print("|-");
						}
						String move=movethatcreated.get(child);
						String fromone=move.substring(1, 2);
						String fromtwo=move.substring(3, 4);
						String toone=move.substring(8, 9);
						String totwo=move.substring(10, 11);
						//String printing="A"+(depth+1)+":  "+"("+fromtwo+","+fromone+")=>("+totwo+","+toone+").";

						out.print("A"+(depth+1)+":  "+"("+fromtwo+","+fromone+")=>("+totwo+","+toone+").\n");


						if(depth==0){ //need to get move from here
							Main.movethisrun="("+fromtwo+","+fromone+")=>("+totwo+","+toone+")";
							try{
							if(Main.nextmove.isEmpty())
								Main.nextmove="("+fromtwo+","+fromone+")=>("+totwo+","+toone+")";
							}
							catch(NullPointerException e){
								e.printStackTrace();
							}
						}

						alpha=Math.max(alpha, minvalue(child,board,movethatcreated,normalmoves,jumpmoves,depth+1,alpha,beta,childboards,boardvalues,player,out));







						if(beta<=alpha){
							if(adj.length!=0){
								for(int level=x;level<depth;level++)
								{

									out.print("|-");
								}

								out.print("B"+(depth+1)+":  pruning ");
								for(int pot = 0;pot<adj.length;pot++ ) //for each of the successors do 
								{


									move=movethatcreated.get(adj[pot]);
									fromone=move.substring(1, 2);
									fromtwo=move.substring(3, 4);
									toone=move.substring(8, 9);
									totwo=move.substring(10, 11);
									out.print("("+fromtwo+","+fromone+")=>("+totwo+","+toone+"),");
								}
								out.print(" alpha="+alpha+","+"beta="+beta+".\n");
							}





							return beta;
						}
					}

				}
				catch(NullPointerException e){
					e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}

			}

		return alpha;
	}



	static int minvalue(String boardname,Map<String,Character>[][] board,Map<String,String> movethatcreated,ArrayList<String>[][] normalmoves,ArrayList<String>[][] jumpmoves,int depth,int alphavalues,int betavalues,MultiMap childboards,int[][] boardvalues,int player,PrintWriter out){


		int alpha=alphavalues;
		int beta=betavalues;

		int var1=Character.getNumericValue(boardname.charAt(1));
		int var2=Integer.parseInt(boardname.substring(3));	

		if(!childboards.containsKey(boardname) || depth==4){
			//	System.out.println("Code reached inside min leaf check");
			int eval=evaluation(board[var1][var2]);
			boardvalues[var1][var2]=eval;


			for(int level=0;level<depth-2;level++)
			{

				out.print("|-");
			}
			String move=movethatcreated.get(boardname);
			String fromone=move.substring(1, 2);
			String fromtwo=move.substring(3, 4);
			String toone=move.substring(8, 9);
			String totwo=move.substring(10, 11);


			out.print("B"+depth+":  "+"("+fromtwo+","+fromone+")=>("+totwo+","+toone+");  h="+eval+"\n");


			return eval;

		}
		else
			if(childboards.containsKey(boardname)){
				String[] adj = childboards.get(boardname).toString().replaceAll("[\\[,\\]]","").split(" ");

				try{
					//	System.out.println("Code reached inside min get kids");
					int childvar1;
					int childvar2;
					for(int x = 0;x<adj.length;x++ ) //for each of the successors do 
					{
						String child = adj[x];
						childvar1=Character.getNumericValue(child.charAt(1));
						childvar2=Integer.parseInt(child.substring(3));


						for(int level=0;level<depth;level++)
						{

							out.print("|-");
						}
						String move=movethatcreated.get(child);
						String fromone=move.substring(1, 2);
						String fromtwo=move.substring(3, 4);
						String toone=move.substring(8, 9);
						String totwo=move.substring(10, 11);

						if(depth!=3)
							out.print("B"+(depth+1)+":  "+"("+fromtwo+","+fromone+")=>("+totwo+","+toone+").\n" );

						if(depth==3){  //need to get value from here
							int eval=evaluation(board[var1][var2]);
							if(eval>Main.eva)
							{
								Main.eva=eval;
								Main.nextmove=Main.movethisrun;
							}
							out.print("B"+(depth+1)+":  "+"("+fromtwo+","+fromone+")=>("+totwo+","+toone+");  h="+eval+"\n" );
						}

						//			
						beta=Math.min(beta, maxvalue(child,board,movethatcreated,normalmoves,jumpmoves,depth+1,alpha,beta,childboards,boardvalues,player,out));




						if(beta<=alpha)
						{	
							if(adj.length!=0){
								for(int level5=0;level5<depth;level5++)
								{

									out.print("|-");
								}

								out.print("B"+(depth+1)+":  pruning ");
								for(int pot = 0;pot<adj.length;pot++ ) //for each of the successors do 
								{
									move=movethatcreated.get(adj[pot]);
									fromone=move.substring(1, 2);
									fromtwo=move.substring(3, 4);
									toone=move.substring(8, 9);
									totwo=move.substring(10, 11);
									out.print("("+fromtwo+","+fromone+")=>("+totwo+","+toone+"),");
								}
								out.print(" alpha="+alpha+","+"beta="+beta+".\n");
							}
							return alpha;
						}
					}

				}
				catch(NullPointerException e){
					e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}

			}

		return beta;

	}

}
