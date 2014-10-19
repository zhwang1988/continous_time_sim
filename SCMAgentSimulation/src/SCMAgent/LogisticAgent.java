package SCMAgent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

//决定不同port和port之间进行运输的logistic type
class LogisticAgent extends SCMElements {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//时间的格式
	private String name;
	private boolean used;
	private int Logistictype;
	private double transprice;
	private double maxtransferamount;
	private int mintransrate; //运输速度的最大值
	private int maxtransrate;  //运输速度的最小值
	private Date currentDate; //当前时间
	ArrayList<SCMElements> sourcenode; //运输的方向和节点
	ArrayList<SCMElements> targetnode;//运输的目的，本质上不参与上游的	
	ArrayList<Material> materials; //运输的原油或者说运输的物料
	Map<Material,Date> TransferOrders; //运输的订单，可能有很多个运输的订单，对这些总的订单也需要进行统一管理
	
	
	public LogisticAgent(String name, int logistictype){
		//确定当前物料的流量,初始化设定用的
		super(name);
		this.name=super.getName();
		this.Logistictype=logistictype;
	}
	
	public double getTransspeed(){
		double transrate=ToolsClass.getrandom(mintransrate,maxtransrate);
		return transrate;
	}
	
	public double emissionFunction(double oilamount){
		double coamount=oilamount*0.00001;
		return coamount;
	}
	
		
	public void getMessage(Message e){
		//发货商给出的信息指令
		TransferOrders=e.getOrders();//发货商吗？给出的时间限制	
	}
	
	public void sendMessage(Message e){
		
	}
	
	public boolean confirmorders(){
		return false;
	}
	
	// logistic types there are different types of that
	
	
		
	//sendMessage(){
    //}
	
	public void cacluatetime(){
		//计算运输的时间，需求可能也有多少时间内到达的，暂时可以不用
	}

}
