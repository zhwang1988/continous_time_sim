package ShortMap;
import java.util.ArrayList;

import ShortMap.GenerateMap;

public class SimPerson {
	private static int groupnum=30;
	private static int vertnum=58;
	int time=0;//������0��1��2��3
	Integer number;//����
	Integer start;//��ʼλ��
	Integer end;//Ŀ��λ��
	
	Integer nextpoint;//��һ��Ŀ���м��
	Integer longpath;//·������
	Integer distance;//���߾���
	ArrayList<Integer[]>  chain= new ArrayList<Integer[]>();//����·��
	
	//����һ������chain�Ľ��
	public int simnextstep(ArrayList<Integer[][]> list, Integer[] route1){
		time++;
		int minstep=8;
		int maxstep=12;
		GenerateMap gp=new GenerateMap();
		
		this.chain.add(time, route1);
		System.out.println("\n");	
		
		Integer[][] dist=list.get(0);
		int i=0,temp=0;
		  
		while(route1[i+1]!=null){
				//System.out.println(chain[i]+" chain i ");
				//System.out.println(chain[i+1]+ "chain i+1 ");
				temp=dist[route1[i]][route1[i+1]]+temp;
				i++;
		   }
		
		this.longpath=temp;
		if (longpath-distance<maxstep){
			distance=longpath;
			System.out.println("�����벻����󲽳������Եִ��յ㡣");		
			System.out.println("��"+this.time+"������������������"+this.number.toString()+" ��㣺"+
					this.start.toString()+" �յ㣺"+this.end.toString()+" ����ǰ���㣺"+ this.nextpoint.toString()+
					 " ��·�̣�"+this.longpath.toString()+ " �Ѿ���·�̣�"+this.distance.toString());
			return 0;//����������
		}
		else{
			distance+=GenerateMap.getrandom(minstep, maxstep);
			this.nextpoint=getnextpoint(list,chain,time,distance);
			System.out.println("��"+this.time+"�����棬������"+this.number.toString()+" ��㣺"+
			this.start.toString()+" �յ㣺"+this.end.toString()+" ����ǰ���㣺"+ this.nextpoint.toString()+
			 " ��·�̣�"+this.longpath.toString()+ " �Ѿ���·�̣�"+this.distance.toString());
			return 1;//����δ����
		}
		
	}
	
	public void initSimPerson(ArrayList<Integer[][]> list, int end){
		this.distance=0;
		this.end=end;
		GenerateMap gp=new GenerateMap();
		this.chain.add(0, gp.getchain(list, start, end, end));
		Integer[][] dist=list.get(0);
    	
		this.longpath=dist[start][end];
		System.out.print("\n");
		this.nextpoint=getnextpoint(list,chain,time,distance);
		System.out.println("��ʼ���棬������"+this.number.toString()+" ��㣺"+
		 this.start.toString()+" �յ㣺"+this.end.toString()+" ����ǰ���㣺"+ this.nextpoint.toString()+
		 " ��·�̣�"+this.longpath.toString()+ " �Ѿ���·�̣�"+this.distance.toString());
	}
	
	private Integer getnextpoint(ArrayList<Integer[][]> list,ArrayList<Integer[]> chain, int time,
			Integer distance) {
		// TODO Auto-generated method stub
		//����chain
		Integer[][] dist=list.get(0);
		Integer[] route=chain.get(time);
		int i=0;
		int j=0;
		Integer temp=distance;
		while(route[i]!=null){
			i++;
		}
		i--;//���·���ĳ���
		if (i==0){
			System.out.print("��������յ��غ�\n");
			return route[i];
		}
		while(temp-dist[route[j]][route[j+1]]>0){
			temp-=dist[route[j]][route[j+1]];
			j++;
		}
		if(route[j+1]==null) 
			return route[j];
		return route[j+1];//������һ����
	}

	public SimPerson(Integer number,Integer start){
		//���캯��
		this.number=number;
		this.start=start;
	}	
	
	public static void main(String[] args) {
		
		
	}


}

