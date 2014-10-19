package ShortMap;
import ShortMap.GenerateMap;
import ShortMap.SimPerson;
import ShortMap.Calrep;

import java.util.ArrayList;
import java.util.Iterator;


public class runmap {
	     private static int pnum=30;
	public static void main(String[] args) {
		 boolean flag[]=new boolean[pnum];
		int fp=28; //火灾地点
		GenerateMap gp=new GenerateMap();
		Integer[][] dist0 =gp.gemap();
		Integer[][] maxpeople=gp.maxcp(10,20);//得到路的宽度
		Integer[][] dist=gp.changemap(dist0,fp);//地图信息初始化
		GenerateMap gp2=new GenerateMap();
		Integer[][] dist1=gp2.changemap(dist0,fp);//地图信息初始化
		gp.Initpeople(fp); //人员初始化
		ArrayList<Integer[][]> list=gp.Fmap(dist); //计算最短路径信息，改变了dist
		//gp.GetInformation(list,dist1,fp); //计算人，车路径时间，重叠时间
//重新	   
	   
	   System.out.println("\n ----------------------------- \n");	
	   ArrayList<SimPerson> allPerson=new ArrayList<SimPerson>();
	   int count=gp.peoplelocation.length;
	   //int count=1;
	   Integer[] state=new Integer[count];
	   for (int i=0;i<count;i++){
		   int end;
		   SimPerson one=new SimPerson(gp.peoplenumber[i],gp.peoplelocation[i]);
		   allPerson.add(one);
		   end=gp.getescapepoint(list, gp.peoplelocation[i], 21, 53);
		   state[i]=1;//仿真未结束
		   allPerson.get(i).initSimPerson(list, end);
		  
		   
	   }
	   
	   ArrayList<Integer[]> allroutes=new ArrayList<Integer[]>();//初始化得到所有的chain; 而且应该只在第一步才去完成;
	   for(int i=0;i<count;i++){
	   Integer[] chain;
	   chain=gp.getchain(list, gp.peoplelocation[i], 21,53);//每个人的都可以得到
	   allroutes.add(chain);//长度和人的组数一样
	   }
	   
	   while (runmap.isRunning(state)){
		   //在此处需要得到一个初始的
		   //后期判断必须先知道每个人的路径，所有人的路径必须已经知道，需要从这一点来改变
		   //chain的长度是100
		   Calrep cp1=new Calrep();//本质上可以在一个构造函数内解决这些问题
		   ArrayList<NewWay> nwylist=new ArrayList<NewWay>(); //用于存储新的路径参数
	       Integer[] nextpoint=new Integer[pnum];
		   for(int i=0;i<count;i++){
		   
		   //到最后地图上剩下的人会越来越少，要把之前的一些人都去掉
			 cp1.peoplepastpath[i]=allPerson.get(i).distance;//已经经过的路径
			 cp1.peopleallpath[i]=allPerson.get(i).longpath;//总长
			 cp1.caldist(allroutes.get(i), list.get(0), i, gp.peoplelocation);//当前路径上的每个点的距离信息都能够保存下来
			 System.out.println("xxx");
			 cp1.calculatetime(allPerson.get(i).time*5, i);//计算每个人在这条路上的每个点的通过的时间
			 nextpoint[i]=allPerson.get(i).nextpoint;
		   }
		     cp1.pathrepete(gp.peoplenumber,gp.peoplelocation); //把一些重复的人选出来
	 cp1.choosepeople(maxpeople); //挑出最先到达的人给他们重新安排路径
	
	 nwylist=cp1.ChangePeoplepath(dist1,fp,gp.peoplelocation,nextpoint,allroutes);
	 if(!nwylist.isEmpty())
	 System.out.println("\n 新的路径信息如上");
			 
			  
			 
		   for (int i=0;i<count;i++){
			   //得到每个人的路径
			   //修正路径
			   //根据外面的参数来修改这个chain
			   if(state[i]==1){
				  
				  flag[i]=true;
				  for(int j=0;j<nwylist.size();j++){
						    if(nwylist.get(j).plabel==i&&flag[i]){
						   //state[i]=allPerson.get(i).simnextstep(list,nwylist.get(j).route);
						    	System.out.println("\n新修改的路径 该人数为"+gp.peoplenumber[i]+" 起点是 "+gp.peoplelocation[i]);
						    	allroutes.remove(i);
						    	allroutes.add(i,nwylist.get(j).route);
						    flag[i]=false;
						    }
					 }
				  state[i]=allPerson.get(i).simnextstep(list,allroutes.get(i));
				  //if(flag[i])
				      				
			  /* }
			   else{
				   flag=false;
				   //gp.peoplelocation[i]=gp.getescapepoint(list, gp.peoplelocation[i], 41, 5);
			   }*/
			   }
		   }
		   
		 
			

		
				   
		   System.out.print("\n  ----");
      //if (!flag)
       //break;
	   }	
	}
	
	

	public static boolean isRunning(Integer[] state){
		int length=state.length;
		for(int i=0;i<length;i++){
			if (state[i]==1)
				return true;
		}
		return false;
	}
}
