import java.util.ArrayList;
import java.util.Collections;

/**
 * Here in this class BTree was implemented. All the operations of BTree was implemented in here.
 * The root value is for keeping the root of BTree at the known place.
 * The MAX value is for max number of keys in one node.
 * @author nehil
 *
 */

public class BalancedTree {
	static BalancedTreeNode root;
	static int MAX;
	
	/**
	 * Here is the nested class for nodes of BTree.
	 * Here is array lists for child nodes of one node and keys(elements) of one node.
	 * @author nehil
	 *
	 */
	static class BalancedTreeNode{
		private boolean isLeaf;//If the node is leaf , then this value of that node will be true.
		public ArrayList<Key> elements=new ArrayList<Key>();
		public ArrayList<BalancedTreeNode> children=new ArrayList<BalancedTreeNode>();
		private BalancedTreeNode parent;//This is the parent node of that node.
		private int numberOfElements;//This is the number of keys(elements) in that node.
		
		
		/**
		 * Here is a nested class for each key(element) of one node.
		 * There is an array list for file list of each key(element).
		 * @author nehil
		 *
		 */
		static class Key{
			public ArrayList<File> files=new ArrayList<File>();
			private String word;//This is the value for keeping the name of key.
			public Key(){
				//
			}
			
			public Key(String wordName) {
				word=wordName;
			}
			
			public String getWord() {
				return word;
			}
			public void setWord(String word) {
				this.word = word;
			}
			
			/**
			 * Here is a nested class for keeping the file names.
			 * There is an array list for keeping the row and column numbers of each word in the consistent
			 * file name.
			 * @author nehil
			 *
			 */
			
			static class File{
				private String fileName;
				public ArrayList<FilePlace> filePlaces=new ArrayList<FilePlace>();
				
				public String getFileName() {
					return fileName;
				}

				public void setFileName(String fileName) {
					this.fileName = fileName;
				}
				public File(String file) {
					fileName=file;
				}
				
				/**
				 * Here is a nested class for keeping the row and column numbers of each word.
				 * @author nehil
				 *
				 */
				
				static class FilePlace{
					private int row;
					private int column;
					public FilePlace(int rowName,int columnName){
						row=rowName;
						column=columnName;
					}
					public int getRow() {
						return row;
					}
					public void setRow(int row) {
						this.row = row;
					}
					public int getColumn() {
						return column;
					}
					public void setColumn(int column) {
						this.column = column;
					}
					
				}
			}
		}
		
		
		public boolean isLeaf() {
			return isLeaf;
		}
		public void setLeaf(boolean isLeaf) {
			this.isLeaf = isLeaf;
		}
		public BalancedTreeNode getParent() {
			return parent;
		}
		public void setParent(BalancedTreeNode parent) {
			this.parent = parent;
		}
		public int getNumberOfElements() {
			return numberOfElements;
		}
		public void setNumberOfElements(int numberOfElements) {
			this.numberOfElements = numberOfElements;
		}
		public BalancedTreeNode(){
			//
		}
		
	}
	
	public BalancedTree(){
		//
	}
	
	/**
	 * Here in this constructor , we change the root. 
	 * And we can adjust the type of constructor by changing the MAX value.
	 * @param word
	 */
	public BalancedTree(String word){
		MAX=4;
		root=new BalancedTreeNode();
		root.setParent(null);
		root.setLeaf(true);
		root.setNumberOfElements(0);
	}
	
	/**
	 * This method takes 4 parameter , and insert word into the BTree. Before inserting word 
	 * the method calls another method to control the existence of the word that we wanted to insert.
	 * If it is not exist , another method will be called to find the consistent place of inserting node.
	 * If the number of keys in one node are more than or equal to MAX+1 ,then the method will call the split method.
	 * @param word This is the element that we wanted to add the BTree.
	 * @param fileName This is the file name of word.
	 * @param rowName This is the row number of word in that file.
	 * @param columnName This is the column number of word in that row.
	 */
	
