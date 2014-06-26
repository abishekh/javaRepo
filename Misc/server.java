import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
class server extends Frame implements ActionListener
{
TextField tf;
PrintStream ps;
BufferedReader br1;
server()
{
Button b1;
TextArea ta=new TextArea(10,30);
setLayout(new FlowLayout());
b1=new Button("Submit");
tf=new TextField(20);
add(ta);
add(tf); 
add(b1);
setTitle("Server");
ta.setEditable(false);
setSize(300,300);
b1.addActionListener(this);

show();

try
{
ServerSocket so=new ServerSocket(56743);
ta.setText("Server ready");
//when a server secures a connection from the client, the accept() method of ServerSocket class accepts the connection
Socket sc=so.accept();
//getInputStream()-reading from the socket
InputStreamReader In=new InputStreamReader(sc.getInputStream());
br1=new BufferedReader(In);//BufferedReader()-to read from the socket
//getOutputStream()-writing to a socket
ps=new PrintStream(sc.getOutputStream()); //PrintStream() write into a socket
ps.println("Connection ok");
for(;;)
{
ta.setText(ta.getText()+"\n"+br1.readLine());
}
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent e)
{
String s1=tf.getText();
ps.println(s1);
tf.setText(" ");
}
public static void main(String s[])
{
new server();
}
}