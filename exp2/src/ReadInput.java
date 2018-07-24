import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReadInput {
	public static void readList(String args,String command){ 
	//Here are 6 arraylist.Each of them will be sorted depend on different field of object.
		ArrayList<Customer> nameOrdered = new ArrayList<Customer>(); 
        ArrayList<Customer> surnameOrdered = new ArrayList<Customer>(); 
        ArrayList<Customer> addressOrdered = new ArrayList<Customer>(); 
        ArrayList<Customer> cidOrdered = new ArrayList<Customer>(); 
        ArrayList<Customer> cityOrdered = new ArrayList<Customer>(); 
        ArrayList<Customer> ssnOrdered = new ArrayList<Customer>(); 
        
		try {
            BufferedReader in = new BufferedReader(new FileReader(args));
            in.readLine();
            String str=null;
            while ((str = in.readLine()) != null) {
            	String ar[]=str.split(Pattern.quote("|"));//Each line is partitioned by using | separator.
            	int cid = Integer.parseInt(ar[0].trim());
                long ssn=Long.parseLong(ar[5].trim());
            	Customer cstmr=new Customer(cid, ar[1], ar[2], ar[3], ar[4], ssn);
            	nameOrdered.add(cstmr);
                surnameOrdered.add(cstmr);
                addressOrdered.add(cstmr);
                cidOrdered.add(cstmr);
                cityOrdered.add(cstmr);
                ssnOrdered.add(cstmr);
            }
            
            //Here I sorted each array list for next step (finding people by using binary search)
            Sort.sort(nameOrdered, 0, nameOrdered.size()-1,"name");
            Sort.sort(surnameOrdered, 0, surnameOrdered.size()-1,"surname");
            Sort.sort(addressOrdered, 0, addressOrdered.size()-1,"address");
            Sort.sort(cidOrdered, 0, cidOrdered.size()-1,"cid");
            Sort.sort(cityOrdered, 0, cityOrdered.size()-1,"city");
            Sort.sort(ssnOrdered, 0, ssnOrdered.size()-1,"ssn");
            
            //After sorting each array list depend on another quality , we implement commands.
            readCommand(command, nameOrdered, surnameOrdered, cityOrdered, addressOrdered, cidOrdered, ssnOrdered);
            
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
	
	private static void readCommand(String args,ArrayList<Customer> name,ArrayList<Customer> surname
			,ArrayList<Customer> city,ArrayList<Customer> address,ArrayList<Customer> cid
			,ArrayList<Customer> ssn){
		
		try {
            BufferedReader in = new BufferedReader(new FileReader(args));
            String str=null;
            while ((str = in.readLine()) != null) {//Here we read input file line by line.
            	long startTime=System.currentTimeMillis();
            	int counter=0;
            	String ar[]=str.split(" WHERE ");
            	String array[]=ar[0].split(" ");
            	String commands[]=ar[1].split(" AND ");
            	String parts[]=array[1].split(",");
            	//parts are the fields that we have to show on output file.
            	//commands are the qualities of people that we want to find.
            	for(int i=0;i<commands.length;i++){
            		String delimiter=findDelimiter(commands[i]);
            		String elements[]=commands[i].split(delimiter);
            		int error=1;
            		if(delimiter.equals(">") || delimiter.equals("<") || delimiter.equals("=")){//This part is control part.We control the element that comes after this tokens.
            			char []values=elements[1].toCharArray();
            			int control=0;
            			for(int k=0;k<values.length;k++){
            				if(values[k]=='0' || values[k]=='1' || values[k]=='2' || values[k]=='3' || values[k]=='4' || values[k]=='5' || values[k]=='6' ||
                					values[k]=='7' || values[k]=='8' || values[k]=='9'){
                				control++;
                			}
            				else {//If there is anything different from numbers , the command will be invalid command.
            					control=0;
            					break;
            				}
            			}
            			if(control!=0){
            				error=0;
            			}
            		}
            		else {
            			error=0;
            		}
            		if(error==0){
            			int result=implementCommand(parts, elements,delimiter, name, surname, city, address, cid, ssn);
                		if(result==-1){
                			counter++;
                			break;
                		}
            		}
            		else{
            			counter=-1;
            			break;
            		}
            	}
            	 if(counter==0){
            		 	//Here we intersect each command's result array lists.And then we found out the intersection array list. 
            			if(!Search.ar1.isEmpty() && Search.ar2.isEmpty() && Search.ar3.isEmpty()  && Search.ar4.isEmpty() && Search.ar5.isEmpty()){
            				// 
            			}
            			else if(!Search.ar1.isEmpty() && !Search.ar2.isEmpty() && Search.ar3.isEmpty()  && Search.ar4.isEmpty() && Search.ar5.isEmpty()){
            				Search.ar1=Intersection.intersect(Search.ar1, Search.ar2);
            			}
            			else if(!Search.ar1.isEmpty() && !Search.ar2.isEmpty() && !Search.ar3.isEmpty()  && Search.ar4.isEmpty() && Search.ar5.isEmpty()){
            				Search.ar1=Intersection.intersect(Search.ar1, Search.ar2);
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar3);
            				}
            				
            			}
            			else if(!Search.ar1.isEmpty() && !Search.ar2.isEmpty() && !Search.ar3.isEmpty()  && !Search.ar4.isEmpty() && Search.ar5.isEmpty()){
            				Search.ar1=Intersection.intersect(Search.ar1, Search.ar2);
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar3);
            				}
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar4);
            				}
            			}
            			else if(!Search.ar1.isEmpty() && !Search.ar2.isEmpty() && !Search.ar3.isEmpty()  && !Search.ar4.isEmpty() && !Search.ar5.isEmpty()){
            				Search.ar1=Intersection.intersect(Search.ar1, Search.ar2);
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar3);
            				}
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar4);
            				}
            				if(!Search.ar1.isEmpty()){
            					Search.ar1=Intersection.intersect(Search.ar1, Search.ar5);
            				}
            			}
            			long endTime=System.currentTimeMillis();
            			long runTime=endTime-startTime;
            			PrintOutput.printOutput(Search.ar1, parts,str ,runTime ,counter);
             		//print --> parts ,str(command text),runtime and also intersection list.
            		 
             	}
             	else{
             		long endTime=System.currentTimeMillis();
             		long runTime=endTime-startTime;
             		Search.ar1.clear();
             		if(counter==-1){
             			PrintOutput.printOutput(Search.ar1, parts,str ,runTime ,counter);
             		}
             		else{
             			PrintOutput.printOutput(Search.ar1, parts,str ,runTime ,counter);
             		}
        			
             		
             		//print --> parts str runtime and empty intersection list.
             	}
            	 Search.ar1.clear();
        		 Search.ar2.clear();
        		 Search.ar3.clear();
        		 Search.ar4.clear();
        		 Search.ar5.clear();
        		 
            	
            }
            
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
	//In this function , we implemented the commands.(binary search)
	private static int implementCommand(String [] parts,String [] elements,String delimiter,
			ArrayList<Customer> name,ArrayList<Customer> surname
			,ArrayList<Customer> city,ArrayList<Customer> address,ArrayList<Customer> cid
			,ArrayList<Customer> ssn){
		
		if(delimiter.equals("<") || delimiter.equals(">") || delimiter.equals("=")){
			
			if(elements[0].equals("SocialSecurityNumber")){
				int searchResult=Search.commandsForNumber(ssn, elements[0], elements[1], delimiter);
				if(searchResult==-1){
					return -1;
				}
			}
			
			else if(elements[0].equals("Cid")){
				int searchResult=Search.commandsForNumber(ssn, elements[0], elements[1], delimiter);
				if(searchResult==-1){
					return -1;
				}
			}
			
		}
		
		else if(delimiter.equals("~")){
			
			if(elements[0].equals("FirstName")){
				int searchResult=Search.commandsForWords(name, elements[0], elements[1]);
				if(searchResult==-1){
					return -1;
				}
				
			}
			
			else if(elements[0].equals("LastName")){
				int searchResult=Search.commandsForWords(surname, elements[0], elements[1]);
				if(searchResult==-1){
					return -1;
				}
				
			}
			
			else if(elements[0].equals("City")){
				int searchResult=Search.commandsForWords(city, elements[0], elements[1]);
				if(searchResult==-1){
					return -1;
				}
				
			}
			
			else if(elements[0].equals("AddressLine1")){
				int searchResult=Search.commandsForWords(address, elements[0], elements[1]);
				if(searchResult==-1){
					return -1;
				}
				
			}
			
		}
		return 0;
		
	}
	
	//Here we found out which token is used in command part. 
	private static String findDelimiter(String element){
		char [] array=element.toCharArray();
		for(int i=0;i<array.length;i++){
			if(array[i]=='<'){
				return "<";
			}
			else if(array[i]=='>'){
				return ">";
			}
			else if(array[i]=='='){
				return "=";
			}
			else if(array[i]=='~'){
				return "~";
			}
		}
		return " ";
	}

}
