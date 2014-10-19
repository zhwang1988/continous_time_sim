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
		int fp=28; //���ֵص�
		GenerateMap gp=new GenerateMap();
		Integer[][] dist0 =gp.gemap();
		Integer[][] maxpeople=gp.maxcp(10,20);//�õ�·�Ŀ���
		Integer[][] dist=gp.changemap(dist0,fp);//��ͼ��Ϣ��ʼ��
		GenerateMap gp2=new GenerateMap();
		Integer[][] dist1=gp2.changemap(dist0,fp);//��ͼ��Ϣ��ʼ��
		gp.Initpeople(fp); //��Ա��ʼ��
		ArrayList<Integer[][]> list=gp.Fmap(dist); //�������·����Ϣ���ı���dist
		//gp.GetInformation(list,dist1,fp); //�����ˣ���·��ʱ�䣬�ص�ʱ��
//����	   
	   
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
		   state[i]=1;//����δ����
		   allPerson.get(i).initSimPerson(list, end);
		  
		   
	   }
	   
	   ArrayList<Integer[]> allroutes=new ArrayList<Integer[]>();//��ʼ���õ����е�chain; ����Ӧ��ֻ�ڵ�һ����ȥ���;
	   for(int i=0;i<count;i++){
	   Integer[] chain;
	   chain=gp.getchain(list, gp.peoplelocation[i], 21,53);//ÿ���˵Ķ����Եõ�
	   allroutes.add(chain);//���Ⱥ��˵�����һ��
	   }
	   
	   while (runmap.isRunning(state)){
		   //�ڴ˴���Ҫ�õ�һ����ʼ��
		   //�����жϱ�����֪��ÿ���˵�·���������˵�·�������Ѿ�֪������Ҫ����һ�����ı�
		   //chain�ĳ�����100
		   Calrep cp1=new Calrep();//�����Ͽ�����һ�����캯���ڽ����Щ����
		   ArrayList<NewWay> nwylist=new ArrayList<NewWay>(); //���ڴ洢�µ�·������
	       Integer[] nextpoint=new Integer[pnum];
		   for(int i=0;i<count;i++){
		   
		   //������ͼ��ʣ�µ��˻�Խ��Խ�٣�Ҫ��֮ǰ��һЩ�˶�ȥ��
			 cp1.peoplepastpath[i]=allPerson.get(i).distance;//�Ѿ�������·��
			 cp1.peopleallpath[i]=allPerson.get(i).longpath;//�ܳ�
			 cp1.caldist(allroutes.get(i), list.get(0), i, gp.peoplelocation);//��ǰ·���ϵ�ÿ����ľ�����Ϣ���ܹ���������
			 System.out.println("xxx");
			 cp1.calculatetime(allPerson.get(i).time*5, i);//����ÿ����������·�ϵ�ÿ�����ͨ����ʱ��
			 nextpoint[i]=allPerson.get(i).nextpoint;
		   }
		     cp1.pathrepete(gp.peoplenumber,gp.peoplelocation); //��һЩ�ظ�����ѡ����
	 cp1.choosepeople(maxpeople); //�������ȵ�����˸��������°���·��
	
	 nwylist=cp1.ChangePeoplepath(dist1,fp,gp.peoplelocation,nextpoint,allroutes);
	 if(!nwylist.isEmpty())
	 System.out.println("\n �µ�·����Ϣ����");
			 
			  
			 
		   for (int i=0;i<count;i++){
			   //�õ�ÿ���˵�·��
			   //����·��
			   //��������Ĳ������޸����chain
			   if(state[i]==1){
				  
				  flag[i]=true;
				  for(int j=0;j<nwylist.size();j++){
						    if(nwylist.get(j).plabel==i&&flag[i]){
						   //state[i]=allPerson.get(i).simnextstep(list,nwylist.get(j).route);
						    	System.out.println("\n���޸ĵ�·�� ������Ϊ"+gp.peoplenumber[i]+" ����� "+gp.peoplelocation[i]);
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