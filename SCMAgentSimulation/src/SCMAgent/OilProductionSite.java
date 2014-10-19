package SCMAgent;
import java.util.ArrayList;

class OilProductionSite extends SCMElements {
    int Type=1;
  
    private String Oiltype;
    private String name;//定义为私有以便于调用增加节点
    private int minp; //最小开采量
    private int maxp; //最大开采量
    ArrayList<Port> ports;  //定义原油挖掘生产点的port属性
    ArrayList<Crudeoil> crudes;//定义该供应商所有的不同种类的原油
    ArrayList<Double> produceamount; //某几种原油能开采多少，一般也只有一种或者两种
   
	
	public OilProductionSite(String name,String Oiltype){
		super(name);
		this.name=super.getName();//
	}
//below is the initialization parameter//
	
//----------------------------------------------------//	

	public void setCrudes(Crudeoil crude){
		crudes.add(crude);
	}
	
	public void setPorts(Port port){
		Ports.add(port);
	}
	   
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOiltype() {
		return Oiltype;
	}

	public void setOiltype(String oiltype) {
		Oiltype = oiltype;
		
	}
	
	public void orderSale(ArrayList<Double> price){
		for(int i=0;i<crudes.size();i++)
			crudes.get(i).setPrice(price.get(i));
	}
//------------------------------------------------------//	
	public void oilProcure(){
		//随机产生,并且量和原油的种类个数一样多
		for(int i=0;i<crudes.size();i++){
		produceamount.add(i,ToolsClass.getrandom(minp, maxp));
		}
	}
	
	//定义生产平衡数据
	public void materialBalance(){
		//每一种原油的oilamount
		for(int i=0;i<crudes.size();i++)
		crudes.get(i).setAmount(crudes.get(i).getAmount()+produceamount.get(i));
	}
	
	//询问价格？是否需要时刻发起询问
	public void bidgamble(ArrayList<SCMElements> Suppliers){
		
	}
	//指定自己每一个物料的个体价格,价格基本可以从数据库读取

	//信息传输
	public void messageTransfer(){
		
	}
	
	//得到信息,如果是所有的supplier都发送价格询问，物料的需求过来，该如何应对？这个message应该是一组
	public void getMessage(){
		//Messgae,供应商发送的价格，发送的物料需求量至少要多少，至多要多少，因为库存，限制，发送的价格最低价格，是否同意
	}
	//发送信息
	public void sendMessage(){
		
	}
	
	public void operation(){
		//进行相关的操作，一种port 对应相应的transportation mode, 最终的运输过程
	}
	
	public void Decision(){
		//进行决策,每个月有开采的量，因为也有了必须要卖掉得量
	}
	
	public boolean Test(){
		return false;
	}
	
	public void behavior(){
		oilProcure();
        do{
	    getMessage();
		Decision();
		sendMessage();
        }
        while(Test());
		operation();
	}	
}
