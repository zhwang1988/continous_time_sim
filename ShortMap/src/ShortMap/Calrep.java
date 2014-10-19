package ShortMap;

import java.util.*;

import ShortMap.GenerateMap;

//��¼�ظ�����
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
//�ظ�����һ����
class repeopleset{
	ArrayList<repeople> rep=new ArrayList<repeople>();
    int allpeople;
    
    public repeopleset(int alp, ArrayList<repeople> rep){
    	this.allpeople=alp;
    	this.rep=rep;
    }
}

//��¼ÿ��������·�ϣ�ÿ�������ʵ�ʾ���
class vertexdist{
	int peoplelabel;
	ArrayList<Integer> pathroute= new ArrayList<Integer>();
	Integer[] pathdist;//������ⳤ��δ֪
	
	
	//��chain��������Ϣ��ͬ�������������ε�������ʼ��ľ���
	public vertexdist(Integer chain[], Integer[][] dist, int p, Integer[] ploc){
	    //������·������Ϣ
		this.pathdist=new Integer[chain.length];//����Ҫ֪��
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
	ArrayList<vertexdist> vtlist=new ArrayList<vertexdist>(); //ÿ����·����·�������Ϣ
	public static int M=Integer.MAX_VALUE;
	public Integer[] peoplepastpath=new Integer [groupnum]; //��ǰ�Ѿ��ߵĳ�����Ҫ����
	public Integer[] peopleallpath=new Integer[groupnum]; //�ܵĳ�����Ҫ����
	public Integer[][][] Pathtime=new Integer[vertnum][vertnum][groupnum];//��ĳ������֮�����Ϣ��������Ϣ��NULL
	
	
//��ÿ���˵�·������Ϣ���浽������
public void caldist(Integer chain[], Integer[][] dist, int pl, Integer[] ploc){
	vertexdist vt1=new vertexdist(chain,dist, pl, ploc);	
	vtlist.add(vt1);
}

//����ÿ�������ÿ���˵�ͨ��ʱ��
public void calculatetime(int currenttime, int pl){
	
    int speed=2; //�ٶ�
    int time=currenttime; //��ǰʱ��
     //��ÿ���˶��Ե�ÿ��������ĵ�ǰ�ľ��� 
    //System.out.println("\n "+currenttime+" ��ǰʱ�� ");
    //System.out.println(vtlist.size()+" vtlist.size ");
    //System.out.println(vtlist.get(pl).pathroute.size()+ " routesize");
    for(int i=0;i<vtlist.get(pl).pathroute.size()-1;i++){  
    	System.out.println("xxx1"+ vtlist.get(pl).pathdist[i]);
         if(vtlist.get(pl).pathdist[i]>peoplepastpath[vtlist.get(pl).peoplelabel]){  //��¼����·���Ͻ���ȥ�ĵ�����е�ͨ��ʱ��
        	 //System.out.println(vtlist.get(pl).pathroute.get(i)+" route i ");
        	 //System.out.println(vtlist.get(pl).pathroute.get(i+1)+" route i+1 ");
			Pathtime[vtlist.get(pl).pathroute.get(i)][vtlist.get(pl).pathroute.get(i+1)][pl]
        			 =(vtlist.get(pl).pathdist[i]-peoplepastpath[vtlist.get(pl).peoplelabel])/speed+currenttime;
         }
    }
    	
    }

//�ú���ʵ�ֱȽ�����ĵ�����ͨ����ʱ����Ϣ����������ص�����ô��¼��Ϣ    	
	public void pathrepete(Integer[] pnum,Integer[] ploc){
		int Pathcount;
		Integer[] peoplenumber=pnum;
		Integer[] peoplelocation=ploc;
		ArrayList alist=new ArrayList<Integer[]>();
		
		for (int i=0;i<vertnum;i++)
			for(int j=0;j<vertnum;j++){
				
				// ��¼i,j�Ͼ������ظ�������
		        for(int k=0;k<groupnum;k++){
		        	Integer ik=k;
    				Iterator p = alist.iterator();
    				if(p.hasNext())
    				    if(ik==p.next()){
    				    	continue;
    				    }
		        	Pathcount=0;
		        	if(Pathtime[i][j][k]!=null){
		        		ArrayList<repeople> rep=new ArrayList<repeople>();//����ͬ�ĲŷŽ�ȥһ����
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
	          		        	//System.out.println(repset.size()+" repset.size "); //��ģ����
	          		        	System.out.println(" ");
	          		        	}
		    			   }
		        	
		        }
			}
	
	}
	

	
//ѡ������ͨ�����˵���Ϣ	
	public void choosepeople(Integer[][] maxpeople){
		 //����·���������
		Integer[][][] Pst=Pathtime;//ÿһ����
		for(int i=0;i<repset.size();i++){ 
			System.out.println(repset.size()+" repset.size ");
	
				int sr=repset.get(i).rep.get(0).source;
				int dt=repset.get(i).rep.get(0).destination;
				int max=maxpeople[sr][dt];
				ArrayList<Integer> listpeople=new ArrayList<Integer>();
				System.out.println(repset.get(i).allpeople+ " all people ");
				System.out.println(" "+max+" ");
				while(repset.get(i).allpeople>max){
					//��Ҫ����Щ������ѡ��һ������
					int temp=0;
					int min=999;
					for(int j=0;j<repset.get(i).rep.size();j++){//�����⣬��Ҫ�����޸�
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
					//��¼�����Ϣ��������˴�ԭ�����ж���ȥ��
					//��¼Ҫȥ��������ˣ�����˾������������㣬ȥ�޸�����˵���Ϣ����¼
                    repeople rept1=new repeople(repset.get(i).rep.get(temp).peoplelabel,sr,dt);
                    changedpeople.add(rept1);//error�ж�����
				max=max+min;	
			   }
		}//�ҳ��ж����������ظ�����
	}
	
//�ı��ͼ��Ϣ
public Integer[][] changepath(Integer[][] omap,int ploc,int start, int end){
    //�ı�omap��ĳһ��·����������
	omap[start][end]=M;
	return omap;	
}

		
//����ÿ������Ҫ�޸�·�����˵��µ�·��
	public ArrayList<NewWay> ChangePeoplepath(Integer[][] disto, int fp, Integer[] peopleloc, Integer[] nextpoint, ArrayList<Integer[]> allroutes){
		Integer[][] omap=new Integer[vertnum][vertnum];
		ArrayList<NewWay> nwylist=new ArrayList<NewWay>(); 
	     for(int i=0;i<changedpeople.size();i++){
	    	  Integer[] newroute;//����ÿ���˵��µ�·��
	    	  Integer[] oldroute;//����ÿ���˵��µ�·��
	    	  Integer[] finalroute = new Integer[20];//����ÿ���˵��µ�·��
	    	 System.out.println(" changedpeople.size "+i);
	    		GenerateMap gpnew=new GenerateMap();
	    		Integer[][] dist1=gpnew.changemap(disto,fp);//��ͼ��Ϣ��ʼ��
	    		
	    	     omap=changepath(dist1,changedpeople.get(i).peoplelabel,
	    			 changedpeople.get(i).source,changedpeople.get(i).destination);//error ÿ���˴洢����ϢӦ�ö��ǲ�ͬ��
                    
				   ArrayList<Integer[][]> listnew=gpnew.Fmap(omap); //���·����Ϣ
				   //��ÿ���˶�����һ���µ����·������Ϣ
				
				  oldroute=allroutes.get(changedpeople.get(i).peoplelabel);//ԭ����chain
				  System.out.println(" ��Щ������ "+ peopleloc[changedpeople.get(i).peoplelabel]);
				  newroute=gpnew.getchain(listnew, nextpoint[changedpeople.get(i).peoplelabel], 21, 53);
				  
				  for(int j=0; j<oldroute.length;j++){
					  finalroute[j]=oldroute[j];
					if(oldroute[j]==peopleloc[changedpeople.get(i).peoplelabel]){//���ҵ���һ��·��ʱ
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
	
	//���캯����������Щ��Ϣ�����������˵ĵ�ǰ��λ����Ϣ
	public  Calrep(){
		
	}


	


}





