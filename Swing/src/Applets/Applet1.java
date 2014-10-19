package Applets;
import javax.swing.*;
import java.awt.*;

public class Applet1 extends JApplet{
	private JButton
	    b1=new JButton("Button 1"),
	    b2=new JButton("Button 2");
	public void init(){
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(b1);
		cp.add(b2);
	}
	public static void main(String[] args){
        Console.run(new Applet1(), 500, 500);
	}

}
