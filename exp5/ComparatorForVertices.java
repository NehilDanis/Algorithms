import java.util.Comparator;


public class ComparatorForVertices {
	
	public static final Comparator<Graph.Vertex>BY_DEST=new ByDest();
	//This is for keeping the vertices in the priority queue depend on their destination weights.
	
	public static class ByDest implements Comparator<Graph.Vertex>{
		public int compare(Graph.Vertex a,Graph.Vertex b){
			if(a.getDestTo()<b.getDestTo()){//If a's destination is smaller than b's,then this method will
				//return -1
				return -1;
			}
			else if(a.getDestTo()>b.getDestTo()){//If a's destination is bigger than b's,then this method will
				//return 1
				return 1;
			}
			//If a's destination is equal to b's,then this method will
			//return 0
			return 0;
		}
	}

}
