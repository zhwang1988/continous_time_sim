package SCMAgent;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Comparable;

public class SCMElements implements Comparable{
	//define the basic operation
	//所有的供应链主体公有的方法和属性是什么
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
	//定义物料平衡的
	public void materialBalance(){
		
	}
	//定义传输的功能，哪些操作信息
	public void MessageTransfer(){
		
	}
	//有静态类

	
	
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
		SCMElements s1=(SCMElements) o;//(转换比较); 
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

//定义节点的库存
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
