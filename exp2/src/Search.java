import java.util.ArrayList;

public class Search {
	
	static ArrayList<Customer> ar1=new ArrayList<Customer>();
	static ArrayList<Customer> ar2=new ArrayList<Customer>();
	static ArrayList<Customer> ar3=new ArrayList<Customer>();
	static ArrayList<Customer> ar4=new ArrayList<Customer>();
	static ArrayList<Customer> ar5=new ArrayList<Customer>();
	
	//I created 5 array list because we can just use 4 AND operation in each command.
	//So there can be used 5 array list at most ,in one command.
	
	//At first we called binary search functions and then , we called linear search.
	//And then we found out all wanted values.
	//And then we added one of empty array list. 
	public static int commandsForNumber(ArrayList<Customer> alist , String field , String number , String delimiter){
		int y=0;
		if(delimiter.equals(">")){
			int result=BinarySearchForFindLarger(alist, number, field);
			if(ar1.isEmpty()){
				y=linearForLarger(alist, number, field, result, ar1);
			}
			else if(ar2.isEmpty()){
				y=linearForLarger(alist, number, field, result, ar2);
			}
			else if(ar3.isEmpty()){
				y=linearForLarger(alist, number, field, result, ar3);
			}
			else if(ar4.isEmpty()){
				y=linearForLarger(alist, number, field, result, ar4);
			}
			else if(ar5.isEmpty()){
				y=linearForLarger(alist, number, field, result, ar5);
			}
			if(result!=-1 && y!=-1){
				return 0;
			}
			
		}
		else if(delimiter.equals("<")){
			int result=BinarySearchForFindLSmaller(alist, number, field);
			if(ar1.isEmpty()){
				y=linearForSmaller(alist, number, field, result, ar1);
			}
			else if(ar2.isEmpty()){
				y=linearForSmaller(alist, number, field, result, ar2);
			}
			else if(ar3.isEmpty()){
				y=linearForSmaller(alist, number, field, result, ar3);
			}
			else if(ar4.isEmpty()){
				y=linearForSmaller(alist, number, field, result, ar4);
			}
			else if(ar5.isEmpty()){
				y=linearForSmaller(alist, number, field, result, ar5);
			}
			if(result!=-1 && y!=-1){
				return 0;
			}
			
		}
		else if(delimiter.equals("=")){
			int result=BinarySearchForFindEqual(alist, number, field);
			if(result==-1){
				return -1;
			}
			if(ar1.isEmpty()){
				y=linearForEqual(alist, number, field, result, ar1);
			}
			else if(ar2.isEmpty()){
				y=linearForEqual(alist, number, field, result, ar2);
			}
			else if(ar3.isEmpty()){
				y=linearForEqual(alist, number, field, result, ar3);
			}
			else if(ar4.isEmpty()){
				y=linearForEqual(alist, number, field, result, ar4);
			}
			else if(ar5.isEmpty()){
				y=linearForEqual(alist, number, field, result, ar5);
			}
			if(result!=-1 && y!=-1){
				return 0;
			}
			
		}
		return -1;
	}
	
	public static int commandsForWords(ArrayList<Customer> alist , String field , String cmp ){
		int x=BinarySearchForWords(alist, cmp, field);
		if(x!=-1){
			if(ar1.isEmpty()){
				linearForWords(alist, cmp, field, x, ar1);
			}
			else if(ar2.isEmpty()){
				linearForWords(alist, cmp, field, x, ar2);
			}
			else if(ar3.isEmpty()){
				linearForWords(alist, cmp, field, x, ar3);
			}
			else if(ar4.isEmpty()){
				linearForWords(alist, cmp, field, x, ar4);
			}
			else if(ar5.isEmpty()){
				linearForWords(alist, cmp, field, x, ar5);
			}
			
			return 0;
		}
		return -1;
	}
	
