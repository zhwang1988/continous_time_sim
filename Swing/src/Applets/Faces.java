package Applets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.*;
import java.io.*;

public class Faces extends JApplet{
	private static Icon[] faces;
	private JButton jb,jb2=new JButton("Disable");
	private boolean mad=false;
	public void init(){
		faces=new Icon[]{
				new ImageIcon("/Users/zhwang1988/Pictures/¶À½ÇÊÞ2.jpg"),
				new ImageIcon("/Users/zhwang1988/Pictures/inventory.png"),
				new ImageIcon("/Users/zhwang1988/Pictures/inventory.png"),
				new ImageIcon("/Users/zhwang1988/Pictures/inventory.png"),
				new ImageIcon("/Users/zhwang1988/Pictures/inventory.png"),
		};
		jb=new JButton("JButton", faces[0]);
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		jb.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  if(mad){
				  jb.setIcon(faces[0]);
				  mad=false;
			  }
			  else
			  {
				  jb.setIcon(faces[0]);
				  mad=true;
			  }
			  jb.setVerticalAlignment(JButton.TOP);
			  jb.setHorizontalAlignment(JButton.LEFT);
		  }
		});
		jb.setRolloverEnabled(true);
		jb.setRolloverIcon(faces[0]);
		jb.setPressedIcon(faces[0]);
		jb.setDisabledIcon(faces[0]);
		jb.setToolTipText("Yow!");
		cp.add(jb);
		jb2.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  if(jb.isEnabled()){
					  jb.setEnabled(false);
					  jb2.setText("Enable");
				  }
				  else{
					  jb.setEnabled(true);
					  jb2.setText("Disable");
				  }
			  }
		});
		cp.add(jb2);
	}
	public static void main(String[] args){
		Console.run(new Faces(), 400, 200);
	}

}
