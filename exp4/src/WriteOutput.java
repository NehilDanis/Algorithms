import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class WriteOutput {
	
	public void printOut(Stack<Stack<String>> allTheWays,String command,String output){
		try{
			/*
			 * Here we printed all result to the output.txt file.
			 */
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			out.println("Command : "+command);
			for(int i=0;i<allTheWays.size();i++){
				/*
				 * At the end of the allTheWays stack , I kept the arriving date and hour of one path .
				 * We don't need to print them , so we popped them from stack.
				 */
				allTheWays.get(i).pop();
				allTheWays.get(i).pop();
				for(int j=0;j<allTheWays.get(i).size();j++){
					if(j<allTheWays.get(i).size()-3){
						out.print(allTheWays.get(i).get(j)+"||");
					}
					else if(j==allTheWays.get(i).size()-2){
						out.print("\t"+allTheWays.get(i).get(j)+"/");
					}
					else {
						out.print(allTheWays.get(i).get(j));
					}
				}
				out.println();
			}
			out.println();
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}

}
