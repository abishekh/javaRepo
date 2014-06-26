import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Scrollbar.*;

public class client extends Frame implements ActionListener
{
TextField t1;
TextArea t;
Scrollbar redSlider;
PrintStream ps;//write into a socket
BufferedReader br1;//read from a socket
public client()
{
t=new TextArea(10,30);
setLayout(new FlowLayout());
Button b1=new Button("Submit");
t1=new TextField(20);
redSlider=new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 255);
 add(redSlider);
 
add(t);
add(t1); 
add(b1);
setTitle("Client");
t.setEnabled(false);
setSize(300,300);
show();
b1.addActionListener(this);
try
{
Socket sc=new Socket("localhost",56743);
//getInputStream()-reading from a socket
InputStreamReader In=new InputStreamReader(sc.getInputStream());
br1=new BufferedReader(In);
//PrintStream()-writing into a socket
ps=new PrintStream(sc.getOutputStream()); 
ps.println("Trying to connect");
for(;;)
{
t.setText(t.getText()+"\n"+br1.readLine());
}
}
catch(Exception e){System.out.println(e);}
}

public void actionPerformed(ActionEvent e)
{
String s1=t1.getText();
ps.println(s1);
t1.setText(" ");
}
public static void main(String s[])
{
client s21=new client();
}
}