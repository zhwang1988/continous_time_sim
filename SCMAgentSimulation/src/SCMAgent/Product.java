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
		//������Ĭ�ϵĸ���Ĺ��캯��,producttype---���ͻ��ǲ��ͻ�����ϩ������--���е����ϵ�����
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
