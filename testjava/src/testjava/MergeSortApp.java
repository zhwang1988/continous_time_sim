package testjava;

class DArray {
private long[] theArray;
private int nElems;

public DArray(int max){
	theArray= new long[max];
	nElems=0;
}

public void insert(long value){
	theArray[nElems]=value;
	nElems++;
}

public void display(){
	for(int j=0;j<nElems;j++){
		System.out.print(theArray[j]+" ");
	}
	System.out.println("");
}

public void mergeSort(){
	long[] workSpace=new long[nElems];
	recMergeSort(workSpace,0,nElems-1);
	
}

public void recMergeSort(long[] workSpace, int lowerBound, int upperBound){
	if(lowerBound==upperBound){
		return;
	}
	else{
		int mid=(lowerBound+upperBound)/2;
		recMergeSort(workSpace,lowerBound,mid);
		recMergeSort(workSpace,mid+1,upperBound);
		Merge(workSpace,lowerBound,mid+1,upperBound);
	}
}

private void Merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
	int j=0;
	int lowerBound=lowPtr;
	int mid=highPtr-1;
	int n=upperBound-lowerBound+1;
	while(lowPtr<=mid&&highPtr<=upperBound)
		if(theArray[lowPtr]<theArray[highPtr])
			workSpace[j++]=theArray[lowPtr++];
		else
			workSpace[j++]=theArray[highPtr++];
	while(lowPtr<=mid)
		workSpace[j++]=theArray[lowPtr++];
	while(highPtr<=upperBound)
		workSpace[j++]=theArray[highPtr++];
	for(j=0;j<n;j++)
		theArray[lowerBound+j]=workSpace[j];
}

}
public class MergeSortApp{
	public static void main(String[] args){
		int Maxsize=100;
		DArray arry=new DArray(Maxsize);
		
		arry.insert(64);
		arry.insert(21);
		arry.insert(33);
		arry.insert(70);
		arry.insert(12);
		arry.insert(85);
		arry.insert(44);
		arry.insert(3);
		arry.display();
		arry.mergeSort();
		arry.display();
	}
}
