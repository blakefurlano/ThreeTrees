
/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the SplayTree Class
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class SplayTree<E extends Comparable<E>> {
    private SplayBSTNode<E> root;
    private int size;

    //Constructor
    public SplayTree() {
        root = null;
        size = 0;
    }

    //Another Constructor
    public SplayTree(SplayBSTNode<E> node) {
        root = node;
        size = 1;
    }

    //returns true if tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(E it) {
        return (search(it) != null);
    }




    /**
     * This method searches the tree
     * @param it is the element called
     * @return returns the searched for element
     */
    public SplayBSTNode<E> search(E it) {
        SplayBSTNode<E> node = root;
        while (node != null) {
            int compareResult = it.compareTo(node.getElement());
            if (compareResult < 0) {
                node = node.getLeft();
            } else if (compareResult > 0) {
                node = node.getRight();
            } else {
                //this is it!
                return node;
            }
        }
        System.out.println(it + " is not in the tree");
        return null;     //not there homie SORRY
    }



    /**
     * This method inserts into the splay tree
     * @param it is the element inserted
     */

    public void insert(E it) {
        SplayBSTNode<E> newNode = new SplayBSTNode<E>(it);
        if (root == null) {
            root = newNode;
            return;
        }
        //System.out.println(root.getElement());
        SplayBSTNode<E> parent = null;
        SplayBSTNode<E> node = this.root;
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
            newNode.setParent(parent);
        } else {
            parent.setRight(newNode);
            newNode.setParent(parent);
        }
        size++;
        //System.out.println("right here");
        splay(newNode);
    }



    /**
     * This method calls a helper to delete an element in the tree
     * @param it is the elemtn called ot be deleted
     */
    public void delete(E it) {
            SplayBSTNode<E> parent = null;
            SplayBSTNode<E> child = null;
            SplayBSTNode<E> node = root;
            //find the node that contains it
            if(node.getElement() == it){
                join(node.getLeft(), node.getRight());
            }
            else {
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
                if (node != root) {
                    splay(node);
                }
                SplayBSTNode<E> rightTreeroot = node.getRight();
                SplayBSTNode<E> leftTreeRoot = node.getLeft();
                join(leftTreeRoot, rightTreeroot);
            }/*

            System.out.println(node + "here");

            if(leftTreeRoot.hasRight()) {

                SplayBSTNode<E> checkNode = leftTreeRoot.getRight();
                System.out.println("here");
                if(checkNode == null){
                    leftTreeRoot.setParent(null);

                    leftTreeRoot.setRight(rightTreeroot);


                }
                while (checkNode != null) {

                    parent = checkNode;
                    if (parent.getRight() == null) {
                        System.out.println("here");
                        splay(parent);
                        parent.setRight(rightTreeroot);
                        checkNode = null;

                    } else {
                        checkNode = checkNode.getRight();
                    }
                }
            }
            else{
                splay(rightTreeroot);
            }
*/

    }




    /**
     * This method joins two trees together
     * @param s this is one tree called
     * @param t this is the other tree called
     * @return returns the new tree
     */
    private SplayBSTNode<E> join(SplayBSTNode<E> s, SplayBSTNode<E> t) {
        if (s == null) {
            return t;
        }
        if (t == null) {
            return s;
        }
        SplayBSTNode<E> x = maximum(s);
        splay(x);
        x.setRight(t);
        t.setParent(x);
        return x;
    }

    /**
     * This method returns the max value in the tree
     * @param node this is the tree called
     * @return returns the max element
     */
    public SplayBSTNode<E> maximum(SplayBSTNode<E> node) {
        SplayBSTNode<E> parent = null;
        while (node.hasRight()) {
            parent = node;
            if(parent.getRight() == null){
                return  parent;
            }
            else{
                node = parent.getRight();
            }
            //System.out.println("Here max");
            //node.setRight(node);

        }
        return node;
    }

    /**
     * This method rotates the tree when something is inserted
     * @param x is the tree called
     */
    private void splay(SplayBSTNode<E> x) {
       // System.out.println("in splay " + x.getElement());
        while (x.getParent() != null) {
            SplayBSTNode<E> Parent = x.getParent();
            SplayBSTNode<E> GrandParent = Parent.getParent();
           // System.out.println("HERE");
            //zig
            if (GrandParent == null) {

                if (x == Parent.getLeft())
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            } //go to next slide
            else {
                if (x == Parent.getLeft()) {
                    //left-left child zig zig
                    if (Parent == GrandParent.getLeft()) {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    } else { //zig-zag
                        makeLeftChildParent(x, Parent);
                        makeRightChildParent(x, x.getParent());
                    }
                } else {//zig-zag
                    if (Parent == GrandParent.getLeft()) {
                        makeRightChildParent(x, Parent);
                        makeLeftChildParent(x, x.getParent());
                    } else {//zig-zig
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }//ends while
        root = x;
    }

    /**
     * This method makes a left child into a parent, or rotate left
     * @param c is the first tree called
     * @param p is the second tree called
     */
    public void makeLeftChildParent(SplayBSTNode<E> c, SplayBSTNode<E> p) {
        if (p.getParent() != null) {
            if (p == p.getParent().getLeft()) {
                p.getParent().setLeft(c);
            } else {
                //System.out.println("Make left child parent 2");
                p.getParent().setRight(c);
            }
        }
        if (c.getRight() != null) {
            c.getRight().setParent(p);
            //System.out.println("Make left child parent");
        }
        c.setParent(p.getParent());
        p.setParent(c);
        p.setLeft(c.getRight());
        c.setRight(p);
    }

    /**
     * This method makes a right child a parent or rotate right
     * @param c is the first tree called
     * @param p is the second tree called
     */
    public void makeRightChildParent(SplayBSTNode<E> c, SplayBSTNode<E> p) {
        if (p.getParent() != null) {
            if (p == p.getParent().getRight()) {
                p.getParent().setRight(c);
            } else {
                //System.out.println("Make Right child parent 2");
                p.getParent().setLeft(c);
            }
        }
        if (c.getLeft() != null) {
            c.getLeft().setParent(p);
            //System.out.println("Make right child parent 1");
        }
        c.setParent(p.getParent());
        p.setParent(c);
        p.setRight(c.getLeft());
        c.setLeft(p);
    }

    /**
     * This method gets the height of the tree
     */
    public int getHeight() {
        int height = -1;
        QueueList<SplayBSTNode> queue = new QueueList<SplayBSTNode>();
        if (isEmpty()) {
            System.out.println("Tree is empty");
            return height;
        }
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            height++;
            while (count > 0) {
                SplayBSTNode<E> node = queue.dequeue();
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
     * This method prints the tree by the elements at each level of the tree
     */
    public void printLevelOrder() {
        QueueList<SplayBSTNode> queue = new QueueList<SplayBSTNode>();
        //int levelNodes = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int levelNodes = queue.size();
            while (levelNodes > 0) {
                SplayBSTNode<E> n = queue.dequeue();
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
     * This method prints by depth, meaning it prints the tree from top to bottom
     */
    public void printByDepth() {
        StackList<SplayBSTNode> stack = new StackList<SplayBSTNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            SplayBSTNode<E> node = stack.pop();
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
     * This method prints the tree in order the way it is
     */
    public void printInOrder() {
        StackList<SplayBSTNode> stack = new StackList<SplayBSTNode>();
        SplayBSTNode<E> node = root;
        pushLeftNodesToStack(stack, node);
        while (!stack.isEmpty()) {
            SplayBSTNode<E> n = stack.pop();
            System.out.println(n.getElement());
            n = n.getRight();
            pushLeftNodesToStack(stack, n);
        }
    }

    /**
     * This method pushes the nodes onto a stack to be printed easier
     * @param s is the stack of nodes to be printed
     * @param b is the tree called
     */
    private void pushLeftNodesToStack(StackList<SplayBSTNode> s, SplayBSTNode<E> b) {
        while (b != null) {
            s.push(b);
            b = b.getLeft();
        }
    }
}