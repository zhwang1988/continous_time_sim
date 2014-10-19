package Applets;
import java.beans.*;
import java.lang.reflect.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class BeanDumper extends JFrame {
	private JTextField query =new JTextField(20);
	private JTextArea results=new JTextArea();
	public void print(String s){results.append(s+"\n");}
    public void dump(Class bean){
    	results.setText("");
    	BeanInfo bi=null;
    	try{
    		bi=Introspector.getBeanInfo(bean,Object.class);
    	}catch(IntrospectionException e){
    		print("Couldn't introspect"+bean.getName());
    		return;
    	}
    	PropertyDescriptor[] properties=bi.getPropertyDescriptors();
    	for(int i=0; i<properties.length;i++){
    		Class p=properties[i].getPropertyType();
    		if(p==null) continue;
    		print("Property type:\n"+p.getName()+"Property name:\n"+properties[i].getName());
    		Method readMethod=properties[i].getReadMethod();
    		if(readMethod !=null)
    			print("Read method:\n"+readMethod);
    		Method writeMethod =properties[i].getWriteMethod();
    		if(writeMethod !=null)
    			print("Write :\n"+writeMethod);
    		print("=============");	
    	}
    	print("Public methods:");
    	MethodDescriptor[] methods=bi.getMethodDescriptors();
    	for(int i=0; i<methods.length;i++)
    		print(methods[i].getMethod().toString());
    	print("==================");
    	print("Event support:");
    	EventSetDescriptor[] events=bi.getEventSetDescriptors();
    	for(int i=0; i<events.length;i++){
    		print("Listener type:\n "+events[i].getListenerType().getName());
    		
    		
    		
    	}
    	
    	
    	
    }

}