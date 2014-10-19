package Supplychain;

import java.util.*;

import uchicago.src.sim.analysis.BinDataSource;
import uchicago.src.sim.analysis.DataSource;
import uchicago.src.sim.analysis.OpenHistogram;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;
import uchicago.src.sim.engine.*;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.util.SimUtilities;

import javax.xml.parsers.DocumentBuilder;//尝试解析XML的数据格式和结构

public class SCMAgentsModel extends SimModelImpl{
	private static final int NRetailer=3;
	private static final int NSupplier=2;
	private static final int DemandMin=100;
	private static final int DemandMax=200;
	private static final int SupplyMin=50;
	private static final int SupplyMax=100;

	private static final int RET_MIN_LEVEL=500;
	private static final int RET_MAX_LEVEL=1000;
	
	private int numRetalierAgents=NRetailer;
	private int numSupplierAgents=NSupplier;
	private int retmin=RET_MIN_LEVEL;
	private int retmax=RET_MAX_LEVEL;
	private int dmin=DemandMin;
	private int dmax=DemandMax;
	private int supmin=SupplyMin;
	private int supmax=SupplyMax;
	
	private Schedule schedule;
	private DisplaySurface displaySurf;	
	private ArrayList<SupCusAgents> CustomerAgents; //产生需求一群客户
	private SupCusAgents CustomerMarket; //单独的客户需求
	private ArrayList<SupCusAgents> SupplierAgents; //供应商的所有主体
	private ArrayList<DisManRetAgents> RetailerAgents; //分销商仓库主体
	
	private OpenHistogram RetailerInventoryDistribution;
	private OpenSequenceGraph amountOfOilInSpace;
	//用来画图的，其实自己用swing画或者用JDBC输出到数据库当中也可以
    class OilInSpace implements DataSource, Sequence{
    	
    	public Object execute(){
    		return new Double(getSValue());
    	}
    	
    	public double getSValue(){
    		double TotalOil=0;
    		for(int i=0;i<RetailerAgents.size();i++){
    		TotalOil=(double)RetailerAgents.get(i).getOil()+TotalOil;
    		}
			return TotalOil;
    	}
    }
	
	public void setup(){
		System.out.println("Running setup");
		CustomerMarket=new SupCusAgents("cus");
		RetailerAgents=new ArrayList<DisManRetAgents>(); //新建retailer主体链表
		SupplierAgents=new ArrayList<SupCusAgents>(); //新建supplier主体链表
		CustomerAgents=new ArrayList<SupCusAgents>();
	    schedule = new Schedule(1);
	    
		if(amountOfOilInSpace!=null){
			amountOfOilInSpace.dispose();
		}
		amountOfOilInSpace=null;    
		amountOfOilInSpace=new OpenSequenceGraph("Amount of Total Oil In Retailers",this);
	}
	
	public void begin(){
		buildModel();
		buildSchedule();
		buildDisplay();
		
		if(displaySurf!=null){
			displaySurf.dispose();
		}
		
		displaySurf=null;
		displaySurf=new DisplaySurface(this,"Carry Drop Model Window 1");

		amountOfOilInSpace.display();
		//Register Displays
		registerDisplaySurface("Carry Drop Model Window 1", displaySurf);
		

	}
	
	public void buildModel() {
		System.out.println("Running BuildModel");
		//产生分销商
		for(int i=0;i<numRetalierAgents;i++){
			addNewRetailerAgent();
		}
		
		for(int i=0;i<numSupplierAgents;i++){
			addNewSupplierAgent();
		}
		
		//build behavior initialization the first demand?
		CustomerMarket.Randomdemand(dmin, dmax);
		
		
		//initial status of retailer
		for(int i=0;i<RetailerAgents.size();i++){
			DisManRetAgents ret=(DisManRetAgents)RetailerAgents.get(i);
			ret.report();			
		}	
		//initial status of supplier
		for(int i=0;i<SupplierAgents.size();i++){
			SupCusAgents sup=(SupCusAgents)SupplierAgents.get(i);
			sup.Randomsupply(supmin, supmax);
			sup.report();
		}
	}

	public void buildDisplay() {
		System.out.println("Running BuildDisplay");	
		
		amountOfOilInSpace.addSequence("Oil In Retailers", new OilInSpace());
	}

