
import java.util.Stack;

public class Commands {
	/*
	 * Cities stack is for the cities that we have already arrived.
	 * The path stack uses for defining each path from departure city to arrival city.
	 * allTheWays stack keeps the possible ways depending on the type of command.
	 */
	private Stack<String> cities = new Stack<String>();
	private Stack<String> path = new Stack<String>();
	private Stack<Stack<String>> allTheWays = new Stack<Stack<String>>();
	private WriteOutput obj = new WriteOutput();
	private String startTime;
	private String arrivingTime;
	private String startDate;
	private String arrivingDate;
	
	/*
	 * Here in this method we call the find function to find the all possible ways.
	 */
	private int list(String command, String dept, String arr, String deptDate, String company,String mission){
		FlightMap.FlightCities deptCity = searchCity(dept);
		FlightMap.FlightCities arrCity = searchCity(arr);
		if (deptCity != null && arrCity != null) {
			cities.push(deptCity.getName());
			for (int i = 0; i < deptCity.airportsOfEachCity.size(); i++) {
				find(company, deptCity.airportsOfEachCity.get(i), arrCity, deptDate, "00:00", 0, true, mission);
				path.clear();
				cities.clear();
			}
			return 1;

		} else {
			System.out.println("asdfghjkl");
			return 0;
		}
	}
	/*
	 * At first we call the list command to list all possible paths.
	 * And then this method will choose the proper ones from this paths.
	 */
	private int listProperElements(String command, String dept, String arr, String deptDate,String company,String mission) {
		if(mission!=null){
			mission=null;
			company=null;
		}
		if(list(command, dept, arr, deptDate, company, mission)==1){
			for (int i = 0; i < allTheWays.size(); i++) {
				/*
				 * This method choose the proper paths depend on the sum of the price and duration of each path. 
				 */
				int price = Integer.parseInt(allTheWays.get(i).get(allTheWays.get(i).size() - 3));
				String duration = allTheWays.get(i).get(allTheWays.get(i).size() - 4);
				for (int j = 0; j < allTheWays.size(); j++) {
					if (price > Integer.parseInt(allTheWays.get(j).get(allTheWays.get(j).size() - 3))) {
						if (controlHour(duration, allTheWays.get(j).get(allTheWays.get(j).size() - 4)) > 0) {
							allTheWays.remove(i);
							i--;
							break;
						}
					} 
				}
			}
			return 1;
		}
		return 0;
	}
	
	/*
	 * Here in this method , all possible paths will find by calling the list method.
	 * And then printOut Method will be called to printing the all possible paths.
	 */
	public void listAll(String command, String dept, String arr, String deptDate, String output,String company,String mission) {
			if(list(command, dept, arr, deptDate, company, mission)==1){
				obj.printOut(allTheWays, command, output);
				allTheWays.clear();
			}

	}
	
	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * And then printOut Method will be called to printing the all proper paths.
	 */

