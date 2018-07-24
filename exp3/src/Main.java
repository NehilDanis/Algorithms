/**
 * Here in this class the program will start to implementation.
 * @author nehil
 *
 */
public class Main {

	/**
	 * This main method takes four parameter.If the size of parameters is different from 4 the 
	 * program will print an error message.
	 * @param args First parameter is for path way of files , the second one is for stop words ,the third one is for commands and the last one is for output file.
	 */
	public static void main(String[] args) {
		if(args.length==4){
			ReadInput obj=new ReadInput();
			obj.readPath(args[0],args[1]);
			obj.readCommand(args[2], args[3]);
			
		}
		else {
			System.err.println("Wrong parameter size !");
		}
		
	}

}
