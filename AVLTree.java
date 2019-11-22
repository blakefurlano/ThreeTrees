/**file: AVLTree.java
* Description: Implements AVL Tree - uses code from Mark Allen Weiss
* taken from book: "Data Structures and Algorithm Analysis in Java"   
*/
public class AVLTree<E extends Comparable <E>>{
	private AVLNode<E> root;//root of the tree
	private int size;//size of the tree
	private static final int ALLOWED_IMBALANCE= 1;
	
	//Constructor
	public AVLTree(){
		root = null;
		size = 0;
	}
	//Contructor
	public AVLTree(AVLNode<E> node){
		root = node;
		size = 1;
	}
	
	/**determines if the tree is empty
	*/
	public boolean isEmpty(){
		return (root == null);
	}

	/** searches for a node that contains it.
	 * if it finds it, it returns that node
	 * else it returns null
	 *@param it - the element to look for
	 *@return the node that contains it
	 */
	public AVLNode<E> search(E it){
		AVLNode<E> node = root;
		while(node != null){
			int res = it.compareTo(node.getElement());
			if(res < 0 ){
				node = node.getLeft();
			}
			else if(res > 0){
				node = node.getRight();
			}
			else{
				return node; //found it
			}
		}
		return null; //no in the tree
	}
	
	/**determines is the tree contains the element 
	* @return true if it is in the tree
	*/
	public boolean contains(E it){
		return (search(it) != null);
	}
			
	/**Add the element to the correct location
	 * all elements to the left are less than the parent
	 * all elements to the rights are greater than the parent
	 * Do not allow duplicates
	 * @param it the element to insert
	 */
	public void insert(E it){
		root = insert(it, root);
		size++;
	}
	/**
	 * INternal method to insert into a subtree
	 * @param it the element to insert
	 * @param t the node that roots the subtree
	 * @return the new root of the subtree.
	 */
	private AVLNode<E> insert(E it, AVLNode<E> t){
		if(t == null){
			return new AVLNode<E>(it); //the new root
		}
		int compareResult = it.compareTo(t.getElement());
		if( compareResult < 0){
			t.setLeft(insert(it, t.getLeft()));
		}
		else if( compareResult > 0){
			t.setRight(insert(it, t.getRight()));
		}
		else{ //duplicate

		}
		return balance(t); //where the rotations occur.
	}

	/**
	 * Internal method to balance the tree
	 * @param t node to balance
	 * @return a balanced node
	 */
	private AVLNode<E> balance(AVLNode<E> t){
		if(t == null){
			return t;
		}
		if(height(t.getLeft()) - height(t.getRight()) > ALLOWED_IMBALANCE){ //left heavy
			if(height(t.getLeft().getLeft()) >= height(t.getLeft().getRight())){ //left child is left heavy
				t = rotateWithLeftChild(t);
			}
			else{ //left child is right heavy
				t = doubleWithLeftChild(t);
			}
		}
		else if(height(t.getRight()) - height(t.getLeft()) > ALLOWED_IMBALANCE){ // right heavy
			if(height(t.getRight().getRight()) >= height(t.getRight().getLeft())){//right child is right heavy
				t = rotateWithRightChild(t);
			}
			else{
				t = doubleWithRightChild(t); //right child is left heavy
			}
		}
		else{
			//nothing
		}
		t.setHeight(max(height(t.getLeft()), height(t.getRight())) +1);
		return t;
	}

	
	
	/**Removes the node that contains it.  
	* If the tree does not contain it, it prints that to 
	* the user and does nothing else.
	* Otherwise it removes the node and maintains the 
	* BST properties
	* if removing a node with two children, replace it 
	* with its inorder predecessor.
	* @param it element of the node you want to remove.
	*/
	public void remove(E it){
		remove(it, root);
		size--;
	}

