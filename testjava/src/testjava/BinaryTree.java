package testjava;

class node{
	int iData;
	double fData;
	node leftchild;
	node rightchild;
	
	public void displaynode(){
		System.out.print("Node "+iData+"= "+fData);
	}
	
}
class Tree{
	private node root;
	
	public node find(int key){
	node current = root;
	while(current.iData!=key){
		if(key<current.iData)
			current=current.leftchild;
		else
			current=current.rightchild;
		if(current==null)
			return null;
	}
	return current;
	}
	
	public void insert(int id, double dd){
		node current=root;
		node newNode=new node();
		newNode.iData=id;
		newNode.fData=dd;
		if(root==null)
			root=newNode;
		else
		{
		while(true){	
			
		if(current.iData<id&&current.leftchild==null){
			current.leftchild=newNode;	
			return;
		}
		else if(current.iData<id&&current.leftchild!=null){
			current=current.leftchild;
		}
		else if(current.iData>=id&&current.rightchild==null){
			current.rightchild=newNode;
			return;
		}
		else if(current.iData>=id&&current.rightchild!=null){
			current=current.rightchild;
		}
		}
		}
	}
	
	public void delete(int id){
		
	}		
}

public class BinaryTree {

	public static void main(String[] args){
		Tree theTree = new Tree();
		theTree.insert(50, 1.5);
		theTree.insert(25, 1.7);
		theTree.insert(75, 1.9);
		
		node found=theTree.find(25);
    }
	
	}
	
