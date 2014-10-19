package Applets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.*;


public class Buttons extends JApplet{
	private JButton jb=new JButton("JButton");
	private BasicArrowButton up=new BasicArrowButton(BasicArrowButton.NORTH);
	private BasicArrowButton down=new BasicArrowButton(BasicArrowButton.SOUTH);
	private BasicArrowButton left=new BasicArrowButton(BasicArrowButton.WEST);
	private BasicArrowButton right=new BasicArrowButton(BasicArrowButton.EAST);
	
	public void init(){
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(jb);
		cp.add(new JToggleButton("JToggleButton"));
		cp.add(new JCheckBox("JCheckBox"));
		cp.add(new JRadioButton("JRadioButton"));
		JPanel jp=new JPanel();
		jp.setBorder(new TitledBorder("Directions"));
		jp.add(up);
		jp.add(down);
		jp.add(right);
		jp.add(left);
		cp.add(jp);
	}
	public static void main(String[] args){
		Console.run(new Buttons(), 700,200);
	}

}
