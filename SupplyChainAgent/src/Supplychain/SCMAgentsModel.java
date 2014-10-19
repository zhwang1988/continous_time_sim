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

import javax.xml.parsers.DocumentBuilder;//���Խ���XML�����ݸ�ʽ�ͽṹ

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
	private ArrayList<SupCusAgents> CustomerAgents; //��������һȺ�ͻ�
	private SupCusAgents CustomerMarket; //�����Ŀͻ�����
	private ArrayList<SupCusAgents> SupplierAgents; //��Ӧ�̵���������
	private ArrayList<DisManRetAgents> RetailerAgents; //�����ֿ̲�����
	
	private OpenHistogram RetailerInventoryDistribution;
	private OpenSequenceGraph amountOfOilInSpace;
	//������ͼ�ģ���ʵ�Լ���swing��������JDBC��������ݿ⵱��Ҳ����
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
		RetailerAgents=new ArrayList<DisManRetAgents>(); //�½�retailer��������
		SupplierAgents=new ArrayList<SupCusAgents>(); //�½�supplier��������
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
		//����������
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
					 //���ݵ�ǰ��������Լ���supply������������,���������Ϊ
					 //...���ٶ����������Ӷ����������Կ��Ծ��⣬�����ÿ���ܹ��ȶ������ܿ��࣬��������
					 
					 
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
					 //�������Ժ��ٸ���
					 int ssize=SupplierAgents.size();
					 for(int j=0;j<SupplierAgents.size();j++){
						 //�����agent�����Ҹ��º���ʾ�������޷�����򷵻�false,������Ӧ�������ж���һ��
						 //������ʾ�Ƿ���ǰ�ķ������Ĺ���
						 if((rsize-i)!=1){
							 supamount=(int)(((1+Math.random()/10)/(rsize-i))*SupplierAgents.get(j).getOilSupply());
							 //�ж��Ƿ����ڿ�������
							 if(RetailerAgents.get(i).getOil()+supamount>retmax){
								 supamount=retmax-RetailerAgents.get(i).getOil();
								 supleft=SupplierAgents.get(j).getOilSupply()-supamount;
								 SupplierAgents.get(j).setOilSupply(supleft);
								 RetailerAgents.get(i).steprecieve(supamount);
								 break;//��retailer�Ľڵ���͹���������Ҫ�ٿ���supplier,��supplier�Ĺ�Ӧ��������Ӧ�ñ���
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
						 //��Ҫ�ж��Ƿ����ڵ�ǰ��ȫ��������
						 //......
						 retamount=(int)(((1+Math.random()/10)/(rsize-i))*CustomerMarket.getOildemand());
						 if(RetailerAgents.get(i).getOil()-retamount<retmin){
							 retamount=RetailerAgents.get(i).getOil()-retmin;
						     //������ǰ��retalier��forѭ�������������Ͳ�Ʒ
						 }
						 
						 leftamount=CustomerMarket.getOildemand()-retamount;
						 RetailerAgents.get(i).stepsend(retamount);
						 CustomerMarket.setOildemand(leftamount);
					 }
					 else{
						 //��Ҫ�ж��Ƿ����ڵ�ǰ��ȫ��������
						 //......
						 if(RetailerAgents.get(i).getOil()-CustomerMarket.getOildemand()<retmin){
							 retamount=RetailerAgents.get(i).getOil()-retmin;
							 leftamount=CustomerMarket.getOildemand()-retamount;
							 RetailerAgents.get(i).stepsend(retamount);
							 CustomerMarket.setOildemand(leftamount);
							 System.out.println(" �� "+leftamount+"������û������");
							//û��ͳ�������ж���û������
						 }else{
						 RetailerAgents.get(i).stepsend(CustomerMarket.getOildemand());
						 }
					 }
					 
					 RetailerAgents.get(i).report();
				 }
				 for(int j=0;j<SupplierAgents.size();j++){
				 System.out.println("��Ӧ�� "+j+1+" �� "+SupplierAgents.get(j).getOilSupply()+"�ֹ�Ӧʣ��");
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
