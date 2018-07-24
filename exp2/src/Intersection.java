import java.util.ArrayList;

public class Intersection {
	
	//Here in this class we find the intersection of some array lists.
	//We will compare the array list by using cid numbers.
	//Because these numbers are unique.So when we find a number that stays in these two array list,
	//we have to add it to the intersection list.
	//For controlling the array lists we used binary search operation.
	//In one time we intersect two of array lists and return intersection of these two. 
	public static ArrayList<Customer> intersect(ArrayList<Customer> a1,ArrayList<Customer> a2){
		ArrayList<Customer> result=new ArrayList<Customer>();
		Sort.sort(a1, 0, a1.size()-1, "cid");
		Sort.sort(a2, 0, a2.size()-1, "cid");
		if(a1.size()<=a2.size()){
			for(int i=0;i<a1.size();i++){
				int x=binarySearch(a2, a1.get(i).getCid());
				if(x!=-1){
					result.add(a1.get(i));
				}
			}
		}
		else if(a1.size()>a2.size()){
			for(int i=0;i<a2.size();i++){
				int x=binarySearch(a1, a2.get(i).getCid());
				if(x!=-1){
					result.add(a2.get(i));
				}
			}
		}
		return result;
	}
	
	private static int binarySearch(ArrayList<Customer> a,int number){
		int lo = 0;
        int hi = a.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (a.get(mid).getCid()>number) hi=mid-1;
            else if (a.get(mid).getCid()<number) lo=mid+1;
            else return mid;
        }
        return -1;
		
	}

}
