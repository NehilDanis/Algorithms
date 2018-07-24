import java.util.Random;
import java.util.Scanner;


public class BinarySearch {
	public static int BSearch(int a[],int value,int left,int right){
		while(left<=right){
			int mid=(left+right)/2;
			if(a[mid]==value){
				return mid;
			}
			else if(a[mid]>value){
				right=mid-1;
			}
			else {
				left=mid+1;
			}
			
		}
		return -1;
	}
	
	public static void randomArray(){
		Random rand = new Random();
		System.out.println("How many element do you want in your array ? ");
		Scanner obj=new Scanner(System.in);
		int n=obj.nextInt();
		int [] array=new int [n];
		for (int i=0;i<n;i++){
			array[i]=rand.nextInt(2500);
		}
		sort(array);
		obj.close();
		
	}
	
	public static void sort(int [] array){
		Random rand = new Random();
		for (int i=0;i<array.length-1;i++){
			int index=i;
			for(int j=i+1;j<array.length;j++){
				if(array[j]<array[index]){
					index=j;
				}
			}
			int smallerNumber = array[index]; 
            array[index] = array[i];
            array[i] = smallerNumber;
		}
		int num=rand.nextInt(2500);
		long startTime=System.nanoTime();
		System.out.println(BSearch(array,num, 0, array.length-1));
		long endTime=System.nanoTime();
		System.out.println("The time elapsed : "+(endTime-startTime));

		
	}

}
