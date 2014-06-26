import java.awt.*;
import java.awt.Panel;
import java.awt.Scrollbar.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.sql.*;

class MsgBox extends Dialog implements ActionListener {
    private Button ok,can;
    public boolean isOk = false;

    /*
     * @param frame   parent frame 
     * @param msg     message to be displayed
     * @param okcan   true : ok cancel buttons, false : ok button only 
     */
    MsgBox(Frame frame, String msg, boolean okcan){
        super(frame, "Message", true);
        setLayout(new BorderLayout());
        add("Center",new Label(msg));
        addOKCancelPanel(okcan);
        createFrame();
        pack();
        setVisible(true);
    }
    
    MsgBox(Frame frame, String msg){
        this(frame, msg, false);
    }
    
    void addOKCancelPanel( boolean okcan ) {
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        createOKButton( p );
        if (okcan == true)
            createCancelButton( p );
        add("South",p);
    }

    void createOKButton(Panel p) {
        p.add(ok = new Button("OK"));
        ok.addActionListener(this); 
    }

    void createCancelButton(Panel p) {
        p.add(can = new Button("Cancel"));
        can.addActionListener(this);
    }

    void createFrame() {
        Dimension d = getToolkit().getScreenSize();
        setLocation(d.width/3,d.height/3);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == ok) {
            isOk = true;
            setVisible(false);
        }
        else if(ae.getSource() == can) {
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        Frame f = new Frame();
        f.setSize(200,200);
        f.setVisible(true);
               
        
      
        
    }
}






//NEW CLASS BEGINS****


class gui extends WindowAdapter implements ActionListener,Runnable,Serializable
{
//Variables for first window

String pass_word="";
String usr_name="";

int countError=0,checker;
Frame frame1;
Panel pan1;

Label label1,label2,label3;
TextField usr,ip_Var,pwd;


Button b1;



//Variables for second window



Frame frm;
Panel p;
TextField txtip;

Label lbl,lbl1;
Button b,bcon;
TextArea txta,txtb;

Label lbl2;
Label lbl3;
Label lbl4;
Socket sc;
PrintStream ps;
BufferedReader br1;
Scrollbar bar;


//constructor 1 login frame 


public gui(int q)

	{

	frame1=new Frame("Login");
	pan1=new Panel();

	usr=new TextField(10);
	pwd=new TextField(10);
	pwd.setEchoChar('*');
	ip_Var=new TextField(10);

	label1=new Label("Username:");
	label2=new Label("Password:");
	label3=new Label("ServerIP:");
	b1=new Button("Sumbit");



	pan1.setLayout(new FlowLayout());
                                                          
	label1.setLocation(50,50);
	label1.setSize(100,100);
	pan1.add(label1);


	usr.setLocation(125,90);
	usr.setSize(75,25);
	pan1.add(usr);


	label2.setLocation(50,100);
	label2.setSize(100,100);
	pan1.add(label2);


	pwd.setLocation(125,140);
	pwd.setSize(75,25);
	pan1.add(pwd);

	label3.setLocation(50,155);
	label3.setSize(100,100);
	pan1.add(label3);

	ip_Var.setLocation(125,200);
	ip_Var.setSize(75,25);
	pan1.add(ip_Var);


	b1.setLocation(120,300);
	b1.setSize(75,25);
	pan1.add(b1);
	b1.addActionListener(this);


	frame1.show();
	frame1.setSize(700,100);
	frame1.setLocation(500,100);



	frame1.add(pan1);
	frame1.addWindowListener(this);
	

	

}
//end constructor 1


//constructor 2 for messenger frame


public gui(String confirmation_message)

