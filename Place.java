import java.util.ArrayList;

/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the Place Class
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class Place implements Comparable<Place> {
    private String cityStateName;
    public ArrayList<Integer> zipCodes = new ArrayList<Integer>();

    /**
     * This is the constructor for the class
     * @param cityStateName is the city and state name called from the driver
     * @param zipCode is the zip code called from the class
     */
    public Place(String cityStateName, int zipCode){
        this.cityStateName = cityStateName;
        zipCodes.add(zipCode);

    }

    /**
     * This method compares city and state names
     * @param p is the object called
     * @return returns the int associated with the compareTo
     */
    public int compareTo(Place p){
        String pt = p.getTownAndState();
        //System.out.println(pt + "      " + cityStateName);
        return pt.compareTo(this.cityStateName);
    }

    /**
     * This method gets the town and state name
     * @return returns the name
     */
    public String getTownAndState(){
        return cityStateName;
    }

    /**
     * This method makes an array list for the zip codes
     * @return returns the zip codes in an array list
     */
    public ArrayList<Integer> getZipCodes(){
        return zipCodes;
    }

    /**
     * This method adds the zip codes to the array list
     * @param zipCode the zip code called
     */
    public void addZip(int zipCode){
        this.zipCodes.add(zipCode);
    }

    /**
     * This method returns the city/state name and zipC codes arrayList in string form
     * @return is the string returned
     */

    public String toString(){
        return "City and State: " + cityStateName + " with zip codes: " + zipCodes;
    }



    /**
     * This method compares one cities name to another cities name
     * @param cityStateName1 the first city/state name called from driver
     * @param cityStateName2 the second city/stateN name called from driver
     * @return returns true/false depending on whether or not the cities names
     * are equal to each other or not
     */
    public boolean compareTo(String cityStateName1, String cityStateName2){
        boolean result;
        if(cityStateName1.equals(cityStateName2)){
            result = true;
        }
        result = false;
        return result;
    }
}
