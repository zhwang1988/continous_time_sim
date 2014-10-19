package SCMAgent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Distributor extends SCMElements {
	/*
	 * ���⴫ֵ��ַ������
	 */

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// ʱ��ĸ�ʽ

	private Date currentDate;
	// ���ڶ��ڶ�������ʱ��������ⲿӦ���з���ʱ�䣬���Ի���ⲿ�ķ���ʱ�䣬���������ʱ���time ����,��Ҫ���ⲿ�����ʱ��
	private String name;

	private static final double safecoeff = 0.3; // ��ȫ���͹������ĳ�ʼ�趨
	private static final double maxcoeff = 0.8; // ������������
	private static final double supplyline = 0.5; // ��Ӧ��ָ����

	private ArrayList<Product> InventoryProducts;// ������ڲ����ڵĿ��ͽṹ

	/* some information above will not be used here */

	private int MaxCapProductType[];// �������ʾ���п��ܴ����������ֵ�г�����1-10�ֲ�Ʒ��0�ͱ�ʾ���ܴ�Ÿ�������,��ϩ��
	private double ProductPurchasePrice[];
	private double ProductSalesPrice[];
	private ArrayList<Message> DemandOrders; // ���ζ�����Ϣ���в�ͬ�Ŀͻ������ȼ��أ�,�Ѿ��������ģ��ǶԸÿͻ��Ķ���
	private ArrayList<Message> PurchaseOrders; // ���εķ��͵Ļ���
	private ArrayList<Message> ConfirmOrders; // �϶��ᵽ��Ķ�������Ϣ����Ϊ�Ǽƻ��õ��ģ���ô�������е�ǣǿ
	private ArrayList<Message> BackInformation; // ��Ӧ�̷��ص���Ϣ
	private Map<Customer, Integer> DemandPriority;// ���ε�priority ��Ҫָ�ͻ�,���Ƚ�������
	
	private Map<Product,TreeMap<SCMElements, Integer>> PurchaseRefPriority; //���εĹ����priority
	private Map<Product,TreeMap<SCMElements, Integer>> PurchaseChePriority; // ���εĹ����priority
															// ���ð����е����ӹ�ϵ����������һ��ʼ�������
	// ��Ȼ������������������ģ�Ȼ���ٷ���

	private double dieselprice1;
	private double dieselprice0;
	private double gasolineprice97;
	private double gasolineprice93;

	private double locx; // �����̵�λ������
	private double locy;

	public Distributor(String name) {
		super(name);
		this.name = super.getName();

	}

	// �����ݿ������������
	public Distributor(ArrayList<Product> SaleProducts, String name,
			int ProductType[]) {
		super(name);
		this.name = super.getName();
		this.InventoryProducts = SaleProducts; // �趨��ʼ���
		this.MaxCapProductType = ProductType; // �趨���Դ�ŵ������
	}

	// ������һ��ʼ��Ҫ�����±��ڵĿ�棬��ʼ����棬��������һ���ڵģ����߽��к��㣬��Ӧ���Ǻ������ⲿ�����ģ���ֹ��Щ
	private void initializedata() {
		// �������ʵĳ�ʼ������
		double amountd0 = ToolsClass.getrandom(100, 500);
		Product d0 = new Product("0�Ų���", amountd0, dieselprice0, 0, 1);
		InventoryProducts.add(d0);

		double amountd1 = ToolsClass.getrandom(100, 500);
		Product d1 = new Product("1�Ų���", amountd1, dieselprice1, 1, 1);
		InventoryProducts.add(d1);

		double amountp97 = ToolsClass.getrandom(100, 500);
		Product p97 = new Product("97������", amountp97, gasolineprice97, 97, 2);
		InventoryProducts.add(p97);

		double amountp93 = ToolsClass.getrandom(100, 500);
		Product p93 = new Product("93������", amountp93, gasolineprice93, 93, 2);
		InventoryProducts.add(p93);

	}

	public void getMessageFromCustomer(Message e) {
		// �ŵ���Ӧ�����ݵ���ȥ,����������͸������ȼ��������������ø���������ֵ
		// �������ȼ��𲽻�ȡ���ε���Ʒ�����Ұ������ȼ���������
		int size = DemandOrders.size(); // �����С
		Message last = DemandOrders.get(size);
		if (size == 0)
			DemandOrders.add(e);
		else {
			int lastp = DemandPriority.get(DemandOrders.get(size)
					.getSourceNode());// ���һ�������ȼ�
			int newp = DemandPriority.get(e.getSourceNode());// �õ����ǵ�ǰ��SCM�Ľڵ㣬����Ҫ�ж��������ȼ�
			if (lastp < newp) {
				DemandOrders.remove(size);
				DemandOrders.add(e);
				DemandOrders.add(last);
			} else
				DemandOrders.add(e);
		}
	}

	public void getMessageFromPlants(Message e) {
		
		ConfirmOrders.add(e);
		// �̶��ı��µĶ���
		/*
		 * �ֳ����ɸ����������У����ݵ��Ƕ���ĵ�ַ����ŸĶ��󣬵�Ӧ�ò��ı������
		 * ��û�й�Ӧ�����ȼ��أ��̶���Ӧ�����µף�5000�֣���1000�֣���5000�֣�һ��������ĵ��������ܲ���һֱ����һ���µ�
		 * ��Ӧû�����ȼ�Ŀ������Ӧ�÷ֱ����
		 */
	}

	// Ԥ�⵽�����ȵ��������ȹ�����

	// ��������Ϣ��֪���������Ҫ������Ӧ�Ĺ滮,�ȸ������ε���������ƻ������α������й�Ӧ��
	public void planningFirstStage() {
		/*
		 * ���ݿ�棬��һ�ڵ��������һ�ڵģ�Ԥ�ڵĿͻ��Ķ�������һ����ʵ�ʵĿͻ����󣬲��������η��͵���Ϣ
		 * ��Ҫ��һ���Ŀ������ȼ������⣬���Ҹ������ȼ�������������ݣ����Ƿ���С�
		 * ����˳�򣬴������������Լ�������ʱ�䣬��һ���趨���������������Ȱ�һЩ��ǰ�����������������������ˣ����²����
		 * ʱ����ֱ���˵������Ȳ�����ʱ�䣬ʱ����logistic ���俼�ǣ��ȳ���������� �ѿ�治��Ļ�������ȫ����
		 * ������1һ���µ������أ����ͬһ����������Ϊ������ʱ���ǹ���ʱ���ǰ5�죬�ҾͰѸû���Ĺ���������ȥ����������Ǿ������� ����棿
		 * �Ӵ����ͻ����ȼ��Ķ�����ʼ
		 */

		for (int i = 0; i < DemandOrders.size(); i++) {
			// ÿһ������
			Iterator demanditerator = DemandOrders.get(i).getOrders().keySet()
					.iterator();
			// �Ըĳ����ص����ϵ���Ϣ
			Map<Material, Date> sendbackmaterial = new HashMap();
			// Is this material in supply orders?
			boolean flaginsupply = false;

			while (demanditerator.hasNext()) {
				Product ordermaterial = (Product) demanditerator.next();// ��ȡ��ֵMaterial��ѡ��
				int productnum = InventoryProducts
						.indexOf((Product) ordermaterial);
				Product product = InventoryProducts.get(productnum); // ��ȡ��ǰ����������,��浱�е�����

				for (int j = 0; j < ConfirmOrders.size(); j++) {
					/*
					 * confirmOrders may have different message e but with the
					 * same material m from different plants
					 */
					Iterator supplyiterator = ConfirmOrders.get(i).getOrders()
							.keySet().iterator();
					Product plantmaterial = (Product) supplyiterator.next();// ��ȡ�����ļ�ֵ��ֵMaterial��ѡ��
					flaginsupply = DemandOrders.get(i).getOrders()
							.containsKey(plantmaterial);// demand���Ƿ�����ü�ֵ
					long aheaddays = 0;
					// judge the time gap

					if (flaginsupply) {
						try {
							Date demanddate = DemandOrders.get(i).getOrders()
									.get(ordermaterial);
							Date supplydate = ConfirmOrders.get(j).getOrders()
									.get(plantmaterial);
							long diff = demanddate.getTime()
									- supplydate.getTime();
							aheaddays = diff / (1000 * 60 * 60 * 24);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					/*
					 * if the day is ahead for 5 days, we can see the material
					 * should be transferred into the distributor, this the
					 * inventory products is increased added before or added
					 * later
					 */

					if (aheaddays > 5) {
						double newinventory = InventoryProducts.get(productnum)
								.getAmount() + plantmaterial.getAmount();
						InventoryProducts.get(productnum).setAmount(
								newinventory);
						// supply in and remove, but put the supply into zero is
						// better
						ConfirmOrders.get(j).getOrders().remove(plantmaterial);
					}
					// finished material getting.the left material will be used
					// in the second stage

				}

				double leftinv = product.getAmount(); // ��ǰ��浱�п������������������
				double testleft = leftinv
						- ((Product) ordermaterial).getAmount();
				// ���������ȥ�������Ժ�ʣ�¶��٣�����ʣ��0��200��ʣ�µĲ��ֻ�ʣ�¶��٣�ʣ�µļ�ȥ���������
				double repmin = MaxCapProductType[product.materialtype]
						* safecoeff - testleft;
				// ��С��ȫ��棬����200-200=0��200-(-50)=250,>0��˵����������,<0���� 100-500
				double repmax = MaxCapProductType[product.materialtype]
						* maxcoeff - testleft;
				// ���ɴ�ſ�棬��һ���������ϵ���û�취���������ˣ�����������

				// ������������Ƿ��ܹ�ȥ�����ϲ�������
				// ҵ���߼������鷳
				// ��������������������Ҫ����ʣ�µ�����, �����Ĺ̶��Ŀ�汻������
				double realsendamount = 0;// ��ʼ��,���Ƿ��ܹ�����������������󣬷����̣�����һ����
				if (repmin > 0) {
					// ����������η���,������Ҫ����������
					realsendamount = leftinv
							- MaxCapProductType[product.materialtype]
							* safecoeff;
					// ʵ��ʵ��ʣ�µ���
					InventoryProducts.get(productnum)
							.setAmount(
									MaxCapProductType[product.materialtype]
											* safecoeff);// ȷ��ʣ�����Ҫ����
					// ��Ҫ�ٽ�һ�����͵���Ϣ�����뵽ԭ���ĵ��У����ҷ���
					// ���÷��ص���Ϣ�е㷱��
				} else {
					// �ܹ������
					realsendamount = ordermaterial.getAmount(); // ��õ���ʣ��Ĳ���
					InventoryProducts.get(productnum).setAmount(
							leftinv - realsendamount);
					// ������Ӧ�����ϵ�����
				}
				// ʣ��Ĳ�������Щ

				// ���ص���Ϣ���У�������Щ��־
				if (InventoryProducts.get(productnum).getAmount() > repmax) {
					// ������βɹ��ľ����𣿸÷����̣��������ж࣬������Ϣ��ƽ̨�����Ϻͷ�����
					// ����÷����̵�competition-information�൱�У�����
					// ����-��Ӧ��-��󹩻���-�۸�-ʱ�����Ϣ
				}

				// 1.����ÿһ��ԭ����demandorder����ʣ�¶���û�����㣬���ҷ��ص������Ĺ���
				// 2.�ٸ��ݵ�ǰ�Ŀ������������ε�����Ҫ�Ķ�����Ϣ
				Product m = new Product(); // �ֿ����У����ڸ������ֵ���ص���Ϣ������
				// ȷ������Ҫ��
				m.setAmount(ordermaterial.getAmount() - realsendamount);// realamount
																		// ָʾ����ʵ�ʴ��ڵĶ���
				m.materialtype = ordermaterial.materialtype;
				m.setPrice(ordermaterial.getPrice());
				// ��ֵ����ָ����ͬһ��λ��
				Date a = new Date();
				a = DemandOrders.get(i).getOrders().get(ordermaterial);
				sendbackmaterial.put(m, a);
				Message e = new Message();
				e.setOrders(sendbackmaterial);
				e.setSourceNode(this);
				e.setTargetNode(DemandOrders.get(i).getTargetNode());
				// ����
				BackInformation.add(e);// ��������ȫһ������Ϣ���з���,������Щ�ͻ������˶���
				// ����һ��BackInformation,�������Ϣ�Ǹ÷������ĵ���Ӧ�Ŀͻ�֮��ģ��ͻ���Ҫ��������Ϣ���˺���������߿϶���һ����
				// ���ص����߳��Ժ�һ���ֵĿͻ�����û�а취�õ����㣬��ô�ͻ�Ӧ�ã���ÿ���������ĵ��У��û����ǵ����������
				// �޷������������ʱֻ����һ�֣����ǵõ�������Ϣ��flag���кܶཨ����Բɹ���Ӧ�Ķ���Ŀǰ������Ҳֻ����һ�ֵ�����
				// �ͻ��ڵõ���Щ�Ժ�ȥcompetitionmarketѡ���Լ�����Ҫ�ģ���ȷ��Ҫ��

			}
		}
	}

	public void competitionMarketSend() {
		// ��ʱ�����Ƿ����������������
		
	}

	// ��ɶ�����ʱ��ƻ�֮��������εĶ����ƻ�
	// �Ѿ������һ���ֵ�PurchaseOrders,�������ε��������α�����Ҳ�ǻᷢ�͹̶��Ķ��������ĺ�һ�㹩Ӧ������ͬ
	// ���еĶ���Ӧ��������֣������İ����𣿣���һ�׶ι滮

	public void planningSecondStage() {
		// ������οͻ��Ķ���,���������ã���ͬ�Ĺ��������������еģ��¸��µļƻ��仯
		for (int i = 0; i < InventoryProducts.size(); i++) {
			Product product = InventoryProducts.get(i);
			double needamount = MaxCapProductType[product.materialtype]
					* supplyline - product.getAmount();
			while (needamount > 0) {
				
				
				Material purchasematerial = new Product();
				Date purchaseDueDate = new Date();
				Map<Material, Date> orders = new TreeMap<Material, Date>();
				Message purchasemes = new Message();

				purchasematerial.materialtype = product.materialtype;
				double pamount = needamount * ToolsClass.getrandom(0, 1) * 0.3
						+ needamount;
				purchasematerial.setAmount(pamount);
				needamount = needamount - pamount;
				purchasematerial
						.setPrice(ProductPurchasePrice[product.materialtype]);
				// ѡ��ʱ��
				try {
					Calendar c = Calendar.getInstance();
					c.setTime(this.currentDate);
					int dateOfMonth = c.getActualMaximum(Calendar.DATE);
					c.add(Calendar.DAY_OF_MONTH,
							(int) ((dateOfMonth - 1) * (1 - needamount
									/ MaxCapProductType[product.materialtype]
									* supplyline)));
					purchaseDueDate = c.getTime();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				
				

				if (PublicStaticData.refineryMaterial
						.contains(product.materialtype)) {// ��������ͳ�������
					// �Ƿ�Ҫ���������ͻ��ǻ����أ�����Ҫ��
					// �������ȼ����з���
					Map<SCMElements, Integer> resultMap = ToolsClass
							.sortPriorityMapByValue(PurchaseRefPriority
									.get(product));
					Iterator DownPlants = resultMap.keySet().iterator();
					Refinery refinery = (Refinery) DownPlants.next();
					purchasemes.setTargetNode(refinery);
					
				} else if (PublicStaticData.petrochemicalMaterial
						.contains(product.materialtype)) {
					Map<SCMElements, Integer> resultMap = ToolsClass
							.sortPriorityMapByValue(PurchaseChePriority
									.get(product));
					Iterator DownPlants = resultMap.keySet().iterator();
					ChemicalPlant cheplant = (ChemicalPlant) DownPlants.next();
					purchasemes.setTargetNode(cheplant);
				}
				
	
				if(PurchaseOrders.size()>0){
					int flag=0;
					for(int j=0;j<PurchaseOrders.size();j++){
						if(PurchaseOrders.get(j).getTargetNode()==purchasemes.getTargetNode()){
							PurchaseOrders.get(j).getOrders().put(purchasematerial, purchaseDueDate);
							flag=1;
							break;
						}
					}
					if(flag==0){
						orders.put(purchasematerial, purchaseDueDate);
						purchasemes.setSourceNode(this);
						purchasemes.setOrders(orders);
						PurchaseOrders.add(purchasemes);
					}
				}else{
					orders.put(purchasematerial, purchaseDueDate);
					purchasemes.setSourceNode(this);
					purchasemes.setOrders(orders);
					PurchaseOrders.add(purchasemes);
				}
				// ����ɹ���Ϣ����,
			}
		}
		// ��Ӧ��ʱ���MAP����Ƿ��г���ʱ�䰡��װж���������ǵ�̫�࣬Ŀǰֻ���ǲ������ʱ��
	}

	public ArrayList<Message> SendPurchaseMessages(){
		//�ⲿ�ֶ���
		return PurchaseOrders;
		//���������������εĲ��֣�����ˣ�			
	}

	public void confirm() {
		// ȷ�����η��͹��������յ���������Ҳ��Ҫһ��ʱ��,
		
	}
	
	
	public void chooseLogisticAgent(){
		//��Ҫѡ����Ӧ���������壬��purchaseOrder�����ݷ��뵽�г�����
	}
	

	public Date getCurrentDate(){
		return currentDate;
	}

	// ���ⲿ���̻߳�ȡ��ǰ��ʱ��
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public int[] getMaxCapProductType() {
		return MaxCapProductType;
	}

	public void setMaxCapProductType(int[] maxCapProductType) {
		MaxCapProductType = maxCapProductType;
	}

}