	{

	frm=new Frame("Instant Messenger");
	lbl1=new Label("Ip Address");

	p=new Panel();

	txtb=new TextArea(2,2);
	bar=new Scrollbar(Scrollbar.VERTICAL,0,1,0,255);
	p.add(bar);
	txtip=new TextField(50);
	lbl=new Label("Message:");
	

	lbl2=new Label("Chat Box:");

	b=new Button("Send");
	bcon=new Button("Connect");
	txta=new TextArea(5,5);
	txta.setEditable(false);




	System.out.println("System Says:" +confirmation_message);
	
	p.setLayout(null);

	lbl1.setLocation(95,7);
	lbl1.setSize(100,100);
	p.add(lbl1);
	
	txtip.setLocation(195,42);
	txtip.setSize(175,25);
	p.add(txtip);

	bcon.setLocation(400,42);
	bcon.setSize(100,25);
	bcon.addActionListener(this);
	p.add(bcon);

	lbl2.setLocation(95,136);
	lbl2.setSize(100,100);
	p.add(lbl2);
	
	txta.setLocation(100,200);
	txta.setSize(400,200);
	p.add(txta);

	lbl.setLocation(95,400);
	lbl.setSize(100,100);
	p.add(lbl);

	txtb.setLocation(95,475);
	txtb.setSize(500,80);
	p.add(txtb);
	


	b.setLocation(600,520);
	b.setSize(75,25);
	b.setBackground(SystemColor.controlDkShadow);
b.addActionListener(this);
	p.add(b);


	

	frm.show();
	frm.setSize(720,600);
	frm.setLocation(200,0);
	p.setBackground(Color.yellow);
	p.repaint();


	frm.add(p);
	frm.addWindowListener(this);
     

}
public void run()	
{

try
			{
			
	
		 sc=new Socket("localhost",56743);
		
		InputStreamReader In=new InputStreamReader(sc.getInputStream());
		br1=new BufferedReader(In);
		
		ps=new PrintStream(sc.getOutputStream()); 
		ps.println("Trying to connect");
			for(;;)
			{
				txta.setText(txta.getText()+"\n"+br1.readLine());
			}
		}
		catch(Exception e)
{
System.out.println(e);
}
}
	


	

//authentication + database connectivity 
/**
public void authenticate()
{
try
{

usr_name=usr.getText();
pass_word=String.valueOf(pwd.getPassword());
System.out.println(pass_word);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("jdbc:odbc:mydsn");


PreparedStatement pstat=con.prepareStatement("select * from useraccounts where UserName='"+usr_name+"' and password='"+ pass_word+"'");

ResultSet rs;
rs=pstat.executeQuery();
while(rs.next())
{
checker=1;
System.out.println("System says:Login Successful!");

}
if (checker==0)
{

System.out.println("System says:Incorrect password!");
countError=countError+1;

}

}
catch (Exception e)
{
System.out.println(e);

}

if(countError>3)
{

MsgBox message = new MsgBox(frame1 , "Hey you user, are you sure ?", false);
        
        if (message.isOk) 
{
	message.dispose();
	System.exit(0);
}

}




}
*/

//all button actions


public void actionPerformed(ActionEvent et)
	{

		if(et.getSource()==bcon)
		{
		String p_Var=txtip.getText();
		

	
			
		}

else if (et.getSource()==b1)
{

//authenticate();
checker=1;
	if(this.checker==1)
{

	gui secondwindow=new gui("\n\n\tStatus:Online!");
Thread threadgui=new Thread(secondwindow);
threadgui.start();
System.out.println("System says:thread has begun!");

frame1.hide();			
	
}





}



	else if(et.getSource()==b)
	{ 


try
{
System.out.println(txtb.getText());
ps=new PrintStream(sc.getOutputStream()); 
		String s1=txtb.getText();
		ps.println(s1);
		txtb.setText(" ");
}
catch(Exception e)
{}


	}

	
}



//window closing  event

		public void windowClosing(WindowEvent e)
{
		System.exit(0);
}

	

}
//the main class

public class themain 
{

	public static void main(String ar[])
	{
	gui firstwindow=new gui(5);
	
}

	}








