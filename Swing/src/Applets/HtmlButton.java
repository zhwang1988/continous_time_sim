package Applets;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class HtmlButton extends JApplet{
	private JButton b=new JButton("<html><b><font size=+1>"+
     "<center>hello!<br/>我是羊驼助手，请点我");
	
	private JProgressBar pb=new JProgressBar();
	private JSlider sb=new JSlider(JSlider.HORIZONTAL,0,100,60);
	
	
	
	public void init(){
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getContentPane().add(new JLabel("<html>"+"<i><font size=+1>咩!"));
				validate();
			}
		});
		Container cp=getContentPane();
		cp.setLayout(new GridLayout(4,2));
		cp.add(pb);
		cp.add(b);
		sb.setValue(0);
		sb.setPaintTicks(true);
		sb.setMajorTickSpacing(20);
		sb.setMinorTickSpacing(5);
		sb.setBorder(new TitledBorder("羊驼进度"));
		pb.setModel(sb.getModel());
		cp.add(sb);
		
	}
	
	
	
	public static void main(String[] args){
		Console.run(new HtmlButton(),200,500);
	}

}
