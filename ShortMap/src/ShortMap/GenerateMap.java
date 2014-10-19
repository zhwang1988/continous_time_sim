package ShortMap;
import java.util.*;

public class GenerateMap {

	private Integer[][] IniMap;
	private int fp;
	public static int M=Integer.MAX_VALUE;
	private static int groupnum=30;
	private static int vertnum=58;
	public static int MAXSUM(int a, int b){
	return(a!=M&&b!=M)?(a+b):M;
	}
	Integer[] peoplelocation=new Integer[groupnum];
	Integer[] peoplenumber=new Integer[groupnum];
	Integer[] peopleend=new Integer[groupnum];
	private Integer[][][] Pathinfo1=new Integer[vertnum][vertnum][groupnum];
	private Integer[][][] Pathinfo2=new Integer[vertnum][vertnum][groupnum];
	private Integer[][][] Pathstarttime=new Integer[vertnum][vertnum][groupnum];
	private Integer[][][] Pathfinishtime=new Integer[vertnum][vertnum][groupnum];
	private Integer[][]   Pathcount=new Integer[vertnum][vertnum];
	private Integer[][] path=new Integer[vertnum][vertnum];
	ArrayList<StoreWp> stm=new ArrayList<StoreWp>();
	
	public ArrayList<Integer[][]> Fmap(Integer[][] dist){
    int a=vertnum;
	
    for(int i=0;i<a;i++){
    	for(int j=0;j<a;j++){
	     Pathcount[i][j]=0;
    	}
    }
	for(int i=0;i<vertnum;i++)
		for(int j=0;j<vertnum;j++)
			for(int k=0;k<groupnum;k++){
				Pathstarttime[i][j][k]=0;
				Pathfinishtime[i][j][k]=0;
		}
	
	for(int i=0;i<a;i++){
		for(int j=0; j<a;j++)
			path[i][j]=i;
	}
	for(int k=0;k<a;k++){
		for(int i=0;i<a;i++){
			for(int j=0;j<a;j++){
				if(dist[i][j]>MAXSUM(dist[i][k],dist[k][j])){
					path[i][j]=path[k][j];//最后一个经过节点
					dist[i][j]=MAXSUM(dist[i][k],dist[k][j]);
				}
		}
	}	
	
	}
	
    ArrayList<Integer[][]> list=new ArrayList<Integer[][]>();
    list.add(dist);
    list.add(path);
    return list;
	}
	
	
	
