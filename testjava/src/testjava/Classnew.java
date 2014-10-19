package testjava;

public class Classnew {
	int a=1;
	
	public void print(){
	 a+=1;
	 System.out.println(a);
	}
	
	public static void main(String[] args){
		Classnew c1=new Classnew();
		c1.print();
		Classnew c2=new Classnew();
		c2.print();
	}
}
