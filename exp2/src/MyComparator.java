import java.util.Comparator;


public class MyComparator {
	
	//I have to compare each field of object individually.So I created this class to compare each field apart.

	public static final Comparator<Customer>BY_CID=new ByCid();
	public static final Comparator<Customer>BY_NAME =new ByName();
	public static final Comparator<Customer>BY_SURNAME =new BySurname();
	public static final Comparator<Customer>BY_CITY =new ByCity();
	public static final Comparator<Customer>BY_ADDRESS =new ByAddress();
	public static final Comparator<Customer>BY_SSN=new BySsn();
	
	//Here we used nested classes and we implements Comparator interface.
	public static class ByName implements Comparator<Customer>{
		public int compare(Customer a,Customer b){
			return a.getName().compareToIgnoreCase(b.getName());
		}
	}
	
	public static class BySurname implements Comparator<Customer>{
		public int compare (Customer a,Customer b){
			return a.getSurname().compareToIgnoreCase(b.getSurname());
		}
	}
	
	public static class ByCid implements Comparator<Customer>{
		public int compare (Customer a,Customer b){
			return a.getCid()-b.getCid();
		}
	}
	
	public static class BySsn implements Comparator<Customer>{
		public int compare (Customer a,Customer b){
			return (int)(a.getSocialSecurityNumber()-b.getSocialSecurityNumber());
		}
	}
	
	public static class ByCity implements Comparator<Customer>{
		public int compare (Customer a,Customer b){
			return a.getCity().compareToIgnoreCase(b.getCity());
		}
	}
	
	public static class ByAddress implements Comparator<Customer>{
		public int compare (Customer a,Customer b){
			return a.getAddress().compareToIgnoreCase(b.getAddress());
		}
	}
	
}
