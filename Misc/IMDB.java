    import java.util.regex.*;
    import java.util.Scanner;
    import java.util.HashMap;
    import java.util.ArrayList;
   


    class IMDB
    {
        public static void main(String[] args)
        {

    /********************************************
    * This space for testing
    *********************************************/

    // String txt="SET n 200";

    /*******************************************
    *	Regex for each command defined
    ********************************************/

    final String set="(SET)\\s([a-z]+)\\s([0-9]+)"; // SET Command
    final String get= "(GET)\\s([a-z]+)"; //GET Command
    final String unset= "(UNSET)\\s([a-z]+)"; //UNSET Command
    final String numequalto="(NUMEQUALTO)\\s([0-9]+)"; //NUMEQUALTO Command
    final String end="(END)"; //END Command
    final String begin="(BEGIN)"; //Begin Command
    final String rollback="(ROLLBACK)"; //ROLLBACK Command'
    final String commit="(COMMIT)"; //COMMIT Command


    /*************************************************
    *  Pattern Objects to compile the Regex from above
    **************************************************/

    Pattern p_set = Pattern.compile(set,Pattern.DOTALL);
    Pattern p_get = Pattern.compile(get,Pattern.DOTALL);
    Pattern p_unset = Pattern.compile(unset,Pattern.DOTALL);
    Pattern p_net= Pattern.compile(numequalto,Pattern.DOTALL);
    Pattern p_end = Pattern.compile(end,Pattern.DOTALL);
    Pattern p_begin = Pattern.compile(begin,Pattern.DOTALL);
    Pattern p_roll = Pattern.compile(rollback,Pattern.DOTALL);
    Pattern p_commit = Pattern.compile(commit,Pattern.DOTALL);



    /******************************************************************************************************************
    *  Matchers - This is just one of several approaches to extract tokens and values.
    *             One could easily use delimiters to achieve this through the Scanner itself. 
    *             I use this approach because it makes condition checking easier.
    *******************************************************************************************************************/
    Matcher m_set;
    Matcher m_get;
    Matcher m_unset;
    Matcher m_net;
    Matcher m_end;
    Matcher m_begin;
    Matcher m_roll;
    Matcher m_commit;

    /**************************************************************************************************************
    *   Data Structures 

    I've use HashMap since it has O(1) for the 'get' and 'put' methods under the assumption that the the hash function disperses the elements properly among the buckets.
    The performance considerations state that:
    'The vast majority of transactions will only update a small number of variables'

    If there was frequent restructuring of the HashMap during SET, we could use a TreeMap instead which has a gauranteed O(log n) for its methods.
    Also the TreeMap would be advantageous from a space conservation standpoint because it is not allocated in blocks like the HasHMap.

    *****************************************************************************************************************/

    Scanner scan = new Scanner(System.in);



    /*************************************************************************************************************************
    ArrayList 'add' has amortized constant time since it may need to be grown in size if there are too many elements to store.
    Using 'set' does not immediately help us avoid this because we would have to keep track of the freed up locations to make 
    things space efficient and subsequently time efficient.
    **************************************************************************************************************************/

    ArrayList<HashMap<String,Integer>> countmachine = new ArrayList<HashMap<String,Integer>>();

    ArrayList<HashMap<String,String>> timemachine = new ArrayList<HashMap<String,String>>();

    /********************************* 
        System is initialized here 
    **********************************/

    int workingindex = 0;   // 0 is the default workspace index and the commit space index since any data command that is run outside of a transaction block should 'commit immediately' as stated in the Problem.

    timemachine.add(new HashMap<String,String>());
    countmachine.add(new HashMap<String,Integer>());


    //This is the primary input loop
    while(scan.hasNextLine()){

    /******************************************************
    * Feeding all the Matchers
    *******************************************************/
    String temp = scan.nextLine();

    m_set= p_set.matcher(temp);
    m_get=p_get.matcher(temp);
    m_unset=p_unset.matcher(temp);
    m_net=p_net.matcher(temp);
    m_end=p_end.matcher(temp);
    m_begin=p_begin.matcher(temp);
    m_roll=p_roll.matcher(temp);
    m_commit=p_commit.matcher(temp);


    if(m_end.find()){
        onEnd();

    }

    else if(m_set.find()){
        onSet( ((HashMap<String,String>)timemachine.get(workingindex)),
        m_set.group(2),m_set.group(3),
        ((HashMap<String,Integer>)countmachine.get(workingindex)));
    }
    else if(m_get.find()){
        onGet(((HashMap<String,String>)timemachine.get(workingindex)),m_get.group(2));

    }
    else if(m_unset.find()){
        onUnset(((HashMap<String,String>)timemachine.get(workingindex)),
        m_unset.group(2),
        ((HashMap<String,Integer>)countmachine.get(workingindex)));
    }
    else if(m_begin.find()){
        onBegin(workingindex,timemachine,countmachine);
        workingindex++;
    }
    else if(m_net.find()){
        onNet(m_net.group(2),countmachine,workingindex);

    }
    else if(m_roll.find()){
        onRoll(workingindex,timemachine,countmachine);
        workingindex--;
     }
    else if(m_commit.find()){
        onCommit(workingindex,timemachine,countmachine);
        workingindex=0;
    }

    else{
        System.out.println("\t\tReceived bad syntax. "+ temp);
     }


}
scan.close(); 

   }//main ends


    static void onSet(HashMap<String,String> workingmem,String name,String value,HashMap<String,Integer> count){


    /************************************************************************************************************************************************
    Since the Problem states that vast majority of operations will be updating only a small number of variables we can use a HashMap 'put' operation.
    Although this runs the risk of turning into O(n) if the size of the HashMap is exceeded and calls for restructuring the HashMap.
    The alternate approach would be to use a TreeMap which has a gauranteed O(log n). It comes down to deciding what you wish to trade off
    *************************************************************************************************************************************************/ 

    workingmem.put(name,value);  
    if(count.containsKey(value)){
        count.put(value,(count.get(value)+ 1));
    }
    else{
        count.put(value,1);
    }
    //till here operations are O(1) if the initial HashMap size (16) has NOT been exceeded.

};


static void onGet(HashMap<String,String> workingmem,String name){

        System.out.print("\t\t"+workingmem.get(name)+"\n"); //HashMap Operation of O(1)

    };

static void onUnset(HashMap<String,String> workingmem,String name,HashMap<String,Integer> count){

    String tempval=workingmem.get(name);               //HashMap Operation of O(1)
    int tempcount = count.get(tempval)- 1;             //HashMap Operation of O(1)
    count.put(tempval,tempcount);                       //HashMap Operation of O(1)
    workingmem.remove(name);                            //HashMap Operation of O(1)

};


static void onEnd(){
    System.exit(0);
};  


static void onNet(String value,ArrayList<HashMap<String,Integer>> countmachine,int workingindex){
    System.out.println(countmachine.get(workingindex).get(value));
};  



static void onBegin(int workingindex,ArrayList<HashMap<String,
String>> timemachine,ArrayList<HashMap<String,Integer>> countmachine){
        //Begin needs to hava copy of the previous HashMap

    //create new HashMapObject
    timemachine.add(new HashMap<String,String>());
    countmachine.add(new HashMap<String,Integer>());

    //Copy over previous values
    (timemachine.get(workingindex+1)).putAll(timemachine.get(workingindex));
    (countmachine.get(workingindex+1)).putAll(countmachine.get(workingindex));

};  

static void onRoll(int workingindex,ArrayList<HashMap<String,
String>> timemachine,ArrayList<HashMap<String,Integer>> countmachine){
    if(workingindex==0)
    {
        System.out.println("\t\tNO TRANSACTION");
    }
    else{
            //clear the workingmem
        timemachine.remove(workingindex);
        countmachine.remove(workingindex);

    }

};  

static void onCommit(int workingindex,ArrayList<HashMap<String,
String>> timemachine,ArrayList<HashMap<String,Integer>> countmachine){
    
    //reset the workingindex and keep whatever is in workingindex currently
    
    HashMap<String,String> tempmap = new HashMap<String,String>();
    HashMap<String,Integer> tempcount = new HashMap<String,Integer>();

    tempmap.putAll(timemachine.get(workingindex));

    tempcount.putAll(countmachine.get(workingindex));

    timemachine.clear();
    countmachine.clear();

        //Create object at zero
    timemachine.add(new HashMap<String,String>());
    countmachine.add(new HashMap<String,Integer>());

        //copy currentworking index Maps to 0
    (timemachine.get(0)).putAll(tempmap);
    (countmachine.get(0)).putAll(tempcount);




};

    }//class ends
