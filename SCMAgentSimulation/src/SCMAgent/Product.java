package SCMAgent;

public class Product extends Material {
	private double amount;

	private double price;
	private double property;
	private int producttype;
	private String productname;
	
	public Product(){
		super();
	}
	
	public Product(String name, double amount, double price, double property, int producttype){
		//调用了默认的父类的构造函数,producttype---汽油还是柴油还是乙烯？名称--所有的物料的名称
		super(producttype);
		this.amount=amount;
		this.setPrice(price);
		this.property=property;
		this.producttype=producttype;
		this.productname=name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	


}
