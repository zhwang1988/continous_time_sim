package SCMAgent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

//��������֮�䴫����Ϣ�ķ�ʽ
public class Message {
	
	//ArrayList<Material> demandmaterial; //ԭ�͵����࣬���ԣ����϶��٣���������
	//ArrayList<Material> sendmaterial;//�������������Ժ����϶��٣��������ֶ��ٵķ��͵�����

	//��������ֻ�������ϵ����
	double locx;//�õ�����֮��ľ���
    double locy;//�õ����߾���
	//һ�����϶�Ӧһ������ʱ��
    private SCMElements SourceNode; //���͵Ľ��շ�node���ȿ��������ε�Ҳ���������ε� 
    private SCMElements TargetNode;
    private Map<Material,Date> orders;//�����Ķ�����Ҳ�����Ƿ�������Ϣ,��������ʵ�������������ʵ��ֵ����Ҫ���ඨ�壬������ʱ��

    //��ȡ��Ϣ�Ľڵ�
	public SCMElements getSourceNode() {
		return SourceNode;
	}
	
	public void setSourceNode(SCMElements sourcenode) {
		this.SourceNode = sourcenode;
	}
	
	public SCMElements getTargetNode() {
		return TargetNode;
	}
	
	public void setTargetNode(SCMElements targetnode) {
		this.TargetNode = targetnode;
	}
	public Map<Material,Date> getOrders() {
		return orders;
	}
	public void setOrders(Map<Material,Date> orders) {
		this.orders = orders;
	}
    
	
	

}