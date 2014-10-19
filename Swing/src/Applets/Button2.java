package Applets;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Button2 extends JApplet{
	private JButton
	b1= new JButton("Button 1"),
	b2= new JButton("Button 2");
	
	private JTextField txt=new JTextField(10);
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name=((JButton)e.getSource()).getText();
			txt.setText(name);
		}
	}
	private ButtonListener bl=new ButtonListener();
	public void init(){
		b1.addActionListener(bl);
		b2.addActionListener(bl);
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(b1);
		cp.add(b2);
		cp.add(new JScrollPane(txt));
	}
	public static void main(String[] args){
        Console.run(new Button2(), 1200, 500);
	}
	
	

}
