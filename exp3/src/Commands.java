import java.util.ArrayList;
import java.util.Collections;

/**
 * Here in this class all commands will be implemented.
 * @author nehil
 *
 */
public class Commands {
	WriteOutput obj=new WriteOutput();
	static ArrayList<BalancedTree.BalancedTreeNode.Key> element=new ArrayList<BalancedTree.BalancedTreeNode.Key>();
	
	/**
	 * This method work like an interface to reach the search functions.
	 * @param command This is the command that we wanted to implement.
	 * @param word This is the word that we wanted to search.
	 * @param outputFile This is the output file .
	 */
	public void searchIndividualElement(String command,String word, String outputFile){
		if(word.contains("*")){
			word=word.substring(0, word.length()-1);
			searchAsterisk(BalancedTree.root, word, outputFile);
			Collections.sort(element, ComparatorForKeys.BY_WORD);
			obj.printAsterisk(element, command, outputFile);
			element.clear();
		}
		
		else{
			BalancedTree.BalancedTreeNode.Key result=new BalancedTree.BalancedTreeNode.Key();
			result=search(BalancedTree.root, word);
			if(result!=null){
				element.add(result);
			}
			obj.printOut(element, command, outputFile);
			element.clear();
		}
	}
	
	/**
	 * This method works like an interface . This helps us to reach the in order or level order command.
	 * @param type This represent the command is for in order or level order.
	 * @param command This is the command that we wanted to implement.
	 * @param outputFile This is the output file.
	 */
	
	public void traverse(String type,String command,String outputFile){
		if(type.equals("in-order")){
			inOrder(BalancedTree.root);
			obj.printElement(element,command, outputFile);
		}
		else if(type.equals("level-order")){
			ArrayList<BalancedTree.BalancedTreeNode> b=new ArrayList<BalancedTree.BalancedTreeNode>();
			b.add(BalancedTree.root);
			levelOrder(b);
			obj.printElement(element,command, outputFile);
			
		}
		element.clear();
		
	}
	
	/**
	 * This function is for level order.
	 * @param a  Here is the array list for nodes that we wanted to added.
	 */
	
	private void levelOrder(ArrayList<BalancedTree.BalancedTreeNode> a){
		ArrayList<BalancedTree.BalancedTreeNode> b=new ArrayList<BalancedTree.BalancedTreeNode>();
		for(int i=0;i<a.size();i++){
			for(int j=0;j<a.get(i).getNumberOfElements();j++){
				element.add(a.get(i).elements.get(j));
			}
		}
		for(int i=0;i<a.size();i++){
			for(int j=0;j<a.get(i).children.size();j++){
				b.add(a.get(i).children.get(j));
			}
		}
		a.clear();
		if(!b.isEmpty()){
			levelOrder(b);
		}
		
	}
	
	/**
	 * This method implements in order sequence of the B-Tree
	 * @param node This is for the place where we start.
	 */
	private void inOrder(BalancedTree.BalancedTreeNode node){
		if(node.getParent()==null){
			if(node.isLeaf()){
				for(int i=0;i<node.getNumberOfElements();i++){
					element.add(node.elements.get(i));
				}
			}
			else{
				for(int i=0;i<node.children.size();i++){
					if(i>=1){
						element.add(node.elements.get(i-1));
						inOrder(node.children.get(i));
					}
					else{
						inOrder(node.children.get(i));
					}
					
				}
				
			}
			
		}
		else{
			if(node.isLeaf()){
				for(int i=0;i<node.getNumberOfElements();i++){
					element.add(node.elements.get(i));
				}
			}
			else{
				for(int i=0;i<node.children.size();i++){
					if(i>=1){
						element.add(node.elements.get(i-1));
						inOrder(node.children.get(i));
					}
					else{
						inOrder(node.children.get(i));
					}
					
				}
				
			}
			
		}
	}
	
