/**This class is a node for an AVL tree
*/
public class AVLNode<E> {
	private E element;        // Value for this node
	private AVLNode<E> left;     // reference to left child
	private AVLNode<E> right;	//reference to right child
	private int height;			//this is the height balance factor
	
	  // Constructor
	public AVLNode(E it){
	  	element = it;
	  	left = null;
		right = null;
		height = 0;	  
	}
	/** @return true of the node is a leaf
	*/
	public boolean isLeaf(){
		return( left == null && right == null);
	}
	 
	/**@return the left child
	*/
	 public AVLNode<E> getLeft() { 
	 	return left; 
	 }  
	 /**@param the node to be set as left child
	 */
	 public void setLeft(AVLNode<E> l){
	 	 left = l; 
	 }     
	 /**@return the right child
	*/
	 public AVLNode<E> getRight() { 
	 	return right; 
	 }  
	 /**@param the node to be set as right child
	 */
	 public void setRight(AVLNode<E> r){
	 	 right = r; 
	 }     
	 /**@return the element in the node
	 */
	 public E getElement() { 
	 	return element; 
	 } 
	 /**@param the element to be placed in node
	 */
	 public E setElement(E it) {
	 	return element = it; 
	 }
	 /**@return String representation of node's element
	 */
	public String toString(){
		return (element.toString());
	}
	/**@return true if node has left child
	*/
	public boolean hasLeft(){
		return left != null;
	}
	/**@return true if node has right child
	*/
	public boolean hasRight(){
		return right !=null;
	}
	/**@return height of this node
	*/
	public int getHeight(){
		return height;
	}
	/**@param the height to be set for this node
	*/
	public void setHeight(int h){
		height = h;
	}
	
	
}
