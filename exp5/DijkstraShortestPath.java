import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraShortestPath {
	private double shortestDistance=Double.POSITIVE_INFINITY;//This keeps the distance for shortest path.
	private Stack<String> path=new Stack<String>();	
	private ArrayList<String>pathWay=new ArrayList<String>();
	private ArrayList<String> shortestPath=new ArrayList<String>();
	private PriorityQueue<Graph.Vertex> indexMinPQ = new PriorityQueue<Graph.Vertex>(ComparatorForVertices.BY_DEST);
	//Here is an priority queue whose elements are ordered in the their destination values.
	private ArrayList<ArrayList<Graph.Vertex>>mustPassArray=new ArrayList<ArrayList<Graph.Vertex>>();
	//This keeps the possible sequences of must pass vertices.
	
	//The vertices represent us the vertices of graph.
	//The value is the weight of start point.
	//It will be zero in very first time but then when we tried to find constrained shortest path it will be different than zero.
	private void dijkstra(ArrayList<Graph.Vertex> vertices, int start, int dest,double value) {
		for (int i = 0; i < vertices.size(); i++) {
			for(int j=0;j<vertices.get(i).getEdgesOfThisVertice().size();j++){
				vertices.get(i).getEdgesOfThisVertice().get(j).setRelaxEdge(false);//All edges are assigned as they haven't relaxed yet.
			}
			if (vertices.get(i).getNumberOfVertice() == start) {//The first time start vertex will be added to the priority queue.
				vertices.get(i).setDestTo(value);//And its destination weight will be assigned as value.
				indexMinPQ.add(vertices.get(i));
			}
			else{//Else the vertices are assigned like in their very first time assignment.
				vertices.get(i).setDestTo(Double.POSITIVE_INFINITY);
				vertices.get(i).setPath(null);
				vertices.get(i).setPathTo(null);
			}
		}
		
		while (!indexMinPQ.isEmpty()) {//If the priority queue is not empty , we will poll the first element.
			Graph.Vertex startVertex = indexMinPQ.poll();
			 for (Graph.Vertex.Edge e : startVertex.getEdgesOfThisVertice()){
				 if(!e.isRelaxEdge()){//Here we have to relax all edges of the element that was polled from queue.
					 relax(e); 
				 } 
			 }
		}	
	}
	
	
	private void relax(Graph.Vertex.Edge e) {
		Graph.Vertex fromPoint= new Graph.Vertex();//Here is the start point of edge.
		Graph.Vertex toPoint= new Graph.Vertex();//Here is the end point of edge.
		fromPoint=e.getEdgeFrom();
		toPoint=e.getEdgeTo();
		//Here we can change the destination of the vertex.
		//If there is more shortest way to reach toPoint vertex, we can reassign the pathTo and destTo of that vertex.
		if (toPoint.getDestTo() > fromPoint.getDestTo() + e.getWeight()) {
			e.setRelaxEdge(true);
			toPoint.setDestTo(fromPoint.getDestTo() + e.getWeight());
			toPoint.setPathTo(e);
			
			if(!indexMinPQ.contains(toPoint)){//If the element that is in the end of the edge hasn't been in the queue yet,
				//then it will be added to the queue.
				indexMinPQ.add(toPoint);
			}
		}
	}
	
	//This method helps us to find the path sequence.If we reach the start point than we will pop all the elements from stack and 
	//add it to the main path way.
	private void findPath(Graph.Vertex end , int start){
		if(end.getNumberOfVertice()==start){
			while(!path.isEmpty()){
				pathWay.add(path.pop());
			}
			return ;
		}
		else{
			path.push(String.valueOf(end.getPathTo().getEdgeFrom().getNumberOfVertice()));
			findPath(end.getPathTo().getEdgeFrom(), start);
		}
	}
	
	//Here in this method we will call the other methods to find shortest path and the constrained shortest path, after all these the 
	//result will be printed to the output.txt.
	public void path(ArrayList<Graph.Vertex> vertices, int start, int dest,String output){
		writeOutput writeObj=new writeOutput();
		dijkstra(vertices, start, dest,0.0);
		for(int i=0;i<vertices.size();i++){
			if(vertices.get(i).getNumberOfVertice()==dest){
				if(vertices.get(i).getDestTo()!=Double.POSITIVE_INFINITY){//This means that we reach the end point
					//If it was an infinity , this would mean that there is no way to reach from start point to the end point.
					findPath(vertices.get(i), start);
					pathWay.add(String.valueOf(vertices.get(i).getNumberOfVertice()));
					writeObj.printShortestPath(pathWay,vertices.get(i).getDestTo(), output);
					break;
				}
				else{
					writeObj.printShortestPath(pathWay,vertices.get(i).getDestTo(), output);
					break;
				}
			}
		}
		//Here we called the constrainedPaths method to find the constrained shortest path.
		constraintPaths(vertices, start, dest);
		if(shortestPath.isEmpty()){
			writeObj.printConstrainedShortestPath(vertices,shortestPath,shortestDistance, output);
		}
		else{//If the path that we have found is not empty, we have to add that path the end point.
			shortestPath.add(String.valueOf(dest));
			writeObj.printConstrainedShortestPath(vertices,shortestPath,shortestDistance, output);
		}
		
	}

	//Here in this method we find the constrained shortest path.
	private void constraintPaths(ArrayList<Graph.Vertex> vertices, int start, int dest){
		ArrayList<Graph.Vertex> mustpass = new ArrayList<Graph.Vertex>();
		for(int i=0;i<vertices.size();i++){//Here we will discover all the must pass elements.
			if(vertices.get(i).isMustPass()){//And then we will add these elements to the must pass array list.
				mustpass.add(vertices.get(i));
			}
		}
		
		boolean[] visited = new boolean[mustpass.size()];
		Graph.Vertex [] branch = new Graph.Vertex [mustpass.size()];
		for(int i=0;i<mustpass.size();i++){//We assigned all the visits' elements as unvisited.
			//The number of visited array is equals to the size of must pass array list.
			visited[i] = false;
		}
		
		//We sent the level number as -1.
		//Here we found the all permutations of must passes.
		generatePermutations(mustpass, branch, -1, visited);
		
		Graph.Vertex last=new Graph.Vertex();//This keeps the end point of the shortest path's.
		for(int x=0;x<vertices.size();x++){
			if(vertices.get(x).getNumberOfVertice()==dest){
				last=vertices.get(x);
			}
		}
		for(int i=0;i<mustPassArray.size();i++){
			pathWay.clear();
			dijkstra(vertices, start, mustPassArray.get(i).get(0).getNumberOfVertice(),0.0);//Here by using the start point 
			//first dijkstra was implemented.
			//We try to reach the next must pass element.
			if( mustPassArray.get(i).get(0).getDestTo()!=Double.POSITIVE_INFINITY){//If we reach the first must pass then the path 
				//will be added to the main path way.
				findPath(mustPassArray.get(i).get(0), start);
			}
			else{//Else the path way will be cleaned. And this result shows us there is no way to end point by using this sequence of must
				//passes.We have to check the next sequence of must pass array.
				pathWay.clear();
				continue;
			}
			int counter=0;
			for(int j=0;j<mustPassArray.get(i).size();j++){//Each element of the must pass array's i. element's will be controlled.
				//And each time the dijkstra's will be found.
				counter=0;
				if(j==mustPassArray.get(i).size()-1){
					dijkstra(vertices,mustPassArray.get(i).get(j).getNumberOfVertice(),dest ,mustPassArray.get(i).get(j).getDestTo());
					if( last.getDestTo()!=Double.POSITIVE_INFINITY){
						findPath(last,mustPassArray.get(i).get(j).getNumberOfVertice());
					}
					else{
						counter++;
						break;
					}
				}
				else{
					dijkstra(vertices,mustPassArray.get(i).get(j).getNumberOfVertice(),mustPassArray.get(i).get(j+1).getNumberOfVertice(),mustPassArray.get(i).get(j).getDestTo());
					if( mustPassArray.get(i).get(j+1).getDestTo()!=Double.POSITIVE_INFINITY){
						findPath(mustPassArray.get(i).get(j+1),mustPassArray.get(i).get(j).getNumberOfVertice());
					}
					else{
						counter++;
						break;
					}
				}
					
			}
			if(counter!=0){
				pathWay.clear();
				continue;
			}
			if(shortestDistance>last.getDestTo()){
				shortestPath.clear();
				shortestDistance=last.getDestTo();
				shortestPath.addAll(pathWay);
			}
		}
	}
	
	
	//Here in this method we found the all possible permutations of must passes.
	//The main idea of this method is binary tree. We think the must pass array as an tree.
	//By using the levels of tree , all permutations are discovered.
	private void generatePermutations(ArrayList<Graph.Vertex> arr,Graph.Vertex [] branch, int level, boolean[] visited)
	{
	    if (level >= arr.size()-1)
	    {
	    	ArrayList<Graph.Vertex> element=new ArrayList<Graph.Vertex>();
	    	for(int i=0;i<branch.length;i++){
	    		element.add(branch[i]);
	    	}
	    	mustPassArray.add(element);
	    	return ;
	    }
	    else{
	    	for (int i = 0; i < arr.size(); i++)
		    {
		        if (!visited[i])
		        {
		            branch[++level]=arr.get(i);
		            visited[i] = true;
		            generatePermutations(arr, branch, level, visited);
		            visited[i] = false;
		            level--;
		        }
		    }
	    }
	}

}