	public void buildSchedule() {
		System.out.println("Running BuildSchedule");
		
		class MarketStep extends BasicAction{
			public void execute(){
			CustomerMarket.Randomdemand(dmin, dmax);
			CustomerMarket.report();
			}
		}
		
		schedule.scheduleActionBeginning(0,new MarketStep());
		
		
		class SupplierStep extends BasicAction{
			public void execute(){
				 SimUtilities.shuffle(SupplierAgents);
				 for(int i=0;i<SupplierAgents.size();i++){
					 SupCusAgents sup=SupplierAgents.get(i);
					 //根据当前的情况可以减少supply如果库存逐渐上升,智能体的行为
					 //...减少订单或者增加订单量，尝试可以均衡，并且让库存能够稳定下来总库存多，削减订单
					 
					 
					 sup.Randomsupply(supmin, supmax);
					 sup.report();
				 } 
			}
		}
		
		schedule.scheduleActionBeginning(0,new SupplierStep());
		
		
		
		class RetailerStep extends BasicAction{
			int retamount=0;
			int leftamount=0;
			int supamount=0;
			int supleft=0;
			@Override
			public void execute() {
				 SimUtilities.shuffle(RetailerAgents);
				 int rsize=RetailerAgents.size();
				 for(int i=0;i<RetailerAgents.size();i++){
							 
					 //CustomerMaket is the total Oil demand, we need the strategy to split the demand
					 //先收油以后再付油
					 int ssize=SupplierAgents.size();
					 for(int j=0;j<SupplierAgents.size();j++){
						 //最好由agent来自我更新和显示，比如无法完成则返回false,给出供应量，即判断这一步
						 //函数表示是否向当前的分销中心供油
						 if((rsize-i)!=1){
							 supamount=(int)(((1+Math.random()/10)/(rsize-i))*SupplierAgents.get(j).getOilSupply());
							 //判断是否会高于库存的上限
							 if(RetailerAgents.get(i).getOil()+supamount>retmax){
								 supamount=retmax-RetailerAgents.get(i).getOil();
								 supleft=SupplierAgents.get(j).getOilSupply()-supamount;
								 SupplierAgents.get(j).setOilSupply(supleft);
								 RetailerAgents.get(i).steprecieve(supamount);
								 break;//该retailer的节点的油罐已满不需要再考虑supplier,但supplier的供应多余量还应该保留
							 }
							 
						 }
						 else{
							 if(RetailerAgents.get(i).getOil()+SupplierAgents.get(j).getOilSupply()>retmax){
								 supamount=retmax-RetailerAgents.get(i).getOil();
								 supleft=SupplierAgents.get(j).getOilSupply()-supamount;
								 SupplierAgents.get(j).setOilSupply(supleft);
								 RetailerAgents.get(i).steprecieve(supamount);
								 break;
							 }else{
								 RetailerAgents.get(i).steprecieve(SupplierAgents.get(j).getOilSupply());
							 }
						 }
					 }
					 if((rsize-i)!=1){
						 //System.out.println(CustomerMarket.getOildemand());
						 //需要判断是否会低于当前安全库存的下限
						 //......
						 retamount=(int)(((1+Math.random()/10)/(rsize-i))*CustomerMarket.getOildemand());
						 if(RetailerAgents.get(i).getOil()-retamount<retmin){
							 retamount=RetailerAgents.get(i).getOil()-retmin;
						     //跳出当前的retalier的for循环，不能再输送产品
						 }
						 
						 leftamount=CustomerMarket.getOildemand()-retamount;
						 RetailerAgents.get(i).stepsend(retamount);
						 CustomerMarket.setOildemand(leftamount);
					 }
					 else{
						 //需要判断是否会低于当前安全库存的下限
						 //......
						 if(RetailerAgents.get(i).getOil()-CustomerMarket.getOildemand()<retmin){
							 retamount=RetailerAgents.get(i).getOil()-retmin;
							 leftamount=CustomerMarket.getOildemand()-retamount;
							 RetailerAgents.get(i).stepsend(retamount);
							 CustomerMarket.setOildemand(leftamount);
							 System.out.println(" 有 "+leftamount+"吨需求没有满足");
							//没有统计需求有多少没有满足
						 }else{
						 RetailerAgents.get(i).stepsend(CustomerMarket.getOildemand());
						 }
					 }
					 
					 RetailerAgents.get(i).report();
				 }
				 for(int j=0;j<SupplierAgents.size();j++){
				 System.out.println("供应商 "+j+1+" 有 "+SupplierAgents.get(j).getOilSupply()+"吨供应剩余");
				 }

				 //displaySurf.updateDisplay();
			}
		}
		schedule.scheduleActionBeginning(10, new RetailerStep());
		
	class SCMRetailerUpdateOilInSpace extends BasicAction{
		public void execute(){
				amountOfOilInSpace.step();
		}
	}
		
		schedule.scheduleActionAtInterval(5,new SCMRetailerUpdateOilInSpace());
			
	}



	private void addNewRetailerAgent() {
			DisManRetAgents a=new DisManRetAgents(retmin, retmax);
			RetailerAgents.add(a);
	}
	
	
	private void addNewSupplierAgent() {
		SupCusAgents a=new SupCusAgents("sup");
		SupplierAgents.add(a);
    }
	

	@Override
	public String[] getInitParam() {
		String[] initParams={"NumMarkets","Minlevel","Maxlevel","NumSuppliers","Minproduce","Maxproduce"};
		return initParams;
	}

	public int getNumRetailerAgents() {
		return numRetalierAgents ;
	}
	
	public void setNumRetailerAgents(int nra){
		numRetalierAgents=nra;
	}

	@Override
	public Schedule getSchedule() {
	
		return schedule;
	}
	
	@Override
	public String getName() {
		return "Supply Chain MAS";
	}
	
	public static void main(String[] args){
		SimInit init=new SimInit();
		SCMAgentsModel model=new SCMAgentsModel();
		init.loadModel(model, "", false);
	}



}
