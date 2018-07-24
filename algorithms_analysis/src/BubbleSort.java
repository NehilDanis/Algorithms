import java.util.Random;
import java.util.Scanner;


public class BubbleSort {
	
	public static void randomArray(){
		Random rand = new Random();
		System.out.println("How many element do you want in your array ? ");
		Scanner obj=new Scanner(System.in);
		int n=obj.nextInt();
		int [] array=new int [n];
		for (int i=0;i<n;i++){
			array[i]=rand.nextInt(10000);
		}
		long startTime=System.currentTimeMillis();
		BSort(array);
		long endTime=System.currentTimeMillis();
		System.out.println("The time elapsed : "+(endTime-startTime));
		obj.close();
		
	}
	
	public static void BSort(int array []){
		for(int i=1;i<array.length;i++){
			int swaps=0;
			for(int j=0;j<(array.length-i);j++){
				if(array[j]>array[j+1]){
					int var=array[j+1];
					array[j+1]=array[j];
					array[j]=var;
					swaps+=1;
				}
			}
			if(swaps==0){
				break;
			}
		}
		
	}

}
