README file 

Requirements for compilation  of CS561-HW1 :
1. Eclipse or any other project manager 
2. 'org.apache.commons.collections' package (should be included with the submitted files ,but just incase they arent, the jars from org.apache.commons.collections need to be added to ('Run Configurations...' under Class Path tab  > User Entries).

If you aren't comfortable using a project manager and want to do it oldschool , you need to point the path to the org.apache.commons.collections so that the compiler can reference it. 


3. The program takes command line arguments , if you are using Eclipse , the instructions for setting them are :
	a. Click the run drop down icon (green play symbol)
	b. Click 'Run Configurations...' and they select Java Application from the list on the left
	c. Click the '(x)= Arguments' tab
	d.The format for the commandline arguments are as <input file path >space<start node>space<Goal node>
		  for example : /Users/abishekh/Documents/CS561-HW1/social-network.txt Alice Noah
		  
4.Now run the program

5.Upon successful run , you should see :
	Start Node: Alice
	Goal Node: Noah
	Breadth First Search finished successfully!
	Depth First Search finished successfully!
	Uniform Cost search for time value finished successfully!
	Uniform Cost search for risk value finished successfully!	

6.The output files :
	1. breadth-first.result.txt
	2. depth-first.result.txt
	3. uniform-cost.time.result.txt 
	4. uniform-cost.risk.result.txt	  

are generated in the root folder of the project , navigate to the location of the project folder after successful execution. Or if you are using Eclipse , right click the project folder in the Workspace and Refresh.They should show up within the Workspace and can be viewed from Eclipse itself.





		  
		  