package testjava;

public class hanno {
	static int nDisks=2;
	
	public static void hannotower(int topN, char from, char inter, char to){
		if (topN==1)
			System.out.println("Disk 1 from "+from+" to "+to);
		else
		{
			hannotower(topN-1,from,to,inter);
			System.out.println("Disk "+topN+" from "+from+" to "+to);
		    hannotower(topN-1,inter,from,to);
		}
	}
   public static void main(String[] args){ 
	   hannotower(nDisks,'A', 'B', 'C');
   }
}
