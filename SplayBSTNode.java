/**
 * Author: Blake Furlano
 cool
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the SplayNode Class
 * In collaboration with: Mason Waters and Robert Hable
 */
public class SplayBSTNode<E> {
    private E element;        // Value for this node
    private SplayBSTNode<E> left;     // reference to left child
    private SplayBSTNode<E> right;    //reference to right child
    private int height;            //this is the height balance factor
    private SplayBSTNode<E> parent;

    // Constructor
    public SplayBSTNode(E it) {
        element = it;
        left = null;
        right = null;
        parent = null;
        height = 0;
    }

    /**
     * @return true of the node is a leaf
     */
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    /**
     * @return the left child
     */
    public SplayBSTNode<E> getParent() {
        return parent;
    }

    public void setParent(SplayBSTNode<E> l) {
        parent = l;
    }

    /**
     * @return the left child
     */
    public SplayBSTNode<E> getLeft() {
        return left;
    }

    /**
     * @param l is the node to be set as the left
     */
    public void setLeft(SplayBSTNode<E> l) {
        left = l;
    }

    /**
     * @return the right child
     */
    public SplayBSTNode<E> getRight() {
        return right;
    }

    /**
     * @param r the node to be set as right child
     */
    public void setRight(SplayBSTNode<E> r) {
        right = r;
    }

    /**
     * @return the element in the node
     */
    public E getElement() {
        return element;
    }

    /**
     * @param it the element to be placed in node
     */
    public E setElement(E it) {
        return element = it;
    }

    /**
     * @return String representation of node's element
     */
    public String toString() {
        return (element.toString());
    }

    /**
     * @return true if node has left child
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return true if node has right child
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * @return height of this node
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param h the height to be set for this node
     */
    public void setHeight(int h) {
        height = h;
    }
}
