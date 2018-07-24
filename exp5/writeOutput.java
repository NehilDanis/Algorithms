import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class writeOutput {
	
	/**
	 * Here in this method the shortest constrained path was printed.
	 * @param vertices These are the vertex array list of the graph.
	 * @param constrainedShortestPath This the the proper shortest constrained path array list.
	 * @param weight This is the shortest distance from start point to the end point.
	 * @param output This is for the output.txt
	 */
	public void printConstrainedShortestPath(ArrayList<Graph.Vertex> vertices,ArrayList<String>constrainedShortestPath,double weight
			,String output){
		try{
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			if(weight==Double.POSITIVE_INFINITY){//This means that we didn't arrive the end point from start.
				out.println("Constrained Shortest Path:"+"  "+"There is no proper constrained shortest path.");
			}
			else{
				out.print("Constrained Shortest Path:"+"  "+"Distance="+weight+"00000     ");
				out.print("Path=(");
				for(int i=0;i<constrainedShortestPath.size();i++){
					int counter=0;
					for(int j=0;j<vertices.size();j++){
						if(vertices.get(j).getNumberOfVertice()==Integer.valueOf(constrainedShortestPath.get(i))){
							//All vertices are controlled and if the vertex is in constrained shortest path and it is also a must pass
							//then there will be printed must pass near the number of vertex.
							if(vertices.get(j).isMustPass()){
								out.print(constrainedShortestPath.get(i)+"(mustpass) ");
								counter++;
								break;
							}
						}
					}
					if(counter==0){
						out.print(constrainedShortestPath.get(i)+" ");
					}
				}
				out.print(")");
			}
			out.close();
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}
	
	/**
	 * Here in this method the shortest path was printed.
	 * @param shortestPath THis is the array list for keeping the shortest path nodes in an accurate order.
	 * @param weight This is the shortest distance from start point to the end point.
	 * @param output This is for an output.txt
	 */
	public void printShortestPath(ArrayList<String>shortestPath,double weight
			,String output){
		try{
			PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
			out.println("21327876");
			out.println();
			if(weight==Double.POSITIVE_INFINITY){
				out.println("Shortest Path:"+"  "+"There is no proper shortest path.");
				out.println();
			}
			else{
				out.print("Shortest Path:"+"  "+"Distance="+weight+"00000     ");
				out.print("Path=(");
				for(int i=0;i<shortestPath.size();i++){
					out.print(shortestPath.get(i)+" ");
				}
				out.print(")");
				out.println();
				out.println();
			}
			out.close();
			
		}catch(IOException e){
			System.out.println("File write error.");
		}
	}

}
