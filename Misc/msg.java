import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


class messenger extends WindowAdapter implements ActionListener,Thread 
{



JFrame frm;
JPanel p;
JTextField txtb,txtip;

JLabel lbl,lbl1;
JButton b,bcon;
JTextArea txta;

JLabel lbl2;
JLabel lbl3;
JLabel lbl4;
JScrollBar scrbar;
PrintStream ps;
BufferedReader br1;
	public messenger()
	{


	System.out.println("hello");
	frm=new JFrame("Instant Messenger");
	lbl1=new JLabel("Ip Address");

	p=new JPanel();

	txtb=new JTextField(50);
	txtip=new JTextField(50);
	lbl=new JLabel("Message:");
	scrbar=new JScrollBar();

	lbl2=new JLabel("Chat Box:");

	b=new JButton("Send");
	bcon=new JButton("Connect");
	txta=new JTextArea(5,5);
	txta.setEnabled(false);


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
	p.add(b);


	

	frm.show();
	frm.setSize(720,600);
	frm.setLocation(200,0);
	p.setBackground(Color.yellow);
	p.repaint();


	frm.getContentPane().add(p);
	frm.addWindowListener(this);

public void actionPerformed(ActionEvent et)
	{

		if(et.getSource()==bcon)
		{
		String p_Var=txtip.getText();
		

	
			
		}

		if(et.getSource()==b)
		{
		String s1=txtb.getText();
		ps.println(s1);
		txtb.setText(" ");
		}

	}
		public void windowClosing(WindowEvent e)
		{
		System.exit(0);
		}


public void run()
{

	try
			{
			
	
			Socket sc=new Socket("127.0.0.1",631);
		//getInputStream()-reading from a socket
		InputStreamReader In=new InputStreamReader(sc.getInputStream());
		br1=new BufferedReader(In);
		//PrintStream()-writing into a socket
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

}

	
	

}


public class msg extends WindowAdapter implements ActionListener,Thread
{

int checker,countError=0;;
String pass_word="";
String usr_name="";


JFrame frame1;
JPanel pan1;

JLabel lbl1;
JTextField usr,ip_Var;
JLabel lbl2,lbl3;
JPasswordField pwd;
JButton b1;


 
public void authenticate()
{

if(String.valueOf(pwd.getPassword()=="pass")
{
checker=1;
} 


else
{
countError=countError+1;
System.out.println(pass_word);
JOptionPane.showMessageDialog(null,"Password Incorrect!!");

if(countError>3)
{

JOptionPane.showMessageDialog(null,"Password has been input incorrectly more than 3 times;Do not abuse my software!! ");

System.exit(0);

}

}


}





	public msg()
	{
	frame1=new JFrame("Login");
	pan1=new JPanel();

	usr=new JTextField(10);
	pwd=new JPasswordField(6);
	ip_Var=new JTextField(10);

	lbl1=new JLabel("Username:");
	lbl2=new JLabel("Password:");
	lbl3=new JLabel("ServerIP:");
	b1=new JButton("Sumbit");

	pan1.setLayout(null);
                                                          
	lbl1.setLocation(50,50);
	lbl1.setSize(100,100);
	pan1.add(lbl1);


	usr.setLocation(125,90);
	usr.setSize(75,25);
	pan1.add(usr);


	lbl2.setLocation(50,100);
	lbl2.setSize(100,100);
	pan1.add(lbl2);


	pwd.setLocation(125,140);
	pwd.setSize(75,25);
	pan1.add(pwd);

	lbl3.setLocation(50,155);
	lbl3.setSize(100,100);
	pan1.add(lbl3);

	ip_Var.setLocation(125,200);
	ip_Var.setSize(75,25);
	pan1.add(ip_Var);


	b1.setLocation(120,300);
	b1.setSize(75,25);
	pan1.add(b1);
	b1.addActionListener(this);


	frame1.show();
	frame1.setSize(200,500);
	frame1.setLocation(500,100);



	frame1.getContentPane().add(pan1);
	frame1.addWindowListener(this);
	usr_name=usr.getText();
	pass_word=String.valueOf(pwd.getPassword());
	System.out.println(pass_word);
	}

	public void actionPerformed(ActionEvent et)
	{  authenticate();
if(checker==1)
{
		messenger message=new messenger();
		frame1.hide();
}
	}

	



	public void windowClosing(WindowEvent e)
	{
	System.exit(0);
	}

	public static void main(String ar[])
	{	
	start obj=new start();
	}




}










