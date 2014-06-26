import javax.swing.*;
import java.awt.*;

class messenger
{
JFrame frm;
JPanel p;
JTextField txtb;

JLabel lbl;
JButton b;
JTextArea txta;

JLabel lbl2;
JLabel lbl3;
JLabel lbl4;
JScrollBar scrbar;

public messenger()
{
System.out.println("hello");
frm=new JFrame("Instant Messenger");

p=new JPanel();

txtb=new JTextField(50);
usr=new JTextField(10);
lbl=new JLabel("Message:");
scrbar=new JScrollBar();

lbl2=new JLabel("Chat Box:");

b=new JButton("Send");

txta=new JTextArea(5,5);

pwd=new JPasswordField(6);

p.setLayout(null);

lbl2.setLocation(95,42);
lbl2.setSize(100,100);
p.add(lbl2);

txta.setLocation(95,100);
txta.setSize(500,250);
p.add(txta);

lbl.setLocation(95,400);
lbl.setSize(100,100);
p.add(lbl);

txtb.setLocation(95,475);
txtb.setSize(500,80);
p.add(txtb);



b.setLocation(600,520);
b.setSize(75,25);
p.add(b);


frm.show();
frm.setSize(200,200);



frm.getContentPane().add(p);


}

class start
{
JFrame frame1;
JPanel pan1;

JLabel lbl1;
JTextField usr;
JLabel lbl2;
JPassword pwd;

public start()

{
frame1=new JFrame("Login");
pan1=new JPannel();
usr=new JtextField(10);
pwd=new JPassword(6);
lbl1=new JLabel("Username:");
lbl2=new Jlabel("Password:");
pan1.setLayout(null);

lbl1.setLocation(50,50);
lbl1.setSize(100,100);
pan1.add(lbl1);


usr.setLocation(75,50);
usr.setSize(100,100);
pan1.add(usr);


lbl2.setLocation(50,75);
lbl2.setSize(100,100);
pan1.add(lbl2);


pwd.setLocation(75,75);
pwd.setSize(100,100);
pan1.add(pwd);

}
}
class go()
{
public static void main(String ar[])
{
start obj=new start();
}
}
