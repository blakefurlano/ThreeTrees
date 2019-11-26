import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the Trees Class Driver
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class Trees {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        File file = new File("Zippie.txt");
        Scanner data = new Scanner(file);
        SplayTree<Place> s = new SplayTree<Place>();
        BinarySearchTree<Place> b = new BinarySearchTree<Place>();
        AVLTree<Place> a = new AVLTree<Place>();
        addToTrees(data, s,b,a);
        askUser(console, s, b, a);


    }



    public static void askUser(Scanner console,SplayTree<Place> s ,BinarySearchTree<Place> b, AVLTree<Place> a) throws FileNotFoundException {
        boolean game = true;
        while (game == true) {
            s.printLevelOrder();
            System.out.print("You want to search for the city: ");
            String response = console.nextLine();
            Place searchFor = new Place(response, 0000);
            SplayBSTNode<Place> found = s.search(searchFor);
            BSTNode<Place> found1 = b.search(searchFor);
            AVLNode<Place> found2 = a.search(searchFor);
            ArrayList<Integer> zipps = found.getElement().getZipCodes();
            System.out.print("The number of comparisons needed to find the entry in BST: ");
            System.out.println(b.getCompareNum());
            System.out.print("The number of comparisons needed to find the entry in AVL: ");
            System.out.println(a.getCompareNum());
            System.out.print("The number of comparisons needed to find the entry in Splay: ");
            System.out.println(s.getCompareNum());
            System.out.print("The zip codes that belong to " + response + " are: ");
            System.out.println(zipps.toString());
            System.out.print("Do you want me to search again? ");
            String response2 = console.nextLine();
            if (response2.equalsIgnoreCase("yes")) {
                System.out.println();
                game = true;
            } else {
                System.exit(0);
            }
        }
    }

    public static void addToTrees(Scanner data, SplayTree<Place> s,BinarySearchTree<Place> b,AVLTree<Place> a) throws FileNotFoundException {
        //FileOutputStream fout = new FileOutputStream("temporary.txt");            //temp delete later
        //PrintStream out = new PrintStream(fout);                                        //temp delete later
/*
        SplayTree<Place> s = new SplayTree<Place>();
        BinarySearchTree<Place> b = new BinarySearchTree<Place>();
        AVLTree<Place> a = new AVLTree<Place>();
*/
        String lastName;
        int lastCode;

        String firstLine = data.nextLine();
        int zipCode = data.nextInt();
        double xValue = data.nextDouble(); //ignore
        double yValue = data.nextDouble(); //ignore
        String cityState = data.nextLine().trim();
        //System.out.println("HERE");
        Place newPlace = new Place(cityState, zipCode);
        //System.out.println("Past");
        lastName = cityState.trim();

        while (data.hasNextLine()) {
            int zipCode1 = data.nextInt();
            double  xValue1= data.nextDouble(); //ignore
            double yValue1 = data.nextDouble(); //ignore
            String cityState1 = data.nextLine().trim();

            if (cityState1.equals(lastName)) {
                //System.out.println("HERE");
                //System.out.println(zipCode1);
                newPlace.addZip(zipCode1);//problem
                //System.out.println("HERE2");//add zip code to existing city
                if (!data.hasNextLine()) {                  // Last line of file check
                    s.insert(newPlace);                 //delete the printstream
                    b.insert(newPlace);
                    a.insert(newPlace);
                }
            } else {
                s.insert(newPlace);                 //delete the printstream
                b.insert(newPlace);
                a.insert(newPlace);

                Place newPlace1 = new Place(cityState1, zipCode1);
                if (data.hasNextLine()) {
                    System.out.println("HERE");// Last line of file check
                    s.insert(newPlace1);                 //delete the printstream
                    b.insert(newPlace1);
                    a.insert(newPlace1);
                }
                lastName = cityState.trim();
            }
        }
    }

}
