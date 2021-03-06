package ShortMap;
import java.util.ArrayList;

import ShortMap.GenerateMap;

public class SimPerson {
	private static int groupnum=30;
	private static int vertnum=58;
	int time=0;//步长，0、1、2、3
	Integer number;//人数
	Integer start;//初始位置
	Integer end;//目标位置
	
	Integer nextpoint;//下一个目标中间点
	Integer longpath;//路径长度
	Integer distance;//已走距离
	ArrayList<Integer[]>  chain= new ArrayList<Integer[]>();//经过路径
	
	//增加一个更新chain的借口
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
			System.out.println("最后距离不足最大步长，可以抵达终点。");		
			System.out.println("第"+this.time+"步仿真后结束，人数："+this.number.toString()+" 起点："+
					this.start.toString()+" 终点："+this.end.toString()+" 即将前往点："+ this.nextpoint.toString()+
					 " 总路程："+this.longpath.toString()+ " 已经过路程："+this.distance.toString());
			return 0;//仿真结束标记
		}
		else{
			distance+=GenerateMap.getrandom(minstep, maxstep);
			this.nextpoint=getnextpoint(list,chain,time,distance);
			System.out.println("第"+this.time+"步仿真，人数："+this.number.toString()+" 起点："+
			this.start.toString()+" 终点："+this.end.toString()+" 即将前往点："+ this.nextpoint.toString()+
			 " 总路程："+this.longpath.toString()+ " 已经过路程："+this.distance.toString());
			return 1;//仿真未结束
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
		System.out.println("开始仿真，人数："+this.number.toString()+" 起点："+
		 this.start.toString()+" 终点："+this.end.toString()+" 即将前往点："+ this.nextpoint.toString()+
		 " 总路程："+this.longpath.toString()+ " 已经过路程："+this.distance.toString());
	}
	
	private Integer getnextpoint(ArrayList<Integer[][]> list,ArrayList<Integer[]> chain, int time,
			Integer distance) {
		// TODO Auto-generated method stub
		//更新chain
		Integer[][] dist=list.get(0);
		Integer[] route=chain.get(time);
		int i=0;
		int j=0;
		Integer temp=distance;
		while(route[i]!=null){
			i++;
		}
		i--;//获得路径的长度
		if (i==0){
			System.out.print("出发点和终点重合\n");
			return route[i];
		}
		while(temp-dist[route[j]][route[j+1]]>0){
			temp-=dist[route[j]][route[j+1]];
			j++;
		}
		if(route[j+1]==null) 
			return route[j];
		return route[j+1];//返回下一个点
	}

	public SimPerson(Integer number,Integer start){
		//构造函数
		this.number=number;
		this.start=start;
	}	
	
	public static void main(String[] args) {
		
		
	}


}