	//There are four different search function.
	//One of them is for find the larger than any value.
	private static int BinarySearchForFindLarger(ArrayList<Customer> alist , String number ,String field){
		int lo=0;
		int hi=alist.size()-1;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getSocialSecurityNumber()>num) hi=mid-1;
	            else if (alist.get(mid).getSocialSecurityNumber()<num) return mid;
	            else return mid;
	        }
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getCid()>num) hi=mid-1;
	            else if (alist.get(mid).getCid()<num) return mid;
	            else return mid;
	        }
			
		}
		
		return 0;
		
	}
	//One of them is for find the smaller than any value.
	private static int BinarySearchForFindLSmaller(ArrayList<Customer> alist , String number ,String field){
		int lo=0;
		int hi=alist.size()-1;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getSocialSecurityNumber()>num) return mid;
	            else if (alist.get(mid).getSocialSecurityNumber()<num) lo=mid+1;
	            else return mid;
	        }
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getCid()>num) return mid;
	            else if (alist.get(mid).getCid()<num) lo=mid+1;
	            else return mid;
	        }
			
		}
		return alist.size()-1;
		
	}
	//One of them is for find the any word that starts with any string value.
	private static int BinarySearchForWords(ArrayList<Customer> alist , String cmp ,String type){
		int lo = 0;
		int hi = alist.size() - 1;
		if(type.equals("FirstName")){
			while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            String array=alist.get(mid).getName();
	            array=array.substring(0, cmp.length());
	            if      (compareTo(cmp, array)<0) hi = mid - 1;
	            else if (compareTo(cmp, array)>0) lo = mid + 1;
	            else return mid;
	        }
			
		}
		else if(type.equals("LastName")){
			while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            String array=alist.get(mid).getSurname();
	            array=array.substring(0, cmp.length());
	            if      (compareTo(cmp, array)<0) hi = mid - 1;
	            else if (compareTo(cmp, array)>0) lo = mid + 1;
	            else return mid;
	        }
			
		}
		else if(type.equals("AddressLine1")){
			while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            String array=alist.get(mid).getAddress();
	            array=array.substring(0, cmp.length());
	            if      (compareTo(cmp, array)<0) hi = mid - 1;
	            else if (compareTo(cmp, array)>0) lo = mid + 1;
	            else return mid;
	        }
			
		}
		else if(type.equals("City")){
			while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            String array=alist.get(mid).getCity();
	            array=array.substring(0, cmp.length());
	            if      (compareTo(cmp, array)<0) hi = mid - 1;
	            else if (compareTo(cmp, array)>0) lo = mid + 1;
	            else return mid;
	        }
	
		}
		return -1;
	}

	//One of them is for find the any value in array list.
	public static int BinarySearchForFindEqual(ArrayList<Customer> alist , String number ,String field){
		int lo=0;
		int hi=alist.size()-1;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getSocialSecurityNumber()>num) hi=mid-1;
	            else if (alist.get(mid).getSocialSecurityNumber()<num) lo=mid+1;
	            else return mid;
	        }
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
	        while (lo <= hi) {
	            int mid = lo + (hi - lo) / 2;
	            if      (alist.get(mid).getCid()>num) hi=mid-1;
	            else if (alist.get(mid).getCid()<num) lo=mid+1;
	            else return mid;
	        }
		}
		return -1;
	
	}
	//There are 4 different linear search method .
	//After finding one of wanted values , we will use this methods to find other wanted values.
	private static int linearForLarger(ArrayList<Customer> alist , String number ,String field ,int x,ArrayList<Customer> ar){
		int counter=0;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
			for(int i=x;i<alist.size();i++){
				if(alist.get(i).getSocialSecurityNumber()>num){
					ar.add(alist.get(i));
					counter++;
				}
			}
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
			for(int i=x;i<alist.size();i++){
				if(alist.get(i).getCid()>num){
					ar.add(alist.get(i));
					counter++;
				}
				
			}
			
		}
		if(counter!=0){
			return 0;
		}
		return -1;//If there is no addition , the method will return -1.
	}
	
	private static int linearForSmaller(ArrayList<Customer> alist , String number ,String field ,int x,ArrayList<Customer> ar){
		int counter=0;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
			for(int i=x;i>=0;i--){
				if(alist.get(i).getSocialSecurityNumber()<num){
					ar.add(alist.get(i));
					counter++;
				}
			}
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
			for(int i=x;i>=0;i--){
				if(alist.get(i).getCid()<num){
					ar.add(alist.get(i));
					counter++;
				}
				
			}
			
		}
		if(counter!=0){
			return 0;
		}
		return -1;//If there is no addition , the method will return -1.
		
	}
	
	private static int linearForEqual(ArrayList<Customer> alist , String number ,String field ,int x,ArrayList<Customer> ar){
		int counter=0;
		if(field.equals("SocialSecurityNumber")){
			long num=Long.parseLong(number);
			if(x==0 || x==alist.size()-1){
				ar.add(alist.get(x));
				counter++;
			}
			else {
				if(alist.get(x-1).getSocialSecurityNumber()==num){
					for(int i=x;i>=0;i--){
						if(alist.get(i).getSocialSecurityNumber()==num){
							ar.add(alist.get(i));
							counter++;
						}
					}
				}
				if(alist.get(x+1).getSocialSecurityNumber()==num){
					for(int i=x;i<alist.size();i++){
						if(alist.get(i).getSocialSecurityNumber()==num){
							ar.add(alist.get(i));
							counter++;
						}
					}
				}
				else{
					ar.add(alist.get(x));
					counter++;
				}
			}
			
		}
		else if(field.equals("Cid")){
			int num=Integer.parseInt(number);
			if(x==0 || x==alist.size()-1){
				ar.add(alist.get(x));
				counter++;
			}
			else {
				if(alist.get(x-1).getCid()==num){
					for(int i=x;i>=0;i--){
						if(alist.get(i).getCid()==num){
							ar.add(alist.get(i));
							counter++;
						}
					}
				}
				if(alist.get(x+1).getCid()==num){
					for(int i=x;i<alist.size();i++){
						if(alist.get(i).getCid()==num){
							ar.add(alist.get(i));
							counter++;
						}
					}
				}
				else{
					ar.add(alist.get(x));
					counter++;
				}
			}
			
		}
		if(counter!=0){
			return 0;
		}
		return -1;//If there is no addition , the method will return -1.
	}

	private static int compareTo(String cmp, String array){
		if(cmp.compareToIgnoreCase(array)<0){
			return -1;
		}
		else if(cmp.compareToIgnoreCase(array)>0){
			return 1;
		}
		return 0;
		
	}
	
	private static void linearForWords(ArrayList<Customer> alist,String cmp,String type,int index,ArrayList<Customer> ar){
		if(index-1<0 || index+1>alist.size()){
			if(type.equals("FirstName")){
				ar.add(alist.get(index));
			}
			else if(type.equals("LastName")){
				ar.add(alist.get(index));
			}
			else if(type.equals("City")){
				ar.add(alist.get(index));
			}
			else if(type.equals("AddressLine1")){
				ar.add(alist.get(index));
			}
			return;
		}
		else{
			if(type.equals("FirstName")){
				String array=alist.get(index-1).getName();
		        array=array.substring(0, cmp.length());
		        
		        String array1=alist.get(index+1).getName();
		        array1=array1.substring(0, cmp.length());
		        
		        int counterr=index+1;
		        if(array.equalsIgnoreCase(cmp)){
		        	while(index>=0){
		        		String a1=alist.get(index).getName();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(index));
		        		index--;
		        	}
		        	
		        }
		        if(array1.equalsIgnoreCase(cmp)){
		        	while(counterr<alist.size()){
		        		String a1=alist.get(counterr).getName();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(counterr));
		        		counterr++;
		        	}
		        	
		        }
		        else{
		        	ar.add(alist.get(index));
		        }
				
			}
			else if(type.equals("LastName")){
				String array=alist.get(index-1).getSurname();
		        array=array.substring(0, cmp.length());
		        
		        String array1=alist.get(index+1).getSurname();
		        array1=array1.substring(0, cmp.length());
		        
		        int counterr=index+1;
		        if(array.equalsIgnoreCase(cmp)){
		        	while(index>=0){
		        		String a1=alist.get(index).getSurname();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(index));
		        		index--;
		        	}
		        	
		        }
		        if(array1.equalsIgnoreCase(cmp)){
		        	while(counterr<alist.size()){
		        		String a1=alist.get(counterr).getSurname();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(counterr));
		        		counterr++;
		        	}
		        	
		        }
		        else{
		        	ar.add(alist.get(index));
		        }
				
			}
			else if(type.equals("City")){
				String array=alist.get(index-1).getCity();
		        array=array.substring(0, cmp.length());
		        
		        String array1=alist.get(index+1).getCity();
		        array1=array1.substring(0, cmp.length());
		        
		        int counterr=index+1;
		        if(array.equalsIgnoreCase(cmp)){
		        	while(index>=0){
		        		String a1=alist.get(index).getCity();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(index));
		        		index--;
		        	}
		        	
		        }
		        if(array1.equalsIgnoreCase(cmp)){
		        	while(counterr<alist.size()){
		        		String a1=alist.get(counterr).getCity();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(counterr));
		        		counterr++;
		        	}
		        	
		        }
		        else{
		        	ar.add(alist.get(index));
		        }
				
			}
			else if(type.equals("AddressLine1")){
				String array=alist.get(index-1).getAddress();
		        array=array.substring(0, cmp.length());
		        
		        String array1=alist.get(index+1).getAddress();
		        array1=array1.substring(0, cmp.length());
		        
		        int counterr=index+1;
		        if(array.equalsIgnoreCase(cmp)){
		        	while(index>=0){
		        		String a1=alist.get(index).getAddress();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(index));
		        		index--;
		        	}
		        	
		        }
		        if(array1.equalsIgnoreCase(cmp)){
		        	while(counterr<alist.size()){
		        		String a1=alist.get(counterr).getAddress();
		        		a1=a1.substring(0, cmp.length());
		        		if(!a1.equalsIgnoreCase(cmp)){
		        			break;
		        		}
		        		ar.add(alist.get(counterr));
		        		counterr++;
		        	}
		        	
		        }
		        else{
		        	ar.add(alist.get(index));
		        }
			
			}
			
		}
	}
}
