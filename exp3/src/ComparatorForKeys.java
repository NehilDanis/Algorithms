import java.util.Comparator;

/**
 * Here is where I implemented comparators.
 * @author nehil
 *
 */
public class ComparatorForKeys {

	public static final Comparator<BalancedTree.BalancedTreeNode.Key>BY_WORD=new ByWord();
	
	public static final Comparator<BalancedTree.BalancedTreeNode>BY_ELEMENETWORD=new ByElementWord();
	
	public static final Comparator<BalancedTree.BalancedTreeNode.Key.File>BY_FILENAME=new ByFileName();
	
	public static final Comparator<BalancedTree.BalancedTreeNode.Key.File>BY_FILESIZE=new ByFileSize();
	
	/**
	 * This nested comparator class implement comparator for keys.
	 * This class compares keys by using their word value.
	 * @author nehil
	 *
	 */
	public static class ByWord implements Comparator<BalancedTree.BalancedTreeNode.Key>{
		public int compare(BalancedTree.BalancedTreeNode.Key a,BalancedTree.BalancedTreeNode.Key b){
			return a.getWord().compareToIgnoreCase(b.getWord());
		}
	}
	
	/**
	 * This nested comparator class implement comparator for nodes.
	 * This class compares child nodes by using the word value of their first element(key).
	 * @author nehil
	 *
	 */
	public static class ByElementWord implements Comparator<BalancedTree.BalancedTreeNode>{
		public int compare(BalancedTree.BalancedTreeNode a,BalancedTree.BalancedTreeNode b){
			return a.elements.get(0).getWord().compareToIgnoreCase(b.elements.get(0).getWord());
		}
	}
	
	/**
	 * This nested comparator class implement comparator for files.
	 * This class compares files by using their fileName value.
	 * @author nehil
	 *
	 */
	
	public static class ByFileName implements Comparator<BalancedTree.BalancedTreeNode.Key.File>{
		public int compare(BalancedTree.BalancedTreeNode.Key.File a,BalancedTree.BalancedTreeNode.Key.File b){
			return a.getFileName().compareToIgnoreCase(b.getFileName());
		}
	}
	
	/**
	 * This nested comparator class implement comparator for files.
	 * This class compares files by using their number of filePlaces elements.
	 * If one size is bigger than the other ones size the method will return -1
	 * If one size is smaller than the other ones size the method will return 1
	 * If one size is equal the other ones size the method will return 0
	 * This implementation is for descending order.
	 * @author nehil
	 *
	 */
	
	public static class ByFileSize implements Comparator<BalancedTree.BalancedTreeNode.Key.File>{
		public int compare(BalancedTree.BalancedTreeNode.Key.File a,BalancedTree.BalancedTreeNode.Key.File b){
			if(a.filePlaces.size()<b.filePlaces.size()){
				return 1;
			}
			else if(a.filePlaces.size()>b.filePlaces.size()){
				return -1;
			}
			return 0;
		}
	}
	
}
