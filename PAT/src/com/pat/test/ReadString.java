package com.pat.test;

import java.io.*;
import java.util.Scanner;
public class ReadString {
	
	public static boolean teststring(String s){
		boolean a = true;
		int p=0;
		int t;
		int end;
		
	return a;
	
	}
	
	public static void main(String[] args) throws IOException{
		
		Scanner sc=new Scanner(System.in);	
		int a=sc.nextInt();
	
		for(int i=0;i<a;i++){
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		String str=br.readLine();
		if(teststring(str))
		System.out.println("YES");
		else
		System.out.println("NO");	
		
		}
	}
}