	public void insert( String word,String fileName,int rowName,int columnName){
		word=word.toLowerCase();
		if(searchExistanceOfNode(root, word, fileName, rowName, columnName)==0){//Word wasn't found.
			BalancedTree.BalancedTreeNode.Key element=new BalancedTree.BalancedTreeNode.Key(word);
			BalancedTreeNode insertedRoot=searchForConsistentLeaf(root, word);//Here we search for consistent leaf to add new word.
			insertedRoot.elements.add(element);
			addFile(insertedRoot.elements.get(insertedRoot.getNumberOfElements()), fileName, rowName, columnName);
			insertedRoot.setNumberOfElements(insertedRoot.getNumberOfElements()+1);
			Collections.sort(insertedRoot.elements, ComparatorForKeys.BY_WORD);//We sorted the inserted node keys in ascending order.
			
			if(insertedRoot.numberOfElements>=(MAX+1)){
				splitNode(insertedRoot, insertedRoot.elements.get(MAX/2));
			}
			
			
		}
		else{//word was found in b tree.
			//System.out.println("The word has already been inserted.");
			
		}
			
	}
	/**
	 * Here in this method we insert a middle element of any of the divided node to the parent node.
	 * If the number of keys in one node more than or equal to MAX+1 then it will call the split function.
	 * @param insertedRoot This is the parent of divided node.
	 * @param key This is the middle key of divided node.
	 */
	private void insertRoot(BalancedTreeNode insertedRoot,BalancedTreeNode.Key key){
		insertedRoot.elements.add(key);
		insertedRoot.setNumberOfElements(insertedRoot.getNumberOfElements()+1);
		Collections.sort(insertedRoot.elements, ComparatorForKeys.BY_WORD);
		
		
		if(insertedRoot.getNumberOfElements()>=(MAX+1)){
			splitNode(insertedRoot, insertedRoot.elements.get(MAX/2));
		}
	}
	
	/**
	 * If in any insertion expression , after inserting , the number of keys in one node is more than or
	 * equal to MAX+1 , then this method will be called to split the node and adjust the new places of 
	 * child nodes.
	 * @param splittedNode This is the node that we wanted to split.
	 * @param middleElement This is the middle element of the node that we wanted to split.
	 */
	private void splitNode(BalancedTreeNode splittedNode,BalancedTreeNode.Key middleElement){
		if(splittedNode.parent==null){//If the node that we wanted to split is root , then program will execute this if block.
			BalancedTree root=new BalancedTree("root");//The new root will be created.
			insertRoot(BalancedTree.root, middleElement);
			BalancedTreeNode sibling=new BalancedTreeNode();
			sibling.isLeaf=splittedNode.isLeaf;
			BalancedTree.root.isLeaf=false;
			splittedNode.parent=BalancedTree.root;
			sibling.parent=BalancedTree.root;
			
			sibling.setNumberOfElements(MAX/2);
			splittedNode.setNumberOfElements(MAX/2);
			
			for(int i=0,j=MAX/2+1;i<MAX/2;i++,j++){
				sibling.elements.add(splittedNode.elements.get(i));
				Collections.swap(splittedNode.elements, i, j);
			}
			
			for(int i=MAX;i>=MAX/2;i--){
				splittedNode.elements.remove(i);
			}

			
			BalancedTree.root.children.add(sibling);
			BalancedTree.root.children.add(splittedNode);	
			
			
			ArrayList<BalancedTree.BalancedTreeNode> a=new ArrayList<BalancedTree.BalancedTreeNode>();
			a.addAll(splittedNode.parent.children);
			Collections.sort(a, ComparatorForKeys.BY_ELEMENETWORD);
			splittedNode.parent.children.clear();
			for(int i=0;i<a.size();i++){
				if(a.get(i)!=null){
					decideThePLaceOfChildNode(BalancedTree.root,a.get(i));
				}
			}
			
		}
		
		else{//If the node that we wanted to split is not root, then program will execute this else block.
			insertRoot(splittedNode.parent, middleElement);
			BalancedTreeNode sibling=new BalancedTreeNode();
			sibling.isLeaf=splittedNode.isLeaf;
			splittedNode.parent=splittedNode.parent;
			sibling.parent=splittedNode.parent;
			
			sibling.setNumberOfElements(MAX/2);
			splittedNode.setNumberOfElements(MAX/2);
			
			
			for(int i=0,j=MAX/2+1;i<MAX/2;i++,j++){
				sibling.elements.add(splittedNode.elements.get(i));
				Collections.swap(splittedNode.elements, i, j);
			}
			
			for(int i=MAX;i>=MAX/2;i--){
				splittedNode.elements.remove(i);
			}
			
			
			if(splittedNode.isLeaf()){
				ArrayList<BalancedTree.BalancedTreeNode> a=new ArrayList<BalancedTree.BalancedTreeNode>();
				a.addAll(splittedNode.parent.children);
				a.add(sibling);
				Collections.sort(a, ComparatorForKeys.BY_ELEMENETWORD);
				splittedNode.parent.children.clear();
				for(int i=0;i<a.size();i++){
					if(a.get(i)!=null){
						decideThePLaceOfChildNode(BalancedTree.root,a.get(i));
					}
				}
				
			}
			else {
			
				ArrayList<BalancedTree.BalancedTreeNode> a=new ArrayList<BalancedTree.BalancedTreeNode>();
				a.addAll(splittedNode.parent.children);
				a.add(sibling);
				Collections.sort(a, ComparatorForKeys.BY_ELEMENETWORD);
				splittedNode.parent.children.clear();
				for(int i=0;i<a.size();i++){
					if(a.get(i)!=null){
						decideThePLaceOfChildNode(BalancedTree.root,a.get(i));
					}
				}
			}
			
		}
		
	}
	
