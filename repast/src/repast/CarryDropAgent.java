package repast;

import java.awt.Color;

import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.space.Object2DGrid;


public class CarryDropAgent implements Drawable {
	private int x;
	private int y;
	private int vX;
	private int vY;
	private int money;
	private int stepsToLive;
	private static int IDNumber=0;
	private int ID;
	private CarryDropSpace cdSpace;
	
	public CarryDropAgent(int minLifespan, int maxLifespan){
		x=-1;
		y=-1;
		money=0;
		setVxVy();
		stepsToLive=((int)((Math.random()*(maxLifespan-minLifespan))+minLifespan));
		IDNumber++; //staic is fixed for every new agent
		ID=IDNumber;
	}
	
	private void setVxVy(){
		vX=0;
		vY=0;
		while((vX==0)&&(vY==0)){
			vX=(int)Math.floor(Math.random()*3)-1;
			vY=(int)Math.floor(Math.random()*3)-1;
		}
	}
	
	public void setXY(int newX, int newY){
		x=newX;
		y=newY;
	}
	
	public void setCarryDropSpace(CarryDropSpace cds){
		cdSpace=cds;
	}

	public void step(){
		int newX=x+vX;
		int newY=y+vY;
		Object2DGrid grid=cdSpace.getCurrentAgentSpace();
		newX=(newX+grid.getSizeX())%grid.getSizeX();
		newY=(newY+grid.getSizeY())%grid.getSizeY();
		if(tryMove(newX,newY)){
			money+=cdSpace.takeMoneyAt(x,y);
		}else{
			CarryDropAgent cda=cdSpace.getAgentAt(newX, newY);
			if(cda!=null){
				if(money>0){
					cda.recieveMoney(1);
					money--;
				}
			}
			setVxVy();
	    }
		stepsToLive--;
	}
	
	private void recieveMoney(int i) {
	     this.money+=i;
		
	}

	private boolean tryMove(int newX, int newY){
		return cdSpace.moveAgentAt(x,y,newX,newY);
	}
	
	
	public String getID() {
		return "A-"+ID;
	}

    public int getMoney(){
    	return money;
    }
	
	
	public int getStepsToLive() {
		return stepsToLive;
	}
	
	public void report(){
		System.out.println(getID()+" at "+x+","+y+" has "+getMoney()+" dollars "+"and "+
	getStepsToLive()+" steps to live.");
	}

	@Override
	public void draw(SimGraphics G) {
		
		if(stepsToLive>10)
		G.drawFastRoundRect(Color.green);
		else
	     G.drawFastRoundRect(Color.blue);
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	



}
