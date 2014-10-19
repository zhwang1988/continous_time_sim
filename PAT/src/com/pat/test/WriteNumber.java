package com.pat.test;

import java.io.*;

public class WriteNumber {

	static String[] numpy={"ling","yi","er","san","si","wu","liu","qi","ba","jiu"};
	
	public static void main(String[] args) throws IOException{
	 
	 InputStreamReader is=new InputStreamReader(System.in);
	 BufferedReader br=new BufferedReader(is);
	 String str=br.readLine();
	 int sum=0;
	 
	 for(int i=0;i<str.length();i++){
		 char num=str.charAt(i);
		 int gap=num-'0';
		 sum=sum+gap;
	 }
	 //¿Ï¶¨Ð¡ÓÚ999
	if(sum/100!=0)
		System.out.print(numpy[sum/100]+" ");
	if(sum/10!=0)
		System.out.print(numpy[(sum%100)/10]+" ");
	System.out.print(numpy[(sum%100)%10]);
	 
	}

}
