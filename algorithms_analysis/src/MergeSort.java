import java.util.Random;
import java.util.Scanner;


public class MergeSort {
	public static void mergeSort(int [ ] a)
	{
		int[] tmp = new int[a.length];
		mergeSort(a, tmp,  0,  a.length - 1);
	}


	public static void mergeSort(int [ ] a, int [ ] tmp, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}


    public static void merge(int[ ] a, int[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd){
            if(a[left]<=a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];
        }
        while(left <= leftEnd)   
            tmp[k++] = a[left++];

        while(right <= rightEnd) 
            tmp[k++] = a[right++];

        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
	
	
	
	
	public static void randomArray(){
		Random rand = new Random();
		System.out.println("How many element do you want in your array ? ");
		Scanner obj=new Scanner(System.in);
		int n=obj.nextInt();
		int [] array=new int [n];
		for (int i=0;i<n;i++){
			array[i]=rand.nextInt(10000);
		}
		long startTime=System.nanoTime();
		mergeSort(array);
		long endTime=System.nanoTime();
		System.out.println("The time elapsed : "+(endTime-startTime));
		obj.close();
		
	}
	
	

}
