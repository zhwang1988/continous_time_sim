package SCMAgent;
//表结构文件，那么说明本质上还是要和其基本的框架是一致的吗？
public class RefineryBatis {
	private int RefineryId;
	private String RefineryName;
	private int RefineryType;
	private double RefineryCap;
	
	public RefineryBatis(){};//是否是为了反射方便而使用？
	
	public RefineryBatis(int idrefinery, String name){
		this.setRefineryId(idrefinery);
		this.setRefineryName(name);
	}
	
	public int getRefineryId() {
		return this.RefineryId;
	}
	
	public void setRefineryId(int idrefinery) {
		this.RefineryId = idrefinery;
	}
	
	public String getRefineryName() {
		return this.RefineryName;
	}
	public void setRefineryName(String refineryName) {
		this.RefineryName = refineryName;
	}
	public int getRefineryType() {
		return this.RefineryType;
	}
	public void setRefineryType(int refineryType) {
		this.RefineryType = refineryType;
	}
	public double getRefineryCap() {
		return this.RefineryCap;
	}
	public void setRefineryCap(double refineryCap) {
		this.RefineryCap = refineryCap;
	}
	
	@Override
	public String toString(){
		return "Refinery [id="+this.RefineryId+", name="+this.RefineryName+", "
				+ "refineryCap="+this.RefineryCap+"]";	
	}
}
