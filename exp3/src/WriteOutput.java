import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class WriteOutput {
	
	/**
	 * Here in this method ,the program will print the file names , row and column indexes of an individual word.
	 * @param elements This array list will keep the element that we wanted to print of its informations.
	 * @param command THis is the command that we wanted to implement.
	 * @param output This is the output file ,that we wanted to show the results.
	 */
	public void printOut(ArrayList<BalancedTree.BalancedTreeNode.Key> elements,String command,String output){
		try{
			//Here we printed all result to the output.txt file.
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			out.println(command);
			if(elements.isEmpty()){
				out.println();
			}
			else {
				for(int i=0;i<elements.size();i++){
					Collections.sort(elements.get(i).files, ComparatorForKeys.BY_FILENAME);
					Collections.sort(elements.get(i).files, ComparatorForKeys.BY_FILESIZE);
					
					for(int k=0;k<elements.get(i).files.size();k++){
						out.print(elements.get(i).files.get(k).getFileName());
						for(int j=0;j<elements.get(i).files.get(k).filePlaces.size();j++){
							if(j==elements.get(i).files.get(k).filePlaces.size()-1){
								out.print(" "+elements.get(i).files.get(k).filePlaces.get(j).getRow()+":"+
										elements.get(i).files.get(k).filePlaces.get(j).getColumn());
							}
							else{
								out.print(" "+elements.get(i).files.get(k).filePlaces.get(j).getRow()+":"+
										elements.get(i).files.get(k).filePlaces.get(j).getColumn()+",");
							}
						}
						out.println();
					}
				}
				
			}
			out.println();
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}
	/**
	 * This method is used for print in order or level order sequence.
	 * @param elements This is the list of in ordered or level ordered elements.
	 * @param command This is the command that we wanted to implemented.
	 * @param output This is the output file that we wanted to show the results in it.
	 */
	public void printElement(ArrayList<BalancedTree.BalancedTreeNode.Key> elements,String command,String output){
		try{
			//Here we printed all result to the output.txt file.
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			out.println(command);
			if(elements.isEmpty()){
				out.println();
			}
			else {
				for(int i=0;i<elements.size();i++){
					out.print(elements.get(i).getWord()+" ");
				}
				out.println();
			}
			out.println();
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}
	/**
	 * This method is used for printing the words start with specific string.
	 * @param elements This is the list of words that start with specific string.
	 * @param command This is the command that we wanted to implement.
	 * @param output This is the output file that we wanted to show the results in it.
	 */
	public void printAsterisk(ArrayList<BalancedTree.BalancedTreeNode.Key> elements,String command,String output){
		try{
			//Here we printed all result to the output.txt file.
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			out.println(command);
			if(elements.isEmpty()){
				out.println();
			}
			else {
				for(int i=0;i<elements.size();i++){
					
					//Collections.sort(elements.get(i).files, ComparatorForKeys.BY_FILENAME);
					
					for(int k=0;k<elements.get(i).files.size();k++){
						out.print(elements.get(i).getWord()+" ");
						Collections.sort(elements.get(i).files, ComparatorForKeys.BY_FILENAME);
						Collections.sort(elements.get(i).files, ComparatorForKeys.BY_FILESIZE);
						out.print(elements.get(i).files.get(k).getFileName());
						for(int j=0;j<elements.get(i).files.get(k).filePlaces.size();j++){
							if(j==elements.get(i).files.get(k).filePlaces.size()-1){
								out.print(" "+elements.get(i).files.get(k).filePlaces.get(j).getRow()+":"+
										elements.get(i).files.get(k).filePlaces.get(j).getColumn());
							}
							else{
								out.print(" "+elements.get(i).files.get(k).filePlaces.get(j).getRow()+":"+
										elements.get(i).files.get(k).filePlaces.get(j).getColumn()+",");
							}
						}
						out.println();
					}
				}
				
			}
			out.println();
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}
	
	/**
	 * This function is used for printing the information of two of elements .
	 * @param command This is the command that we wanted to implement.
	 * @param word1 This is the first word.
	 * @param word2 This is the second word.
	 * @param difference This can be and or not.
	 * @param outputFile This is the output file that we wanted to show the results in it.
	 */
	public void printTwoElements(String command,BalancedTree.BalancedTreeNode.Key word1,BalancedTree.BalancedTreeNode.Key word2,String difference,String outputFile){
		int counter=0;
		if(word1!=null && word2!=null){
		Collections.sort(word1.files, ComparatorForKeys.BY_FILENAME);
		Collections.sort(word1.files, ComparatorForKeys.BY_FILESIZE);
		
		Collections.sort(word2.files, ComparatorForKeys.BY_FILENAME);
		Collections.sort(word2.files, ComparatorForKeys.BY_FILESIZE);
			if(difference.equals("not")){
				ArrayList<BalancedTree.BalancedTreeNode.Key.File> a=new ArrayList<BalancedTree.BalancedTreeNode.Key.File>();
				try{
					//Here we printed all result to the output.txt file.
					PrintWriter out = new PrintWriter(new FileWriter(outputFile,true)); 
					out.println(command);
					for(int i=0;i<word1.files.size();i++){
						a.add(word1.files.get(i));
					}
					for(int i=0;i<a.size();i++){
						for(int j=0;j<word2.files.size();j++){
							if(a.get(i).getFileName().equals(word2.files.get(j).getFileName())){
								counter++;
								break;
							}
						}
						if(counter==0){
							out.print(word1.getWord()+" "+a.get(i).getFileName());
							for(int z=0;z<a.get(i).filePlaces.size();z++){
								if(z==a.get(i).filePlaces.size()-1){
									out.print(" "+a.get(i).filePlaces.get(z).getRow()+":"
											+a.get(i).filePlaces.get(z).getColumn());
								}
								else{
									out.print(" "+a.get(i).filePlaces.get(z).getRow()+":"
											+a.get(i).filePlaces.get(z).getColumn()+",");
								}
							}
							out.println();
						}
						else {
							counter=0;
						}
						
						
					}
					out.println();
					out.close();
					
				}catch(IOException e){
					System.out.println("File write error.");
				}
				
				
			}
			else if(difference.equals("and")){
				try{
					//Here we printed all result to the output.txt file.
					PrintWriter out = new PrintWriter(new FileWriter(outputFile,true)); 
					out.println(command);
					for(int i=0;i<word1.files.size();i++){
						for(int j=0;j<word2.files.size();j++){
							if(word1.files.get(i).getFileName().equals(word2.files.get(j).getFileName())){
								counter++;
								out.print(word1.getWord()+" "+word1.files.get(i).getFileName());
								for(int z=0;z<word1.files.get(i).filePlaces.size();z++){
									if(z==word1.files.get(i).filePlaces.size()-1){
										out.print(" "+word1.files.get(i).filePlaces.get(z).getRow()+":"
												+word1.files.get(i).filePlaces.get(z).getColumn());	
									}
									else{
										out.print(" "+word1.files.get(i).filePlaces.get(z).getRow()+":"
												+word1.files.get(i).filePlaces.get(z).getColumn()+",");
									}
								}
								out.println();
								break;
							}
						}
					}
					for(int i=0;i<word2.files.size();i++){
						for(int j=0;j<word1.files.size();j++){
							if(word2.files.get(i).getFileName().equals(word1.files.get(j).getFileName())){
								counter++;
								out.print(word2.getWord()+" "+word2.files.get(i).getFileName());
								for(int z=0;z<word2.files.get(i).filePlaces.size();z++){
									if(z==word2.files.get(i).filePlaces.size()-1){
										out.print(" "+word2.files.get(i).filePlaces.get(z).getRow()+":"
												+word2.files.get(i).filePlaces.get(z).getColumn());
									}
									else{
										out.print(" "+word2.files.get(i).filePlaces.get(z).getRow()+":"
												+word2.files.get(i).filePlaces.get(z).getColumn()+",");
									}
								}
								out.println();
								break;
							}
						}
					}
					if(counter==0){
						out.println();
					}
					out.println();
					out.close();
					
				}catch(IOException e){
					System.out.println("File write error.");
				}
			}
		}
		else{
			try{
				PrintWriter out = new PrintWriter(new FileWriter(outputFile,true)); 
				out.println(command);
				out.println();
				out.close();
				
			}catch(IOException e){
				System.out.println("File write error.");
			}
		}
	}

}
