package ShortMap;

import java.util.*;

import ShortMap.GenerateMap;

//记录重复的人
class repeople{
	int peoplelabel;
	int source;
	int destination;
	
	public repeople(int pl, int sr, int ds){
		this.peoplelabel=pl;
		this.source=sr;
		this.destination=ds;
	}
    
}
//重复的这一组人
class repeopleset{
	ArrayList<repeople> rep=new ArrayList<repeople>();
    int allpeople;
    
    public repeopleset(int alp, ArrayList<repeople> rep){
    	this.allpeople=alp;
    	this.rep=rep;
    }
}

//记录每个人那条路上，每个定点的实际距离
class vertexdist{
	int peoplelabel;
	ArrayList<Integer> pathroute= new ArrayList<Integer>();
	Integer[] pathdist;//这个玩意长度未知
	
	
	//和chain保存点的信息不同，用来保存依次点距离的起始点的距离
	public vertexdist(Integer chain[], Integer[][] dist, int p, Integer[] ploc){
	    //保存了路径的信息
		this.pathdist=new Integer[chain.length];//长度要知道
	int i=0;
	    pathroute.add(chain[i]);
	   while(chain[i+1]!=null){
		//System.out.println(chain[i]+" chain i ");
		//System.out.println(chain[i+1]+ "chain i+1 ");
		pathdist[i]=dist[chain[i]][ploc[p]];
		pathroute.add(chain[i+1]);
		i++;
		
	}
	   pathdist[i]=dist[chain[i]][ploc[p]];
	   
	  // System.out.println(pathroute.size()+" routesize ");
}
}

class NewWay{
	int plabel;
	Integer[] route;
	
	public NewWay(int pl, Integer[] chain){
		this.plabel=pl;
		this.route=chain;
	}
}

