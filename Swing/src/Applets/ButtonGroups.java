package Applets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.*;
import java.lang.reflect.*;

public class ButtonGroups extends JApplet{
	private static String[] ids={
		"June","Ward","Beaver",
		"Wally","Eddie","Lumpy",
	};
	
	static JPanel makeBPanel(Class klass, String[] ds){
		ButtonGroup bg=new ButtonGroup();
		JPanel jp=new JPanel();
		String title=klass.getName();
		title=title.substring(title.lastIndexOf('.')+1);
		jp.setBorder(new TitledBorder(title));
		for(int i=0;i<ids.length;i++){
			AbstractButton ab=new JButton("failed");
		
		try{
			Constructor ctor=
					klass.getConstructor(new Class[]{String.class});
			ab=(AbstractButton)ctor.newInstance(new Object[]{ds[i]});
		}catch (Exception e){
			System.err.println("can't create"+klass);
		}
		bg.add(ab);
		jp.add(ab);
		}
		return jp;
	}
	public void init(){
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(makeBPanel(JButton.class,ids));
		cp.add(makeBPanel(JToggleButton.class,ids));
		cp.add(makeBPanel(JCheckBox.class,ids));
		cp.add(makeBPanel(JRadioButton.class,ids));
	}
	
	public static void main(String[] args){
		Console.run(new ButtonGroups(),500,300);
	}

}
