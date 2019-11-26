/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the BinarySearchTree Class
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root;
    private int size;
    public int searchCounter;
    public int compareNum = 0;

    //Constructor
    public BinarySearchTree() {
        root = null;
        size = 0;
    }
    public int getCompareNum(){
        return compareNum;
    }
    //Another Constructor
    public BinarySearchTree(BSTNode<E> node) {
        root = node;
        size = 1;
    }

    //returns true if tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * returns the number of nodes in the tree
     * @return number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * @return root of tree
     */
    public BSTNode<E> getRoot() {
        return root;
    }

    /**
     * searches for a node that contains it.
     * if it finds it, it returns that node
     * else it returns null
     * @param it - the element to look for
     * @return the node that contains it
     */
    public BSTNode<E> search(E it) {
        BSTNode<E> node = root;
        while (node != null) {
            System.out.println(node.getElement() + " HERE");
            int compareResult = it.compareTo(node.getElement());
            compareNum++;
            if (compareResult < 0) {
                node = node.getLeft();
            } else if (compareResult > 0) {
                node = node.getRight();
            } else {
                //this is it!
                return node;
            }
            //searchCounter++;
        }
        System.out.println(it + " is not in the tree lol");
        return null;     //not there homie SORRY
    }

    /**
     * determines is the tree contains the element
     * @return true if it is in the tree
     */
    public boolean contains(E it) {
        return (search(it) != null);
    }

    /**
     * Add the element to the correct location
     * all elements to the left are less than the parent
     * all elements to the rights are greater than the parent
     * Do not allow duplicates
     *
     * @param it the element to insert
     */
    public void insert(E it) {
        BSTNode<E> newNode = new BSTNode<E>(it);
        if (root == null) {
            root = newNode;
            return;
        }
        BSTNode<E> parent = null;
        BSTNode<E> node = root;
        while (node != null) {
            parent = node;
            int compareResult = it.compareTo(node.getElement());
            if (compareResult < 0) {
                node = node.getLeft();
            } else if (compareResult > 0) {
                node = node.getRight();
            } else {
                //duplicate
                return;
            }
        }
        int res = it.compareTo(parent.getElement());
        if (res < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        size++;
    }

    /**
     * Removes the node that contains it.
     * If the tree does not contain it, it prints that to
     * the user and does nothing else.
     * Otherwise it removes the node and maintains the
     * BST properties
     * if removing a node with two children, replace it
     * with its inorder predecessor.
     * @param it the element of the node you want to remove.
     */
    public void remove(E it) {
        BSTNode<E> parent = null;
        BSTNode<E> child = null;
        BSTNode<E> node = root;
        //find the node that contains it
        while (node != null && node.getElement() != it) {
            parent = node;
            int compareResult = it.compareTo(node.getElement());
            if (compareResult < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        if (node == null) {
            System.out.println("Failed to find: " + it + " for removal.");
            return;
        }
        //you are at the node that has the value you want to delete
        //if it has children, transform the node into its replacement
        //then delete that node and update
        //if(node.getLeft() == null & node.getRight() == null){ //leaf node
        if (node.isLeaf()) {
            if (parent == null) {//you were a single node tree
                root = null;
            } else if (it.compareTo(parent.getElement()) < 0) { //traversed left
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
        //not a leaf but has a right child
        else if (node.getLeft() == null) {//has a right child
            child = node.getRight(); //get the child
            swapElements(node, child);//swap the elements
            node.setLeft(child.getLeft()); //attach the children
            node.setRight(child.getRight());
        }
        //not a leaf but has a left child
        else if (node.getRight() == null) {//has a left child
            child = node.getLeft(); //get the child
            swapElements(node, child);//swap the elements
            node.setLeft(child.getLeft()); //attach the children
            node.setRight(child.getRight());
        }
        //it has two kids and you need to replace the node
        //with its inorder predecessor - or the right
        // most child in it left subtree
        else {
            child = node.getLeft();//go to the left subtree
            parent = null;
            while (child.getRight() != null) {//traverse to the right
                parent = child;
                child = parent.getRight();
            }
            if (parent == null) {//you do not go right because there was no right tree
                swapElements(node, child);
                node.setLeft(child.getLeft());//link the child's left tree
            } else {//you did go right
                swapElements(node, child);
                parent.setRight(child.getLeft());//link the child's left tree
            }
        }
        size--;
    }

    /**
     * Returns the height of the tree
     * if tree is empty, height is -1
     * if tree only has one node, height is 0
     * @return the integer height of the tree
     */
    public int getHeight() {
        int height = -1;
        QueueList<BSTNode> queue = new QueueList<BSTNode>();
        if (isEmpty()) {
            System.out.println("Tree is empty");
            return height;
        }
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            height++;
            while (count > 0) {
                BSTNode<E> node = queue.dequeue();
                if (node.hasLeft()) {
                    queue.enqueue(node.getLeft());
                }
                if (node.hasRight()) {
                    queue.enqueue(node.getRight());
                }
                count--;
            }
        }
        return height;
    }

    /**
     * Helper method
     * For removal you need to swap elements of nodes
     * @param node1 , node2 the nodes whose contents you are swapping
     */
    private void swapElements(BSTNode<E> node1, BSTNode<E> node2) {
        E element1 = node1.getElement();
        E element2 = node2.getElement();
        node1.setElement(element2);
        node2.setElement(element1);
    }

    /**
     * prints each level of the tree on its own line
     * use your Queue class
     */
    public void printLevelOrder() {
        QueueList<BSTNode> queue = new QueueList<BSTNode>();
        int levelNodes = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            levelNodes = queue.size();
            while (levelNodes > 0) {
                BSTNode<E> n = queue.dequeue();
                System.out.print(n.getElement() + " ");  //seperate the element on that level by a space
                if (n.hasLeft()) {
                    queue.enqueue(n.getLeft());
                }

                if (n.hasRight()) {
                    queue.enqueue(n.getRight());
                }
                levelNodes--;
            }
            System.out.println();
        }
    }

    /**
     * prints the tree in a depth-first fashion
     * use your Stack class
     */
    public void printByDepth() {
        StackList<BSTNode> stack = new StackList<BSTNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BSTNode<E> node = stack.pop();
            System.out.println(node.getElement());
            if (node.hasRight()) {
                stack.push(node.getRight());
            }
            if (node.hasLeft()) {
                stack.push(node.getLeft());
            }
        }
    }

    /**
     * prints the tree in an inorder fashion.
     * uses a stack to push left children onto the stack
     */
    public void printInOrder() {
        StackList<BSTNode> stack = new StackList<BSTNode>();
        BSTNode<E> node = root;
        pushLeftNodesToStack(stack, node);
        while (!stack.isEmpty()) {
            BSTNode<E> n = stack.pop();
            System.out.println(n.getElement());
            n = n.getRight();
            pushLeftNodesToStack(stack, n);
        }
    }

    /**
     * This method pushes the left side nodes onto the stack
     * @param s is the stack list
     * @param b is the BST
     */
    private void pushLeftNodesToStack(StackList<BSTNode> s, BSTNode<E> b) {
        while (b != null) {
            s.push(b);
            b = b.getLeft();
        }
    }
}
