package testjava;
import java.io.*;

class StackX
{
	private final int SIZE=20;
	private int[] st;
	private int top;
	public StackX()
	{
		st=new int[SIZE];
		top=-1;
	}
	public void push(int j)
	{
		st[++top]=j;
	}
	public int pop()
	{return st[top--];}
	
	public int peek()
	{return st[top];}
	
	public boolean isEmpty()
	{return(top==-1);}
}
class Vertex{
	public String name;
	public boolean wasVisited;
	public Vertex(String label){
		name=label;
		wasVisited=false;
	}
}

class Graph{
	private Vertex vertexList[];
	private final int Maxverts=20;
	private int adjMat[][];
	private int cverts;
	private StackX theStack;
	public Graph(){
		 vertexList =new Vertex[Maxverts];
		 adjMat= new int[Maxverts][Maxverts];
		 cverts=0;
		 for(int j=0;j<Maxverts;j++)
			for(int i=0;i<Maxverts;i++)
				adjMat[j][i]=0;
		 theStack=new StackX();
			
	}
	public void addVertex(String label){
	    vertexList[cverts++]= new Vertex(label);
	}
	public void addEdge(int start, int end){
		adjMat[start][end]=1;
		adjMat[end][start]=1;
		
	}
	public void displayVertex(int num){
		System.out.print(vertexList[num].name);
		
	}
	public int getAdjunvisitedVertex(int v){
		for(int j=0;j<cverts;j++)
		if(adjMat[v][j]==1&&vertexList[j].wasVisited==false){
			//System.out.print(j);
		    return j;
		}
		  return -1;
	}
	
	/*public void dfs(int v){
	    displayVertex(v);	
	    int j=getAdjunvisitedVertex(v);
	    System.out.print(j);
	    if(j==-1)
	    	return;
	    else
	    vertexList[j].wasVisited=true;
		dfs(j);
			
	}*/
	
	 public void dfs(){
		 vertexList[0].wasVisited=true;
		 displayVertex(0);
		 theStack.push(0);
		 while(!theStack.isEmpty())
		 {
			 int v=getAdjunvisitedVertex(theStack.peek());
			 System.out.print(v);
			 if(v==-1)
				 theStack.pop();
			 else
			 {
			  vertexList[v].wasVisited=true;
			  displayVertex(v);
			  theStack.push(v);
			 }
		 }
		 resetvisted();
	 }
	public void resetvisted(){
		for(int j=0;j<cverts;j++)
			vertexList[j].wasVisited=false;
	}
	
}


public class Dijskstra {
	


	public static void main(String[] args){
		Graph theGraph=new Graph();
		theGraph.addVertex("IN1");
		theGraph.addVertex("CDU1");
		theGraph.addVertex("CDU2");
		theGraph.addVertex("FCC");
		theGraph.addVertex("CRU");
		theGraph.addVertex("TK1");
		theGraph.addVertex("TK2");
		theGraph.addVertex("TK3");
		
		theGraph.addEdge(0, 1);
		theGraph.addEdge(0, 2);
		theGraph.addEdge(1, 3);
		theGraph.addEdge(2, 3);
		theGraph.addEdge(3, 5);
		theGraph.addEdge(2, 5);
		theGraph.addEdge(2, 4);
		theGraph.addEdge(4, 6);
		
		System.out.print("Visit:");
		theGraph.dfs();
		System.out.println();
	}
		
}