	 public Integer[][] gemap(){//返回值为data[][]，储存的是地图两点之间的距离
	    	int  i,j,nv,ne,k;
			nv = vertnum;//点的数量
			System.out.printf("无向图顶点数="+nv);
			Integer data[][] = new Integer[nv][nv];
			//如下是地图
			for(i=0;i<data.length;i++)
				for(j=0;j<data.length;j++)
				{
				     if (i == j)
				        data[i][j] = 0;
				        else {
				         data[i][j] = M;
				        }
				}//初始化地图，点到自己为0，点到其他为无穷大
			//1-58
			data[0][1]=30;data[0][9]=16;
			data[1][0]=30;data[1][10]=9;data[1][2]=15;
			data[2][1]=15;data[2][11]=6;data[2][3]=15;
			data[3][2]=15;data[3][12]=4;data[3][4]=30;
			data[4][3]=30;data[4][19]=15;data[4][5]=30;
			data[5][4]=30;data[5][20]=12;data[5][6]=30;
			data[6][5]=30;data[6][27]=25;data[6][29]=28;data[6][7]=20;
			data[7][6]=20;data[7][28]=12;data[7][8]=15;
			data[8][7]=15;data[8][28]=12;data[7][32]=22;
			data[9][0]=16;data[9][10]=26;data[9][15]=14;
			data[10][1]=9;data[10][9]=26;data[10][13]=7;
			data[11][2]=6;data[11][14]=7;data[11][12]=12;
			data[12][3]=4;data[12][11]=12;data[12][18]=14;
			data[13][10]=7;data[13][16]=7;data[13][14]=12;
			data[14][11]=7;data[14][13]=12;data[14][17]=7;
			data[15][9]=14;data[15][16]=26;data[15][21]=14;
			data[16][13]=7;data[16][15]=26;data[16][22]=14;
			data[17][14]=12;data[17][23]=14;data[17][18]=12;
			data[18][12]=14;data[18][17]=12;data[18][24]=14;data[18][19]=27;
			data[19][4]=15;data[19][18]=27;data[19][25]=14;data[19][20]=27;
			data[20][5]=12;data[20][19]=27;data[20][26]=14;
			data[21][15]=14;data[21][33]=28;data[21][22]=26;
			data[22][16]=14;data[22][21]=26;data[22][34]=28;data[22][23]=12;
			data[23][17]=14;data[23][22]=12;data[23][24]=12;
			data[24][18]=14;data[24][23]=12;data[24][30]=18;data[24][25]=27;
			data[25][19]=14;data[25][24]=27;data[25][26]=27;
			data[26][20]=14;data[26][25]=27;data[26][31]=14;data[26][27]=12;
			data[27][26]=12;data[27][6]=25;data[27][36]=30;
			data[28][7]=12;data[28][8]=12;data[28][29]=18;data[28][32]=20;
			data[29][6]=28;data[29][36]=25;data[29][37]=20;data[29][28]=18;
			data[30][24]=18;data[30][35]=10;data[30][38]=25;
			data[31][26]=14;data[31][39]=26;data[31][40]=20;
			data[32][28]=20;data[32][37]=18;data[32][8]=22;data[32][50]=30;
			data[33][21]=28;data[33][42]=28;data[33][34]=26;
			data[34][22]=28;data[34][33]=26;data[34][43]=28;data[34][35]=24;
			data[35][30]=10;data[35][34]=24;data[35][44]=28;
			data[36][27]=30;data[36][45]=23;data[36][41]=12;data[36][29]=25;
			data[37][29]=20;data[37][46]=25;data[37][32]=18;data[37][50]=27;
			data[38][30]=25;data[38][47]=25;data[38][39]=28;
			data[39][38]=28;data[39][31]=26;data[39][48]=24;
			data[40][31]=20;data[40][48]=14;data[40][45]=10;
			data[41][36]=12;data[41][49]=23;data[41][46]=8;
			data[42][23]=28;data[42][51]=28;data[42][43]=26;
			data[43][34]=28;data[43][42]=26;data[43][44]=24;data[43][52]=25;
			data[44][35]=28;data[44][43]=24;data[44][47]=8;
			data[45][40]=10;data[45][49]=12;data[45][36]=23;
			data[46][41]=8;data[46][56]=28;data[46][37]=25;
			data[47][44]=8;data[47][38]=25;data[47][53]=14;
			data[48][39]=24;data[48][40]=14;data[48][54]=14;
			data[49][45]=12;data[49][41]=23;data[49][55]=14;
			data[50][37]=27;data[50][32]=30;data[50][56]=25;data[50][57]=17;
			data[51][42]=28;data[51][52]=30;
			data[52][43]=25;data[52][51]=30;data[52][53]=26;
			data[53][47]=14;data[53][52]=26;data[53][54]=28;
			data[54][48]=14;data[54][53]=28;data[54][55]=15;
			data[55][54]=15;data[55][49]=14;data[55][56]=30;
			data[56][55]=30;data[56][46]=28;data[56][50]=25;data[56][57]=20;
			data[57][50]=17;data[57][56]=20;
			//如下计算边的数量
			ne=0;
			for(i=0;i<data.length;i++){
				for(j=0;j<data.length;j++){
					if(i!=j&&data[i][j]!=0&&data[i][j]!=M){
					ne++;
					}
				}
			}
			ne=ne/2;
			System.out.print(" 边数="+ne+"\n");
			return data;
	    }
 
