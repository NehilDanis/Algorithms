
public class Main {

	public static void main(String[] args) {
		if(args.length==2){//The first input has to be input.txt and the second one has to be output.txt
			ReadInput readObject=new ReadInput();
			readObject.readInputFile(args[0],args[1]);
		}
		else{//If there is more or less than two input file , program will give an error to the console.
			System.err.println("Wrong parameter size !");
		}
	}

}
