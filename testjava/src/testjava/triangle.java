package testjava;
import java.io.*;
public class triangle {
	static int theNumber;
	public static int calculate(int n){
		if (n==1)
			return 1;
		else
		{
			return(n+calculate(n-1));
		}
	}
	public static void main(String[] args) throws IOException{
		System.out.print("enter a number:");
		theNumber=getInt();
		int theAnswer=calculate(theNumber);		
		System.out.print("The answer is "+theAnswer);
	}
	public static String getString() throws IOException{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		String s=br.readLine();
		return s;
	}
    public static int getInt() throws IOException{
    	String s=getString();
    	return Integer.parseInt(s);
    }

}
