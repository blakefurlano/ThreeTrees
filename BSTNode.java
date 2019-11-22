/** Node for a binary search tree*/
public class BSTNode<E> {
	private E element;        // Value for this node
	private BSTNode<E> left;     // reference to left child
	private BSTNode<E> right;	//reference to right child
	
	// Constructors
	public BSTNode(E it){
	  	element = it;
	  	left = null;
		right = null;
		  
	}
	public BSTNode(E it, BSTNode<E> l, BSTNode<E> r){ 
		element = it;  
		left = l;
		right = r;
	 }
	    
	  
	/** determines if the node has kids
	* @return if the node is a leaf
	*/
	public boolean isLeaf(){
		return( left == null && right == null);
	}
	 
	/** returns left child
	* @return left child
	*/
	 public BSTNode<E> getLeft() { 
	 	return left; 
	 }  
	 /**sets the left child
	 * @param l the node that should be set as the left child
	 */
	 public BSTNode<E> setLeft(BSTNode<E> l){
	 	return left = l; 
	 }   
	 
	/** returns right child
	* @return right child
	*/
	 public BSTNode<E> getRight() { 
	 	return right; 
	 }  
	  /**sets the right child
	 * @param r the node that should be set as the left child
	 */
	 public BSTNode<E> setRight(BSTNode<E> r){
	 	return right = r; 
	 }    

	/** return the element in the node
	*@return the element in the node
	*/
	 public E getElement() { 
	 	return element; 
	 }  
	/** sets the element in the node
	* @param it the item to be placed in the node
	*/
	 public E setElement(E it) {
	 	return element = it; 
	 }
	 
	 /**
	 * @return the string representation of what is in the node
	 */
	 public String toString(){
		return element.toString();
	}
	/**
	* @return if the node has a left child
	*/
	public boolean hasLeft(){
		return left != null;
	}
	/**
	* @return if the node has a right child
	*/
	public boolean hasRight(){
		return right !=null;
	}
	
	
}
