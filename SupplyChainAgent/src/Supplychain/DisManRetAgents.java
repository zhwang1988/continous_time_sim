package Supplychain;

public class DisManRetAgents {
	private int Oil; // oil amount in a distribution center 
	private static int RetIDNumber=0;
	private int RetID;
	public DisManRetAgents(int minlevel, int maxlevel){
		
		setOil(((int)((Math.random()*(maxlevel-minlevel))+minlevel)));//get the initial oil
		RetIDNumber++; //get the retailer number
		RetID=RetIDNumber;
	}
	
	
	
	private void recieveOil(int amount) {
	     this.setOil(this.getOil() + amount);
	}

	private void sendOil(int amount) {
	     this.setOil(this.getOil() - amount);
	}


	public int getOil() {
		return Oil;
	}
	
	public void setOil(int oil) {
		Oil = oil;
	}
	
	
	public int getRetID() {
		return RetID;
	}
	
	public void report(){
		System.out.println(getRetID()+" has "+getOil()+" left.");
	}



	public void steprecieve(int amount) {
		recieveOil(amount);	
	}

	public void stepsend(int amount) {
		sendOil(amount);	
	}

	
}
