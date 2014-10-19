package testjava;

public class Threadtest extends Thread {
	private int countDown =5;
	private static int threadCount=0;
	private volatile double d=0;
	
	public Threadtest(int priority){
	  setPriority(priority);
	  start();
	}
	
	public String toString(){
		return super.toString()+":"+countDown;
	}
	
	public void run(){
		while(true){
			for(int i=1;i<99999999;i++)
				d=d+(Math.PI+Math.E)/(double)i;
			System.out.println(this);
			if(--countDown==0) return;
		}
	}
	
	public static void main(String[] args){
	    new Threadtest(Thread.MAX_PRIORITY);
	    for(int i=0; i<5; i++)
	    	new Threadtest(Thread.MIN_PRIORITY);
	}

}
