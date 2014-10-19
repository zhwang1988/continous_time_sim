package repast;

import uchicago.src.sim.analysis.BinDataSource;
import uchicago.src.sim.analysis.DataSource;
import uchicago.src.sim.analysis.*;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;

import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.*;
import uchicago.src.sim.util.SimUtilities;

import java.awt.Color;
import java.util.ArrayList;

public class CarryDropModel extends SimModelImpl{
	private static final int NA=100;
	private static final int WX=40;
	private static final int WY=40;
	private static final int TOTALMY=1000;
	private static final int AGENT_MIN_LIFESPAN=30;
	private static final int AGENT_MAX_LIFESPAN=50;
	
	private int numAgents=NA;
	private int worldXSize=WX;
	private int worldYSize=WY;
	private int money=TOTALMY;
	private int agentMinLifespan=AGENT_MIN_LIFESPAN;
	private int agentMaxLifespan=AGENT_MAX_LIFESPAN;
	
	private Schedule schedule;
	private CarryDropSpace cdSpace;
	private DisplaySurface displaySurf;	
    private ArrayList agentList;
    private OpenSequenceGraph amountOfMoneyInSpace;
    private OpenHistogram agentWealthDistribution;
    
    class moneyInSpace implements DataSource, Sequence{
    	public Object execute(){
    		return new Double(getSValue());
    	}
    	
    	public double getSValue(){
    		return (double)cdSpace.getTotalMoney();
    	}
    }
    
    class agentMoney implements BinDataSource{
    	public double getBinValue(Object o){
    		CarryDropAgent cda=(CarryDropAgent)o;
    		return (double)cda.getMoney();		
    	}
    }

	
	
	@Override
	public void setup() {
		System.out.println("Running setup");
		cdSpace=null;
		agentList=new ArrayList();
		schedule=new Schedule(1);
		
		//window surface display
		//Tear down Displays
		if(displaySurf!=null){
			displaySurf.dispose();
		}
		
		displaySurf=null;
		
		if(amountOfMoneyInSpace!=null){
			amountOfMoneyInSpace.dispose();
		}
		amountOfMoneyInSpace=null;
		
		if(agentWealthDistribution!=null){
			agentWealthDistribution.dispose();
		}
		agentWealthDistribution=null;
		
		//create Displays
		displaySurf=new DisplaySurface(this,"Carry Drop Model Window 1");
		amountOfMoneyInSpace=new OpenSequenceGraph("Amount of Money In Space",this);
		agentWealthDistribution=new OpenHistogram("Agent Wealth",8,0);
		
		//Register Displays
		registerDisplaySurface("Carry Drop Model Window 1", displaySurf);
		this.registerMediaProducer("Plot",amountOfMoneyInSpace);
	}
	
	
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		buildModel();
		buildSchedule();
		buildDisplay();
		
