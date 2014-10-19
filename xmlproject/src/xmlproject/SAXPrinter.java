package xmlproject;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class SAXPrinter extends DefaultHandler{
	public void startDocument() throws SAXException{
		System.out.println("<?xml version='1.0' encoding='UTF-8'?>");	
	}
	
	public void processingInstruction(String target, String data) throws SAXException{
		System.out.println("<?"+target+" "+data+"?>");
	}

	public void startElement(String uri,String localName,String qName,Attributes attrs) throws SAXException{
		System.out.print("<"+qName);
		int len=attrs.getLength();
		for(int i=0;i<len;i++){
			System.out.print(" ");
			System.out.print(attrs.getQName(i));
			System.out.print("=\"");
			System.out.print(attrs.getValue(i));
			System.out.print("\"");
		}
		System.out.print(">");
	}
	public void characters(char[] ch, int start, int length) throws SAXException{
		System.out.print(new String(ch,start,length));
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		System.out.print("</"+qName+">");
	}
	
	public static void main(String[] args){
		SAXParserFactory spf=SAXParserFactory.newInstance();
		SAXParser sp=null;
		try{
			sp=spf.newSAXParser();
			File file=new File("student.xml");
			sp.parse(file, new SAXPrinter());
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
