import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadInput {
	
	/**
	 * Here in this method, input.txt was read.Each line of input keep into the lines 
	 * array list for using it them more than one times,without reading file again.
	 * @param input This is an input.txt
	 * @param output This is an output.txt
	 */
	public void readInputFile(String input,String output){
		ArrayList<String> lines = new ArrayList<String>();
		try {
            BufferedReader in = new BufferedReader(new FileReader(input));
            String str;
            int counter=0;
            String firstLine=null;
            String start = null;
    		String dest = null;
            while ((str = in.readLine()) != null) {
            	if(counter==0){//Here the first line of input was read.
            		//The start point and end point keeps to use after creating the graph.
            		firstLine=str;
            		String points[] = firstLine.split(",");
            		start = points[0].substring(2);//This is the start point of shortest path.
            		dest = points[1].substring(2);//This is the end point of shortest path.
            		
            	}
            	else {//If the lines except first one is read,then it will added to the lines array list.
            		lines.add(str);
            	}
            	counter++;
            }
            //After reading each line,then Graph constructor is called to create the graph.
            Graph reach = new Graph(lines,Integer.parseInt(start),Integer.parseInt(dest),output);
            
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	/**
	 * Here in this method the edges of vertices will be specified.
	 * If one of the end of any edge doesn't involve into the vertex array ,the edge won't be added.
	 * @param line This is one of the line of input.txt
	 * @param vertices These are the vertices that have already enrolled.
	 * @return
	 */
	
	public ArrayList<ArrayList<Integer>> splitAndAddEdges(String line,ArrayList<Graph.Vertex> vertices){
		ArrayList<ArrayList<Integer>> edgesAndTheirWeights=new ArrayList<ArrayList<Integer>>();
		if(line.charAt(line.length()-1)==' '){
			ArrayList<Integer> edgeToWeight=new ArrayList<Integer>();
			edgeToWeight.add(Integer.parseInt(line.substring(0, line.length()-2)));
			edgeToWeight.add(0);
			edgesAndTheirWeights.add(edgeToWeight);
			return edgesAndTheirWeights;
		}
		String [] fromVertex=line.split("\\.");
		String lineWithoutDot=fromVertex[0]+fromVertex[1];
		String [] elements=lineWithoutDot.split(" ");
		String [] edges=elements[1].split(",");
		for(int i=0;i<edges.length;i++){
			int counter=0;
			String edgeTo=null;
			String weight=null;
			for(int j=0;j<edges[i].length();j++){
				if(edges[i].charAt(j)=='('){
					counter++;
					j++;
				}
				else if(edges[i].charAt(j)==')'){
					counter++;
				}
				if(counter==0){
					if(edgeTo==null){
						edgeTo = String.valueOf(edges[i].charAt(j));
					}
					else {
						edgeTo += edges[i].charAt(j);
					}
				}
				else if(counter==1){
					if(weight==null){
						weight = String.valueOf(edges[i].charAt(j));
					}
					else {
						weight += edges[i].charAt(j);
					}
				}
				else if(counter==2){
					break;
				}
			}
			ArrayList<Integer> edgeToWeight=new ArrayList<Integer>();
			int count=0;
			for(int j=0;j<vertices.size();j++){
				if(vertices.get(j).getNumberOfVertice()==Integer.parseInt(edgeTo)){
					count++;
				}
			}
			if(count!=0){
				edgeToWeight.add(Integer.parseInt(edgeTo));
				edgeToWeight.add(Integer.parseInt(weight));
				edgesAndTheirWeights.add(edgeToWeight);
			}
		}
		ArrayList<Integer> lastElement=new ArrayList<Integer>();
		lastElement.add(Integer.valueOf(elements[0]));
		lastElement.add(0);
		edgesAndTheirWeights.add(lastElement);
		return edgesAndTheirWeights;
	}
	
	/**
	 * Here in this method all line string will be divided.
	 * After that the vertex number will be presented.
	 * If it is must pass,then it will be added to the array list with one as a must pass key.
	 * else the must pass key will be zero. 
	 * @param line This is one of the line of input.txt
	 * @return This will return an array list for each vertex.
	 * The element of this array list will include the number of vertex and the must pass key of vertex.
	 */
	
	public ArrayList<Integer> split(String line){
		ArrayList<Integer> oneVertex =new ArrayList<Integer>();
		if(line.charAt(line.length()-1)==' '){//If there is no related edge with this vertex.
			oneVertex.add(Integer.valueOf(line.substring(0, line.length()-2)));
			oneVertex.add(0);
		}
		else{
			String [] fromVertex=line.split("\\.");
			String lineWithoutDot=fromVertex[0]+fromVertex[1];
			String [] elements=lineWithoutDot.split(" ");
			if(elements.length==2){//If one of the vertex has no related edge,but it is must pass.
				if(elements[1].equalsIgnoreCase("mustpass")){
					oneVertex.add(Integer.valueOf(elements[0]));
					oneVertex.add(1);
				}
				else{
					oneVertex.add(Integer.valueOf(elements[0]));
					oneVertex.add(0);
				}
			}
			else if(elements.length==3){//If the vertex is must pass.
				oneVertex.add(Integer.valueOf(elements[0]));
				if(elements[2].equalsIgnoreCase("mustpass")){
					oneVertex.add(1);
				}
				else{
					oneVertex.add(0);
				}
			}
		}
		return oneVertex;
	}
	
	

}
