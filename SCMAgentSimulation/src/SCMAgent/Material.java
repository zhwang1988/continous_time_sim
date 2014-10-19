package SCMAgent;
//所有物料的父类,认为物料是一个抽象类
abstract class Material {
	protected int materialtype;//原油，石脑油，乙烯，汽油，柴油，等等
	private double amount;//该种物料有多少
	private double price;//该种物料的固定价格
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

//物料的价格，都是某一种工厂的物料价格，认为是包含在内的数据结构
  public double getPrice() {
	return price;
  }


  public void setPrice(double price) {
	this.price = price;
  }
	
}
