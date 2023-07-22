import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class WrongReg extends Exception
{
	String str="Name already exists";
	public String toString() {
		return this.str;
	}
	public String getMessage()
	{
		return this.str;
	}
}
class WrongAge extends Exception
{
	String str="Invalid age";
	public String toString() {
		return this.str;
	}
	public String getMessage()
	{
		return this.str;
	}
}
class Donor
{
	String name,gender,age,bGroup,city,number;
	Donor(String name,String gender,String age,String bgroup,String city,String number)
	{
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.bGroup=bgroup;
		this.city=city;
		this.number=number;
	}
}
class bloodDonor extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField tf1,tf2,tf3,tf4;
	JList<String> list;
	JRadioButton rb1,rb2;
	JButton b1,b2,b3;
	JTextArea ta1;
	JScrollPane ps;
	Donor dn;
	ArrayList<Donor> al=new ArrayList<Donor>();
	void addDonor(Donor bd)
	{
		al.add(bd);
	}
	double checkDonor(String n) throws WrongReg
	{
		for(Donor d:al)
		{
			if(d.name.equals(n))
			{
				throw new WrongReg();
			}
			else{
				return 1;
			}
		}
		return 0;
	}
	double checkage(double age) throws WrongAge
	{
		if(age>18 && age<60)
		{
			return 1;
		}
		else
	    {
		throw new WrongAge();
		}
	}
	bloodDonor()
	{
		l1=new JLabel("Name");
		l1.setBounds(50,100,75,35);
		tf1=new JTextField();
		tf1.setBounds(150,100,150,35);
		l2=new JLabel("Gender");
		l2.setBounds(50,150,75,35);
		ButtonGroup bg=new ButtonGroup();
	    rb1=new JRadioButton("Male");
		rb1.setBounds(150,150,75,35);
		rb2=new JRadioButton("Female");
		rb2.setBounds(250,150,75,35);
		bg.add(rb1); bg.add(rb2);
		l3=new JLabel("Age");
		l3.setBounds(50,200,75,35);
		tf2=new JTextField();
		tf2.setBounds(150,200,100,35);
		l4=new JLabel("Blood Group");
		l4.setBounds(50,250,130,35);
		String s[]={"O+","O-","AB+","AB-","A+","A-","B+","B-"};
		list=new JList<>(s);
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		list.setBounds(200,250,100,200);
		l5=new JLabel("City");
		l5.setBounds(50,500,75,35);
		tf3=new JTextField();
		tf3.setBounds(150,500,150,35);
		l6=new JLabel("Contact Number");
		l6.setBounds(50,550,125,35);
		tf4=new JTextField();
		tf4.setBounds(200,550,150,35);
		b1=new JButton("Register");
		b1.setBounds(50,600,100,35);
		b1.addActionListener(this);
		b2=new JButton("Search");
		b2.setBounds(200,600,100,35);
		b2.addActionListener(this);
		ta1=new JTextArea();
		ps = new JScrollPane(ta1);
		ps.setBounds(400,200,300,300);
		ta1.setEditable(false);
		b3=new JButton("Display");
        b3.setBounds(400,100,100,35);
		b3.addActionListener(this);
		add(l1); add(l2); add(l3); add(l4); add(l5); add(l6);
		add(tf1); add(tf2); add(tf3); add(tf4);
		add(b1); add(b2); add(b3); add(rb1); add(rb2);
		add(list); add(ps);
		setSize(300,300);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
        
		try{
			if(e.getSource()==b1)
		   {
			String name=tf1.getText();
		    String gender="Male";
		    String age=tf2.getText();
			double ag=Double.parseDouble(age);
		    String bGroup=list.getSelectedValue();
		    String city=tf3.getText();
		    String number=tf4.getText();
		    dn=new Donor(name,gender,age,bGroup,city,number);
		    double d1=checkDonor(name);
            double d2=checkage(ag);
            addDonor(dn);			
		}}  
		
		catch(WrongReg w)
		{
			JOptionPane.showMessageDialog(null,w.getMessage());
						return;
		}
		catch(WrongAge a)
		{
			JOptionPane.showMessageDialog(null,a.getMessage());
						return;
		}
	
	if(e.getSource()==b2)
	{
	  String bGroup=list.getSelectedValue();
		String s1="";
		for(Donor dd : al)
		{
			if(dd.bGroup==bGroup)
			{
				s1+="\nDonor name : "+dd.name+"\nContact number : "+dd.number;
			}
		}
		ta1.setText(s1);
	}
	if(e.getSource()==b3)
	{
		String s="";
		for(Donor dl : al)
		{
			s+="\nDonor name is "+dl.name+"\n"+"Gender is "+dl.gender+"\n"
			           +"Age is "+dl.age+"\n"+"Blood Group is : "+dl.bGroup+"\n"
					   +"City is "+dl.city+"\n"+"Contact Number : "+dl.number+"\n";
	       
		}
		 ta1.setText(s);
	}
	}
	public static void main(String args[])
	{
		new bloodDonor();
	}
	
}