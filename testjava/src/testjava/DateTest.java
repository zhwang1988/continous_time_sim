package testjava;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	
	
	//时间的格式
	public static void main(String[] args){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
	    	Date d1=format.parse("2014-8-10");
	    	Date d2=format.parse("2014-8-15");
	    	long diff=d2.getTime()-d1.getTime();
	    	long days=diff/(1000*60*60*24);
	    	System.out.println(days);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		
		
		
		
	}
	
}