    public Integer[][] maxcp(int min, int max){
    	int  i,j,nv,k;
		nv = vertnum;//点的数量
		System.out.printf("无向图顶点数="+nv);
		Integer data[][] = new Integer[nv][nv];
		//如下是地图
		for(i=0;i<data.length;i++)
			for(j=0;j<data.length;j++)
			{
			     if (i == j)
			        data[i][j] = 0;
			        else {
			         data[i][j] = M;
			        }
			}//初始化地图，点到自己为0，点到其他为无穷大
		//1-58
		data[0][1]=30;data[0][9]=16;
		data[1][0]=30;data[1][10]=9;data[1][2]=15;
		data[2][1]=15;data[2][11]=6;data[2][3]=15;
		data[3][2]=15;data[3][12]=4;data[3][4]=30;
		data[4][3]=30;data[4][19]=15;data[4][5]=30;
		data[5][4]=30;data[5][20]=12;data[5][6]=30;
		data[6][5]=30;data[6][27]=25;data[6][29]=28;data[6][7]=20;
		data[7][6]=20;data[7][28]=12;data[7][8]=15;
		data[8][7]=15;data[8][28]=12;data[7][32]=22;
		data[9][0]=16;data[9][10]=26;data[9][15]=14;
		data[10][1]=9;data[10][9]=26;data[10][13]=7;
		data[11][2]=6;data[11][14]=7;data[11][12]=12;
		data[12][3]=4;data[12][11]=12;data[12][18]=14;
		data[13][10]=7;data[13][16]=7;data[13][14]=12;
		data[14][11]=7;data[14][13]=12;data[14][17]=7;
		data[15][9]=14;data[15][16]=26;data[15][21]=14;
		data[16][13]=7;data[16][15]=26;data[16][22]=14;
		data[17][14]=12;data[17][23]=14;data[17][18]=12;
		data[18][12]=14;data[18][17]=12;data[18][24]=14;data[18][19]=27;
		data[19][4]=15;data[19][18]=27;data[19][25]=14;data[19][20]=27;
		data[20][5]=12;data[20][19]=27;data[20][26]=14;
		data[21][15]=14;data[21][33]=28;data[21][22]=26;
		data[22][16]=14;data[22][21]=26;data[22][34]=28;data[22][23]=12;
		data[23][17]=14;data[23][22]=12;data[23][24]=12;
		data[24][18]=14;data[24][23]=12;data[24][30]=18;data[24][25]=27;
		data[25][19]=14;data[25][24]=27;data[25][26]=27;
		data[26][20]=14;data[26][25]=27;data[26][31]=14;data[26][27]=12;
		data[27][26]=12;data[27][6]=25;data[27][36]=30;
		data[28][7]=12;data[28][8]=12;data[28][29]=18;data[28][32]=20;
		data[29][6]=28;data[29][36]=25;data[29][37]=20;data[29][28]=18;
		data[30][24]=18;data[30][35]=10;data[30][38]=25;
		data[31][26]=14;data[31][39]=26;data[31][40]=20;
		data[32][28]=20;data[32][37]=18;data[32][8]=22;data[32][50]=30;
		data[33][21]=28;data[33][42]=28;data[33][34]=26;
		data[34][22]=28;data[34][33]=26;data[34][43]=28;data[34][35]=24;
		data[35][30]=10;data[35][34]=24;data[35][44]=28;
		data[36][27]=30;data[36][45]=23;data[36][41]=12;data[36][29]=25;
		data[37][29]=20;data[37][46]=25;data[37][32]=18;data[37][50]=27;
		data[38][30]=25;data[38][47]=25;data[38][39]=28;
		data[39][38]=28;data[39][31]=26;data[39][48]=24;
		data[40][31]=20;data[40][48]=14;data[40][45]=10;
		data[41][36]=12;data[41][49]=23;data[41][46]=8;
		data[42][23]=28;data[42][51]=28;data[42][43]=26;
		data[43][34]=28;data[43][42]=26;data[43][44]=24;data[43][52]=25;
		data[44][35]=28;data[44][43]=24;data[44][47]=8;
		data[45][40]=10;data[45][49]=12;data[45][36]=23;
		data[46][41]=8;data[46][56]=28;data[46][37]=25;
		data[47][44]=8;data[47][38]=25;data[47][53]=14;
		data[48][39]=24;data[48][40]=14;data[48][54]=14;
		data[49][45]=12;data[49][41]=23;data[49][55]=14;
		data[50][37]=27;data[50][32]=30;data[50][56]=25;data[50][57]=17;
		data[51][42]=28;data[51][52]=30;
		data[52][43]=25;data[52][51]=30;data[52][53]=26;
		data[53][47]=14;data[53][52]=26;data[53][54]=28;
		data[54][48]=14;data[54][53]=28;data[54][55]=15;
		data[55][54]=15;data[55][49]=14;data[55][56]=30;
		data[56][55]=30;data[56][46]=28;data[56][50]=25;data[56][57]=20;
		data[57][50]=17;data[57][56]=20;
		//如下计算边的数量
		for(i=0;i<data.length;i++){
			for(j=0;j<data.length;j++){
				if(i!=j&&data[i][j]!=0&&data[i][j]!=M){
			        data[i][j]=getrandom(min,max);
				}
			}
		}
		return data;
    }
    
    
    //把路径反过来逆向输出
    public Integer[] reverse(Integer[] chain, int count){
    	int temp;
    	for(int i=0,j=count-1;i<j;i++,j--){
    		temp=chain[i];
    		chain[i]=chain[j];
    		chain[j]=temp;
    	}
    	return chain;
    }
    
