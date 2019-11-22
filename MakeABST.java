import java.util.*;

public class MakeABST{
	public static void main(String[] args){
		
		integerTree();
		stringTree();
		
	}
	public static void integerTree(){
		BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>();
		bt.insert(37);
		bt.insert(24);
		bt.insert(32);
		bt.insert(25);
		bt.insert(7);
		bt.insert(42);
		bt.insert(120);
		bt.insert(40);
		System.out.println("Height: " + bt.getHeight());
		System.out.println("Printing by level:");
		bt.printLevelOrder();
		System.out.println();
		System.out.println("Printing by depth first:");
		bt. printByDepth();
		System.out.println();
		System.out.println("Printing by inorder: ");
		bt.printInOrder();
		System.out.println();
		BSTNode<Integer> node = bt.search(24);
		if(node.isLeaf()){
			System.out.println("The node with " + node.getElement()+ "has no children.");
		}
		else{
			System.out.println("The node with " + node.getElement()+ " has at least one child.");
			if(node.hasLeft()){
				System.out.println("It's left child has: "+ node.getLeft().getElement());
			}
			if(node.hasRight()){
				System.out.println("It's right child has: "+ node.getRight().getElement());
			}
		}
		bt.remove(24);
		bt.contains(24);
		node = bt.search(7);
		if(node.isLeaf()){
			System.out.println("The node with " + node.getElement()+ " has no children.");
		}
		else{
			System.out.println("The node with " + node.getElement()+ " has at least one child.");
			if(node.hasLeft()){
				System.out.println("Its left child has: "+ node.getLeft().getElement());
			}
			if(node.hasRight()){
				System.out.println("Its right child has: "+ node.getRight().getElement());
			}
		}
		System.out.println();
		System.out.println("Printing by level:");
		bt.printLevelOrder();
		System.out.println();
		System.out.println("Printing by depth first:");
		bt. printByDepth();
		System.out.println();
		System.out.println("Printing by inorder: ");
		bt.printInOrder();
		System.out.println();
		
		
		System.out.println("Same numbers, but a different order:");
		BinarySearchTree<Integer> bt2 = new BinarySearchTree<Integer>();
		bt2.insert(120);
		bt2.insert(42);
		bt2.insert(7);
		bt2.insert(32);
		bt2.insert(37);
		bt2.insert(24);
		bt2.insert(25);
		bt2.insert(40);
		System.out.println("Height: " + bt2.getHeight());
		System.out.println();
		System.out.println("Printing by level:");
		bt2.printLevelOrder();
		System.out.println();
		System.out.println("Printing by depth first:");
		bt2. printByDepth();
		System.out.println();
		System.out.println("Printing by inorder: ");
		bt2.printInOrder();
		System.out.println();
		node = bt2.search(32);
		if(node.isLeaf()){
			System.out.println("The node with " + node.getElement()+ "has no children.");
		}
		else{
			System.out.println("The node with " + node.getElement()+ " has at least one child.");
			if(node.hasLeft()){
				System.out.println("Its left child has: "+ node.getLeft().getElement());
			}
			if(node.hasRight()){
				System.out.println("Its right child has: "+ node.getRight().getElement());
			}
		}
		bt2.remove(32);
		node = bt2.search(25);
		if(node.isLeaf()){
			System.out.println("The node with " + node.getElement()+ " has no children.");
		}
		else{
			System.out.println("The node with " + node.getElement()+ " has at least one child.");
			if(node.hasLeft()){
				System.out.println("Its left child has: "+ node.getLeft().getElement());
			}
			if(node.hasRight()){
				System.out.println("Its right child has: "+ node.getRight().getElement());
			}
		}
		System.out.println();
		System.out.println("Height: " + bt2.getHeight());
		System.out.println();
		System.out.println("Printing by level:");
		bt2.printLevelOrder();
		System.out.println();
		System.out.println("Printing by depth first:");
		bt2. printByDepth();
		System.out.println();
		System.out.println("Printing by inorder: ");
		bt2.printInOrder();
		System.out.println();
		
	}
	public static void stringTree(){
		BinarySearchTree<String> sbst = new BinarySearchTree<String>();
		System.out.println("Strings too");
		sbst.insert("mango");
		sbst.insert("banana");
		sbst.insert("pear");
		sbst.insert("kiwi");
		sbst.insert("apple");
		sbst.insert("orange");
		sbst.insert("raspberry");
		System.out.println("Height: " + sbst.getHeight());
		System.out.println();
		System.out.println("Printing by level:");
		sbst.printLevelOrder();
		System.out.println();
		System.out.println("Printing by depth first:");
		sbst. printByDepth();
		System.out.println();
		System.out.println("Printing by inorder: ");
		sbst.printInOrder();
		
	}	
}
