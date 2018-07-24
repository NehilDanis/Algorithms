
public class Main {

	public static void main(String[] args) {
		/*
		 * There has to be four input files.
		 */
		if(args.length==4){
			ReadInput obj=new ReadInput();
			obj.readInputFile(args[0],"airportsList",args[3]);
			obj.readInputFile(args[1],"flightsList",args[3]);
			obj.readInputFile(args[2],"commandsList",args[3]);
		}
		/*
		 * If there are more or less than four inputs , the program will give an error message.
		 */
		else{
			System.err.println("Wrong parameter size!");
		}

	}

}
