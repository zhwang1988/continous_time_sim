package SCMAgent;
import java.util.ArrayList;

class OilProductionSite extends SCMElements {
    int Type=1;
  
    private String Oiltype;
    private String name;//����Ϊ˽���Ա��ڵ������ӽڵ�
    private int minp; //��С������
    private int maxp; //��󿪲���
    ArrayList<Port> ports;  //����ԭ���ھ��������port����
    ArrayList<Crudeoil> crudes;//����ù�Ӧ�����еĲ�ͬ�����ԭ��
    ArrayList<Double> produceamount; //ĳ����ԭ���ܿ��ɶ��٣�һ��Ҳֻ��һ�ֻ�������
   
	
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
		//�������,��������ԭ�͵��������һ����
		for(int i=0;i<crudes.size();i++){
		produceamount.add(i,ToolsClass.getrandom(minp, maxp));
		}
	}
	
	//��������ƽ������
	public void materialBalance(){
		//ÿһ��ԭ�͵�oilamount
		for(int i=0;i<crudes.size();i++)
		crudes.get(i).setAmount(crudes.get(i).getAmount()+produceamount.get(i));
	}
	
	//ѯ�ʼ۸��Ƿ���Ҫʱ�̷���ѯ��
	public void bidgamble(ArrayList<SCMElements> Suppliers){
		
	}
	//ָ���Լ�ÿһ�����ϵĸ���۸�,�۸�������Դ����ݿ��ȡ

	//��Ϣ����
	public void messageTransfer(){
		
	}
	
	//�õ���Ϣ,��������е�supplier�����ͼ۸�ѯ�ʣ����ϵ���������������Ӧ�ԣ����messageӦ����һ��
	public void getMessage(){
		//Messgae,��Ӧ�̷��͵ļ۸񣬷��͵���������������Ҫ���٣�����Ҫ���٣���Ϊ��棬���ƣ����͵ļ۸���ͼ۸��Ƿ�ͬ��
	}
	//������Ϣ
	public void sendMessage(){
		
	}
	
	public void operation(){
		//������صĲ�����һ��port ��Ӧ��Ӧ��transportation mode, ���յ��������
	}
	
	public void Decision(){
		//���о���,ÿ�����п��ɵ�������ΪҲ���˱���Ҫ��������
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
