package SCMAgent;


interface StaticTransAgents {
	//the property of the interface is static and public final
	public static final int agenttype=1;
	//every function here is public abstract and can be omitted 
	public abstract double emissionFunction(double oilamount);
	
	//every information from the market
	void getOrders();
	
	void confirmOrders();
	
}

public interface LogisticAgents {
	//the property of the interface is static and public final
	public static final int agenttype=1;
	//every function here is public abstract and can be omitted 
	public abstract double emissionFunction(double oilamount);
	//every information from the market
	void getMarketOrders();
	
	void getSpecificOrders();
	
	void bidfunction();
	
	double sendbid();
	
	void confirmOrders();
	
}


class pipeAgent extends SCMElements implements StaticTransAgents {

	public pipeAgent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double emissionFunction(double oilamount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmOrders() {
		// TODO Auto-generated method stub
		
	}
	
}


class TrainAgent extends SCMElements implements LogisticAgents{

	public TrainAgent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double emissionFunction(double oilamount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getMarketOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSpecificOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bidfunction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double sendbid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void confirmOrders() {
		// TODO Auto-generated method stub
		
	}
	
}

class VehicleAgent extends SCMElements implements LogisticAgents{

	public VehicleAgent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double emissionFunction(double oilamount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getMarketOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSpecificOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bidfunction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double sendbid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void confirmOrders() {
		// TODO Auto-generated method stub
		
	}
	
}