	/**
	 * Here in this method the program decide to the places of the each child nodes of divided node.
	 * @param rootNode This is the root that we wanted to start from searching consistent place.
	 * @param splittedNodeChild This is the child node that we wanted to find the place of it.
	 */
	private void decideThePLaceOfChildNode(BalancedTreeNode rootNode ,BalancedTreeNode splittedNodeChild){
		int counter=0;
		if(rootNode.isLeaf==false){
			for(int i=0;i<rootNode.getNumberOfElements();i++){
				if(splittedNodeChild.elements.get(0).getWord().compareTo(rootNode.elements.get(i).getWord())<0){
					if(rootNode.children.isEmpty()){
						rootNode.children.add(splittedNodeChild);
						splittedNodeChild.parent=rootNode;
						Collections.sort(rootNode.children, ComparatorForKeys.BY_ELEMENETWORD);
						counter++;
						break;
					}
					else{
						if(rootNode.children.size()<(i+1)){
							rootNode.children.add(splittedNodeChild);
							splittedNodeChild.parent=rootNode;
							Collections.sort(rootNode.children, ComparatorForKeys.BY_ELEMENETWORD);
							counter++;
							break;
						}
						else{
							decideThePLaceOfChildNode(rootNode.children.get(i), splittedNodeChild);
							counter++;
							break;
						}
					}
					
				}
			}
			if(counter==0){
				if(splittedNodeChild.elements.get(0).getWord().compareTo(rootNode.elements.get(rootNode.getNumberOfElements()-1).getWord())>0){
					if(rootNode.children.isEmpty()){
						rootNode.children.add(splittedNodeChild);
						splittedNodeChild.parent=rootNode;
						Collections.sort(rootNode.children, ComparatorForKeys.BY_ELEMENETWORD);
					}
					else{
						if(rootNode.children.size()<rootNode.getNumberOfElements()+1){
							rootNode.children.add(splittedNodeChild);
							splittedNodeChild.parent=rootNode;
							Collections.sort(rootNode.children, ComparatorForKeys.BY_ELEMENETWORD);
						}
						else{
							decideThePLaceOfChildNode(rootNode.children.get(rootNode.getNumberOfElements()), splittedNodeChild);
						}
					}
				}
			}
			
		}
		
	}
	