		displaySurf.display();
		amountOfMoneyInSpace.display();
		agentWealthDistribution.display();
	}
	
	public void buildModel(){
		System.out.println("Running BuildModel");
		cdSpace=new CarryDropSpace(worldXSize,worldYSize);
		cdSpace.spreadMoney(money);
		
		for(int i=0;i<numAgents;i++){
			addNewAgent();
		}
		
		for(int i=0;i<agentList.size();i++){
			CarryDropAgent cda=(CarryDropAgent)agentList.get(i);
			cda.report();			
		}
		
	}
	
	public void buildSchedule(){
		System.out.println("Running BuildSchedule");
		
		class CarryDropStep extends BasicAction{

			@Override
			public void execute() {
				 SimUtilities.shuffle(agentList);
				 for(int i=0;i<agentList.size();i++){
					 CarryDropAgent cda=(CarryDropAgent)agentList.get(i);
					 cda.step();
				 }
				 
				 int deadAgents=reapDeadAgents();
				 for(int i=0;i<deadAgents;i++){
					 addNewAgent();
				 }
				 
				 
				 displaySurf.updateDisplay();
			}
		}
		schedule.scheduleActionBeginning(0, new CarryDropStep());
		
		class CarryDropUpdateMoneyInSpace extends BasicAction{
			public void execute(){
				amountOfMoneyInSpace.step();
			}
		}
		
		schedule.scheduleActionAtInterval(10,new CarryDropUpdateMoneyInSpace());
		
		
		class CarryDropCountLiving extends BasicAction{
			public void execute(){
				countLivingAgents();
			}
		}
		schedule.scheduleActionAt(10, new CarryDropCountLiving());
		
		
		class CarryDropUpdateAgentWealth extends BasicAction{
			public void execute(){
				agentWealthDistribution.step();
			}
		}
		
		schedule.scheduleActionAtInterval(10, new CarryDropUpdateAgentWealth());
	}
	
	public void buildDisplay() {
		System.out.println("Running BuildDisplay");	
		ColorMap map=new ColorMap();
		
		for(int i=1;i<16;i++){
			map.mapColor(i, new Color((int)(i*8+127), 0, 0));
		}
		map.mapColor(0, Color.white);
		//ÏÔÊ¾
		Value2DDisplay displayMoney=new Value2DDisplay(cdSpace.getCurrentMoneySpace(),map);
		displaySurf.addDisplayableProbeable(displayMoney, "Money");
		
		Object2DDisplay displayAgents=new Object2DDisplay(cdSpace.getCurrentAgentSpace());
		displayAgents.setObjectList(agentList);
		displaySurf.addDisplayableProbeable(displayAgents, "Agents");
		
		amountOfMoneyInSpace.addSequence("Money In Space", new moneyInSpace());
		agentWealthDistribution.createHistogramItem("Agent Wealth", agentList, new agentMoney());
		
	}
	
	
	
	
	private void addNewAgent(){
		CarryDropAgent a=new CarryDropAgent(agentMinLifespan, agentMaxLifespan);
		agentList.add(a);
		cdSpace.addAgent(a);
	}
	
    private int reapDeadAgents(){
    	int count=0;
    	for(int i=(agentList.size()-1);i>0;i--){
    		CarryDropAgent cda = (CarryDropAgent)agentList.get(i);
    	      if(cda.getStepsToLive() < 1){
    	        cdSpace.removeAgentAt(cda.getX(), cda.getY());
    	        cdSpace.spreadMoney(cda.getMoney());
    	        agentList.remove(i);
    	        count++;
    	      }
    	    }
    	    return count;
    	  }


	
	private int countLivingAgents(){
		int livingAgents=0;
		for(int i=0;i<agentList.size();i++){
			CarryDropAgent cda=(CarryDropAgent)agentList.get(i);
			if(cda.getStepsToLive()>0) livingAgents++;
		}
		System.out.println("Number of living agents is:"+livingAgents);
		
		return livingAgents;
	}

	@Override
	public String[] getInitParam() {
		String[] initParams={"NumAgents","WorldXSize","WorldYSize","Money",
				"AgentMinLifespan","AgentMaxLifespan"};
		return initParams;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "My First Repast Model";
	}

	@Override
	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return schedule;
	}



	public int getNumAgents() {
		return numAgents;
	}

	public void setNumAgents(int na) {
		numAgents = na;
	}

	public int getWorldXSize() {
		return worldXSize;
	}

	public void setWorldXSize(int wx) {
		worldXSize = wx;
	}

	public int getWorldYSize() {
		return worldYSize;
	}

	public void setWorldYSize(int wy) {
		worldYSize = wy;
	}
	
	public int getMoney() {
		return money;
	}


	public void setMoney(int my) {
		money = my;
	}
	
	public int getAgentMaxLifespan(){
		return agentMaxLifespan;
	}
	
	public int agentAgentMinLifespan(){
		return agentMinLifespan;
	}
	
	public void setAgentMaxLifespan(int i){
		agentMaxLifespan=i;
	}
	
	public void setAgentMinLifespan(int i){
		agentMinLifespan=i;
	}
	
	
	public static void main(String[] args){
		SimInit init=new SimInit();
		CarryDropModel model=new CarryDropModel();
		init.loadModel(model, "", false);
		
	}


}