public class Calrep {
	private static int groupnum=30;
	private static int vertnum=58;
	ArrayList<repeopleset> repset=new ArrayList<repeopleset>();
	ArrayList<repeople> changedpeople=new ArrayList<repeople>();
	ArrayList<vertexdist> vtlist=new ArrayList<vertexdist>(); //每个人路径和路过点的信息
	public static int M=Integer.MAX_VALUE;
	public Integer[] peoplepastpath=new Integer [groupnum]; //当前已经走的长度需要输入
	public Integer[] peopleallpath=new Integer[groupnum]; //总的长度需要输入
	public Integer[][][] Pathtime=new Integer[vertnum][vertnum][groupnum];//对某人两点之间的信息，其他信息是NULL
	
	
//把每个人的路径的信息保存到链表当中
public void caldist(Integer chain[], Integer[][] dist, int pl, Integer[] ploc){
	vertexdist vt1=new vertexdist(chain,dist, pl, ploc);	
	vtlist.add(vt1);
}

//计算每个点对于每个人的通过时间
public void calculatetime(int currenttime, int pl){
	
    int speed=2; //速度
    int time=currenttime; //当前时间
     //对每个人而言的每个人上面的当前的距离 
    //System.out.println("\n "+currenttime+" 当前时间 ");
    //System.out.println(vtlist.size()+" vtlist.size ");
    //System.out.println(vtlist.get(pl).pathroute.size()+ " routesize");
    for(int i=0;i<vtlist.get(pl).pathroute.size()-1;i++){  
    	System.out.println("xxx1"+ vtlist.get(pl).pathdist[i]);
         if(vtlist.get(pl).pathdist[i]>peoplepastpath[vtlist.get(pl).peoplelabel]){  //记录该人路径上接下去的点的所有的通过时间
        	 //System.out.println(vtlist.get(pl).pathroute.get(i)+" route i ");
        	 //System.out.println(vtlist.get(pl).pathroute.get(i+1)+" route i+1 ");
			Pathtime[vtlist.get(pl).pathroute.get(i)][vtlist.get(pl).pathroute.get(i+1)][pl]
        			 =(vtlist.get(pl).pathdist[i]-peoplepastpath[vtlist.get(pl).peoplelabel])/speed+currenttime;
         }
    }
    	
    }

//该函数实现比较下面的点所有通过的时间信息，如果发生重叠，那么记录信息    	
	public void pathrepete(Integer[] pnum,Integer[] ploc){
		int Pathcount;
		Integer[] peoplenumber=pnum;
		Integer[] peoplelocation=ploc;
		ArrayList alist=new ArrayList<Integer[]>();
		
		for (int i=0;i<vertnum;i++)
			for(int j=0;j<vertnum;j++){
				
				// 记录i,j上经过的重复的人数
		        for(int k=0;k<groupnum;k++){
		        	Integer ik=k;
    				Iterator p = alist.iterator();
    				if(p.hasNext())
    				    if(ik==p.next()){
    				    	continue;
    				    }
		        	Pathcount=0;
		        	if(Pathtime[i][j][k]!=null){
		        		ArrayList<repeople> rep=new ArrayList<repeople>();//有相同的才放进去一组人
		        		int st1=Pathtime[i][j][k];
	                       boolean kk=false;
		    			   for(int z=k+1;z<20&&z>k;z++){
		    				  if(Pathtime[i][j][z]!=null){
	                          int st2=Pathtime[i][j][z];
	                          alist.add(z);
	                          if(Math.abs(st1-st2)<5){
	                        	  kk=true;
	                        	  System.out.println("\n \n There are "+peoplenumber[z]+" people from "
	            		    				+peoplelocation[z]+" through line "+i+" to "+j+" at the same time from time "
	                          			  +st2);
	                        		repeople repline1=new repeople(z,i,j);
	                        		Pathcount=peoplenumber[z]+Pathcount;
	      		    				rep.add(repline1);
	                          }	                          
	       	    			   }	                         
		        	          }
	                          if(kk==true){
	                        	 System.out.println("\n \n There are "+peoplenumber[k]+" people from "
	            		    				+peoplelocation[k]+" through line "+i+" to "+j+" at the same time from time "
	                          			  +st1);
	       				    		repeople repline1=new repeople(k,i,j); 
	       				    		Pathcount=peoplenumber[k]+Pathcount;
	             		    		rep.add(repline1);
		    				  }
	                          if(!rep.isEmpty()){
	          		        	repeopleset repsetgroup=new repeopleset(Pathcount,rep);
	          		        	repset.add(repsetgroup);
	          		        	//System.out.println(repset.size()+" repset.size "); //规模过大
	          		        	System.out.println(" ");
	          		        	}
		    			   }
		        	
		        }
			}
	
	}
	

	
//选择最先通过的人的信息	
	public void choosepeople(Integer[][] maxpeople){
		 //该条路的最大上限
		Integer[][][] Pst=Pathtime;//每一组人
		for(int i=0;i<repset.size();i++){ 
			System.out.println(repset.size()+" repset.size ");
	
				int sr=repset.get(i).rep.get(0).source;
				int dt=repset.get(i).rep.get(0).destination;
				int max=maxpeople[sr][dt];
				ArrayList<Integer> listpeople=new ArrayList<Integer>();
				System.out.println(repset.get(i).allpeople+ " all people ");
				System.out.println(" "+max+" ");
				while(repset.get(i).allpeople>max){
					//需要让这些人里面选出一组人来
					int temp=0;
					int min=999;
					for(int j=0;j<repset.get(i).rep.size();j++){//有问题，需要进行修改
						Integer ij=j;
						if(!listpeople.isEmpty()){
							Iterator<Integer> p = listpeople.iterator();
		    				if(p.hasNext())
		    				    if(ij==p.next()){
		    				    	continue;
		    				    }
						  }
						if(Pst[sr][dt][repset.get(i).rep.get(j).peoplelabel]<min){
						   min=Pst[sr][dt][repset.get(i).rep.get(j).peoplelabel];
						   temp=j;
						}
				   }
					listpeople.add(temp);
					//记录这个信息，把这个人从原来的判断中去掉
					//记录要去掉得这个人，这个人经过的哪两个点，去修改这个人的信息，记录
                    repeople rept1=new repeople(repset.get(i).rep.get(temp).peoplelabel,sr,dt);
                    changedpeople.add(rept1);//error判断有误
				max=max+min;	
			   }
		}//找出有多少组这样重复的人
	}
	
//改变地图信息
public Integer[][] changepath(Integer[][] omap,int ploc,int start, int end){
    //改变omap的某一条路径，重新求
	omap[start][end]=M;
	return omap;	
}

		
//返回每个人需要修改路径的人的新的路径
	public ArrayList<NewWay> ChangePeoplepath(Integer[][] disto, int fp, Integer[] peopleloc, Integer[] nextpoint, ArrayList<Integer[]> allroutes){
		Integer[][] omap=new Integer[vertnum][vertnum];
		ArrayList<NewWay> nwylist=new ArrayList<NewWay>(); 
	     for(int i=0;i<changedpeople.size();i++){
	    	  Integer[] newroute;//保存每个人的新的路径
	    	  Integer[] oldroute;//保存每个人的新的路径
	    	  Integer[] finalroute = new Integer[20];//保存每个人的新的路径
	    	 System.out.println(" changedpeople.size "+i);
	    		GenerateMap gpnew=new GenerateMap();
	    		Integer[][] dist1=gpnew.changemap(disto,fp);//地图信息初始化
	    		
	    	     omap=changepath(dist1,changedpeople.get(i).peoplelabel,
	    			 changedpeople.get(i).source,changedpeople.get(i).destination);//error 每个人存储的信息应该都是不同的
                    
				   ArrayList<Integer[][]> listnew=gpnew.Fmap(omap); //最短路径信息
				   //对每个人都有了一个新的最短路径的信息
				
				  oldroute=allroutes.get(changedpeople.get(i).peoplelabel);//原来的chain
				  System.out.println(" 这些人来自 "+ peopleloc[changedpeople.get(i).peoplelabel]);
				  newroute=gpnew.getchain(listnew, nextpoint[changedpeople.get(i).peoplelabel], 21, 53);
				  
				  for(int j=0; j<oldroute.length;j++){
					  finalroute[j]=oldroute[j];
					if(oldroute[j]==peopleloc[changedpeople.get(i).peoplelabel]){//当找到下一个路径时
						int count=0;  
						  while(newroute[count]!=null){
							  finalroute[j+count]=newroute[count];
							  count++;
						  }
						  break;
					}
				  }
				  
				  
				   NewWay nwy= new NewWay(changedpeople.get(i).peoplelabel,
						  finalroute);
				  nwylist.add(nwy);
	     }
			return nwylist;	
	}
	
	//构造函数，处理这些信息，输入所有人的当前的位置信息
	public  Calrep(){
		
	}


	


}





