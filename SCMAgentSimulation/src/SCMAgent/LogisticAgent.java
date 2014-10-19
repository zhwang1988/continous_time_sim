package SCMAgent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

//������ͬport��port֮����������logistic type
class LogisticAgent extends SCMElements {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//ʱ��ĸ�ʽ
	private String name;
	private boolean used;
	private int Logistictype;
	private double transprice;
	private double maxtransferamount;
	private int mintransrate; //�����ٶȵ����ֵ
	private int maxtransrate;  //�����ٶȵ���Сֵ
	private Date currentDate; //��ǰʱ��
	ArrayList<SCMElements> sourcenode; //����ķ���ͽڵ�
	ArrayList<SCMElements> targetnode;//�����Ŀ�ģ������ϲ��������ε�	
	ArrayList<Material> materials; //�����ԭ�ͻ���˵���������
	Map<Material,Date> TransferOrders; //����Ķ����������кܶ������Ķ���������Щ�ܵĶ���Ҳ��Ҫ����ͳһ����
	
	
	public LogisticAgent(String name, int logistictype){
		//ȷ����ǰ���ϵ�����,��ʼ���趨�õ�
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
		//�����̸�������Ϣָ��
		TransferOrders=e.getOrders();//�������𣿸�����ʱ������	
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
		//���������ʱ�䣬�������Ҳ�ж���ʱ���ڵ���ģ���ʱ���Բ���
	}

}