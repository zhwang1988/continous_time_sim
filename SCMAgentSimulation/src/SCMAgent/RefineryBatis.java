package SCMAgent;
//��ṹ�ļ�����ô˵�������ϻ���Ҫ��������Ŀ����һ�µ���
public class RefineryBatis {
	private int RefineryId;
	private String RefineryName;
	private int RefineryType;
	private double RefineryCap;
	
	public RefineryBatis(){};//�Ƿ���Ϊ�˷��䷽���ʹ�ã�
	
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
