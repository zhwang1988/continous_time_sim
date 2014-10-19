package testjava;
import java.io.*;
public class anagram {
static int size;
static int count;
static char[] arrchar=new char[100];

public static void calculate(int newsize){
	if(newsize==1){
		return;
	}
	else{
		for(int j=0;j<newsize;j++)
		{
			calculate(newsize-1);
			if(newsize==2)
				displayWord();	
		rotate(newsize);	
		}
	}	
}

public static void rotate(int newsize){
	int j;
	int position=size-newsize;
	char temp=arrchar[position];
	for(j=position+1;j<size;j++)
		arrchar[j-1]=arrchar[j];
	arrchar[j-1]=temp;
}

public static void displayWord(){
	System.out.print(++count+" ");
	for(int j=0;j<size;j++)
	System.out.print(arrchar[j]);
	System.out.print("   ");
	System.out.flush();
	if(count%6==0)
	{
		System.out.println("");
	}
}

public static String getString() throws IOException{
	InputStreamReader isr=new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(isr);
	String s=br.readLine();
	return s;
}


public static void main(String[] args) throws IOException{
	System.out.print("please input ");
	String input=getString();
	size=input.length();
	count=0;
	for(int j=0;j<size;j++)
		arrchar[j]=input.charAt(j);
	calculate(size);	
}

}