    public void display_path(ArrayList<Integer[][]> list){
    	Integer[][] dist=list.get(0);
    	Integer[][] path=list.get(1);
    	Integer[] chain=new Integer[dist.length];
    	System.out.println("orgin->dist"+"dist"+"path");
    	for(int i=0; i<dist.length;i++){
    		for(int j=0; j<dist.length;j++){
    			if(i!=j){
    				System.out.print("\n   "+(i)+"->"+(j)+"   ");
    				if(dist[i][j]==M){
    					System.out.print(" NA ");
    				}
    				else{
    					System.out.print(dist[i][j]+"      ");
    					int count=0;
    					int k=j;
    					do{
    						k=chain[count++]=path[i][k];
    					}while(i!=k);
    					chain=reverse(chain,count);
    					System.out.print(chain[0]+"");
    					for(k=1;k<count;k++){
    						System.out.print("->"+chain[k]);
    					}
    					System.out.print("->"+j);
    				}
    				}
    			}
    		}
    	}
    
    public int getescapepoint(ArrayList<Integer[][]> list, int start, int end1, int end2){
	    int end;	
	    Integer[][] dists2d=list.get(0);
	    if(dists2d[start][end1]>dists2d[start][end2])
    		end=end2;
	    else
	    	end=end1;
    	return end;
    }
    
    
    
    public Integer[] display_path_s2d(ArrayList<Integer[][]> list, int start, int end1, int end2){
    	int end;
    	Integer[][] dist=list.get(0);
    	Integer[][] path=list.get(1);
    	Integer[] chain=new Integer[dist.length];
	    if(dist[start][end1]>dist[start][end2])
    		end=end2;
	    else
	    	end=end1;
    	System.out.print("\n   "+(start)+"->"+(end)+"   ");
		if(dist[start][end]==M){
			System.out.print(" NA ");
		}
		else{
			System.out.print(dist[start][end]+"      ");
			int count=0;
			int k=end;
			do{
				k=chain[count++]=path[start][k];
			}while(start!=k);
			chain=reverse(chain,count);
			chain[count]=end;
			System.out.print(chain[0]+"");
			for(k=1;k<count;k++){
				System.out.print("->"+chain[k]);
			}
			System.out.print("->"+end);
		}
    	return chain;
    }
    
    public Integer[] getchain(ArrayList<Integer[][]> list, int start, int end1,int end2){
    	return display_path_s2d( list,  start, end1, end2);
    }
    
	public static int getrandom(int min, int max){
	    Random random = new Random();
	    int s = random.nextInt(max)%(max-min+1) + min;
	    return s;
		}
	
