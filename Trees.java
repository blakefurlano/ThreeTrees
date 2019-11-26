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


        String firstLine = data.nextLine();//gets ignored
        //grabs first city data
        /*
        int zipCode = data.nextInt();
        double xValue = data.nextDouble(); //ignore
        double yValue = data.nextDouble(); //ignore
        String cityState = data.nextLine().trim();


        Place newPlace = new Place(cityState, zipCode);//creates a place for this city data
        //inserts this place into the tree
        s.insert(newPlace);
        b.insert(newPlace);
        a.insert(newPlace);
        lastName = cityState.trim();

         */
        String prevName = " ";//used for comparisons between lines
        Place holdPlace = new Place("Hold", 5); //dummy temp place that gets overwritten
        while (data.hasNextLine()) {
            //get the data for a city
            int zipCode1 = data.nextInt();
            double  xValue1= data.nextDouble(); //ignore
            double yValue1 = data.nextDouble(); //ignore
            String cityState1 = data.nextLine().trim();


           // Place holdPlace = new Place("Hold", 5);
            //System.out.println(zipCode1 + " " + xValue1 + " " + yValue1 + " " +cityState1);
            if(prevName.equalsIgnoreCase(" ")){//the first town to insert
                Place newPlace2 = new Place(cityState1,zipCode1);//creates a place for first line
                s.insert(newPlace2);
                b.insert(newPlace2);
                a.insert(newPlace2);
                holdPlace = newPlace2;//sets the temp place = the first line
            }
            else {
                if (cityState1.equals(prevName)) {
                    //adds the zip to the place that is in hold because we know that the line we are currently
                    //on has the same city state as the previous line.
                    //do not need to do hold = newPlace2 since we never created a new place
                    System.out.println("Here");
                    holdPlace.addZip(zipCode1);//add zip code to existing city
                    //System.out.println("HERE2");
                /*
                if (!data.hasNextLine()) {                  // Last line of file check
                    s.insert(newPlace);
                    b.insert(newPlace);
                    a.insert(newPlace);
                }
                 */
                } else {//the line we are on does not match the previous lines city state
                    //here we should add the code to search a tree to make sure this city state
                    //is new. if it is not new, then we simple add the zip to that place.
                    Place newPlace2 = new Place(cityState1,zipCode1);//create a new place
                    s.insert(newPlace2);
                    b.insert(newPlace2);
                    a.insert(newPlace2);
                    holdPlace = newPlace2;//sets our temp
                /*
                Place newPlace1 = new Place(cityState1, zipCode1);
                if (data.hasNextLine()) {
                    System.out.println("HERE");// Last line of file check
                    s.insert(newPlace1);                 //delete the printstream
                    b.insert(newPlace1);
                    a.insert(newPlace1);
                }

                 */
                    //lastName = cityState1;
                }
            }
            prevName = cityState1;//readjusts the prevname to what we just looked at so the next line will compare to this line
           // System.out.println(prevName);

        }
    }

}
