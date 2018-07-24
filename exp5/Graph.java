import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();//This is for keeping the all vertices of a graph.
	private int startPoint;//This is the start point of shortest path that expected.
	private int destinationPoint;//This is the end point of shortest path that expected.

	static class Vertex {

		private int numberOfVertice;
		private double destTo;
		private String path;
		private Edge pathTo;//This keeps that where did we reach this vertex from.
		private boolean mustPass;
		private ArrayList<Edge> edgesOfThisVertice = new ArrayList<Edge>();//Each vertex have an edges list.This represents the ways that 
		//are possible to reach from this vertex.

		static class Edge {

			private boolean relaxEdge;
			private Vertex edgeTo;
			private Vertex edgeFrom;
			private int weight;

			public Edge(Vertex edgeFrom, Vertex edgeTo, int weight) {//Here in this method each edge is defined very first time.
				this.relaxEdge=false;
				this.edgeTo = edgeTo;
				this.edgeFrom = edgeFrom;
				this.weight = weight;
			}

			public int getWeight() {
				return weight;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

			public Vertex getEdgeTo() {
				return edgeTo;
			}

			public void setEdgeTo(Vertex edgeTo) {
				this.edgeTo = edgeTo;
			}

			public Vertex getEdgeFrom() {
				return edgeFrom;
			}

			public void setEdgeFrom(Vertex edgeFrom) {
				this.edgeFrom = edgeFrom;
			}

			public boolean isRelaxEdge() {
				return relaxEdge;
			}

			public void setRelaxEdge(boolean relaxEdge) {
				this.relaxEdge = relaxEdge;
			}

		}

		public Vertex(int numberOfVertex, String mustpass) {//Here in this method each vertex is defined very first time.
			this.path=null;
			this.numberOfVertice = numberOfVertex;
			if (mustpass == null) {
				this.mustPass = false;
			} else {
				this.mustPass = true;
			}
			this.setPathTo(null);
			this.destTo = Double.POSITIVE_INFINITY;//The destination of each vertex is positive infinity at the first time.
		}

		public Vertex() {
			//
		}

		public int getNumberOfVertice() {
			return numberOfVertice;
		}

		public void setNumberOfVertice(int numberOfVertice) {
			this.numberOfVertice = numberOfVertice;
		}

		public ArrayList<Edge> getEdgesOfThisVertice() {
			return edgesOfThisVertice;
		}

		public void setEdgesOfThisVertice(ArrayList<Edge> edgesOfThisVertice) {
			this.edgesOfThisVertice = edgesOfThisVertice;
		}

		public boolean isMustPass() {
			return mustPass;
		}

		public void setMustPass(boolean mustPass) {
			this.mustPass = mustPass;
		}

		public double getDestTo() {
			return destTo;
		}

		public void setDestTo(double destTo) {
			this.destTo = destTo;
		}

		public Edge getPathTo() {
			return pathTo;
		}

		public void setPathTo(Edge pathTo) {
			this.pathTo = pathTo;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}

	private void addVertex(ArrayList<ArrayList<Integer>> vertex, int startPoint, int destinationPoint) {//Here in this method all vertices
		//will be added to the graph.
		this.setStartPoint(startPoint);
		this.setDestinationPoint(destinationPoint);
		for (int i = 0; i < vertex.size(); i++) {
			if (vertex.get(i).get(1) == 0) {
				Vertex oneVertex = new Vertex(vertex.get(i).get(0), null);
				vertices.add(oneVertex);
			} else if (vertex.get(i).get(1) == 1) {
				//If it is must pass ,then the variable for must pass will be assigned true.
				Vertex oneVertex = new Vertex(vertex.get(i).get(0), "mustpass");
				vertices.add(oneVertex);
			}
		}
	}

	private void addEdges(int numberOfVertex, ArrayList<ArrayList<Integer>> destinationPointsAndWeights) {//Here in this method all edges
		//will be added to the graph.
		if (!destinationPointsAndWeights.isEmpty()) {
			Vertex start = new Vertex();
			Vertex end = new Vertex();
			for (int i = 0; i < vertices.size(); i++) {
				if (vertices.get(i).numberOfVertice == numberOfVertex) {
					start = vertices.get(i);
					break;
				}
			}

			for (int i = 0; i < destinationPointsAndWeights.size(); i++) {
				for (int j = 0; j < vertices.size(); j++) {
					if (vertices.get(j).numberOfVertice == destinationPointsAndWeights.get(i).get(0)) {
						end = vertices.get(j);
						break;
					}
				}
				Graph.Vertex.Edge edge = new Graph.Vertex.Edge(start, end, destinationPointsAndWeights.get(i).get(1));
				start.edgesOfThisVertice.add(edge);
			}
		}
	}

	public Graph(ArrayList<String> lines, int start, int dest,String output) {//Here in this constructor the graph was created.
		ArrayList<ArrayList<Integer>> vertex = new ArrayList<ArrayList<Integer>>();
		ReadInput obj = new ReadInput();
		for (int i = 0; i < lines.size(); i++) {//All the vertices were added to the vertex array list.
			vertex.add(obj.split(lines.get(i)));
		}
		addVertex(vertex, start, dest);//All the vertices were added to the graph.
		for (int i = 0; i < lines.size(); i++) {//The edges from one available vertex to another and the weight of each edge was defined in here.
			ArrayList<ArrayList<Integer>> destinationPointsAndWeights = new ArrayList<ArrayList<Integer>>();
			destinationPointsAndWeights = obj.splitAndAddEdges(lines.get(i),vertices);
			int number = destinationPointsAndWeights.get(destinationPointsAndWeights.size() - 1).get(0);
			destinationPointsAndWeights.remove(destinationPointsAndWeights.size() - 1);
			addEdges(number, destinationPointsAndWeights);//All edges were added to the graph.
		}
		int counter=0;
		for(int i=0;i<vertices.size();i++){
			if(vertices.get(i).getNumberOfVertice()==start){
				counter++;
			}
			else if(vertices.get(i).getNumberOfVertice()==dest){
				counter++;
			}
		}
		if(counter==2){//If the start point and the and point that were given us via on input.txt , are available in graph.
			DijkstraShortestPath shortestPath = new DijkstraShortestPath();
			shortestPath.path(vertices, start, dest,output);
		}
		else{//If the start and end point are not available in the graph , program will print an error message to the output.txt.
			try{
				PrintWriter out = new PrintWriter(new FileWriter(output,true)); 
				out.println("21327876");
				out.println();
				out.println("One of them or both start and destination points doesn't involve in "
						+ "vertices arraylist");
				out.close();
			}catch(IOException e){
				System.out.println("File write error.");
			}
		}

	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(int destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

}
