package SCMAgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;


class Customer extends SCMElements {
	//�ͻ��Ķ�����Ӧ���Ƿ�ɢ�����Ĳ��ԣ�������������ٱȽϼ۸񣬲�Ȼ̫���ӣ�����������Ķ�Ҫѯ�ۣ��Ƚϼ۸�Ļ���
	//�Ȳ����Ƚϻ��ƣ�ȷ�����ݽṹ
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//ʱ��ĸ�ʽ
	
	ArrayList<Product> Demands;
	//���ڿͻ���˵�Ƿ�����ʱ���أ�
	private String name;
	ArrayList<Distributor> ConnectedDistributors;//���ο������ӵ�һ���ַ�����
	Map<Product,Date> SendProductOrderss=new HashMap<Product,Date>(); //���ڴ�����������һ����
	ArrayList<Message> SendOrders;
	
	////initialize 
	//�ͻ�������ļ۸���
	private double dieselprice1;
	private double dieselprice0;
	private double gasolineprice97;
	private double gasolineprice93;
	

	//�ڹ��캯�����и���products,����ָ��,�����ݿ⵱���㲢�Ҷ�ȡ
	
	
	public Customer(String name, ArrayList<Product> products){
	//���캯��������������Ҫ������
		super(name);
		this.name=super.getName();
	    this.Demands=products;
	}
	
	public void initializedemand(){
		double amount;
		//��������Ϊ��������Ǵ����ݿ⵱�ж�ȡ����ô����Ϊrandom
		amount=Randomdemand(100,500);
		Product d0=new Product("0�Ų���",amount,dieselprice0,0,1);
		Demands.add(d0);
		amount=Randomdemand(100,500);
		Product d1=new Product("1�Ų���",amount,dieselprice1,1,1);
		Demands.add(d1);
		amount=Randomdemand(100,500);
		Product p97=new Product("97������",amount,gasolineprice97,97,2);
		Demands.add(p97);
		amount=Randomdemand(100,500);
		Product p93=new Product("93������",amount,gasolineprice93,93,2);
		Demands.add(p93);	
	}

	//���ڲ��԰���
	public double Randomdemand(int mindemand, int maxdemand) { 
		double amount;
		amount=Math.random()*(maxdemand-mindemand)+mindemand;
		return amount;
	}
	
	public void report() {
		// TODO Auto-generated method stub
		//���岻ͬ���������
	}
	
	public void generateDemands(){
		//	�������ɿͻ��������������еĸ����ͻ������󣬵õ�һ�ű��ͻ��������б������������̣�����ʱ�䣬����order����
		//	for example,�����Ǵ����ݿ��ȡJDBC������mybatis�����ݣ���������Ӧ�ô����ݿ��ȡ��
		for(int i=0;i<ConnectedDistributors.size();i++){
			//���Ϻ���Ӧ�Ĺ�����Ҫ�Ͷ�Ӧ�����ݽṹ��ƥ������
		double amount=Randomdemand(100,500);
		Product p97=new Product("97������",amount,gasolineprice97,97,2);
		Message ms=new Message();
		
		Date dueDate=new Date();
		try{
			dueDate=format.parse("2014-08-05");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		SendProductOrderss.put(p97, dueDate); //�õ������ϣ�������Ҫѡ����ʵķ����̽��з���,ѡ��һ��������
		//�кܶ����϶�Ҫ�������,�źܶ�ط�
		//SendProductOrderss.put(p90, dueDate);
		//SendProductOrderss.put(p93, dueDate);
		
		ms.setSourceNode(this);
		ms.setTargetNode(ConnectedDistributors.get(i));
		//�����еĵط�������һ��
		ConnectedDistributors.get(i).getMessageFromCustomer(ms);
		
		}					
	}
	
	//�ٷ�����Ϣ,����������getter����
	public ArrayList<Message> sendMessageToDistributor(){
		return this.SendOrders;
	}

}