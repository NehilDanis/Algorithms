import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadInput {
	
	/*
	 * Here in this class reading airports list , flights lists , and commands list was completed.
	 */
	
	public void readInputFile(String input,String type,String output){
		/*
		 * All the input files is sent here one by one.
		 * Then after reading each line in that input , the program will call split function.
		 * Type value is for representing which input file will be read.
		 * Type value can be airports , flights or commands. 
		 */
		try {
            BufferedReader in = new BufferedReader(new FileReader(input));
            String str;
            while ((str = in.readLine()) != null) {
            	split(str, type,output);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	private void split(String line,String type,String output){
		/*
		 * Here in this method the line will be divided.
		 * The expression that will be accomplished will depend on the type.
		 */
		String [] dividedLine=line.split("\t");
		ArrayList<String> array=new ArrayList<String>();
		if(type.equals("airportsList")){
			for (int i=0;i< dividedLine.length; i++){
				if(i!=0){
					array.add(dividedLine[i]);
				}
			}
			/*
			 * Here the program will call the FlightMap constructor to define an airport.
			 * dividedLine[0] shows us the city of airports.
			 * The other dividedLİne array elements show the airports of that city.
			 */
			FlightMap element=new FlightMap(dividedLine[0], array);
			array.clear();
		}
		else if(type.equals("flightsList")){
			/*
			 * Here in this block the program will add a flight to the system.
			 * dividedLine[0] shows the flight id.dividedLine[3] shows the duration of flight.
			 * dividedLine[4] shows the price of the flights.
			 * path[0] shows the deptPlace.path[1] shows the arrPlace.
			 * date[0] shows the accurate date of flight.date[1] shows the start hour of flight.
			 * date[2] shows the day of the flight.
			 */
			String path[]=dividedLine[1].split("->");
			String date[]=dividedLine[2].split(" ");
			FlightMap obj=new FlightMap();
			obj.addFlight(dividedLine[0], path[0], path[1], date[0],date[1],date[2], dividedLine[3], dividedLine[4]);
			
		}
		else if(type.equals("commandsList")){
			/*
			 * Here in this block all commands will be read one by one and implemented
			 * by calling methods for individual command.
			 */
			if(dividedLine[0].equals("listall")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.listAll(line, path[0], path[1], dividedLine[2],output,null,null);
				
			}
			else if(dividedLine[0].equals("listexcluding")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.excludedList(line, path[0], path[1], dividedLine[2],dividedLine[3],output,"excluded");
				
			}
			else if(dividedLine[0].equals("listonlyfrom")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.onlyFromList(line, path[0], path[1], dividedLine[2],dividedLine[3],output,"onlyFrom");
				
			}
			else if(dividedLine[0].equals("listcheapest")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.listcheapest(line, path[0], path[1], dividedLine[2],output,null,null);
				
			}
			else if(dividedLine[0].equals("listquickest")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.listQuickest(line, path[0], path[1], dividedLine[2],output,null,null);
				
			}
			else if(dividedLine[0].equals("listcheaper")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.listCheaper(line, path[0], path[1], dividedLine[2],Integer.parseInt(dividedLine[3]),output,null,null);
				
			}
			else if(dividedLine[0].equals("listquicker")){
				String path[]=dividedLine[1].split("->");
				String time[]=dividedLine[3].split(" ");
				Commands obj=new Commands();
				obj.listQuicker(line, path[0], path[1], dividedLine[2],time[0],time[1],output,null,null);
				
			}
			else if(dividedLine[0].equals("listproper")){
				String path[]=dividedLine[1].split("->");
				Commands obj=new Commands();
				obj.listProper(line, path[0], path[1], dividedLine[2],output,null,null);
				
			}
			
			
		}
		
	}

}
