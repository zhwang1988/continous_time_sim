package SCMAgent;
//�������ϵĸ���,��Ϊ������һ��������
abstract class Material {
	protected int materialtype;//ԭ�ͣ�ʯ���ͣ���ϩ�����ͣ����ͣ��ȵ�
	private double amount;//���������ж���
	private double price;//�������ϵĹ̶��۸�
	private double property;
	
	
  public  Material(int mtype){
	   this.materialtype=mtype;
   }


  public Material() {
	// TODO Auto-generated constructor stub
  }


  public double getAmount() {
	return amount;
  }


  public void setAmount(double amount) {
	  this.amount = amount;
  }

//���ϵļ۸񣬶���ĳһ�ֹ��������ϼ۸���Ϊ�ǰ������ڵ����ݽṹ
  public double getPrice() {
	return price;
  }


  public void setPrice(double price) {
	this.price = price;
  }
	
}
