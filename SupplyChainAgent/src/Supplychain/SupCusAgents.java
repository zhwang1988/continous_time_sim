package Supplychain;

public class SupCusAgents {
	private int Oildemand; //The oil need from the customer
	private int OilSupply;//The oil supply from the supplier
	private static int SupIDNumber=0;
	private int SupID;
	private static int CusIDNumber=0;
	private int CusID;
	private boolean flag;

	public SupCusAgents(String a){
		if(a.equals("sup")==true){
			SupIDNumber++; //get the retailer number
			this.SupID=SupIDNumber;
			this.flag=true;
		}
		else{
			CusIDNumber++;
			this.CusID=CusIDNumber;
			this.flag=false;
		}
	}

	public SupCusAgents() {
		// TODO Auto-generated constructor stub
	}

	public void Randomdemand(int mindemand, int maxdemand) { 
		setOildemand((int)((Math.random()*(maxdemand-mindemand))+mindemand));
	}
	
	public void Randomsupply(int minsupply, int maxsupply) { 
		setOilSupply((int)((Math.random()*(maxsupply-minsupply))+minsupply));
	}

	public int getOildemand() {
		return Oildemand;
	}

	public void setOildemand(int oildemand) {
		Oildemand = oildemand;
	}

	public int getOilSupply() {
		return OilSupply;
	}
	
	
	public int getSupID() {
		return SupID;
	}
	
	public int getCusID() {
		return CusID;
	}

	public void setOilSupply(int oilSupply) {
		OilSupply = oilSupply;
	}
	
	public void report(){
		if(flag)
		System.out.println(" Supplier "+ getSupID()+" send "+getOilSupply());
		else
		System.out.println(" Customer "+getCusID()+" recieved "+getOildemand());	
	}

		
	
}
