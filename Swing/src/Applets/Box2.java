package Applets;

import javax.swing.*;

import java.awt.*;

public class Box2 extends JApplet{
	public void init(){
		
	Box bv=Box.createVerticalBox();
	for(int i=0;i<5;i++){
		bv.add(new JButton("bv "+i));
		bv.add(Box.createVerticalStrut(i*10));
	}
	Box bh=Box.createHorizontalBox();
	for(int i=0; i<5;i++){
		bh.add(new JButton("bh" +i));
	    bh.add(Box.createHorizontalStrut(i*10));
	}
	Container cp=getContentPane();
	cp.add(BorderLayout.EAST,bv);
	cp.add(BorderLayout.SOUTH,bh);
	}
	
	public static void main(String args[]){
		Console.run(new Box2(), 450,300);
	}
}
