package Applets;
import javax.swing.*;
import java.awt.*;

public class BoxLayout1 extends JApplet{
	public void init(){
		JPanel jpv=new JPanel();
		jpv.setLayout(new BoxLayout(jpv, BoxLayout.Y_AXIS));
		for(int i=0;i<5;i++){
			jpv.add(new JButton("jpv "+i));
		}
		JPanel jph=new JPanel();
		jph.setLayout(new BoxLayout(jph,BoxLayout.X_AXIS));
		for(int i=0;i<5;i++){
			jph.add(new JButton("jph"+i));
		}
		Container cp=getContentPane();
		cp.add(BorderLayout.EAST,jpv);
		cp.add(BorderLayout.SOUTH,jph);
	}
	public static void main(String[] args){
		Console.run(new BoxLayout1(), 450, 200);
	}
	

}
