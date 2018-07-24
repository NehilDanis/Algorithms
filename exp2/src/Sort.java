import java.util.ArrayList;
import java.util.Collections;

public class Sort {
	//We used Dijkstra's 3-way quick sort operation.Because there are same values.
	//3-way sort is the most efficient way.
	//At the end of the sort operation , we will have three parts in array list.
	//The numbers in the middle part will be equal to the pivot value.
	public static void sort(ArrayList<Customer> a,int lo ,int hi ,String type){
		
		 if (hi <= lo) return;
		 int lt = lo, gt = hi;
		 Customer v=new Customer();
		 v=a.get(lo);
		 int i = lo;
		 while (i <= gt)
		 {
			 int cmp;
			 if(type=="cid"){
				 cmp=MyComparator.BY_CID.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
			 }
			 else if(type=="name"){
				 cmp=MyComparator.BY_NAME.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
			 }
			 else if(type=="surname"){
				 cmp=MyComparator.BY_SURNAME.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
				 
			 }
			 else if(type=="city"){
				 cmp=MyComparator.BY_CITY.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
				 
			 }
			 else if(type=="address"){
				 cmp=MyComparator.BY_ADDRESS.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
				 
			 }
			 else if(type=="ssn"){
				 cmp=MyComparator.BY_SSN.compare(a.get(i), v);
				 if (cmp < 0) exch(a, lt++, i++);
				 else if (cmp > 0) exch(a, i, gt--);
				 else i++;
				 
			 }
		 }
		 sort(a, lo, lt - 1,type);
		 sort(a, gt + 1, hi,type);
		
	}
	
	private static void exch(ArrayList<Customer> a,int i,int j){
		Collections.swap(a, i, j);
		
	}

}
