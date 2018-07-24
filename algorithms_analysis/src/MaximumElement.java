import java.util.Random;
import java.util.Scanner;


public class MaximumElement {
	public static void maxElement(){
		int n,max=0;
		Random rand = new Random();
		System.out.println("How many times do you want to execute ?");
		Scanner obj=new Scanner(System.in);
		n=obj.nextInt();
		int [] array=new int [n];
		for (int i=0;i<n;i++){
			array[i]=rand.nextInt(10000);
		}
		long startTime=System.nanoTime();
		for(int i=0;i<n;i++){
			if(max<array[i]){
				max=array[i];
			}
		}
		long endTime=System.nanoTime();
		System.out.println("The time elapsed : "+(endTime-startTime));
		obj.close();
	}

}
