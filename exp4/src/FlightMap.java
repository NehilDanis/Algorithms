import java.util.ArrayList;


/*
 * In this class we implemented flight graph.
 * The flights keeps the all flights and the cities keeps the all cities that we added to the system.
 */
public class FlightMap {
	FlightMap.FlightCities.FlightAirports.Flights flight=new FlightMap.FlightCities.FlightAirports.Flights();
	static ArrayList<FlightCities> cities=new ArrayList<FlightCities>();
	/*
	 * Each city has its own airport list.
	 */
	static class FlightCities{
		ArrayList<FlightAirports> airportsOfEachCity=new ArrayList<FlightAirports>();
		private String name;//This keeps the name of city.

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		/*
		 * In this constructor we define each city and its own airports one by one.
		 */
		public FlightCities(String name, ArrayList<String> airports){
			for(int i=0;i<airports.size();i++){
				FlightAirports element=new FlightAirports();
				element.setAirportName(airports.get(i));
				element.setCityOfAirport(name);
				element.setMarked(false);
				this.airportsOfEachCity.add(element);
			}
		}
		/*
		 * Each airports have its own flight list.
		 * Each airport has a cityOfAirport field to keep the name of the city of the airport.
		 */
		static class FlightAirports{
			ArrayList<Flights> flights=new ArrayList<Flights>();
			private String airportName;
			private String cityOfAirport;
			private boolean marked;
		
			/*
			 * Each flight has a flightID.
			 * Each flight has an arrived field , if the flight is controlled then it will assign as true ,
			 * else it will be false.
			 * Each flight has deptDate , deptDate , deptHour fields and also 
			 * duration and price field for each flight .
			 */
			static class Flights{
				private String flightID;
				private boolean arrived;
				private FlightAirports next;
				private String deptDate;
				private String deptDay;
				private String deptHour;
				private String duration;
				private int price;
				public String getDeptDate() {
					return deptDate;
				}
				public void setDeptDate(String deptDate) {
					this.deptDate = deptDate;
				}
				public String getDuration() {
					return duration;
				}
				public void setDuration(String duration) {
					this.duration = duration;
				}
				public int getPrice() {
					return price;
				}
				public void setPrice(int price) {
					this.price = price;
				}
				public String getFlightID() {
					return flightID;
				}
				public void setFlightID(String flightID) {
					this.flightID = flightID;
				}
				public FlightAirports getNext() {
					return next;
				}
				public void setNext(FlightAirports next) {
					this.next = next;
				}
				public boolean isArrived() {
					return arrived;
				}
				public void setArrived(boolean arrived) {
					this.arrived = arrived;
				}
				public String getDeptHour() {
					return deptHour;
				}
				public void setDeptHour(String deptHour) {
					this.deptHour = deptHour;
				}
				public String getDeptDay() {
					return deptDay;
				}
				public void setDeptDay(String deptDay) {
					this.deptDay = deptDay;
				}
				
				
			}

			public String getAirportName() {
				return airportName;
			}

			public void setAirportName(String airportName) {
				this.airportName = airportName;
			}

			public boolean isMarked() {
				return marked;
			}

			public void setMarked(boolean marked) {
				this.marked = marked;
			}

			public String getCityOfAirport() {
				return cityOfAirport;
			}

			public void setCityOfAirport(String cityOfAirport) {
				this.cityOfAirport = cityOfAirport;
			}
		}
		
	}
	public FlightMap(String name ,ArrayList<String> airports){
		FlightCities element=new FlightCities(name ,airports);
		element.setName(name);
		cities.add(element);
	}
	public FlightMap(){
		
	}

	/*
	 * Here in this method we add flight to the system.
	 */
	public void addFlight(String flightID,String dept, String arr,String deptDate,String deptHour, String deptDay, String duration,String price){
		int priceInfo=Integer.parseInt(price);
		FlightMap.FlightCities.FlightAirports deptAirport=findAirport(dept);
		FlightMap.FlightCities.FlightAirports arrAirport=findAirport(arr);
		if(arrAirport!=null && deptAirport!=null){
			flight.setFlightID(flightID);
			flight.setNext(arrAirport);
			flight.setDeptDate(deptDate);
			flight.setDeptDay(deptDay);
			flight.setDeptHour(deptHour);
			flight.setDuration(duration);
			flight.setPrice(priceInfo);
			flight.setArrived(false);
			flight.getNext().setMarked(false);
			deptAirport.flights.add(flight);
			
		}
		else {
			System.out.println("sdfxcghjkl");
		}
		
	}
	
	/*
	 * This method control the airport in any city when the airport was found the method will return 
	 * the address of airport.
	 * Else it will return null.
	 */
	private FlightMap.FlightCities.FlightAirports findAirport(String airportName){
		for(int i=0;i<cities.size();i++){
			for(int j=0;j<cities.get(i).airportsOfEachCity.size();j++){
				if(cities.get(i).airportsOfEachCity.get(j).getAirportName().equals(airportName)){
					return cities.get(i).airportsOfEachCity.get(j);
				}
			}
		}
		return null;
	}
	

}