	public void excludedList(String command, String dept, String arr, String deptDate, String company, String output,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			for(int i=0;i<allTheWays.size();i++){
				for(int j=0;j<allTheWays.get(i).size();j++){
					if(allTheWays.get(i).get(j).contains(company)){
						allTheWays.remove(i);
						i--;
						break;
					}
				}
			}
			obj.printOut(allTheWays, command, output);
			allTheWays.clear();
		}
	}
	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * And then printOut Method will be called to printing the all proper paths.
	 */

	public void onlyFromList(String command, String dept, String arr, String deptDate, String company, String output,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			for(int i=0;i<allTheWays.size();i++){
				for(int j=0;j<allTheWays.get(i).size()-4;j++){
					if(!allTheWays.get(i).get(j).contains(company)){
						allTheWays.remove(i);
						i--;
						break;
					}
				}
			}
			obj.printOut(allTheWays, command, output);
			allTheWays.clear();
		}
	}

	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * The cheapest path or paths will be chosen from this cheapest path list.
	 * And then printOut Method will be called to printing the cheapest paths.
	 */
	public void listcheapest(String command, String dept, String arr, String deptDate, String output,String company,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			String price = null;
			Stack<Stack<String>> x = new Stack<Stack<String>>();
			for (int i = 0; i < allTheWays.size(); i++) {
				price = allTheWays.get(i).get(allTheWays.get(i).size() - 3);
				for (int j = 0; j < allTheWays.size(); j++) {
					if (Integer.parseInt(price) > Integer
							.parseInt(allTheWays.get(j).get(allTheWays.get(j).size() - 3))) {
						price = allTheWays.get(j).get(allTheWays.get(j).size() - 3);
					}
				}
			}

			for (int i = 0; i < allTheWays.size(); i++) {
				if (Integer.parseInt(price) == Integer.parseInt(allTheWays.get(i).get(allTheWays.get(i).size() - 3))) {
					Stack<String> path_copy = new Stack<String>();
					path_copy.addAll(allTheWays.get(i));
					x.push(path_copy);
				}
			}

			obj.printOut(x, command, output);
			x.clear();
			allTheWays.clear();
		}

	}

	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * The quickest path or paths will be chosen from this quickest path list.
	 * And then printOut Method will be called to printing the quickest paths.
	 */
	public void listQuickest(String command, String dept, String arr, String deptDate, String output,String company,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			String duration = null;
			Stack<Stack<String>> x = new Stack<Stack<String>>();
			for (int i = 0; i < allTheWays.size(); i++) {
				duration = allTheWays.get(i).get(allTheWays.get(i).size() - 4);
				for (int j = 0; j < allTheWays.size(); j++) {
					if (controlHour(duration, allTheWays.get(j).get(allTheWays.get(j).size() - 4)) == 1) {
						duration = allTheWays.get(j).get(allTheWays.get(j).size() - 4);
					}
				}
			}
			for (int i = 0; i < allTheWays.size(); i++) {
				if (controlHour(duration, allTheWays.get(i).get(allTheWays.get(i).size() - 4)) == 0) {
					Stack<String> path_copy = new Stack<String>();
					path_copy.addAll(allTheWays.get(i));
					x.push(path_copy);
				}
			}
			obj.printOut(x, command, output);
			x.clear();
			allTheWays.clear();
		}

	}

	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * The cheaper path or paths will be chosen from this cheaper path list.
	 * And then printOut Method will be called to printing the cheaper paths.
	 */
	public void listCheaper(String command, String dept, String arr, String deptDate, int price, String output,String company,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			Stack<Stack<String>> x = new Stack<Stack<String>>();
			for (int i = 0; i < allTheWays.size(); i++) {
				if (price > Integer.parseInt(allTheWays.get(i).get(allTheWays.get(i).size() - 3))) {
					Stack<String> path_copy = new Stack<String>();
					path_copy.addAll(allTheWays.get(i));
					x.push(path_copy);
				}
			}
			obj.printOut(x, command, output);
			x.clear();
			allTheWays.clear();
		}

	}

	/*
	 * Here in this method , all proper paths will find by calling the listProperElement method.
	 * The quicker path or paths will be chosen from this quicker path list.
	 * And then printOut Method will be called to printing the quicker paths.
	 */
	public void listQuicker(String command, String dept, String arr, String deptDate, String arrDate, String hour,
			String output,String company,String mission) {
		if(listProperElements(command, dept, arr, deptDate, company, mission)==1){
			Stack<Stack<String>> x = new Stack<Stack<String>>();
			for (int i = 0; i < allTheWays.size(); i++) {
				if (controlDate(arrDate, allTheWays.get(i).get(allTheWays.get(i).size() - 2)) > 0) {
					Stack<String> path_copy = new Stack<String>();
					path_copy.addAll(allTheWays.get(i));
					x.push(path_copy);
				} else if (controlDate(arrDate, allTheWays.get(i).get(allTheWays.get(i).size() - 2)) == 0) {
					if (controlHour(hour, allTheWays.get(i).get(allTheWays.get(i).size() - 1)) > 0) {
						Stack<String> path_copy = new Stack<String>();
						path_copy.addAll(allTheWays.get(i));
						x.push(path_copy);
					}
				}
			}
			obj.printOut(x, command, output);
			x.clear();
			allTheWays.clear();

		}
	}

	/*
	 * Here in this method , all possible paths will find by calling the list method.
	 * And then the proper paths will be chosen from the possible paths list.
	 * And then printOut Method will be called to printing the proper paths.
	 */
	public void listProper(String command, String dept, String arr, String deptDate, String output,String company,String mission) {
		if(list(command, dept, arr, deptDate, company, mission)==1){
			for (int i = 0; i < allTheWays.size(); i++) {
				int price = Integer.parseInt(allTheWays.get(i).get(allTheWays.get(i).size() - 3));
				String duration = allTheWays.get(i).get(allTheWays.get(i).size() - 4);
				for (int j = 0; j < allTheWays.size(); j++) {
					if (price > Integer.parseInt(allTheWays.get(j).get(allTheWays.get(j).size() - 3))) {
						if (controlHour(duration, allTheWays.get(j).get(allTheWays.get(j).size() - 4)) > 0) {
							allTheWays.remove(i);
							i--;
							break;
						}
					} 
				}
			}
			obj.printOut(allTheWays, command, output);
			allTheWays.clear();
		}
	}

	/*
	 * In this method we will control the the arriving city.
	 * If it has already been in cities stack , then this method will return false.
	 * Else this will return true.
	 */
	private boolean controlCity(String city) {
		if (cities.contains(city)) {
			return false;
		}
		return true;

	}

	/*
	 * This compares two hours .
	 * If the deptHour is bigger than the flightHour it will return one.
	 * Else the deptHour is smaller than the flightHour it will return minus one.
	 * Else the deptHour is equal the flightHour it will return zero.
	 */
	private int controlHour(String deptHour, String flightHour) {
		String[] hourInfo = deptHour.split(":");
		int hour = Integer.valueOf(hourInfo[0]);
		int minute = Integer.valueOf(hourInfo[1]);

		String[] hourInfo1 = flightHour.split(":");
		int hour1 = Integer.valueOf(hourInfo1[0]);
		int minute1 = Integer.valueOf(hourInfo1[1]);

		if (hour < hour1) {
			return -1;
		} else if (hour > hour1) {
			return 1;
		} else {
			if (minute < minute1) {
				return -1;
			} else if (minute > minute1) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/*
	 * This compares two dates .
	 * If the deptDate is bigger than the flightDate it will return one.
	 * Else the deptDate is smaller than the flightDate it will return minus one.
	 * Else the deptDate is equal the flightDate it will return zero.
	 */
	private int controlDate(String deptDate, String dateOfFlight) {
		String[] dateInfo = deptDate.split("/");
		int day = Integer.valueOf(dateInfo[0]);
		int month = Integer.valueOf(dateInfo[1]);
		int year = Integer.valueOf(dateInfo[2]);

		String[] dateInfo1 = dateOfFlight.split("/");
		int day1 = Integer.valueOf(dateInfo1[0]);
		int month1 = Integer.valueOf(dateInfo1[1]);
		int year1 = Integer.valueOf(dateInfo1[2]);

		if (year < year1) {
			return -1;
		} else if (year > year1) {
			return 1;
		} else {
			if (month < month1) {
				return -1;
			} else if (month > month1) {
				return 1;
			} else {
				if (day < day1) {
					return -1;
				} else if (day > day1) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	/*
	 * This method controls the measurement of two dates and two hours.
	 */
	private boolean controlDateAndHour(String deptDate, String dateOfFlight, String deptHour, String flightHour) {

		if (controlDate(deptDate, dateOfFlight) == 0) {
			if (controlHour(deptHour, flightHour) < 0) {
				return true;
			} else {
				return false;
			}
		} else if (controlDate(deptDate, dateOfFlight) < 0) {
			return true;
		}
		return false;

	}
	/*
	 * This method controls the all flights and choose possible flights to arrive the arriving city.
	 */
	private void find(String company, FlightMap.FlightCities.FlightAirports deptCityAirport,
			FlightMap.FlightCities arrCity, String deptDate, String deptHour, int price, boolean yesOrNo,String mission){
		/*
		 * If the airport is equals to the one of the arriving city airport ,
		 * Count will be increase by one. 
		 */
		int count = 0;
		for (int i = 0; i < arrCity.airportsOfEachCity.size(); i++) {
			if (arrCity.airportsOfEachCity.get(i).getAirportName().equals(deptCityAirport.getAirportName())) {
				count++;
				break;
			}
		}
		/*
		 * If the flight is arrived the arriving city , the program will implement this if block.
		 * It will add the path to the allTheWays stack.
		 */
		if (count != 0) {
			arrivingDate = deptDate;
			arrivingTime = deptHour;
			path.push(findTheTimeDifference());
			path.push(String.valueOf(price));

			Stack<String> path_copy = new Stack<String>();
			path_copy.addAll(path);
			path_copy.push(arrivingDate);
			path_copy.push(arrivingTime);
			allTheWays.push(path_copy);

			path.pop();
			path.pop();

			path.pop();
			cities.pop();
			return;
		}

		for (int i = 0; i < deptCityAirport.flights.size(); i++) {
			if (!deptCityAirport.flights.get(i).isArrived()) {
				deptCityAirport.flights.get(i).setArrived(true);
				if(mission==null){
					if (controlCity(deptCityAirport.flights.get(i).getNext().getCityOfAirport())) {
						if (controlDateAndHour(deptDate, deptCityAirport.flights.get(i).getDeptDate(), deptHour,
								deptCityAirport.flights.get(i).getDeptHour())) {
							if (yesOrNo == true) {
								startTime = deptCityAirport.flights.get(i).getDeptHour();
								startDate = deptCityAirport.flights.get(i).getDeptDate();
							}
							String hour = sum(deptCityAirport.flights.get(i).getDeptHour(),
									deptCityAirport.flights.get(i).getDuration());
							String date = findTheDate(hour, deptCityAirport.flights.get(i).getDeptHour(),
									deptCityAirport.flights.get(i).getDeptDate());
							cities.push(deptCityAirport.flights.get(i).getNext().getCityOfAirport());
							String road = deptCityAirport.flights.get(i).getFlightID() + "\t"
									+ deptCityAirport.getAirportName() + "->"
									+ deptCityAirport.flights.get(i).getNext().getAirportName();
							path.push(road);
							price = price + deptCityAirport.flights.get(i).getPrice();
							find(company,deptCityAirport.flights.get(i).getNext(), arrCity, date, hour, price, false,mission);
							price = price - deptCityAirport.flights.get(i).getPrice();
						}

					}
					
				}
				
			}
		}
		int counter = 0;
		for (int i = 0; i < deptCityAirport.flights.size(); i++) {
			if (!deptCityAirport.flights.get(i).isArrived()) {
				counter++;
			}
		}
		if (counter == 0) {
			for (int i = 0; i < deptCityAirport.flights.size(); i++) {
				deptCityAirport.flights.get(i).setArrived(false);
			}
			if (!path.isEmpty()) {
				path.pop();
			}
			if (!cities.isEmpty()) {
				cities.pop();
			}
		}
	}
	
	/*
	 * Here in this method , we find the time difference in hours between startTime and endTime of the path.
	 */

	private String findTheTimeDifference() {
		String[] startDateInfo = startDate.split("/");
		int day = Integer.valueOf(startDateInfo[0]);
		int month = Integer.valueOf(startDateInfo[1]);
		int year = Integer.valueOf(startDateInfo[2]);

		String[] arrivingDateInfo = arrivingDate.split("/");
		int day1 = Integer.valueOf(arrivingDateInfo[0]);
		int month1 = Integer.valueOf(arrivingDateInfo[1]);
		int year1 = Integer.valueOf(arrivingDateInfo[2]);

		String[] startInfo = startTime.split(":");
		String[] arriveInfo = arrivingTime.split(":");
		int hour1 = Integer.parseInt(startInfo[0]);
		int hour2 = Integer.parseInt(arriveInfo[0]);
		int minute1 = Integer.parseInt(startInfo[1]);
		int minute2 = Integer.parseInt(arriveInfo[1]);

		int hour = 0;
		int minute = 0;

		if (day == day1) {
			if (month1 > month) {
				int numberOfDaysInThatMonth = 0;
				if (month == 2) {
					if (year % 4 == 0) {// leap year
						numberOfDaysInThatMonth = 29;
					} else {
						numberOfDaysInThatMonth = 28;
					}
				} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
						|| month == 12) {
					numberOfDaysInThatMonth = 31;

				} else if (month == 4 || month == 6 || month == 9 || month == 11) {
					numberOfDaysInThatMonth = 30;
				}
				int x = (24 - hour1) * 60 - minute1;
				hour = x + hour2 * 60 + minute2;
				minute = hour % 60;
				hour = hour / 60;
				if (((numberOfDaysInThatMonth - day) + day1) > 1) {
					hour = hour + ((numberOfDaysInThatMonth - day) + day1 - 1) * 24;
				}
			}
			else{
				int x = hour1 * 60 + minute1;
				int y = hour2 * 60 + minute2;

				if (x > y) {
					int time = x - y;
					hour = time / 60;
					minute = time % 60;
				} else if (x < y) {
					int time = y - x;
					hour = time / 60;
					minute = time % 60;
				}
			}

		} else if (day < day1) {
			if (month1 > month) {
				int numberOfDaysInThatMonth = 0;
				if (month == 2) {
					if (year % 4 == 0) {// leap year
						numberOfDaysInThatMonth = 29;
					} else {
						numberOfDaysInThatMonth = 28;
					}
				} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
						|| month == 12) {
					numberOfDaysInThatMonth = 31;

				} else if (month == 4 || month == 6 || month == 9 || month == 11) {
					numberOfDaysInThatMonth = 30;
				}
				int x = (24 - hour1) * 60 - minute1;
				hour = x + hour2 * 60 + minute2;
				minute = hour % 60;
				hour = hour / 60;
				if (((numberOfDaysInThatMonth - day) + day1) > 1) {
					hour = hour + ((numberOfDaysInThatMonth - day) + day1 - 1) * 24;
				}
			}
			else {
				int x = (24 - hour1) * 60 - minute1;
				hour = x + hour2 * 60 + minute2;
				minute = hour % 60;
				hour = hour / 60;
				if (day1 - day > 1) {
					hour = hour + (day1 - day - 1) * 24;
				}
			}

		} else if (day > day1) {
			if (month1 > month) {
				int numberOfDaysInThatMonth = 0;
				if (month == 2) {
					if (year % 4 == 0) {// leap year
						numberOfDaysInThatMonth = 29;
					} else {
						numberOfDaysInThatMonth = 28;
					}
				} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
						|| month == 12) {
					numberOfDaysInThatMonth = 31;

				} else if (month == 4 || month == 6 || month == 9 || month == 11) {
					numberOfDaysInThatMonth = 30;
				}
				int x = (24 - hour1) * 60 - minute1;
				hour = x + hour2 * 60 + minute2;
				minute = hour % 60;
				hour = hour / 60;
				if (((numberOfDaysInThatMonth - day) + day1) > 1) {
					hour = hour + ((numberOfDaysInThatMonth - day) + day1 - 1) * 24;
				}
			}
		}
		String hourString = String.valueOf(hour);
		String minuteString = String.valueOf(minute);
		if (hourString.length() == 1) {
			hourString = "0" + hourString;
		}
		if (minuteString.length() == 1) {
			minuteString = "0" + minuteString;
		}

		return hourString + ":" + minuteString;
	}
	/*
	 * This method controls the city's existence . If the city exist , then the method will return the address of the city.
	 * Else this method will be return null.
	 */
	
	private FlightMap.FlightCities searchCity(String cityName) {
		for (int i = 0; i < FlightMap.cities.size(); i++) {
			if (FlightMap.cities.get(i).getName().equals(cityName)) {
				return FlightMap.cities.get(i);
			}
		}
		return null;
	}
	
	/*
	 * This method returns the arriving hour of a flight.
	 * This method gets deptHour and duration time of flight as a parameter.
	 * It splits both of them and then find the some of deptHour and the duration time of flight.
	 * The result gives us the arriving hour of flight.
	 */

	private String sum(String deptHour, String duration) {
		String[] deptInfo = deptHour.split(":");
		String[] durationInfo = duration.split(":");
		int hour1 = Integer.parseInt(deptInfo[0]);
		int hour2 = Integer.parseInt(durationInfo[0]);
		int minute1 = Integer.parseInt(deptInfo[1]);
		int minute2 = Integer.parseInt(durationInfo[1]);
		int hour = hour1 + hour2;
		int minute = minute1 + minute2;
		if (minute >= 60) {
			int h = minute / 60;
			hour = hour + h;
			hour = hour % 24;
			minute = minute % 60;
		}
		if (hour >= 24) {
			hour = hour % 24;
		}
		String hourString = String.valueOf(hour);
		String minuteString = String.valueOf(minute);
		if (hourString.length() == 1) {
			hourString = "0" + hourString;
		}
		if (minuteString.length() == 1) {
			minuteString = "0" + minuteString;
		}

		return hourString + ":" + minuteString;
	}
	
	/*
	 * Here in this method depending on the deptHour and arrHour (time) , 
	 * this method will find the arriving date.
	 * If the deptHour smaller than time , then the date will be stayed same.
	 * But if deptHour bigger than time , this means that the date will be changed changed by the next day .
	 */

	private String findTheDate(String time, String deptHour, String date) {
		String[] dateInfo = date.split("/");
		int day = Integer.valueOf(dateInfo[0]);
		int month = Integer.valueOf(dateInfo[1]);
		int year = Integer.valueOf(dateInfo[2]);
		if (controlHour(deptHour, time) < 0) {
			return date;
		}
		if (month == 2) {
			if (year % 4 == 0) {// leap year
				day = day + 1;
				if (day > 29) {
					day = day % 29;
					month = month + 1;
				}
			} else {
				day = day + 1;
				if (day > 28) {
					day = day % 28;
					month = month + 1;
				}
			}
		} else if (month == 12) {
			day = day + 1;
			if (day > 31) {
				day = day % 31;
				month = 1;
				year = year + 1;
			}
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) {
			day = day + 1;
			if (day > 31) {
				day = day % 31;
				month = month + 1;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			day = day + 1;
			if (day > 30) {
				day = day % 30;
				month = month + 1;
			}
		}
		String d = String.valueOf(day);
		String m = String.valueOf(month);
		String y = String.valueOf(year);
		if (d.length() == 1) {
			d = "0" + d;
		}
		if (m.length() == 1) {
			m = "0" + m;
		}
		return d + "/" + m + "/" + y;
	}

}
