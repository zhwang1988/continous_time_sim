package SCMAgent;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Comparable;

public class SCMElements implements Comparable{
	//define the basic operation
	//���еĹ�Ӧ�����幫�еķ�����������ʲô
	private String type;
	private String name;
	private int id;
	double priceout;
	ArrayList<Port> Ports;

	
	public SCMElements(String name){
		this.setName(name);
	}
	
	public void purchase(){
		
	}
	
	public void sale(){
		
	}
	
	public void transfermode(){
		
	}
	
	public void operationrule(){
		
	}
	//��������ƽ���
	public void materialBalance(){
		
	}
	//���崫��Ĺ��ܣ���Щ������Ϣ
	public void MessageTransfer(){
		
	}
	//�о�̬��

	
	
/*	public static int judgematerialtype(Material p){
		switch(p.materialtype){
		case 1:
			
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		default:
			return 0;
		}	
	}*/
	
		

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Object o) {
		SCMElements s1=(SCMElements) o;//(ת���Ƚ�); 
		return this.getName().compareTo(s1.getName());
	}
	//@Override
	/*public boolean equals(Object o){     //equals
         boolean flag = false;
         if(o instanceof SCMElements){
                int result;
                result=this.name.compareTo(((SCMElements)o).name);
				if(result==0)
                       flag = true;
         }
         return false;          
  }   */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

//����ڵ�Ŀ��
class Port{
	boolean Direction;
	double Inventory;
}

class Mode{
	String operationname;
	int optype;//
	public Mode(int mode){
		this.optype=mode;
	}
	
}