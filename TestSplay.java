/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the TestSPlay Class Driver
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class TestSplay {

    public static void main(String[] args) {
        stringTree();
    }

    public static void stringTree() {
        SplayTree<String> s = new SplayTree<String>();
        System.out.println("Test Splay Tree driver");
        s.insert("Peculiar");
        //s.printInOrder();
        s.insert("Crapo");
        //s.printInOrder();

        //System.out.println(s.getHeight());
        s.insert("Accident");

        s.insert("Eau Claire");
       // s.insert("Boring");
        //s.insert("Hell");
        //s.insert("Walla Walla");
       


        s.insert("Surprise");
        s.insert("Joseph");
        s.insert("Romance");
        //s.printLevelOrder();
        s.delete("Romance");
        s.printLevelOrder();
       /* s.insert("Mars");
        s.insert("Nuttsville");
        s.insert("Rough and Ready");
        s.insert("Dynamite");
        s.insert("Walla Walla");
        s.insert("Walla Walla");




        System.out.println("Height: " + s.getHeight());
        System.out.println();
        System.out.println("Printing by level:");
      //  s.printLevelOrder();
        System.out.println();
        System.out.println("Printing by depth first:");
        //s.printByDepth();
        System.out.println();
        System.out.println("Printing by inorder: ");
        //s.printInOrder();


        s.printLevelOrder();
        System.out.println(s.getHeight());
    */}
}