    public Integer[][] changemap(Integer[][]map, int fp){
    Integer[][] map1=new Integer[vertnum][vertnum];
      for(int i=0;i<vertnum;i++){
    	  if(map[i][fp]!=M)
    		  map[i][fp]=M;
      }
      for(int i=0;i<vertnum;i++){
   	   for(int j=0;j<vertnum;j++){
   		   map1[i][j]=map[i][j];
   	   }
      }

      
		return map1;
    }
    
public void calpeople(){
	System.out.println("\n");
	System.out.println("calculate the repitition times with people");
	System.out.println("\n");
	ArrayList alist=new ArrayList<Integer[]>();
	
    for(int i=0;i<vertnum;i++){
    	for(int j=0;j<vertnum;j++){
    			for(int k=0;k<groupnum;k++){
    				Integer ik=k;
    				Iterator p = alist.iterator();
    				if(p.hasNext())
    				    if(ik==p.next()){
    				    	continue;
    				    }
    				if(Pathinfo1[i][j][k]!=null){
                       int st1=Pathstarttime[i][j][k];
                       int ft1=Pathfinishtime[i][j][k];
                       boolean kk=false;
	    			   for(int z=k+1;z<20&&z>k;z++){
	    				  if(Pathinfo1[i][j][z]!=null){
                          int st2=Pathstarttime[i][j][z];
                          int ft2=Pathfinishtime[i][j][z];
                          alist.add(z);
                          if(Math.abs(st1-st2)<5&&Math.abs(ft1-ft2)<5){
                        	  kk=true;
                        	  System.out.println("There are "+Pathinfo1[i][j][z]+" people from "
          		    				+Pathinfo2[i][j][z]+" through line "+i+" to "+j+" at the same time from time "
                        			  +st2+" to time "+ft2);
          		    				Pathcount[i][j]=Pathinfo1[i][j][z]+Pathcount[i][j];
          		    				StoreWp chpline=new StoreWp(z,i,j); 
          		    				stm.add(chpline);
          		    				}
                           }               	   
	    			   }
	    			   if(kk==true){
	    			   System.out.println("There are "+Pathinfo1[i][j][k]+" people from "
		    				       +Pathinfo2[i][j][k]+" through line "+i+" to "+j+" at the same time from time "
                     			  +st1+" to time "+ft1);
				    				Pathcount[i][j]=Pathinfo1[i][j][k]+Pathcount[i][j];
				    				StoreWp chpline1=new StoreWp(k,i,j); 
      		    				stm.add(chpline1);
	    			   }
	    			   if(Pathcount[i][j]!=0){
    	                   System.out.println("total= "+Pathcount[i][j]);
    	                   Pathcount[i][j]=0;
	    			   }
    				}
    				
    			}
    			
   		    	
    	}
               
    }
}

public void Initpeople(int fp){
	for(int i=0;i<groupnum;i++){
		int pa=getrandom(0,vertnum-1);
		int pn=getrandom(8,20);
		while(pa==fp){
			pa=getrandom(0,vertnum-1);
		}
		peoplelocation[i]=pa;
		if(i>0){
			for(int k=0;k<i;k++){
				while(peoplelocation[k]==peoplelocation[i]||peoplelocation[i]==fp){
			     pa=getrandom(0,vertnum-1);					
			     peoplelocation[i]=pa;
			}
		}
	}
	peoplenumber[i]=pn;
	}
}




public void GetInformation(ArrayList list, Integer[][] dist, int fp){
	ArrayList Allpath=new ArrayList<Integer[]>();
	ArrayList Firepath=new ArrayList<Integer[]>();
	
	for(int i=0;i<groupnum;i++){
		
		System.out.println("\n There are "+peoplenumber[i]+" people from location "+peoplelocation[i]);	
	Integer[] Pathroute=display_path_s2d(list,peoplelocation[i],41,5);
	
	int temp1=0;
	int temp2=0;
	for(int k=0;k<Pathroute.length;k++){
        
		if(Pathroute[k]!=null&&Pathroute[k+1]!=null){
		int a=Pathroute[k];
	    int b=Pathroute[k+1];
  
	    Pathinfo1[a][b][i]=peoplenumber[i];
	    Pathinfo2[a][b][i]=peoplelocation[i];
	  
	    //假设移动速度是1
	    if(k==0){
	    	Pathstarttime[a][b][i]=0;
	        Pathfinishtime[a][b][i]=dist[a][b]/1+Pathstarttime[a][b][i];
	    }
	    else{
	    Pathstarttime[a][b][i]=temp1;
	    Pathfinishtime[a][b][i]=dist[a][b]/1+Pathstarttime[a][b][i];
	    }  
	    temp1=Pathfinishtime[a][b][i];
	    System.out.println("\n");
		System.out.println(peoplenumber[i]+" people from "+peoplelocation[i]+" start "+Pathstarttime[a][b][i]
				+" to "+Pathfinishtime[a][b][i]+" in line "+a+" to "+b);
	    
		}
		else
			break;
    }
	Allpath.add(Pathroute);
	}
	
	System.out.println("\n");
	System.out.println("below is the firecarpath");

		Integer[] Pathroute1=display_path_s2d(list,12,fp,fp);
		Firepath.add(Pathroute1);
		Integer[] Pathroute2=display_path_s2d(list,0,fp,fp);
		Firepath.add(Pathroute2);
		Integer[] Pathroute3=display_path_s2d(list,32,fp,fp);
		Firepath.add(Pathroute3);
		Integer[] Pathroute4=display_path_s2d(list,28,fp,fp);
		Firepath.add(Pathroute4);
	
}


public Integer[][] changepath(Integer[][] omap,int ploc,int start, int end){
    //改变omap的某一条路径，重新求
	omap[start][end]=M;
	return omap;	
}




}



class StoreWp{
	int st;
	int ds;
	int plabel;
	
	public StoreWp(int plabel, int st, int ds){
		this.plabel=plabel;
		this.st=st;
		this.ds=ds;
	}
}

		 
		    //根据消防车路径和人跑动路径进行修正，将同一时间人数大于10的边，调整，调整某一个人多的组
       //调整路径重新显示，dist[][]是原来的地图
           

	
	
	

	

	
	

