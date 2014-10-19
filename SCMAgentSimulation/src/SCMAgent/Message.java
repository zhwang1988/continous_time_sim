package SCMAgent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

//定义主体之间传输信息的方式
public class Message {
	
	//ArrayList<Material> demandmaterial; //原油的种类，属性，物料多少，都在里面
	//ArrayList<Material> sendmaterial;//发出的种类属性和物料多少，在其中又多少的发送的物料

	//上述的是只考虑物料的情况
	double locx;//得到两者之间的距离
    double locy;//得到两者距离
	//一种物料对应一个到达时间
    private SCMElements SourceNode; //发送的接收方node，既可以是上游的也可以是下游的 
    private SCMElements TargetNode;
    private Map<Material,Date> orders;//订货的订单，也可以是反馈的信息,反馈的真实可以运输的量和实际值，不要冗余定义，订单的时间

    //获取信息的节点
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
