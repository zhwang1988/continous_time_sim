package Applets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class TextArea extends JApplet {
   public void init(){
	   Container cp=getContentPane();
	   cp.setLayout(new GridLayout(7,3));
	   for(int i=0;i<20;i++){
		   cp.add(new Button(" Button "+i));
	   }
   }
   
   public static void main(String args[]){
	   Console.run(new TextArea(), 300, 250);	   
   }

}
