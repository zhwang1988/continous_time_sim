package SCMAgent;

import java.util.ArrayList;

public class Supplier extends SCMElements{
	  //本质上供应商应该还不止一个
      //供应商更新时间，在该步仿真过程中，更新
	  //每个月，首先根据上个月或者优化的指令和数据，做出订货方案，以及生产的方案
	  //
	public Supplier(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	  ArrayList<Port> inports;  //定义原油挖掘生产点的port属性
	  ArrayList<Port> outports;  //定义原油挖掘生产点的port属性
	  
	  ArrayList<Crudeoil> storecrudes; //从所有inports计算得来的，但是肯定也有多种属性，不同种的原油
	  //得到消息
	  public void getMessagefromOil(){
		  
	  }
	  
	  public void sendMessagetoOil(){
		  
	  }
	  
	  public void getMessagefromPlants(){
		  
	  }
	  
	  public void sendMessagefromPlants(){
		  
	  }
	  
	  public void bidinoffer(){
		 //根据价格和综合的函数选择好的指标，当然也有时间的考虑
	  }//选择进料的价格的选择

	public void report() {
		// TODO Auto-generated method stub
		
	}
	  
	  
	  
	  
	  //there is different kinds of plants
	  //更多的应该是两者的关系
	  //对供应商来说主要是订货和销售的两个功能，然后选择合适的配送商，此后必须对库存进行严格的控制  
	  
	  
	  //供应商是否考虑有几级供应商呢，和原油的供应的区别在哪里
	 //降低库存，给出更好的策略
	
	
	
}