	/**
	 * internal method to remove from a subtree
	 * @param it is the element to remove
	 * @param t is the node that roots the subtree
	 * @return the new root of the subtree
	 */
	private AVLNode<E> remove(E it, AVLNode<E> t){
		if( t == null){
			System.out.println("The " + it + " cannot be removed it does not ecist");
			return null;
		}
		int compareResult = it.compareTo(t.getElement());
		if( compareResult < 0){
			t.setLeft(remove(it, t.getLeft()));
		}
		else if( compareResult > 0){
			t.setRight(remove(it, t.getRight()));
		}
		else if(t.getLeft() != null && t.getRight() != null){ //have two children
			//find the inorder predecesor
			t.setElement((findMax(t.getLeft())).getElement());
			t.setLeft(remove(t.getElement(), t.getLeft()));
		}
		else{
			t = (t.getLeft()!= null) ? t.getLeft() : t.getRight();
		}
		return balance(t);

	}
	/** internal method to find the largest item in a subtree.
	 * @param t node that roots the tree
	 * @return node containing the largest item.
	 */
	private AVLNode<E> findMax(AVLNode<E> t){
		if(t==null){
			return t;
		}
		while(t.getRight() != null){
			t = t.getRight();
		}
		return t;
	}

	/**
	 * @return the height of a node t or -1 if null
	 */
	public int height(AVLNode<E> t){
		return t == null ? -1 : t.getHeight();
	}
	/**
	 * @return the max in between the two sides.
	 */
	public static int max(int lhs, int rhs){
		return lhs > rhs ? lhs : rhs;
	}
	/**
	 * Rotate the binary tree with the left child.
	 * For AVL trees this is a single rotation
	 * update heights then return a new root
	 */
	public AVLNode<E> rotateWithLeftChild(AVLNode<E> k2){
		AVLNode<E> k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeight(max(height(k1.getLeft()), k2.getHeight()) + 1);
		return k1;
	}
	/**
	 * Rotate the binary tree with right child
	 * For AVL trees this is a single rotation
	 * update heights then return a new root
	 */
	public AVLNode<E> rotateWithRightChild(AVLNode<E> k1){
		AVLNode<E> k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight((max(height(k2.getRight()), k1.getHeight()))+1);
		return k2;
	}
	/**
	 * Double rotate first left child with its right child
	 * then node k3 with new left child
	 * Update Heights then return new root to subtree
	 */
	public AVLNode<E> doubleWithLeftChild(AVLNode<E> k3){
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}
	/**
	 * Double rotate first right child with its left child
	 * then node k3 with new right child
	 * Update Heights then return new root to subtree
	 */
	public AVLNode<E> doubleWithRightChild(AVLNode<E> k3){
		k3.setRight(rotateWithLeftChild(k3.getRight()));
		return rotateWithRightChild(k3);
	}


	/**Returns the height of the tree
       * if tree is empty, height is -1
	 * if tree only has one node, height is 0
	 * @return the integer height of the tree
	 *
	 */
	 public int getHeight(){
		QueueList<AVLNode> q = new QueueList<AVLNode>();
		if(isEmpty()){
			System.out.println("Tree has noting in it.");
			return -1;
		}
		q.enqueue(root);
		int height = 0;
		while(!q.isEmpty()){
			int nodeCount = q.size();
			if(nodeCount == 0){
				return height;
			}
			height++;
			while(nodeCount > 0){
				AVLNode<E> node = q.dequeue();
				if(node.getLeft() != null){
					q.enqueue((node.getLeft()));
				}
				if(node.getRight() != null){
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
		}
		return height;


	}


	/**prints each level of the tree on its own line 
* use your Queue class
*/
	public void printLevelOrder(){
		QueueList<AVLNode> q = new QueueList<AVLNode>();
		if(isEmpty()){
			System.out.println("Tree has noting in it.");
			return;
		}

		q.enqueue(root);
		while(!q.isEmpty()){
			int nodeCount = q.size();

			while(nodeCount > 0){
				AVLNode<E> node = q.dequeue();
				System.out.print(node.getElement() + "	");
				if(node.getLeft() != null){
					q.enqueue((node.getLeft()));
				}
				if(node.getRight() != null){
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
			System.out.println();
		}
	}

	
	


}
