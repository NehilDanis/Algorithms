import java.util.Random;
import java.util.Scanner;


public class MatrixMultiplication {
	public static void matrix(){
		System.out.println("How many numbers do you want?");
		Scanner obj=new Scanner(System.in);
		int n=obj.nextInt();
		int [][] a=new int [n][n];
		int [][] b=new int [n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				a[i][j]=0;
				b[i][j]=0;
			}
		}
		a=randomArray(n, a);
		b=randomArray(n, b);
		long startTime=System.currentTimeMillis();
		int [][] c=new int [n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				c[i][j]=0;
				for(int k=0;k<n;k++){
					c[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("The time elapsed : "+(endTime-startTime));
		obj.close();
	}
	
	public static int [][] randomArray(int n,int [][] array){
		Random rand = new Random();
		for (int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				array[i][j]=rand.nextInt(10000);
			}
		}
		return array;
	}
	
}