	/**
	 * This method is for finding the word that starts with specific string.
	 * @param rootElement This is the node that we starts from searching.
	 * @param word This is the specific string
	 * @param output This is the output file. 
	 */
	
	private void searchAsterisk(BalancedTree.BalancedTreeNode rootElement, String word,String output){
		if(rootElement.isLeaf()){
			for(int i=0;i<rootElement.getNumberOfElements();i++){
				if(rootElement.elements.get(i).getWord().length()>=word.length()){
					String a=rootElement.elements.get(i).getWord().substring(0, word.length());
					if(a.equals(word)){
						element.add(rootElement.elements.get(i));
					}
				}
			}
		}
		else {
			for(int i=0;i<rootElement.getNumberOfElements();i++){
				if(rootElement.elements.get(i).getWord().length()>=word.length()){
					String a=rootElement.elements.get(i).getWord().substring(0, word.length());
					if(a.equals(word)){
						element.add(rootElement.elements.get(i));
					}
				}
			}
			for(int i=0;i<rootElement.children.size();i++){
				searchAsterisk(rootElement.children.get(i), word,output);
			}
		}
		
	}
	
	/**
	 * This is the search method for individual element.If the it is leaf and method find the word method 
	 * will return the place of this word else the method will return null.
	 * If it is not leaf , and if method find the word ,the method again return the place of this word,
	 * else it will make another recursive call.
	 * @param rootElement This is the node where we start from.
	 * @param word This is the word that we looking for.
	 * @return It will return the place of word or null.
	 */
	
	private BalancedTree.BalancedTreeNode.Key search(BalancedTree.BalancedTreeNode rootElement, String word ){
		BalancedTree.BalancedTreeNode.Key result=new BalancedTree.BalancedTreeNode.Key();
		if (rootElement.isLeaf()) {
			for(int i=0;i<rootElement.getNumberOfElements();i++){
				if(rootElement.elements.get(i).getWord().equals(word)){
					return rootElement.elements.get(i);
				}
			}
			
		} else {
			for(int i=0;i<rootElement.getNumberOfElements();i++){
				if(rootElement.elements.get(i).getWord().equals(word)){
					return rootElement.elements.get(i);
				}
			}
			
			for(int i=0;i<rootElement.getNumberOfElements();i++){
				if(word.compareToIgnoreCase(rootElement.elements.get(i).getWord())<0){
					result=search(rootElement.children.get(i), word);
					if(result!=null){
						return result;
					}
				}
			}
			
			if(word.compareToIgnoreCase(rootElement.elements.get(rootElement.getNumberOfElements()-1).getWord())>0){
				result=search(rootElement.children.get(rootElement.getNumberOfElements()), word);
				if(result!=null){
					return result;
				}
			}

		}
		
		return null;
	}
	
	/**
	 * Here in this function method calls the search function to find the places of words.
	 * Depending on command (not or and commands) it will call the print function.
	 * @param command This is the command that we wanted to implement.
	 * @param word1 This is the first word.
	 * @param word2 This is the second word.
	 * @param difference This can be not or and.
	 * @param outputFile This is the output file.
	 */
	public void searchForTwoElements(String command,String word1,String word2,String difference,String outputFile){
		BalancedTree.BalancedTreeNode.Key word1Element=new BalancedTree.BalancedTreeNode.Key();
		BalancedTree.BalancedTreeNode.Key word2Element=new BalancedTree.BalancedTreeNode.Key();
		if(difference.equals("not")){
			word1Element=search(BalancedTree.root, word1);
			word2Element=search(BalancedTree.root, word2);
			obj.printTwoElements(command, word1Element, word2Element, difference, outputFile);
		}
		else if(difference.equals("and")){
			word1Element=search(BalancedTree.root, word1);
			word2Element=search(BalancedTree.root, word2);
			obj.printTwoElements(command, word1Element, word2Element, difference, outputFile);
		}
		
		
	}

}
