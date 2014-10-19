package SCMAgent;
//原油的class
class Crudeoil extends Material{
	
	String oilname;
	int oiltype;
	double sulfur;
	double density;
	private double amount;//该种原油有多少
	private double price;//该种原油的固定价格
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
//是否应该定义为私有变量
	
	
}
