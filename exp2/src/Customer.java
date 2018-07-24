
public class Customer {
	
	private int cid;
	private String name;
	private String surname;
	private String city;
	private String address;
	private long socialSecurityNumber;
	
	public Customer(){//I used this class in comparator class.I needed to crate ordinary object from 
		//customer class.
	}
	
	public Customer(int cid,String name,String surname,String city,String address,long socialSecurityNumber) {
		//This constructor is for initializing the first values of each object that I created.
		this.cid=cid;
		this.name=name;
		this.surname=surname;
		this.city=city;
		this.address=address;
		this.socialSecurityNumber=socialSecurityNumber;
	}
	
	//Here are getters and setters to reach and to change the private fields of objects.

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(long socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", surname=" + surname + ", city=" + city + ", address="
				+ address + ", socialSecurityNumber=" + socialSecurityNumber + "]\n";
	}

}
