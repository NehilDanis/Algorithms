import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintOutput {
	
	public static void printOutput(ArrayList<Customer> a,String [] parts,String command,long runtime,int count) {
		Sort.sort(a, 0, a.size()-1, "cid");
		//All result will be given as a cid sorted array list.
		int countername=0;
		int countersurname=0;
		int countercity=0;
		int counteraddress=0;
		int counterssn=0;
		int countercid=0;
		
		//These numbers are for deciding which property will be used in output file.
		
		int sizename=0;
		int sizesurname=0;
		int sizecity=0;
		int sizeaddress=0;
		int sizessn=0;
		int sizecid=0;
		//These numbers are for size of each column.
		//The length of each column will be the longest element in each column plus tab character.
		ArrayList<String> part=new ArrayList<String>();
		try{
			//Here we printed all result to the output.txt file.
			PrintWriter out = new PrintWriter(new FileWriter("output.txt",true)); 
			out.println("Command Text: "+"\""+command+"\""+"\n\nResult:");
			if(!a.isEmpty()){
				
				for(int x=0;x<a.size();x++){
					String ssn=String.valueOf(a.get(x).getSocialSecurityNumber());
					String cid=String.valueOf(a.get(x).getCid());
					if(sizename<a.get(x).getName().length()){
						sizename=a.get(x).getName().length();
					}
					if(sizesurname<a.get(x).getSurname().length()){
						sizesurname=a.get(x).getSurname().length();
					}
					if(sizecity<a.get(x).getCity().length()){
						sizecity=a.get(x).getCity().length();
					}
					if(sizeaddress<a.get(x).getAddress().length()){
						sizeaddress=a.get(x).getAddress().length();
					}
					if(sizessn<ssn.length()){
						sizename=ssn.length();
					}
					if(sizecid<cid.length()){
						sizecid=cid.length();
					}
					
				}
				
				
				
				for(int i=0;i<parts.length;i++){
					if(parts[i].equals("FirstName") && countername==0){
						countername++;
						part.add("FirstName");
						if("FirstName".length()<sizename+5){
							out.print("FirstName");
							for(int j="FirstName".length();j<sizename+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("FirstName	");
							sizename="FirstName".length();
						}
					}
					else if(parts[i].equals("LastName") && countersurname==0){
						countersurname++;
						part.add("LastName");
						if("LastName".length()<sizesurname+5){
							out.print("LastName");
							for(int j="LastName".length();j<sizesurname+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("LastName     ");
							sizesurname="LastName".length();
						}
					}
					else if(parts[i].equals("City") && countercity==0){
						countercity++;
						part.add("City");
						if("City".length()<sizecity+5){
							out.print("City");
							for(int j="City".length();j<sizecity+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("City     ");
							sizecity="City".length();
						}
					}
					else if(parts[i].equals("CID") && countercid==0){
						countercid++;
						part.add("CID");
						if("CID".length()<sizecid+5){
							out.print("CID");
							for(int j="CID".length();j<sizecid+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("CID     ");
							sizecity="CID".length();
						}
					}
					else if(parts[i].equals("AddressLine1") && counteraddress==0){
						counteraddress++;
						part.add("AddressLine1");
						if("AddressLine1".length()<sizeaddress+5){
							out.print("AddressLine1");
							for(int j="AddressLine1".length();j<sizeaddress+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("AddressLine1     ");
							sizecity="AddressLine1".length();
						}
					}
					else if(parts[i].equals("SocialSecurityNumber") && counterssn==0){
						counterssn++;
						part.add("SocialSecurityNumber");
						if("SocialSecurityNumber".length()<sizessn+5){
							out.print("SocialSecurityNumber");
							for(int j="SocialSecurityNumber".length();j<sizessn+5;j++){
								out.print(" ");
							}
						}
						else {
							out.print("SocialSecurityNumber     ");
							sizecity="SocialSecurityNumber".length();
						}
					}
				}
				out.println();
				for(int i=0;i<a.size();i++){
					for(int c=0;c<part.size();c++){
						if(part.get(c).equals("FirstName")){
							String name=a.get(i).getName();
							if(name.length()<sizename+5){
								out.print(name);
								for(int j=name.length();j<sizename+5;j++){
									out.print(" ");
								}
							}
							
						}
						else if(part.get(c).equals("LastName")){
							String surname=a.get(i).getSurname();
							if(surname.length()<sizesurname+5){
								out.print(surname);
								for(int j=surname.length();j<sizesurname+5;j++){
									out.print(" ");
								}
							}
							
						}
						else if(part.get(c).equals("City")){
							String city=a.get(i).getCity();
							if(city.length()<sizecity+5){
								out.print(city);
								for(int j=city.length();j<sizecity+5;j++){
									out.print(" ");
								}
							}
						}
						else if(part.get(c).equals("CID")){
							int x=a.get(i).getCid();
							String cid=String.valueOf(x);
							if(cid.length()<sizecid+5){
								out.print(cid);
								for(int j=cid.length();j<sizecid+5;j++){
									out.print(" ");
								}
							}
						}
						else if(part.get(c).equals("AddressLine1")){
							String address=a.get(i).getAddress();
							if(address.length()<sizeaddress+5){
								out.print(address);
								for(int j=address.length();j<sizeaddress+5;j++){
									out.print(" ");
								}
							}
						}
						else if(part.get(c).equals("SocialSecurityNumber")){
							long ssn=a.get(i).getSocialSecurityNumber();
							String socialSecurity=String.valueOf(ssn);
							if(socialSecurity.length()<sizessn+5){
								out.print(socialSecurity);
								for(int j=socialSecurity.length();j<sizessn+5;j++){
									out.print(" ");
								}
							}
						}
						
					}
					out.println();
				}
				
			}
			else if(count!=-1){
				out.println("Empty rowset");
			}
			
			else if(count==-1){
				out.println("Invalid command");
			}
	
			out.println("-------------------");
			//Here we added the process time .
			//This time contains the binary search operations and intersection operations.
			out.println("Process Time: "+runtime+" milliseconds\n");
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}

}