	/**
	 * After adding each word , the program have to also add the file name row and column number of this word.
	 * Here in this method this expression was implemented.
	 * @param addingFileKey The place of word.
	 * @param fileName This is the name of file of that word.
	 * @param row This is the row number in that file.
	 * @param column This is the column number of that word in that row.
	 */

	private void addFile(BalancedTree.BalancedTreeNode.Key addingFileKey,String fileName,int row,int column){
		BalancedTree.BalancedTreeNode.Key.File addingFile=new BalancedTree.BalancedTreeNode.Key.File(fileName);
		BalancedTree.BalancedTreeNode.Key.File.FilePlace placeOfWord=new BalancedTree.BalancedTreeNode.Key.File.FilePlace(row, column);
		if(addingFileKey.files.size()==0){
			addingFileKey.files.add(addingFile);
			addingFileKey.files.get(0).filePlaces.add(placeOfWord);
		}
		else{
			int counter=0;
			for(int i=0;i<addingFileKey.files.size();i++){
				if(addingFileKey.files.get(i).getFileName().equalsIgnoreCase(fileName)){
					counter++;
					addingFileKey.files.get(i).filePlaces.add(placeOfWord);
					break;
				}
			}
			if(counter==0){
				addingFileKey.files.add(addingFile);
				int index=addingFileKey.files.size();
				index=index-1;
				addingFileKey.files.get(index).filePlaces.add(placeOfWord);
			}
		}
		
	}
	
	/**
	 * Here in this method program search the consistent place for inserting a word to the BTree.
	 * @param insertedRoot This is the root that we wanted to start from.
	 * @param word This is the word that we wanted to insert.
	 * @return This method will return the consistent place for this word.
	 */
	
	private BalancedTreeNode searchForConsistentLeaf(BalancedTreeNode insertedRoot, String word){
		int counter=0;
		if(insertedRoot.isLeaf()==false){
			for(int i=0;i<insertedRoot.numberOfElements;i++){
				if(word.compareToIgnoreCase(insertedRoot.elements.get(i).getWord())<0){
					counter++;
					insertedRoot=searchForConsistentLeaf(insertedRoot.children.get(i), word);
					break;
				}
			}
			if(counter==0){
				if(word.compareToIgnoreCase(insertedRoot.elements.get(insertedRoot.numberOfElements-1).getWord())>0){
					insertedRoot=searchForConsistentLeaf(insertedRoot.children.get(insertedRoot.numberOfElements), word);
				}
			}
			
		}
		return insertedRoot;
	}
	
	/**
	 * This method controls the existence of any word that we wanted to insert the BTree .
	 * If the word has already inserted, then this method just call the addFile method to add the file info to the existence key.
	 * Else the method will just return 0.
	 * @param searchingNode
	 * @param word
	 * @param fileName
	 * @param row
	 * @param column
	 * @return If the word was found this method will return 1,else 0.
	 */
	
	private int searchExistanceOfNode(BalancedTreeNode searchingNode,String word,String fileName,int row,int column){
		int counter=0;
		if(searchingNode.isLeaf()){
			for(int i=0;i<searchingNode.elements.size();i++){
				if(searchingNode.elements.get(i)!=null){
					if(searchingNode.elements.get(i).getWord().equalsIgnoreCase(word)){
						addFile(searchingNode.elements.get(i), fileName, row, column);
						counter++;
						break;
					}
				}
			}
		}
		else{
			for(int i=0;i<searchingNode.elements.size();i++){
				if(searchingNode.elements.get(i)!=null){
					if(searchingNode.elements.get(i).getWord().equalsIgnoreCase(word)){
						addFile(searchingNode.elements.get(i), fileName, row, column);
						counter++;
						break;
					}
				}
			}
			if(counter==0){
				for(int i=0;i<searchingNode.children.size();i++){
					if(searchingNode.children.get(i)!=null){
						counter=searchExistanceOfNode(searchingNode.children.get(i), word, fileName, row, column);
						if(counter==1){
							break;
						}
						
					}
				}
			}
			
			
		}

		if(counter!=1){
			return 0;
		}
		else {
			return 1;
		}
	}



}
