package SCMAgent;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;

import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.gui.DisplaySurface;


//仿真的话可以认为是一个AGENT的工厂，如果需要生产一些对象的话可以通过反射机制来生成所有的代码，工厂模式利用,而利用mybastic 只能用来生成

public class SCMAgentControl extends SimModelImpl{
	//供应链仿真的模型控制
	//1.客户，分销中心，工厂，供应商，原油采购商
	//分成几个阶段进行仿真1.初始化，2.根据上个月的部分操作信息和历史数据，客户提出订单，包括（时间，地点，量，不同物料）
	//3. 工厂根据历史数据和上个月的数据，制定生产计划，进行生产--这些都是预测功能，保证库存的稳定性--（有一定的弹性）
	//4. 客户向工厂发送信息，工厂得到信息，工厂根据信息和制定的计划进行调整，工厂根据可能历史规则分析，调整生产计划，给出到货的时间
	//工厂向运输主体，不同的发送信息,有历史的偏好，优先级，询问给出价格，和运输量
	//运输主体，在化工厂，炼油厂，其他厂--客户之间的分布的各个主体，一部分都收到了单子，单个主体，根据优先级，库存，距离等操作
	//决定是否能够承接着一些订单，如果不能满足的部分则反馈给工厂
	//工厂最后根据价格和一部分情况协调运输，二次发起，知道所有的物料都能够被配送，并给出配送的时间
	//此时工厂向下游也发起消息，给出需求，向所有的供应商发起，供应商排序，选则
	//
	
	//仿真时间按照天来算，仿真的核算按月进行
	//测试几个节点
	
	private String host;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DBconnection DC;
	
	private static int numCustomer=2;
	private static int numDistributor=3;
	private static int numChemicalPlant=1;
	private static int numRefinery=1;
	private static int numSupplier=2;
	private static int numOilProductionSite=2;
	private static int numLogisticAgents=6;
	
	
	
	private Schedule schedule;
	private DisplaySurface displaySurf;	
	private ArrayList<Customer> CustomerAgents;
	private ArrayList<Distributor> DistributorAgents;
	private ArrayList<ChemicalPlant> ChemicalPlantAgents;
	private ArrayList<Refinery> RefineryAgents;
	private ArrayList<Supplier> SupplierAgents;
	private ArrayList<OilProductionSite> OilProAgents;
	private ArrayList<LogisticAgent> LogisticAgents;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//时间的格式
	private Date currentDate;
	
	
	public void setup(){
		System.out.println("Initialization of SCM");
		//初始化，但还没有真正赋予值
		CustomerAgents=new ArrayList<Customer>();
		DistributorAgents=new ArrayList<Distributor>();
		ChemicalPlantAgents=new ArrayList<ChemicalPlant>();
		RefineryAgents=new ArrayList<Refinery>();
		SupplierAgents=new ArrayList<Supplier>();
		OilProAgents=new ArrayList<OilProductionSite>();
		LogisticAgents=new ArrayList<LogisticAgent>();
		
		VehicleAgent vehicle1=new VehicleAgent("testvehicle");
				
	    schedule = new Schedule(1);
	    
		
	}
	
	public void begin(){
		buildModel();
		buildSchedule();
		buildDisplay();
	
	}
	
	public void buildModel() {
		System.out.println("Set the Static Information");
		//产生客户，产生客户,如果是从数据库读出来的，那么应该怎么做？按每条进行读取吗
		initialSimpleData();//调用

			//initial status of retailer

	}
	
	private void initialData() {
		//from database jdbc, mybatis? 数据库当中，写配置文件，找数据结构
	}
	
	private void initialSimpleData(){
		//simply for test
		/*for(int i=0;i<numCustomer;i++){
			//还是要从数据库里面拿
			Customer cus=new Customer();
			//对其他数据进行初始化操作，需求等等
			cus.initializedemand();//初始化需求
			CustomerAgents.add(cus);
		}
		
		for(int i=0;i<numDistributor;i++){
			Distributor dis=new Distributor();
			DistributorAgents.add(dis);	
		}
		
		for(int i=0;i<numChemicalPlant;i++){
			ChemicalPlant cmp=new ChemicalPlant();
			ChemicalPlantAgents.add(cmp);	
		}
		
		for(int i=0;i<numRefinery;i++){
			Refinery ref=new Refinery();
			RefineryAgents.add(ref);	
		}
		
		for(int i=0;i<numSupplier;i++){
			Supplier sup=new Supplier();
			SupplierAgents.add(sup);	
		}
		
		for(int i=0;i<numOilProductionSite;i++){
			OilProductionSite ops=new OilProductionSite();
			OilProAgents.add(ops);	
		}
		
		for(int i=0;i<numLogisticAgents;i++){
			LogisticAgent loa=new LogisticAgent(i);//需要告诉改运输主体，他自身的运输类型
			LogisticAgents.add(loa);	
		}*/
		
				
	}
	
	private void statusReport(){
		//初始化或者其他时间，反应出目前供应商的变化趋势等等
		for(int i=0;i<CustomerAgents.size();i++){
			Customer cg=CustomerAgents.get(i);
			cg.report();			
		}	
		//initial status of supplier
		for(int i=0;i<SupplierAgents.size();i++){
			Supplier sup=SupplierAgents.get(i);
			sup.report();
		}
		
		
		
	}
	
	
	private void buildDisplay() {
		// TODO Auto-generated method stub
		
	}

	private void buildSchedule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getInitParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
}
