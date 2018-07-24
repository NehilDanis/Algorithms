import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Here the program will read each input parameter and depend on these parameter,
 * it will work.
 * @author nehil
 *
 */
public class ReadInput {
	static BalancedTree obj=new BalancedTree("root");
	static ArrayList<File> readFiles=new ArrayList<File>();
	static ArrayList<String> stopWords=new ArrayList<String>();
	static ArrayList<String> findingWords=new ArrayList<String>();
	
	/**
	 * Here the method will read the path
	 * @param path This is for the path way.
	 * @param stopWords This is for the stop words file.
	 */
	
	public void readPath(String path,String stopWords){
		try {
            BufferedReader in = new BufferedReader(new FileReader(stopWords));
            String str;
            while ((str = in.readLine()) != null) {
            	ReadInput.stopWords.add(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		

		ReadInput readInput = new ReadInput();
		readInput.decidePath(path, stopWords);
		readPathFile(readFiles,stopWords);
	}
	
	/**
	 * Here the method will read the commands and call split command method for each line.
	 * @param command This is for command file.
	 * @param output This is for output file.
	 */
	public void readCommand(String command,String output){
		try {
            BufferedReader in = new BufferedReader(new FileReader(command));
            String str;
            while ((str = in.readLine()) != null) {
            	splitCommand(str, output);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	/**
	 * Here the method will split the one of the line of command file by using space delimiter.
	 * Depending on the result of split expression the method will call consistent command method.
	 * @param command This is the command that we wanted to implement.
	 * @param output This is the output file.
	 */
	
	private void splitCommand(String command,String output){
		Commands obj=new Commands();
		String [] commandArray=command.split(" ");
			if(commandArray[0].equals("search")){
				if(commandArray.length==2){
					obj.searchIndividualElement(command,commandArray[1], output);
				}
				if(commandArray.length==4){
					obj.searchForTwoElements(command, commandArray[1], commandArray[3], commandArray[2], output);
				}
			}
			else if(commandArray[0].equals("traverse")){
				obj.traverse(commandArray[1],command, output);
			}

	}
	
	/**
	 * Here in this method program will be get into path and then take all readable files to read later.
	 * If the file is directory the method will make a recursive call.
	 * @param path This is the path.
	 * @param stopWords This is the stop words.
	 */
	
	private void decidePath( String path, String stopWords ){
		File file = new File( path );
		File files[] = file.listFiles();
		
		Arrays.sort( files, new Comparator<File>() {

			/**
			 * Here is a comparator for file read sequence.
			 */
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().toLowerCase().compareTo( o2.getName().toLowerCase() );
			}
		});
		
		
		
		for ( File fileEntry : files ) {
	        if ( fileEntry.isDirectory() ) {
	        	decidePath(fileEntry.getPath(), stopWords);
	        }
	        else  {
	        	readFiles.add(fileEntry);
	        }
	    }
	}
	
	/**
	 * Here the method read each file line by line and the method will call the split line method .
	 * @param file This is the file that the method read. 
	 * @param stopWords This is the list of stop words file.
	 */
	private void readPathFile(ArrayList<File> file,String stopWords){
		for(int i=0;i<file.size();i++){
			try {
				int counter=0;//This is for line number.
	            BufferedReader in = new BufferedReader(new FileReader(file.get(i)));
	            String str;
	            while ((str = in.readLine()) != null) {
	            	if(str!=null){
	            		counter++;
	            		splitLine(str,stopWords,counter,file.get(i).getName());
	            	}
	            }
	            in.close();
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
		}
	}
	
	/**
	 * Here in this method each line split by using regex.
	 * An then the method will call another method to decide the index of each element.
	 * And then the method will call insert method of B-Tree.
	 * @param line This is for the line that we wanted to split.
	 * @param stopWords This is stop words listed file.
	 * @param counter This counter is for the row value of element in that file.
	 * @param file This is the file name of the word.
	 */
	private void splitLine(String line,String stopWords,int counter,String file){
		int count=0;
		String regex = "[\\p{Punct}\\p{IsWhite_Space}&&[^\']]+";
		line=line.toLowerCase();
		String array[]=line.split(regex);
		findingWords.clear();
		for(int i=0;i<array.length;i++){
			if(array[i].length()!=0){
				for(int j=0;j<ReadInput.stopWords.size();j++){
					if(array[i].equalsIgnoreCase(ReadInput.stopWords.get(j))){
						count++;
						break;
					}
				}
				if(count==0){ 
					int pointInLine=decideLinePoint(line, array[i]);
					obj.insert(array[i], file, counter, pointInLine);
				}
				else{
					count=0;
				}
			}
		}
	}
	/**
	 * Here in this method the program will decide the index of word in line.
	 * @param line The line that the word involved.
	 * @param word This is the word that we wanted to find its index in that line.
	 * @return This returns the start index of the word.
	 */
	private int decideLinePoint(String line,String word){
		int i = 0;
		int x=0;
		int counter=0;
		for(int j=0;j<ReadInput.findingWords.size();j++){
			if(ReadInput.findingWords.get(j).equalsIgnoreCase(word)){
				counter++;
			}
		}
		  while (true) {
		    int found = line.indexOf(word, i);
		    if (found == -1) break;
		    int start = found+1 ; // start of actual name
		    x=start;
		    int end = start+word.length()-1;
		    char [] a=line.toCharArray();
		    if(end+1<line.length() && Character.isLetter(a[end])){
		    	i=end+1;
		    }
		    else{
		    	ReadInput.findingWords.add(word);
		    	 i = end + 1;  // advance i to start the next iteration
				    if(counter!=0){
				    	counter--;
				    }
				    else{
				    	return x;
				    }
		    }
		  }
		return x;
	}

}



